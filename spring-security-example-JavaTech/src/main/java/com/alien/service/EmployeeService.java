package com.alien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alien.entity.Employee;
import com.alien.repository.EmployeeRepository;

@Service
public class EmployeeService {

	public static final String DEFAULT_ROLE = "ROLE_EMPLOYEE";
	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee createNewEmployee(Employee employee) {

		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employee.setRoles(DEFAULT_ROLE);

		return repository.save(employee);

	}

	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	public Employee getEmployee(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found with id " + id));
	}

	public Employee changeRoleOfEmployee(Employee employee) {
		Employee existingEmployee = getEmployee(employee.getId());
		existingEmployee.setRoles(employee.getRoles());

		return repository.save(existingEmployee);
	}
}
