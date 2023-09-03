package com.example.MinorProject.models;

import com.example.MinorProject.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String externalId;

    @Enumerated
    private TransactionType transactionType;

    private double payment;

    @ManyToOne
    @JsonIgnoreProperties("transactionList")
    private Book book;

    @ManyToOne
    @JsonIgnoreProperties("transactionList")
    private Student student;

    @CreationTimestamp
    private Date createdOn;

}
