package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/home")
    public String greet(){
        return "Hello, I am Home Method!";
    }

    @GetMapping("/developercode")
    public String developercode(){
        return "Hello, I am executing Developer Code!";
    }

    @GetMapping("/testcode")
    public String testcode(){
        return "Hello, I am executing Test Code!";
    }

    @GetMapping("/generalcode")
    public String generalcode(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        return "Hello "+myUser.getUsername()+", I am executing General Code!";
    }
}
