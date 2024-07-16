package com.testing.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		System.out.println("--------CustomerService()---------");
	}

	@Transactional(readOnly = true)
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

}
