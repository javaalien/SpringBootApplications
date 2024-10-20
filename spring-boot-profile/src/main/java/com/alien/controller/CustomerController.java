package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.CustomerDto;
import com.alien.entity.Customer;
import com.alien.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping
	public List<Customer> saveCustomers(@RequestBody List<Customer> customers) {
		return service.addNewCustomers(customers);
	}

	@GetMapping
	public List<CustomerDto> fetchAllCustomers() {
		return service.getCustomers();
	}

}
