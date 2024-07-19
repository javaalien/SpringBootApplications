package com.alien.validator;

import com.alien.validator.constraint.ValidEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmailAddressForCustomValidator {

	@NotBlank(message = "Email address must not be blank")
	@ValidEmail(message = "Invalid email address format")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
