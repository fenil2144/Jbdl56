package com.example.MinorProject.service;

import com.example.MinorProject.models.Author;

public interface AuthorServiceInterf {

Author createAuthor(Author author);

Author findByEmail(String email);

}
