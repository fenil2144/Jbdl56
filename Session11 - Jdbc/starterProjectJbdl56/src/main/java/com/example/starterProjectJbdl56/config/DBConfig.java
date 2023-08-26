package com.example.starterProjectJbdl56.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
	
	@Bean
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl56","root","root1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
