package com.example.MinorProject.serviceImpl;

import com.example.MinorProject.dtos.StudentCreateRequest;
import com.example.MinorProject.models.Student;
import com.example.MinorProject.repository.StudentRespositoryInterf;
import com.example.MinorProject.service.StudentServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentServiceInterf {

    @Autowired
    StudentRespositoryInterf studentRespositoryInterf;

}
