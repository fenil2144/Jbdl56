package com.example.minorProject1.requestDto;

import com.example.minorProject1.enums.AccountStatus;
import com.example.minorProject1.models.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {

	@NotBlank
	private String name;
	private String email;

	@NotBlank
	private String contact;
	private String address;

	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	public Student toStudent() {
		
		return Student.builder().name(name).email(email).contact(contact).address(address)
				.accountStatus(AccountStatus.ACTIVE).build();
	}

	public UserCreateRequest toUser() {

		return UserCreateRequest.builder()
				.student(toStudent()).username(username).password(password)
				.build();
	}

}
