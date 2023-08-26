package com.example.starterProjectJbdl56.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.starterProjectJbdl56.controller.DemoController;

@Component
@Scope("prototype")
@ComponentScan(basePackages = {"com.example.starterProjectJbdl56.controller"})
public class Demo {
	private static Logger logger = LoggerFactory.getLogger(Demo.class);
	
	public Demo() {
		logger.info("Creating demo object.. "+this);
	}
	
	private int id;
	private String name;

	public Demo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
