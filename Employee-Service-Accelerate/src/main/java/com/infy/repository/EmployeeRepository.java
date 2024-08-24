package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByNameContainingIgnoreCase(String name);

	List<Employee> findBySalaryGreaterThanOrderByName(Double salary);
}
