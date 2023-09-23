package com.example.minorProject1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject1.models.Book;

public interface BookRepositoryInterf extends JpaRepository<Book, Integer>{
	
	List<Book> findByName(String name);
	
	List<Book> findByAuthor_name(String value);

	List<Book> findByGenre(String value);

	List<Book> findByCost(String value);

	
	

}
