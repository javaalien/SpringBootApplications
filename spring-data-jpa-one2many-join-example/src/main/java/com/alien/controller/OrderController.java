package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.OrderRequest;
import com.alien.dto.OrderResponse;
import com.alien.entity.Customer;
import com.alien.repository.CustomerRepository;
import com.alien.repository.ProductRepository;

@RestController
public class OrderController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/placeOrder")
	public Customer placeOrder(@RequestBody OrderRequest request) {
		return customerRepository.save(request.getCustomer());
	}

	@GetMapping("/findAllOrders")
	public List<Customer> findAllOrders() {
		return customerRepository.findAll();
	}

	@GetMapping("/getInfo")
	public List<OrderResponse> getJoinInformation() {
		return customerRepository.getJoinInformation();
	}
}
