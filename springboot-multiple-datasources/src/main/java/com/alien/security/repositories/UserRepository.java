package com.alien.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.security.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
