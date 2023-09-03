package com.example.MinorProject.repository;

import com.example.MinorProject.enums.TransactionType;
import com.example.MinorProject.models.Book;
import com.example.MinorProject.models.Student;
import com.example.MinorProject.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer> {

    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student,
                                                                       TransactionType transactionType);

}
