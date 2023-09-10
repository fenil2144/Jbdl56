package com.example.minorProject1.service;

import java.util.Optional;

import com.example.minorProject1.models.Student;
import com.example.minorProject1.requestDto.StudentCreateRequest;

public interface StudentServiceInterf {

	Student save(StudentCreateRequest studentCreateRequest);
	
	Optional<Student> findById(int studentId);

}
