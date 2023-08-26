package com.example.starterProjectJbdl56.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starterProjectJbdl56.dtos.CreatePersonRequest;
import com.example.starterProjectJbdl56.exceptions.BadPersonRequestException;
import com.example.starterProjectJbdl56.model.Person;
import com.example.starterProjectJbdl56.repository.PersonRepositoryInterf;
import com.example.starterProjectJbdl56.service.PersonServiceInterf;

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
		personRepositoryInterf.createPerson(person);
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
		return personRepositoryInterf.getPersonById(id);
	}

	@Override
	public List<Person> getAllPersons() {
		return personRepositoryInterf.getAllPersons();	}

	@Override
	public Person updatePerson(CreatePersonRequest createPersonRequest, int personId) {
		Person person = personRepositoryInterf.getPersonById(personId);
		if(person==null) {
			throw new BadPersonRequestException("Person with id= "+personId+" is not Present in db");
		}
		person = createPersonRequest.to();
		person.setId(personId);
		
		if(person.getAge() == null) {
			person.setAge(calculateAgeFromDOB(person.getDob()));
		}
		personRepositoryInterf.updatePerson(person);
		return person;
	}

	@Override
	public Person deletePerson(int personId) {
		Person person = personRepositoryInterf.getPersonById(personId);
//		if(person==null) {
//			throw new BadPersonRequestException("Person with id= "+personId+" is not Present in db");
//		}
		boolean isDeleted = personRepositoryInterf.deletePerson(personId);
		if(isDeleted)
			return person;
		throw new BadPersonRequestException("Person with id= "+personId+" not present in db");
		
	}

}
