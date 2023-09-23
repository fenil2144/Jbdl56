package com.example.minorProject1.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.minorProject1.enums.Genre;
import com.example.minorProject1.response.BookSearchResponse;
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
public class Book implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int cost;
	
	@Enumerated(value = EnumType.STRING)
	private Genre genre;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties({"bookList"})
	private Author author;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties({"bookList"})
	private Student student;
	
	@OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
	private List<Transaction> transactionList;
	
	@CreationTimestamp
	private Date createdOn;
	
	@UpdateTimestamp
	private Date updatedOn;
	
//	private int quantity;
//	
//	private String Status;
	
	public BookSearchResponse toBookSearchResponse() {
		
		return BookSearchResponse.builder()
				.id(id)
				.name(name)
				.author(author)
				.cost(cost)
				.genre(genre)
				.student(student)
				.transactionList(transactionList)
				.createdOn(createdOn)
				.updatedOn(updatedOn)
				.build();
	}
	

}
