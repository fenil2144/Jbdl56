package com.example.MinorProject.service;

import com.example.MinorProject.dtos.StudentCreateRequest;
import com.example.MinorProject.models.Student;

public interface StudentServiceInterf {

    Student save (StudentCreateRequest studentCreateRequest);
}
