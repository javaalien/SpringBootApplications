package com.alien.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Optional<Employee> findByUserName(String username);
}
