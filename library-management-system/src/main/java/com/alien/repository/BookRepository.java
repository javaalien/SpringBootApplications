package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
