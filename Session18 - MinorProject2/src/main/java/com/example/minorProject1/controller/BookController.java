package com.example.minorProject1.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorProject1.enums.BookFilterType;
import com.example.minorProject1.enums.BookSearchOperationType;
import com.example.minorProject1.models.Book;
import com.example.minorProject1.requests.BookCreateRequest;
import com.example.minorProject1.response.BookSearchResponse;
import com.example.minorProject1.service.BookServiceInterf;

@RestController
public class BookController {
	
	@Autowired
	BookServiceInterf bookServiceInterf;
	
	//Make This to be accessible only by Admin
	@PostMapping("/saveBook")
	public ResponseEntity saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
		Book book = bookServiceInterf.create(bookCreateRequest);
		return new ResponseEntity(book, HttpStatus.CREATED);
		
	}
	
	//Create Api for Updating the Book Details
	
	//Public Api and should be used by Anyone
	@GetMapping("/books/search")
	public List<BookSearchResponse> findBooks(
			@RequestParam("filter") BookFilterType bookFilterType,
			@RequestParam("value") String value){
		
				return bookServiceInterf.find(bookFilterType,value)
						.stream()
						.map(book -> book.toBookSearchResponse())
						.collect(Collectors.toList());
		
	}
	
	@GetMapping("/books/search/robust")
	public List<BookSearchResponse> findBooks2(
			@RequestParam("filter") BookFilterType bookFilterType,
			@RequestParam("value") String value,
			@RequestParam("bookSearchOperationType") BookSearchOperationType bookSearchOperationType){
		
				return bookServiceInterf.find2(bookFilterType,value,bookSearchOperationType)
						.stream()
						.map(book -> book.toBookSearchResponse())
						.collect(Collectors.toList());
		
	}
	
	
	

}
