package com.example.MinorProject.controller;

import com.example.MinorProject.service.TransactionServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    //findTransaction, saveTransaction

    @Autowired
    TransactionServiceInterf transactionServiceInterf;

    @PostMapping("/transaction/{transactionType}")
    public ResponseEntity transact(
        @RequestParam("studentId") int studentId,
        @RequestParam("bookId") int bookId, @PathVariable("transactionType") String transactionType
    ){
        return new ResponseEntity(transactionServiceInterf.transact(studentId,bookId,transactionType), HttpStatus.OK);
    }

}
