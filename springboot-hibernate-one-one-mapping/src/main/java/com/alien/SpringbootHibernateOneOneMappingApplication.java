package com.alien;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alien.entity.Gender;
import com.alien.entity.User;
import com.alien.entity.UserProfile;
import com.alien.repository.UserRepository;

@SpringBootApplication
public class SpringbootHibernateOneOneMappingApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateOneOneMappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setName("Chetan");
		user.setEmail("chetan@gmail.com");

		UserProfile userProfile = new UserProfile();
		userProfile.setAddress("Pune");
		userProfile.setBirthOfDate(LocalDate.of(1990, 03, 20));
		userProfile.setGender(Gender.MALE);
		userProfile.setPhoneNumber("1234567899");

		user.setUserProfile(userProfile);
		userProfile.setUser(user);

		userRepository.save(user);
	}

}
