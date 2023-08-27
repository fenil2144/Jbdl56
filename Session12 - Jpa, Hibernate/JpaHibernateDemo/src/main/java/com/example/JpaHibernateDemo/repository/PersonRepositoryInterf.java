package com.example.JpaHibernateDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.JpaHibernateDemo.model.Person;

public interface PersonRepositoryInterf extends JpaRepository<Person, Integer>{
	
	Optional<Person> findByDob(String dob);
	
	@Query(value = "select * from person_tab where id=:personId",nativeQuery = true)
	Person getPerson(int personId);

}
