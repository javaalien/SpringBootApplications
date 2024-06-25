package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.security.entity.User;
import com.security.service.UserService;

@SpringBootApplication
public class SpringSecurityExampleApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setUsername("user");
		user.setPassword("password");
		user.setRole("USER");

		userService.save(user);

		User admin = new User();

		admin.setUsername("admin");
		admin.setPassword("password");
		admin.setRole("ADMIN");

		userService.save(admin);

	}

}
