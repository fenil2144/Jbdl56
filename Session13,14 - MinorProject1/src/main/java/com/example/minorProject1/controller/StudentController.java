package com.example.minorProject1.controller;

import javax.validation.Valid;

import com.example.minorProject1.models.MyUser;
import com.example.minorProject1.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.minorProject1.requestDto.StudentCreateRequest;
import com.example.minorProject1.service.StudentServiceInterf;

import java.util.Optional;

@RestController
public class StudentController {
	
	@Autowired
	StudentServiceInterf studentServiceInterf;
	
	@PostMapping("/student")
	public ResponseEntity saveStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
		return new ResponseEntity(studentServiceInterf.save(studentCreateRequest),HttpStatus.CREATED);
	}

	@GetMapping("/student")
	public Optional<Student> getStudent() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUser myUser = (MyUser) authentication.getPrincipal();

		if(myUser.getStudent() == null){
			throw new RuntimeException("User is not a Student!");
		}

		return studentServiceInterf.findById(myUser.getStudent().getId());
	}

	@GetMapping("/student_for_admin")
	public Optional<Student> getStudentForAdmin(@RequestParam("studentId") int studentId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUser myUser = (MyUser) authentication.getPrincipal();

		return studentServiceInterf.findById(studentId);
	}

}
