package com.example.MinorProject.controller;

import com.example.MinorProject.dtos.BookCreateRequest;
import com.example.MinorProject.models.Book;
import com.example.MinorProject.service.BookServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookController {

    //addBook, searchBook, updateBook, deleteBook, findALlBooks(filtering)

    @Autowired
    BookServiceInterf bookServiceInterf;

    @PostMapping("/saveBook")
    public ResponseEntity saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        Book book = bookServiceInterf.create(bookCreateRequest);
        return new ResponseEntity(book, HttpStatus.CREATED);

    }
}
