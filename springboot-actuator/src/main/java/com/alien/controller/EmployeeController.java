package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.Employee;
import com.alien.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() throws JsonProcessingException {
		List<Employee> employees = service.getEmployees();

		log.info("EmployeeController:getAllEmployees fetch all employees {}",
				new ObjectMapper().writeValueAsString(employees));

		return employees;
	}

}
