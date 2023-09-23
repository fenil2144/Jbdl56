package com.example.minorProject1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorProject1.models.MyUser;
import com.example.minorProject1.models.Student;
import com.example.minorProject1.requests.StudentCreateRequest;
import com.example.minorProject1.service.StudentServiceInterf;

@RestController
public class StudentController {
	
	@Autowired
	StudentServiceInterf studentServiceInterf;
	
	@PostMapping("/saveStudent")
	public ResponseEntity saveStudent(@Valid  @RequestBody StudentCreateRequest studentCreateRequest) {
		return new ResponseEntity(studentServiceInterf.save(studentCreateRequest),HttpStatus.CREATED);
		
	}
	
	//Create APi for Updating Student Details, only for Logged In User
	//Create Api for Deleting Student Account and Only accessible for Logged In user.
	
	//Only for Students Role
	@GetMapping("/student")
	public Student getStudent() throws Exception {
		System.out.println("In Student");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();

		if(myUser.getStudent() ==null) {
			throw new Exception("User is Not as Student");
		}
				
		return studentServiceInterf.findById(myUser.getStudent().getId());
	}
	
	//Only for Admin Role
	@GetMapping("/student_for_admin")
	public Student getStudentForAdmin(@RequestParam("studentId") int studentId ) throws Exception {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUser myUser = (MyUser)authentication.getPrincipal();
		
		if(myUser.getAdmin() ==null) {
			throw new Exception("User is Not as Admin");
		}
		
		return studentServiceInterf.findById(studentId);
	}

}
