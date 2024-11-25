package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alien.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
