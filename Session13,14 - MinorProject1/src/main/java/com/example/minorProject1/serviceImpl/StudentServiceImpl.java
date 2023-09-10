package com.example.minorProject1.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorProject1.models.Student;
import com.example.minorProject1.repository.StudentRepositoryInterf;
import com.example.minorProject1.requestDto.StudentCreateRequest;
import com.example.minorProject1.service.StudentServiceInterf;

@Service
public class StudentServiceImpl implements StudentServiceInterf{
	
	@Autowired
	StudentRepositoryInterf studentRepositoryInterf;

	@Override
	public Student save(StudentCreateRequest studentCreateRequest) {
		return studentRepositoryInterf.save(studentCreateRequest.toStudent());
	}

	@Override
	public Optional<Student> findById(int studentId) {
		return studentRepositoryInterf.findById(studentId);
	}

}
