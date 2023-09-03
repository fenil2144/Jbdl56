package com.example.MinorProject.repository;

import com.example.MinorProject.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {}
