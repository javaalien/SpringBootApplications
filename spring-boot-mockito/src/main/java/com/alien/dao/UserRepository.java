package com.alien.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByAddress(String address);

}
