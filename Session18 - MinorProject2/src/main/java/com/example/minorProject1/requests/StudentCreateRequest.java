package com.example.minorProject1.requests;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.minorProject1.enums.AccountStatus;
import com.example.minorProject1.models.Book;
import com.example.minorProject1.models.Student;
import com.example.minorProject1.models.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
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
		return Student.builder()
				.address(address)
				.contact(contact)
				.email(email)
				.name(name)
				.accountStatus(AccountStatus.ACTIVE)
				.build();
	}
	public UserCreateRequest toUser(){
        	return UserCreateRequest.builder()
                .student(toStudent())
                .username(username)
                .password(password)
                .build();
    }
	

}
