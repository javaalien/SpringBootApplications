package com.exception.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exception.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}