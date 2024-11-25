package com.alien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alien.entity.Comment;
import com.alien.entity.Post;
import com.alien.repository.PostRepository;

@SpringBootApplication
public class SpringbootHibernateOneManyMappingApplication implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateOneManyMappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Post post = new Post("one to many mapping using JPA and hibernate",
				"one to many mapping using JPA and hibernate");

		Comment comment1 = new Comment("Very useful");
		Comment comment2 = new Comment("informative");
		Comment comment3 = new Comment("Great post");

		post.getComments().add(comment1);
		post.getComments().add(comment2);
		post.getComments().add(comment3);

		this.postRepository.save(post);

	}
}
