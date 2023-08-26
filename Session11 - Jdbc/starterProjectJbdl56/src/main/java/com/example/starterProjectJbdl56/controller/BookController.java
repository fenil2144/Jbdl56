package com.example.starterProjectJbdl56.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.starterProjectJbdl56.model.Book;

@RestController
public class BookController {
	
	private HashMap<Integer, Book> bookHashMap = new HashMap<>();
	
	private static Logger logger = LoggerFactory.getLogger(BookController.class);

	//insertBook - post - requestBody
	// GetBook - Get - path param
	//GetAllBooks - GetMethod 
	// updateBook - put - requestBody
	//deleteBook - delete - path variable
	
	@PostMapping("/book")
	public String insertBook(@RequestBody Book book) {
		logger.info("Book coming for Insertion {}",book);
		bookHashMap.put(book.getId(), book);
		return "Book Inserted Successfully";
	}
	
	@GetMapping("/book/{bookId}")
	public Book getBook(@PathVariable("bookId") int bookId) {
		logger.info("Book Id Received: {}",bookId);
		return bookHashMap.get(bookId);
	}
	
	@GetMapping("/book")
	public List<Book> getAllBooks() {
		logger.info("Get All Books");
		return bookHashMap.values().stream().collect(Collectors.toList());
	}
	
	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		logger.info("Book coming for Insertion: {}",book);
		bookHashMap.put(book.getId(), book);
		return bookHashMap.get(book.getId());
	}
	
	@DeleteMapping("/book/{bookId}")
	public String deleteBook(@PathVariable("bookId") int bookId) {
		logger.info("Book Id Received: {}",bookId);
		 bookHashMap.remove(bookId);
		 return "Book Deleted Successfully";
	}
}
