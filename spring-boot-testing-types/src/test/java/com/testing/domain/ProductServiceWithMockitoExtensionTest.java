package com.testing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceWithMockitoExtensionTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void shouldReturnOnlyActiveProducts() {

		Product p1 = new Product(1L, "p-name1", "p-desc1", BigDecimal.TEN, false);
		Product p2 = new Product(2L, "p-name2", "p-desc2", BigDecimal.TEN, true);
		BDDMockito.given(productRepository.findAll()).willReturn(List.of(p1, p2));

		List<Product> products = productService.getAllProducts();

		// Assert
		assertThat(products).hasSize(1);
		assertThat(products.get(0).getId()).isEqualTo(1L);

	}
}
