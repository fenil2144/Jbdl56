package com.example.MinorProject.serviceImpl;

import com.example.MinorProject.repository.AuthorRepositoryInterf;
import com.example.MinorProject.service.AuthorServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorServiceImpl implements AuthorServiceInterf{
    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;
}
