package com.example.minorProject1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.minorProject1.models.Author;

public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer>{
	
	
	//1st one - jpql - jpa queries
	//2nd one - Native Query
	//3rd one - Don't need to write the query
	
	Author findByEmail(String email);
	
	@Query("select a from Author a where a.email= ?1")
	public Author getAuthorByEmailId(String email);
	
	@Query(value = "select * from author a where a.email= ?1",nativeQuery = true)
	public Author getAuthorByEmailIdusingNative(String email);
	
	//find the authors above age of 30, living in India and name starting with p
	List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(
			int age, String country,String prefix);
	
	//select * from author where age>=30 and country = 'India' and name like 'p'

}
