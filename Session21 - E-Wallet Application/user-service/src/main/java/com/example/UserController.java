package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserCreateRequest userCreateRequest) throws JsonProcessingException {
        userService.create(userCreateRequest);
        return new ResponseEntity("User Created Successfully", HttpStatus.CREATED);
    }
}
