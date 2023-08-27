package com.example.JpaHibernateDemo.service;

import java.util.List;

import com.example.JpaHibernateDemo.dtos.CreatePersonRequest;
import com.example.JpaHibernateDemo.model.Person;

public interface PersonServiceInterf {

	void createPersonStatic(CreatePersonRequest createPersonRequest);
	
	Person getPersonById(int id);
	
	List<Person> getAllPersons();
	
	Person updatePerson(CreatePersonRequest createPersonRequest,int personId);
	Person deletePerson(int id);
}
