package com.example.minorProject1.serviceImpl;

import java.util.Optional;

import com.example.minorProject1.models.MyUser;
import com.example.minorProject1.requestDto.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.minorProject1.models.Student;
import com.example.minorProject1.repository.StudentRepositoryInterf;
import com.example.minorProject1.requestDto.StudentCreateRequest;
import com.example.minorProject1.service.StudentServiceInterf;

@Service
public class StudentServiceImpl implements StudentServiceInterf{
	
	@Autowired
	StudentRepositoryInterf studentRepositoryInterf;

	@Autowired
	MyUserDetailsServiceImpl myUserDetailsService;

	@Override
	public Student save(StudentCreateRequest studentCreateRequest) {
		return studentRepositoryInterf.save(studentCreateRequest.toStudent());
	}

	@Override
	public Optional<Student> findById(int studentId) {
		return studentRepositoryInterf.findById(studentId);
	}

	public void createUserAndStudent(StudentCreateRequest studentCreateRequest){
		UserCreateRequest userCreateRequest = studentCreateRequest.toUser();
		MyUser myUser = myUserDetailsService.createUser(userCreateRequest);

		Student student = studentCreateRequest.toStudent();
		student.setMyUser(myUser);
		studentRepositoryInterf.save(student);
	}

}
