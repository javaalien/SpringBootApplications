package com.alien.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alien.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Employee.builder().id(rs.getInt("id")).name(rs.getString("name")).dept(rs.getString("dept"))
				.email(rs.getString("email")).doj(rs.getDate("doj")).build();
	}
}
