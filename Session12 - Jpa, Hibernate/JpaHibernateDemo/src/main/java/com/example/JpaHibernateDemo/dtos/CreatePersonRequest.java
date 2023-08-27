package com.example.JpaHibernateDemo.dtos;

import javax.validation.constraints.NotBlank;

import com.example.JpaHibernateDemo.model.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

	@NotBlank(message = "First Name is not valid")
	private String firstname;
	
//	@Pattern(regexp = "**yy**")
	private String lastname;
	private String dateOfBirth; 
	
	public Person to() {
		return Person.builder().firstName(firstname).lastName(lastname)
				.dob(dateOfBirth).build();
	}
	
}
