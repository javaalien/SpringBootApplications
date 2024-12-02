package com.alien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.common.CustomerOrderDTO;
import com.alien.common.OrderRequest;
import com.alien.entity.Customer;
import com.alien.repository.CustomerRepository;
import com.alien.repository.OrderRepository;

@Service
public class OrderFulfillmentService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	public Customer createOrder(OrderRequest orderRequest) {
		Customer customer = (Customer) orderRequest.getCustomer();
		customer.getOrders().forEach(c -> c.setCustomer(customer));

		return customerRepository.save(customer);
	}

	public List<Object[]> findCustomerOrderCount() {
		return customerRepository.findCustomerOrderCount();
	}

	public List<CustomerOrderDTO> findCustomerOrderCountResponse() {
		return customerRepository.findCustomerOrderCountResponse();
	}

}
