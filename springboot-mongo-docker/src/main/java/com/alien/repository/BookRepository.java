package com.alien.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.alien.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {
	
}
