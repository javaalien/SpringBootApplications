package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.dto.InputRequest;
import com.data.model.Employee;
import com.data.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping("/addEmployee")
	public String saveEmployee(@RequestBody InputRequest<Employee> request) {
		return service.saveEmployee(request);
	}

	@PutMapping("/updateEmployee/{id}/{salary}")
	public String updateEmployeeSalary(@PathVariable int id, @PathVariable double salary,
			@RequestBody InputRequest<Employee> request) {

		return service.updateEmployee(id, salary, request);

	}
}
