package com.alien.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.model.Order;
import com.alien.repository.OrderRepository;

import jakarta.annotation.PostConstruct;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@PostConstruct
	public void initDataToDB() {

		List<Order> orders = IntStream.rangeClosed(1, 20)
				.mapToObj(i -> new Order("order" + i, new Random().nextInt(5), new Random().nextDouble()))
				.collect(Collectors.toList());

		orderRepository.saveAll(orders);

	}

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

}
