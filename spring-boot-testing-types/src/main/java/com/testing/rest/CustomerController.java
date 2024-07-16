package com.testing.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.domain.Customer;
import com.testing.domain.CustomerService;

@RestController
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
		System.out.println("--------CustomerController()---------");
	}

	@GetMapping("/api/customers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

}
