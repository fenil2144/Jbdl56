package com.example.minorProject1.requestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.minorProject1.enums.Genre;
import com.example.minorProject1.models.Author;
import com.example.minorProject1.models.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookCreateRequest {
	
	@NotBlank
	private String name;
	
	@Positive
	private int cost;
	
	@NotNull
	private Genre genre;
	
	@NotNull
	private Author author;
	
	public Book toBook() {
		return Book.builder().name(name).cost(cost).genre(genre).author(author).build();
	}
}