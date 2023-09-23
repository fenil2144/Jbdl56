package com.example.minorProject1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.minorProject1.enums.AccountStatus;
import com.example.minorProject1.models.Admin;
import com.example.minorProject1.models.MyUser;
import com.example.minorProject1.models.Student;
import com.example.minorProject1.repository.AdminRepositoryInterf;
import com.example.minorProject1.repository.MyUserRepositoryInterf;
import com.example.minorProject1.repository.StudentRespositoryInterf;

@SpringBootApplication
public class MinorProject1Application implements CommandLineRunner{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${users.authority.admin}")
	String adminAuthority;
	
	@Value("${users.authority.student}")
	String studentAuthority;
	
	@Autowired
	MyUserRepositoryInterf myUserRepositoryInterf;
	
	@Autowired
	AdminRepositoryInterf adminRepositoryInterf;
	
	@Autowired
	StudentRespositoryInterf studentRespositoryInterf;

	public static void main(String[] args) {
		SpringApplication.run(MinorProject1Application.class, args);
	}
	
	@Override
	public void run(String[] args ) {
		MyUser myUser = MyUser.builder()
				.username("raj")
				.password(passwordEncoder.encode("raj123"))
				.authority(adminAuthority)
				.build();
		
		myUser = myUserRepositoryInterf.save(myUser);
		
		Admin admin = Admin.builder()
				.age(30)
				.name("Raj Shah")
				.myUser(myUser)
				.build();
		
		adminRepositoryInterf.save(admin);
		
			MyUser myUser1 = MyUser.builder()
			.username("sai")
			.password(passwordEncoder.encode("sai123"))
			.authority(studentAuthority)
			.build();
	
	myUser1 = myUserRepositoryInterf.save(myUser1);
	
	Student student = Student.builder()
			.contact("98787878")
			.accountStatus(AccountStatus.ACTIVE)
			.email("sai@gmail.com")
			.name("Sai Shah")
			.myUser(myUser1)
			.build();
	
	studentRespositoryInterf.save(student);
		
		
	}

}
