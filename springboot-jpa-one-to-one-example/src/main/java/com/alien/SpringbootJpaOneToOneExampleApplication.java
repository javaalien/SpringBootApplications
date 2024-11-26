package com.alien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alien.model.Instructor;
import com.alien.model.InstructorDetail;
import com.alien.repository.InstructorRepository;

@SpringBootApplication
public class SpringbootJpaOneToOneExampleApplication implements CommandLineRunner {

	@Autowired
	private InstructorRepository instructorRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaOneToOneExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Instructor instructor = new Instructor("Chetan", "Malabade", "chetan@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("Java Guides", "Cricket Playing");

		instructor.setInstructorDetail(instructorDetail);

		instructorRepository.save(instructor);

	}
}
