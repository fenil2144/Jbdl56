package com.example.starterProjectJbdl56.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.starterProjectJbdl56.model.Demo;
import com.example.starterProjectJbdl56.service.paymentServiceInterf;

@RestController
public class DemoController {
	
	private static Logger logger = LoggerFactory.getLogger(DemoController.class);

		
	//Field Injection
//	@Autowired
	Demo demo;
	
	
	//Constructor Injection
//	public DemoController(Demo demo) {
//		this.demo = demo;
//	}
	
//	public DemoController(Integer a) {
//		logger.info("a: {}",a);
//	}
	
	public DemoController(@Value("${server.port}") int a) {
		logger.info("a: {}",a);
	}
	
	@Autowired
	@Qualifier("PaymentServiceImpl2")
	paymentServiceInterf paymentServiceInterf;
	
	@Autowired
	@Qualifier("PaymentServiceImpl")
	paymentServiceInterf paymentServiceInterf2;

	
	@GetMapping("/demo")
	@ResponseBody
	public Demo getDemo() {
//		Demo demo = new Demo();
		logger.info("Demo object is {}",demo);
		return demo;
	}
	//com.example.starterProjectJbdl56.model.Demo@6fcc30b2
	//com.example.starterProjectJbdl56.model.Demo@5c793664
	
	//com.example.starterProjectJbdl56.model.Demo@129bd55d
	//com.example.starterProjectJbdl56.model.Demo@129bd55d
	
	//Prototype
	//com.example.starterProjectJbdl56.model.Demo@35e8316e
	//com.example.starterProjectJbdl56.model.Demo@35e8316e
	

}
