package com.example.starterProjectJbdl56.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starterProjectJbdl56.model.Demo;

@RestController
public class DemoController2 {
	
	private static Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	Demo demo;
	
	@GetMapping("/demo2")
	public Demo getDemo() {
		logger.info("Demo object in DemoController2 is {}",demo);
		return demo;
	}
	
	//Prototype Scope
	//DemoController - com.example.starterProjectJbdl56.model.Demo@24b4d544
	//DemoController2 -  com.example.starterProjectJbdl56.model.Demo@3f9270ed

}
