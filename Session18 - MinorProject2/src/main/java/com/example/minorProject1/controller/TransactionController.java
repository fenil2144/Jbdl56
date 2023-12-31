package com.example.minorProject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorProject1.exceptions.TransactionServiceException;
import com.example.minorProject1.service.TransactionServiceInterf;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionServiceInterf transactionServiceInterf;
	
	//Take StudentId of Logged In User
	@PostMapping("/transaction/issue")
	public ResponseEntity issueTxn(@RequestParam("studentId") int studentId,
			@RequestParam("bookId") int bookId) throws TransactionServiceException {
		return new ResponseEntity(transactionServiceInterf.issueTxn(studentId,bookId), HttpStatus.OK) ;
		
	}
	
	//Take StudentId of Logged In User and check if the book is issued to LoggedIn User Only
	@PostMapping("/transaction/return")
	public ResponseEntity returnTxn(@RequestParam("studentId") int studentId,
            @RequestParam("bookId") int bookId) throws TransactionServiceException {
		return new ResponseEntity(transactionServiceInterf.returntxn(studentId,bookId), HttpStatus.OK) ;

	}

}
