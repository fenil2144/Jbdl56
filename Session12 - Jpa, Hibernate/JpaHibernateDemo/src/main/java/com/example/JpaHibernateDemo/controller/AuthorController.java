package com.example.JpaHibernateDemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.JpaHibernateDemo.model.Author;
import com.example.JpaHibernateDemo.service.AuthorServiceInterf;

@RestController
public class AuthorController {
	
	@Autowired
	AuthorServiceInterf authorServiceInterf;
	
	@PostMapping("/author")
	public ResponseEntity createAuthor(@RequestBody @Valid Author author) {
		Author saveAuthorObj = authorServiceInterf.save(author);
		return new ResponseEntity(saveAuthorObj,HttpStatus.CREATED);
	}

}
