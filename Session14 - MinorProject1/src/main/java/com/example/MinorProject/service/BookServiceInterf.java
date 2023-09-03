package com.example.MinorProject.service;

import com.example.MinorProject.dtos.BookCreateRequest;
import com.example.MinorProject.enums.BookFilterType;
import com.example.MinorProject.models.Book;

import java.util.List;

public interface BookServiceInterf {

    Book create(BookCreateRequest bookCreateRequest);
    List<Book> find(BookFilterType bookFilterType, String value);
}
