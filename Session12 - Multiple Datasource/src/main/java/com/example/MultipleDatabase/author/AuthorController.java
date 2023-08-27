package com.example.MultipleDatabase.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthorController {
	
	@Autowired
	AuthorRepositoryInterf authorRepositoryInterf;
	
	@PostMapping()
	public ResponseEntity createPerson(@RequestBody Author author) {
		
		Author authorSaved  = authorRepositoryInterf.save(author);
		return new ResponseEntity(authorSaved,HttpStatus.ACCEPTED);
		
	}

}
