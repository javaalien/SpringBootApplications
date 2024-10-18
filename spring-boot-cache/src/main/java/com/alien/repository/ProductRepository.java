package com.alien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alien.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

	List<Product> findByProductType(String productType);

	List<Product> findByPriceAndProductType(double price, String productType);

	@Query("from Product p where p.price= ?1 ")
	List<Product> getProductByPrice(double price);

	List<Product> findByPriceIn(List<Double> prices);

	List<Product> findByPriceBetween(double minPrice, double maxPrice);

	List<Product> findByPriceGreaterThan(double price);

	List<Product> findByPriceLessThan(double price);

	List<Product> findByNameIgnoreCaseContaining(String name);

}
