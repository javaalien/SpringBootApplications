package com.alien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alien.entity.Product;
import com.alien.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	@Cacheable
	public List<Product> getProducts() {
		log.info("ProductService::getProducts() connecting to Database");
		return repository.findAll();
	}

	@Cacheable(key = "#id")
	public Product getProductById(int id) {
		log.info("ProductService::getProductById() connecting to Database");
		return repository.findById(id).get();
	}

	public Product getProductByName(String name) {
		return repository.findByName(name);
	}

	public List<Product> getProductsByType(String productType) {
		return repository.findByProductType(productType);
	}

	public List<Product> getProductWithPriceAndType(double price, String productType) {
		return repository.findByPriceAndProductType(price, productType);
	}

	public List<Product> getProductsByPrice(double price) {
		return repository.getProductByPrice(price);
	}

	@CachePut(key = "#id")
	public Product updateProduct(int id, Product productRequest) {
		Product existingProduct = repository.findById(id).get();

		existingProduct.setName(productRequest.getName());
		existingProduct.setDescription(productRequest.getDescription());
		existingProduct.setPrice(productRequest.getPrice());
		existingProduct.setProductType(existingProduct.getProductType());
		return repository.save(existingProduct);
	}

	@CacheEvict(key = "#id")
	public long deleteProduct(int id) {
		repository.deleteById(id);
		return repository.count();
	}

	public List<Product> getProductsByMultiplePriceValue(List<Double> prices) {
		return repository.findByPriceIn(prices);
	}

	public List<Product> getProductsWithinPriceRange(double minPrice, double maxPrice) {
		return repository.findByPriceBetween(minPrice, maxPrice);
	}

	public List<Product> getProductsWithHigherPrice(double price) {
		return repository.findByPriceGreaterThan(price);
	}

	public List<Product> getProductsWithLessPrice(double price) {
		return repository.findByPriceLessThan(price);
	}

	public List<Product> getProductsWithLike(String name) {
		return repository.findByNameIgnoreCaseContaining(name);
	}

	public List<Product> getProductsWithSorting(String fieldName) {
		return repository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
	}

	public Page<Product> getProductsWithPageResponse(int offset, int limit) {
		return repository.findAll(PageRequest.of(offset, limit));
	}

	public Page<Product> getProductsWithSortingAndPagination(String fieldName, int offset, int limit) {
		return repository.findAll(PageRequest.of(offset, limit).withSort(Sort.by(fieldName)));
	}

}
