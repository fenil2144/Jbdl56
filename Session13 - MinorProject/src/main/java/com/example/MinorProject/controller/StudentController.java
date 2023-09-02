package com.example.MinorProject.controller;

import com.example.MinorProject.dtos.StudentCreateRequest;
import com.example.MinorProject.service.StudentServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentController {

    //save, update, delete, read


    @Autowired
    StudentServiceInterf studentServiceInterf;

    @PostMapping("/saveStudent")
    public ResponseEntity saveStudent(@Valid  @RequestBody StudentCreateRequest studentCreateRequest) {
        return new ResponseEntity(studentServiceInterf.save(studentCreateRequest),HttpStatus.CREATED);

    }
}
