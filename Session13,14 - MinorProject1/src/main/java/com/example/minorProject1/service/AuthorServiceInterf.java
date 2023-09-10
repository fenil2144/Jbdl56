package com.example.minorProject1.service;

import com.example.minorProject1.models.Author;

public interface AuthorServiceInterf {
	
	Author findByEmail(String email);

	Author createAuthor(Author author);

}
