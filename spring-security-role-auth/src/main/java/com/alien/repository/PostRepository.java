package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
