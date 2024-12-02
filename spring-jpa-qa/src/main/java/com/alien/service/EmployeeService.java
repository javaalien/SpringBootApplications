package com.alien.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alien.entity.Employee;
import com.alien.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee getEmployee(int id) {
		Optional<Employee> employee = repository.findById(id);

		return employee.orElseThrow(() -> new NoSuchElementException("Employee with ID " + id + " not found"));
	}

	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	public Employee updateEmployee(int id, Employee updatedEmployee) {
		Employee existingEmployee = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee with ID " + id + " not found"));

		existingEmployee.setName(updatedEmployee.getName());
		existingEmployee.setDeptName(updatedEmployee.getDeptName());
		existingEmployee.setSalary(updatedEmployee.getSalary());
		existingEmployee.setEmailId(updatedEmployee.getEmailId());
		existingEmployee.setAge(updatedEmployee.getAge());
		return repository.save(existingEmployee);

	}

	public void deleteEmployee(int id) {
		repository.deleteById(id);
	}

	public List<Employee> filterBySalary(double salary) {

		List<Employee> employeeWithSQL = repository.findEmployeeWithSQL(salary);
		return employeeWithSQL;
	}

	public List<Employee> findEmployeesByAgeRange(int minAge, int maxAge) {

		List<Employee> byAgeBetween = repository.findByAgeBetween(minAge, maxAge);

		return byAgeBetween;
	}

	public Optional<Double> getAverageSalary() {

		Optional<Double> avgSalary = repository.avgSalary();

		return avgSalary;
	}

	public List<Employee> findEmployeesWithSorting(String field) {
		return repository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public Page<Employee> findEmployeesWithPagination(int offset, int pageSize) {
		Page<Employee> employees = repository.findAll(PageRequest.of(offset, pageSize));
		return employees;
	}

	public Page<Employee> findEmployeesWithPaginationAndSorting(int offset, int pageSize, String field) {

		Page<Employee> employees = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return employees;
	}
}
