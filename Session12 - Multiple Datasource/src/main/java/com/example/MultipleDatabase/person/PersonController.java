package com.example.MultipleDatabase.person;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonRepositoryInterf personRepositoryInterf;
	
	private static Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@PostMapping()
	public ResponseEntity createPerson(@RequestBody @Valid Person person) {

		
		logger.info(person.toString());
		Person personSaved  = personRepositoryInterf.save(person);
		return new ResponseEntity(personSaved,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/{personId}")
	public Person getPerson(@PathVariable("personId") int personId) {
		return personRepositoryInterf.findById(personId).get();
	}
	
	@GetMapping("/all")
	public List<Person> getAllPersons(){
		return personRepositoryInterf.findAll();
	}
	
	@DeleteMapping("/{personId}")
	public void deletePerson(@PathVariable("personId") int personId) {
		personRepositoryInterf.deleteById(personId);
	}
}

