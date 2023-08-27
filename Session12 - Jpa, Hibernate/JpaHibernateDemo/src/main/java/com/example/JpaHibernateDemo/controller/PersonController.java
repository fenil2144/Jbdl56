package com.example.JpaHibernateDemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JpaHibernateDemo.dtos.CreatePersonRequest;
import com.example.JpaHibernateDemo.model.Person;
import com.example.JpaHibernateDemo.service.PersonServiceInterf;

@RestController
public class PersonController {
	
	private static Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	PersonServiceInterf personServiceInterf;
	
	@PostMapping("/person")
	public ResponseEntity createPerson(@RequestBody @Valid CreatePersonRequest  createPersonRequest) {
		
//		if(createPersonRequest.getFirstName() == null || createPersonRequest.getFirstName().isEmpty()) {
//			logger.error("Person's firstName is not valid");
//			return "Person's firstName is not valid";
//		}
		logger.info("Going to Saved in db");
		personServiceInterf.createPersonStatic(createPersonRequest);
//		MultiValueMap<String, String> headers = new HttpHeaders();
//		headers.add("Total Pages", "100");
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("getPerson")
	public Person getPerson(@RequestParam("id") int id) {
		return personServiceInterf.getPersonById(id);
	}
	
	@PutMapping("/{personId}")
	public Person updatePerson(@RequestBody @Valid CreatePersonRequest createPersonRequest, 
			@PathVariable("personId") int personId) {
		return personServiceInterf.updatePerson(createPersonRequest,personId);
	}
	
	@GetMapping("getAllPersons")
	public List<Person> getAllPersons(){
		return personServiceInterf.getAllPersons();
	}
	
	@DeleteMapping("/deletePerson/{id}")
	public void deletePerson(@PathVariable("id") int id) {
		personServiceInterf.deletePerson(id);
	}

}

