package com.alien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alien.entity.Post;
import com.alien.entity.Tag;
import com.alien.repository.PostRepository;

@SpringBootApplication
public class SpringbootHibernateManyToManyMappingApplication implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateManyToManyMappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Post post = new Post("Hibernate Many to Many Mapping Example with Spring Boot",
				"Hibernate Many to Many Mapping Example with Spring Boot",
				"Hibernate Many to Many Mapping Example with Spring Boot");

		Post post1 = new Post("Hibernate One to Many Mapping Example with Spring Boot",
				"Hibernate One to Many Mapping Example with Spring Boot",
				"Hibernate One to Many Mapping Example with Spring Boot");

		Tag springBoot = new Tag("Spring Boot");
		Tag hibernate = new Tag("Hibernate");

		post.getTags().add(springBoot);
		post.getTags().add(hibernate);

		springBoot.getPosts().add(post1);
		hibernate.getPosts().add(post);

		springBoot.getPosts().add(post1);
		post1.getTags().add(springBoot);

		this.postRepository.save(post);
		this.postRepository.save(post1);

	}
}
