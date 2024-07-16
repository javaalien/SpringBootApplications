package com.caching.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caching.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
