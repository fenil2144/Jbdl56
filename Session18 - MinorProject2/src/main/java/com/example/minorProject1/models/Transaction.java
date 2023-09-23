package com.example.minorProject1.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.example.minorProject1.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
