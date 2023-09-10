package com.example.minorProject1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.minorProject1.models.Author;

public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer>{
	
	//1 - JPA Function Name
	Author findByEmail(String email);
	
	//2 - JPQL Query
	@Query("select a from Author a where a.email= ?1")
	Author getAuthorByEmail(String email);
	
	//3 - Native Query
	@Query(value = "select a from author a where a.email= ?1",nativeQuery = true)
	Author getAuthorByEmailNative(String email);
	
	//find the author above age of 30, living in India and name starting with M
	List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(
			int age, String country, String prefix); 

}
