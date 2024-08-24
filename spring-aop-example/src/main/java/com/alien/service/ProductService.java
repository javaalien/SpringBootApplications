package com.alien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.annotation.TrackExecutionTime;
import com.alien.entity.Product;
import com.alien.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@TrackExecutionTime
	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	@TrackExecutionTime
	public List<Product> getProducts() {
		return repository.findAll();
	}

	// @LogRequestAndResponse
	public Product getProductById(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("product is not available with id " + id));
	}

	@TrackExecutionTime
	public Product updateProduct(int id, Product productRequest) {
		// get the product from DB by id
		// update with new value getting from request
		Product existingProduct = repository.findById(id).get(); // DB
		existingProduct.setName(productRequest.getName());
		existingProduct.setDescription(productRequest.getDescription());
		existingProduct.setPrice(productRequest.getPrice());
		existingProduct.setProductType(existingProduct.getProductType());
		return repository.save(existingProduct);
	}

	public long deleteProduct(int id) {
		repository.deleteById(id);
		return repository.count();
	}

}
