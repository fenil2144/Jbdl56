package com.example.MinorProject.serviceImpl;

import com.example.MinorProject.repository.TransactionRepositoryInterf;
import com.example.MinorProject.service.BookServiceInterf;
import com.example.MinorProject.service.StudentServiceInterf;
import com.example.MinorProject.service.TransactionServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {

    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    @Autowired
    StudentServiceInterf studentServiceInterf;

    @Autowired
    BookServiceInterf bookServiceInterf;
}
