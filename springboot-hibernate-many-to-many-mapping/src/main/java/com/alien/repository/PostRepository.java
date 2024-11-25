package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alien.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
