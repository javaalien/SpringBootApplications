package com.exception.exHandler;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(Long customerId) {
		super("Customer with id=" + customerId + " not found");
	}
}
