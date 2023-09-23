package com.example.minorProject1.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.minorProject1.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	//int - 4 bytes, Integer - 16 bytes
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String contact;
	
	private String address;
	
	@Enumerated(value = EnumType.STRING )
	private AccountStatus accountStatus;
	
	@OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
	private List<Book> bookList;
	
	@OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
	private List<Transaction> transactionList;
	
	//select * from student join book on student.id = book.student_id
	
	@OneToOne
	@JoinColumn
	@JsonIgnoreProperties({"student","admin","password"})
	private MyUser myUser;
	
	@CreationTimestamp
	private Date createdOn;
	
	@UpdateTimestamp
	private Date updatedOn;

}
