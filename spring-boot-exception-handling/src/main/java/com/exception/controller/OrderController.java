package com.exception.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exception.exHandler.CustomerNotFoundException;
import com.exception.exHandler.ErrorResponse;

@RestController
@RequestMapping("/api/orders")	// http://localhost:8080/api/orders?customerId=101
public class OrderController {

	@GetMapping()
	public List<String> getCustomerOrders(@RequestParam Long customerId) {
		throw new CustomerNotFoundException(customerId);
	}

	@ExceptionHandler({ CustomerNotFoundException.class })		
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse("No orders found for given customer Id", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

}
