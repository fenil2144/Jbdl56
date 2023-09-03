package com.example.MinorProject.controller;

import com.example.MinorProject.dtos.BookCreateRequest;
import com.example.MinorProject.enums.BookFilterType;
import com.example.MinorProject.models.Book;
import com.example.MinorProject.service.BookServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/books/search")
    public List<Book> findBooks(
            @RequestParam("filter") BookFilterType bookFilterType,
            @RequestParam("value") String value){

        return bookServiceInterf.find(bookFilterType,value);
    }
}
