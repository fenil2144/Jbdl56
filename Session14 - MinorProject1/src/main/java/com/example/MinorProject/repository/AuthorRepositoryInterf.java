package com.example.MinorProject.repository;

import com.example.MinorProject.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);

    @Query("select a from Author a where a.email=?1")
    public Author getAuthorByEmailId(String email);

    @Query(value = "select * from author a where a.email=?1",nativeQuery = true)
    public Author getAuthorByEmailIdusingNativeQuery(String email);

    //find all the authors above age of 25, living in India and name starting with P
    List<Author> findByAgeGreaterThanEqualAndCountryAndNamesStartingWith(int age,
                                                                         String country, String prefix);
    //select * from author where age>=25 and country='India' and name like 'p'



}
