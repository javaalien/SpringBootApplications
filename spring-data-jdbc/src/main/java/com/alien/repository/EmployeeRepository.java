package com.alien.repository;

import java.util.List;

import com.alien.entity.Employee;

public interface EmployeeRepository {

	int save(Employee employee);

	List<Employee> findAll();

	List<Employee> getALlEmployees();

	Employee findById(int id);

	String getNameById(int id);

	List<Employee> findByNameAndDept(String name, String dept);

	int update(Employee employee);

	int delete(int id);

}
