package com.alien.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.model.Car;

@RestController
@RequestMapping("/car")
@Validated
public class CarController {

	@PostMapping
	public ResponseEntity<String> createCar(@Validated @RequestBody Car car) {

		return ResponseEntity.ok("Car created successfully");
	}
}
