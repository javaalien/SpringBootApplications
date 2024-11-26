package com.alien.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.orders.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
