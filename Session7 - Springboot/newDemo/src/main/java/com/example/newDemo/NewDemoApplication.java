package com.example.newDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.*;

@SpringBootApplication
public class NewDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewDemoApplication.class, args);
		System.out.println(MathOperations.add(10, 20));
	}

}
