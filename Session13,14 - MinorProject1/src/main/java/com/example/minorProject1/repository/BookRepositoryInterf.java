package com.example.minorProject1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject1.enums.Genre;
import com.example.minorProject1.models.Book;

public interface BookRepositoryInterf extends JpaRepository<Book, Integer>{
	
//	@Query("select b from Book b where name ilike '%:name%'")
	
	List<Book> findByName(String name);
	
	List<Book> findByAuthor_name(String name);

	List<Book> findByGenre(Genre name);


}
