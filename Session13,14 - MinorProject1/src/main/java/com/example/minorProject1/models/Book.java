package com.example.minorProject1.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.minorProject1.enums.Genre;
import com.example.minorProject1.responseDto.BookSearchResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"author"})
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class Book {
	
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
	private Student student;
	
	@OneToMany(mappedBy = "book")
	private List<Transaction> transactionList;
	
	@CreationTimestamp
	private Date createdOn;
	
	@UpdateTimestamp
	private Date updatedOn;
	
	public BookSearchResponse toBookSearchResponse() {
		return BookSearchResponse.builder()
				.id(id).name(name)
				.author(author).cost(cost)
				.genre(genre).student(student).createdOn(createdOn).updatedOn(updatedOn).build();
	}

}
