package com.example.MinorProject.exceptions;

public class TransactionServiceException extends RuntimeException{
    public TransactionServiceException(String message){
        super(message);
    }
}
