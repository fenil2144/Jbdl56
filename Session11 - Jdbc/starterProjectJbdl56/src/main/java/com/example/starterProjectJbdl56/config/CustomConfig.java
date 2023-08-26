package com.example.starterProjectJbdl56.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfig {
	
	@Bean
	@Scope
	public RestTemplate getTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(restTemplate);
		return restTemplate;
	}

}
