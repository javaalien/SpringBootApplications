package com.data.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dto.InputRequest;
import com.data.model.Employee;
import com.data.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public String saveEmployee(InputRequest<Employee> request) {

		String currentUser = request.getLoggedInUser();

		request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());

		Employee employee = request.getEmployee();

		employee.setCreatedBy(currentUser);
		repository.save(employee);

		return "Employee saved successfully...";

	}

	public String updateEmployee(int id, double salary, InputRequest<Employee> request) {

		Employee employee = repository.findById(id).get();

		if (employee != null) {
			employee.setSalary(salary);
			employee.setModifiedBy(request.getLoggedInUser());
			repository.saveAndFlush(employee);
		} else {
			throw new RuntimeException("Employee not found with id : " + id);
		}

		return "Employee Updated successfully ";
	}
}
