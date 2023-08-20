package com.example.starterProjectJbdl56;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.starterProjectJbdl56.controller.DemoController;

@SpringBootApplication
public class StarterProjectJbdl56Application {
	
	private static Logger logger = LoggerFactory.getLogger(StarterProjectJbdl56Application.class);

	public static void main(String[] args) {
		SpringApplication.run(StarterProjectJbdl56Application.class, args);
		
//		DemoController demoController = new DemoController();
//		logger.info("demoController Object is {}",demoController);

	}

}
