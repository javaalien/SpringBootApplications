package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.annotation.TrackExecutionTime;
import com.alien.entity.Product;
import com.alien.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	@TrackExecutionTime
	public Product addProduct(@RequestBody Product product) {

		if (product.getPrice() <= 100) {
			throw new RuntimeException("Product price shouldn't be less than 100");
		}
		Product saveProduct = service.saveProduct(product);
		return saveProduct;
	}

	@GetMapping
	@TrackExecutionTime
	public List<Product> getProducts() {
		return service.getProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) throws Exception {
		return service.getProductById(id);
	}

	@PutMapping("/{id}")
	// @LogRequestAndResponse
	public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
		return service.updateProduct(id, productRequest);
	}

	@DeleteMapping("/{id}")
	@TrackExecutionTime
	public long deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
}
