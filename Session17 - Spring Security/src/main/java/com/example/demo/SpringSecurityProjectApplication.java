package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class SpringSecurityProjectApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		MyUser user = MyUser.builder()
//				.name("sai").email("sai@gmail.com").password(passwordEncoder.encode("sai23"))
//				.authority("dev").build();
//
//		MyUser user2 = MyUser.builder()
//				.name("karan").email("karan@gmail.com").password(passwordEncoder.encode("karan23"))
//				.authority("test,admin").build();
//
//		myUserRepository.saveAll(Arrays.asList(user,user2));
	}
}
