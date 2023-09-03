package com.example.MinorProject.repository;

import com.example.MinorProject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryInterf extends JpaRepository<Book, Integer> {


}
