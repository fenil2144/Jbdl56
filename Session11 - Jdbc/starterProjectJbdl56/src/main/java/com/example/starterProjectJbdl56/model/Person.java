package com.example.starterProjectJbdl56.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
	
	private int id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String dob;
	
}
