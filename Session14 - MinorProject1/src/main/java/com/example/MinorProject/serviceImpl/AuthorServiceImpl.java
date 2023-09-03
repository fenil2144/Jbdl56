package com.example.MinorProject.serviceImpl;

import com.example.MinorProject.models.Author;
import com.example.MinorProject.repository.AuthorRepositoryInterf;
import com.example.MinorProject.service.AuthorServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorServiceInterf{
    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;

    @Override
    public Author createAuthor(Author author) {
        return authorRepositoryInterf.save(author);
    }

    @Override
    public Author findByEmail(String email) {
        return authorRepositoryInterf.findByEmail(email);
    }
}
