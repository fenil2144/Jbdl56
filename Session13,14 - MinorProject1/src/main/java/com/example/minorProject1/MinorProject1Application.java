package com.example.minorProject1;

import java.util.List;

import com.example.minorProject1.enums.AccountStatus;
import com.example.minorProject1.models.Admin;
import com.example.minorProject1.models.MyUser;
import com.example.minorProject1.models.Student;
import com.example.minorProject1.repository.AdminRepositoryInterf;
import com.example.minorProject1.repository.MyUserRepositoryInterf;
import com.example.minorProject1.repository.StudentRepositoryInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.minorProject1.models.Author;
import com.example.minorProject1.repository.AuthorRepositoryInterf;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MinorProject1Application implements CommandLineRunner{
	
	@Autowired
	AuthorRepositoryInterf authorRepositoryInterf;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${users.authority.student}")
	String studentAuthority;

	@Value("${users.authority.admin}")
	String adminAuthority;

	@Autowired
	MyUserRepositoryInterf myUserRepositoryInterf;

	@Autowired
	AdminRepositoryInterf adminRepositoryInterf;

	@Autowired
	StudentRepositoryInterf studentRepositoryInterf;

	public static void main(String[] args) {
		SpringApplication.run(MinorProject1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("In Main Method");
		
//		List<Author> authorList = authorRepositoryInterf.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(
//				20,"India","M");
//		authorList.stream().map(author -> author.getBookList())
//			.forEach(books ->  System.out.println(books.size()));

//		MyUser adminUser = MyUser.builder()
//				.username("raj")
//				.password(passwordEncoder.encode("raj123"))
//				.authority(adminAuthority)
//				.build();
//
//		myUserRepositoryInterf.save(adminUser);
//
//		Admin admin = Admin.builder()
//				.name("Raj Shah")
//				.myUser(adminUser)
//				.email("raj@gmail.com")
//				.build();
//
//		adminRepositoryInterf.save(admin);
//
//		MyUser user = MyUser.builder()
//				.username("sai").password(passwordEncoder.encode("sai123"))
//				.authority(studentAuthority).build();
//
//		user = myUserRepositoryInterf.save(user);
//
//		Student student = Student.builder()
//				.contact("9878787878")
//				.accountStatus(AccountStatus.ACTIVE)
//				.email("sai@gmail.com")
//				.name("sai")
//				.myUser(user)
//				.build();
//		studentRepositoryInterf.save(student);


	}

}
