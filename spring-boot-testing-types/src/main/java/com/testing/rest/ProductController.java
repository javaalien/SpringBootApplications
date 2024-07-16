package com.testing.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.domain.Product;
import com.testing.domain.ProductService;

@RestController
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
		System.out.println("--------ProductController()---------");
	}

	@GetMapping("/api/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

}
