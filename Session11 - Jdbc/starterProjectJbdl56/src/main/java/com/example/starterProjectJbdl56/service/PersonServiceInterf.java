package com.example.starterProjectJbdl56.service;

import java.util.List;

import com.example.starterProjectJbdl56.dtos.CreatePersonRequest;
import com.example.starterProjectJbdl56.model.Person;

public interface PersonServiceInterf {
	
	void createPersonStatic(CreatePersonRequest createPersonRequest);
	
	Person getPersonById(int id);
	
	List<Person> getAllPersons();
	
	Person updatePerson(CreatePersonRequest createPersonRequest,int personId);
	Person deletePerson(int id);

}
