package com.example.minorProject1.service;

import java.util.List;
import java.util.Optional;

import com.example.minorProject1.enums.BookFilterType;
import com.example.minorProject1.models.Book;
import com.example.minorProject1.requestDto.BookCreateRequest;

public interface BookServiceInterf {
	
	Book create(BookCreateRequest bookCreateRequest);
	
	List<Book> find(BookFilterType bookFilterType, String value);

	Optional<Book> findById(int bookId);
	
	Book save(Book book);

}
