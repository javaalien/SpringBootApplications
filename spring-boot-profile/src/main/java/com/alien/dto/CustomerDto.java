package com.alien.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private int id;
	private String name;
	private String email;
	private String phone;
	private String dob;// dd/MM/yyyy

}
