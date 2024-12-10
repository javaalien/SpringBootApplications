package com.alien.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYEES_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(EmployeePKId.class)
public class Employee {

	@EmbeddedId
	private EmployeePKId employeePKId;
	private String name;
	private String email;
	private String phone;

}
