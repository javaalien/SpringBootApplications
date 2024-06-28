package com.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
	private final CustomerService customerService;

	@Override
	public void run(String... args) {
		customerService.createCustomer(new Customer(null, "chetan", "chetan@gmail.com"));
		customerService.createCustomer(new Customer(null, "kavay", "kavay@gmail.com"));
	}
}