package com.example.starterProjectJbdl56.serviceImpl;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starterProjectJbdl56.dtos.CreatePersonRequest;
import com.example.starterProjectJbdl56.model.Person;
import com.example.starterProjectJbdl56.repository.PersonRepository;
import com.example.starterProjectJbdl56.service.PersonServiceInterf;

@Service
public class PersonServiceImpl implements PersonServiceInterf {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public void createPersonStatic(CreatePersonRequest createPersonRequest) {
		// TODO Auto-generated method stub
		
		Person person = createPersonRequest.to();
		
		if(person.getAge() == null) {
			person.setAge(calculateAgeFromDOB(person.getDob()));
		}
		
		personRepository.createPerson(person);
	}
	
	private Integer calculateAgeFromDOB(String dob) {
		if(dob == null) {
			return null;
		}
		LocalDate dobDate = LocalDate.parse(dob);
		LocalDate currentDate = LocalDate.now();
		
		return Period.between(dobDate, currentDate).getYears();
	}

}
