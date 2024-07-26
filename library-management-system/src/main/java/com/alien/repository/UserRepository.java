package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
