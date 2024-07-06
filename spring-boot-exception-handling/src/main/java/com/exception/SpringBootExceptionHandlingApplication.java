package com.exception;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.exception.entity.Customer;
import com.exception.service.CustomerService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class SpringBootExceptionHandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExceptionHandlingApplication.class, args);
	}

}

@Component
@RequiredArgsConstructor
class DataInitializer implements CommandLineRunner {
	private final CustomerService customerService;

	@Override
	public void run(String... args) {
		customerService.createCustomer(new Customer(null, "Alien", "alien@gmail.com"));
		customerService.createCustomer(new Customer(null, "Kavya", "Kavya@gmail.com"));
	}
}