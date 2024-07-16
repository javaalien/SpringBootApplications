package com.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.caching.model.Employee;
import com.caching.service.EmployeeService;

@SpringBootApplication
public class SpringBootCachingApplication implements CommandLineRunner {

	@Autowired
	EmployeeService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCachingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee(1, "John", "Doe", "email@domain.com");
		service.saveEmployee(employee);

		System.out.println(service.getEmployeeById(1));
		System.out.println(service.getEmployeeById(1));
	}
}
