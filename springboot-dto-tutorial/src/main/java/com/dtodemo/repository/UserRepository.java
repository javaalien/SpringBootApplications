package com.dtodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtodemo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
