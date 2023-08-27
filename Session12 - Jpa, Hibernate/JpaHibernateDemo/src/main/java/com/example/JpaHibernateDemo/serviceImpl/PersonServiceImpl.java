package com.example.JpaHibernateDemo.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JpaHibernateDemo.dtos.CreatePersonRequest;
import com.example.JpaHibernateDemo.exceptions.PersonNotFoundException;
import com.example.JpaHibernateDemo.model.Person;
import com.example.JpaHibernateDemo.repository.PersonRepositoryInterf;
import com.example.JpaHibernateDemo.service.PersonServiceInterf;

@Service
public class PersonServiceImpl implements PersonServiceInterf {
	
	@Autowired
	PersonRepositoryInterf personRepositoryInterf;

	@Override
	public void createPersonStatic(CreatePersonRequest createPersonRequest) {
		
		Person person = createPersonRequest.to();
		
		if(person.getAge() == null) {
			person.setAge(calculateAgeFromDOB(person.getDob()));
		}
		personRepositoryInterf.save(person);
	}
	
	private Integer calculateAgeFromDOB(String dob) {
		if(dob == null) {
			return null;
		}
		LocalDate dobDate = LocalDate.parse(dob);
		LocalDate currentDate = LocalDate.now();
		
		return Period.between(dobDate, currentDate).getYears();
	}

	@Override
	public Person getPersonById(int id) {
		return personRepositoryInterf.findById(id).orElseThrow(
				() -> new PersonNotFoundException("Person with id= "+id+" is not Present in db")
				);

	}

	@Override
	public List<Person> getAllPersons() {
		return personRepositoryInterf.findAll();	
	}

	@Override
	public Person updatePerson(CreatePersonRequest createPersonRequest, int personId) {
		Person person = personRepositoryInterf.findById(personId).get();
		if(person==null) {
			throw new PersonNotFoundException("Person with id= "+personId+" is not Present in db");
		}
		person = createPersonRequest.to();
		person.setId(personId);
		
		if(person.getAge() == null) {
			person.setAge(calculateAgeFromDOB(person.getDob()));
		}
		personRepositoryInterf.save(person);
		return person;
	}

	@Override
	public Person deletePerson(int personId) {
		Person person = personRepositoryInterf.findById(personId).get();
		if(person==null) {
			throw new PersonNotFoundException("Person with id= "+personId+" is not Present in db");
		}
		personRepositoryInterf.deleteById(personId);
		return person;
//		if(isDeleted)
//			return person;
//		throw new BadPersonRequestException("Person with id= "+personId+" not present in db");
		
	}

}

