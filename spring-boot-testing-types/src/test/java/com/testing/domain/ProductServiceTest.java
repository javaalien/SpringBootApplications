package com.testing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class ProductServiceTest {

	private ProductRepository productRepository;
	private ProductService productService;

	@BeforeEach
	void setUp() {

		productRepository = Mockito.mock(ProductRepository.class);
		productService = new ProductService(productRepository);
	}

	@Test
	void shouldReturnOnlyActiveProducts() {

		Product p1 = new Product(1L, "p-name1", "p-desc1", BigDecimal.TEN, false);
		Product p2 = new Product(2L, "p-name2", "p-desc2", BigDecimal.TEN, true);
		BDDMockito.given(productRepository.findAll()).willReturn(List.of(p1, p2));

		List<Product> products = productService.getAllProducts();

		assertThat(products).hasSize(1);
		assertThat(products.get(0).getId()).isEqualTo(1L);

	}
}
