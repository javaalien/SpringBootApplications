package com.alien.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	private String brand;

	@Min(value = 10, message = "Price must be greater than 10")
	private Integer price;
}