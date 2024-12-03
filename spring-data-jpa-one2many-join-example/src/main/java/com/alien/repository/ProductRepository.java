package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
