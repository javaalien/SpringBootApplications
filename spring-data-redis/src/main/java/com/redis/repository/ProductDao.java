package com.redis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redis.entity.Product;

@Repository
public class ProductDao {

	public static final String HASH_KEY = "Product";

	@Autowired
	private RedisTemplate template;

	public Product save(Product product) {
		template.opsForHash().put(HASH_KEY, product.getId(), product);
		return product;
	}

	public List<Product> findAll() {
		return template.opsForHash().values(HASH_KEY);
	}

	private static int count = 1;

	public Product findProductById(int id) {
		System.out.println("Called....." + count++);
		return (Product) template.opsForHash().get(HASH_KEY, id);
	}

	public String deleteProduct(int id) {
		template.opsForHash().delete(HASH_KEY, id);
		return "product removed";

	}
}
