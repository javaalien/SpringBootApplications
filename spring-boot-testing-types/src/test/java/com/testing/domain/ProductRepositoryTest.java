package com.testing.domain;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.testing.domain.Product;
import com.testing.domain.ProductRepository;

import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void shouldGetAllActiveProducts() {

		entityManager.persist(new Product(null, "pname1", "pdescr1", BigDecimal.TEN, false));
		entityManager.persist(new Product(null, "pname2", "pdescr2", BigDecimal.TEN, true));

		List<Product> products = productRepository.findAllActiveProducts();

		assertThat(products).hasSize(1);
		assertThat(products.get(0).getName()).isEqualTo("pname1");

	}
}
