package com.alien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.entity.Employee;
import com.alien.repository.EmployeeRepository;

@SpringBootApplication
@RestController
@RequestMapping("/employee")
public class SpringbootCompositeKeyApplication {

	@Autowired
	private EmployeeRepository repository;

	@PostMapping
	public Employee save(@RequestBody Employee employee) {
		return repository.save(employee);
	}

	@GetMapping
	public List<Employee> getAll() {
		return repository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCompositeKeyApplication.class, args);
	}
}
