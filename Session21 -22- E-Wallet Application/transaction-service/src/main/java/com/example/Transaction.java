package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId;
    private String sender;
    private String receiver;
    private String message;
    private Double amount;

    @Enumerated(value=EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updateOn;


}
