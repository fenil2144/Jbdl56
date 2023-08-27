package com.example.JpaHibernateDemo.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JpaHibernateDemo.model.Author;
import com.example.JpaHibernateDemo.repository.AuthorRepositoryInterf;
import com.example.JpaHibernateDemo.service.AuthorServiceInterf;

@Service
public class AuthorServiceImpl implements AuthorServiceInterf {
	
	@Autowired
	AuthorRepositoryInterf authorRepositoryInterf;

	@Override
	public Author save(Author author) {
		return authorRepositoryInterf.save(author);
	}

}
