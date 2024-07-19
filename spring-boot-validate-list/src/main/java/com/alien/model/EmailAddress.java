package com.alien.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailAddress {

	@NotBlank(message = "Email address must not be blank")
	@Email(message = "Invalid email address format")
	private String email;
}