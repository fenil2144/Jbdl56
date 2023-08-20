package com.example.starterProjectJbdl56.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.starterProjectJbdl56.model.Book;
import com.example.starterProjectJbdl56.model.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

	@NotBlank(message = "First Name is not valid")
	private String fName;
	
//	@Pattern(regexp = "**yy**")
	private String lName;
	private String dateOfBirth; 
	
	public Person to() {
		return Person.builder().firstName(fName).lastName(lName)
				.dob(dateOfBirth).build();
	}
	
}
