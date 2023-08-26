package com.example.starterProjectJbdl56.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.starterProjectJbdl56.model.Person;
import com.example.starterProjectJbdl56.repository.PersonRepositoryInterf;

@Repository
public class PersonRepositoyImpl implements PersonRepositoryInterf {
	
	Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private static Logger logger = LoggerFactory.getLogger(PersonRepositoyImpl.class);
	
	public PersonRepositoyImpl(Connection connection) throws SQLException {
		this.connection = connection;
		createTable();
	}
	
	private void createTable() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("create table if not exists person (id int primary key auto_increment, first_name varchar(30),"
				+ "last_name varchar(30),age int,dob varchar(12))");
	}

	@Override
	public void createPersonStatic(Person person) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into person(id,first_name,last_name,age,dob)"
					+ "VALUES(1,'PETER','DAVID',20,'1996-02-04')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void createPerson(Person person){
		try {
			this.preparedStatement = connection.prepareStatement("insert into person(first_name,last_name,age,dob) "
					+ "VALUES(?,?,?,?)");
		
//		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(1, person.getFirstName());
		preparedStatement.setString(2, person.getLastName());
		preparedStatement.setInt(3, person.getAge());
		preparedStatement.setString(4, person.getDob());
		
		int result = preparedStatement.executeUpdate();
		logger.info("Insert Statement result: {}",result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person getPersonById(int id) {
		try {
		this.preparedStatement = connection.prepareStatement("select * from person where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
//				resultSet.getInt("id");
//				resultSet.getString("first_name");
//				resultSet.getString("last_name");
//				resultSet.getInt(4);
//				resultSet.getString(5);
				
				return getPersonFromResultSet(resultSet);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Person getPersonFromResultSet(ResultSet resultSet) throws SQLException {
		return Person.builder().id(resultSet.getInt("id"))
				.firstName(resultSet.getString("first_name"))
				.lastName(resultSet.getString("last_name")).age(resultSet.getInt(4))
				.dob(resultSet.getString(5))
				.build();	
		}

	@Override
	public List<Person> getAllPersons() {
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from person");
			List<Person> personList = new ArrayList<>();
			while(resultSet.next()) {
				Person person = getPersonFromResultSet(resultSet);
				personList.add(person);
			}
			return personList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// To-do
	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deletePerson(int id) {
		try {
			this.preparedStatement = connection.prepareStatement("delete from person where id=?");
			preparedStatement.setInt(1, id);
			
			int result = preparedStatement.executeUpdate();
			return result>=1 ? true: false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
