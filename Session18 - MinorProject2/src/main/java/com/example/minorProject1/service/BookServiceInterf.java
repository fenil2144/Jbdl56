package com.example.minorProject1.service;

import java.util.List;

import com.example.minorProject1.enums.BookFilterType;
import com.example.minorProject1.enums.BookSearchOperationType;
import com.example.minorProject1.models.Book;
import com.example.minorProject1.requests.BookCreateRequest;

public interface BookServiceInterf {

	Book create(BookCreateRequest bookCreateRequest);
	Book save(Book book);


	List<Book> find(BookFilterType bookFilterType, String value);
	
	Book findById(Integer id);
	
	List<Book> find2(BookFilterType bookFilterType, String value, BookSearchOperationType bookSearchOperationType);

	
	

}
