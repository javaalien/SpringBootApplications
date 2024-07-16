package com.caching.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.caching.dao.EmployeeRepository;
import com.caching.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Cacheable(value = "employees", key = "#id") // comment this annotation and check for database queries on console
	public Optional<Employee> getEmployeeById(Integer id) {
		return repository.findById(id);
	}

	@CachePut(cacheNames = "employees", key = "#employee.id")
	public Employee updateEmployee(Employee employee) {
		return repository.save(employee);
	}

	public void saveEmployee(Employee employee) {
		repository.save(employee);
	}
}
