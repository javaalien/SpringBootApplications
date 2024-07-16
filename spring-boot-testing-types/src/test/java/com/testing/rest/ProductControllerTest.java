package com.testing.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.testing.domain.ProductService;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@MockBean
	private ProductService productService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnActiveProducts() throws Exception {
		BDDMockito.given(productService.getAllProducts()).willReturn(List.of());

		mockMvc.perform(get("/api/products")).andExpect(status().isOk());
	}
}
