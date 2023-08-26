package com.example.starterProjectJbdl56.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.starterProjectJbdl56.model.Person;

@Repository
public interface PersonRepositoryInterf {
	void createPersonStatic(Person person);
	public void createPerson(Person person);
	Person getPersonById(int id);
	List<Person> getAllPersons();
	void updatePerson(Person person);
	boolean deletePerson(int id);
}
