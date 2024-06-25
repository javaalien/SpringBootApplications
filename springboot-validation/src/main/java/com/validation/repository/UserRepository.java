package com.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
