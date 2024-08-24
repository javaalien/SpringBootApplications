package com.infy.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.exception.EmployeeNotFoundException;
import com.infy.model.Employee;
import com.infy.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public Optional<Employee> searchEmployeeById(Long id) {
		logger.info("Searching for employee with ID: {}", id);
		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {
			logger.debug("Employee found: {}", employee.get());
		} else {
			logger.warn("No employee found with ID: {}", id);
		}

		return employee;
	}

	public List<Employee> searchEmployeeByName(String name) {
		logger.info("Searching for employees with name containing: {}", name);
		List<Employee> employees = employeeRepository.findByNameContainingIgnoreCase(name);

		if (!employees.isEmpty()) {
			logger.debug("Employees found: {}", employees);
		} else {
			logger.warn("No employees found with name: {}", name);
		}

		return employees;
	}

	public Employee updateEmployee(Long id, Employee updatedEmployee) {

		logger.info("Attempting to update employee with ID: {}", id);

		return employeeRepository.findById(id).map(employee -> {
			logger.debug("Employee found: {}", employee);

			employee.setName(updatedEmployee.getName());
			employee.setSalary(updatedEmployee.getSalary());

			Employee savedEmployee = employeeRepository.save(employee);
			logger.info("Successfully updated employee with ID: {}", id);

			return savedEmployee;
		}).orElseThrow(() -> {
			logger.error("Failed to update. Employee not found with ID: {}", id);
			return new EmployeeNotFoundException("Employee not found with ID " + id);
		});
	}

	public void deleteEmployee(Long id) {
		logger.info("Attempting to delete employee with ID: {}", id);

		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			logger.info("Successfully deleted employee with ID: {}", id);
		} else {
			logger.error("Failed to delete. Employee not found with ID: {}", id);
			throw new EmployeeNotFoundException("Employee not found with ID " + id);
		}
	}

	public List<Employee> getEmployeesBySalaryGreaterThan(Double salary) {
		logger.info("Fetching employees with salary greater than: {}", salary);
		List<Employee> employees = employeeRepository.findBySalaryGreaterThanOrderByName(salary);

		if (!employees.isEmpty()) {
			logger.debug("Employees found: {}", employees);
		} else {
			logger.warn("No employees found with salary greater than: {}", salary);
		}

		return employees;
	}
}
