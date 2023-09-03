package com.example.MinorProject.controller;

import com.example.MinorProject.service.TransactionServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    //findTransaction, saveTransaction

    @Autowired
    TransactionServiceInterf transactionServiceInterf;
}
