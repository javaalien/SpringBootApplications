package com.infy.service;

import org.springframework.stereotype.Service;

@Service
public class TaxService {

	private static final double TAX_PERCENTAGE = 0.10; 

	public Double calculateTax(Double salary) {
		return salary * TAX_PERCENTAGE;
	}
}
