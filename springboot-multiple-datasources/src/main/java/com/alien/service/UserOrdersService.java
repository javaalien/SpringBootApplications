package com.alien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alien.orders.entities.Order;
import com.alien.orders.repositories.OrderRepository;
import com.alien.security.entities.User;
import com.alien.security.repositories.UserRepository;

@Service
public class UserOrdersService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(transactionManager = "securityTransactionManager")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional(transactionManager = "ordersTransactionManager")
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

}
