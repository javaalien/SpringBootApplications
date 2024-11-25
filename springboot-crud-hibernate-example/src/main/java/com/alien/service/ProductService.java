package com.alien.service;

import java.util.List;

import com.alien.model.Product;

public interface ProductService {

	Product createProduct(Product product);

	Product updateProduct(Product product);

	List<Product> getAllProduct();

	Product getProductById(long productId);

	void deleteProduct(long id);

}
