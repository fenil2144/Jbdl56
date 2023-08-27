package com.example.JpaHibernateDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JpaHibernateDemo.model.Author;

@Repository
public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer>{

}
