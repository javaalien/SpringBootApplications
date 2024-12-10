package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dao.UserDatabase;
import com.alien.dto.User;

@RestController
public class UserController {

	@Autowired
	private UserDatabase database;

	@GetMapping("/loadUsers")
	public List<User> getUsers() {
		return database.getAllUsers();
	}
	
}
