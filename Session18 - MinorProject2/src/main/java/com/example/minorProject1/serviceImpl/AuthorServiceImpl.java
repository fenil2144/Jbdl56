package com.example.minorProject1.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.minorProject1.models.Author;
import com.example.minorProject1.repository.AuthorRepositoryInterf;
import com.example.minorProject1.service.AuthorServiceInterf;

@Service
public class AuthorServiceImpl implements AuthorServiceInterf{
	
	@Autowired
	AuthorRepositoryInterf authorRepositoryInterf;

	@Override
	public Author createAuthor(Author author) {
		return authorRepositoryInterf.save(author);
	}

	@Override
	public Author findByEmail(String email) {
		
		return authorRepositoryInterf.findByEmail(email);
	}
	
	

}
