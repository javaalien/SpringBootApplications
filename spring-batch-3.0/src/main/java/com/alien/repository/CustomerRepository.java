package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
