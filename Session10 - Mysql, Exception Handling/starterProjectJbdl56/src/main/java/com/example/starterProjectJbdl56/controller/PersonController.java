package com.example.starterProjectJbdl56.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.starterProjectJbdl56.dtos.CreatePersonRequest;
import com.example.starterProjectJbdl56.dtos.ErrorResponse;
import com.example.starterProjectJbdl56.model.Person;
import com.example.starterProjectJbdl56.service.PersonServiceInterf;

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
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Total Pages", "100");
		
		return new ResponseEntity(new Person(),headers, HttpStatus.CREATED);
	}
	

}
