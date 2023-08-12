package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Demo {
	
	public Demo() {
		System.out.println("In Demo Class Constructor");
	}
	
	public int add (int a, int b) {
		return a+b;
	}
	
//	@Bean
//	public CancelQueryTaskImpl getCancelQueryTaskImplObj() {
//		return new CancelQueryTaskImpl();
//	}

}
