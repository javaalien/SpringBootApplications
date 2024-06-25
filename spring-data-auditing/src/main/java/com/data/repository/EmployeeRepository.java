package com.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
