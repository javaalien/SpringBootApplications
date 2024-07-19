package com.alien.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@Validated
public class UserLogginController {

	@PostMapping
	public ResponseEntity<String> login(@Valid @RequestBody User user) {
		return ResponseEntity.ok("User logged in successfully");
	}
}
