package com.alien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue
	private long patientId;
	private String name;
	private String email;
	private String gender;
	private int age;
	private String address;

}
