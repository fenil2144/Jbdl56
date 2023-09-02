package com.example.MinorProject.serviceImpl;

import com.example.MinorProject.repository.BookRepositoryInterf;
import com.example.MinorProject.service.AuthorServiceInterf;
import com.example.MinorProject.service.BookServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookServiceInterf {

    @Autowired
    BookRepositoryInterf bookRepositoryInterf;

    @Autowired
    AuthorServiceInterf authorServiceInterf;
}
