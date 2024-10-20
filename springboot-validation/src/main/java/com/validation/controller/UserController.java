package com.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validation.model.User;
import com.validation.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.createUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
}
