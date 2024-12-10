package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Employee;
import com.alien.entity.EmployeePKId;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeePKId> {
}
