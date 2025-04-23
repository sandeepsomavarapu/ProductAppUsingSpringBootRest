package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.model.Product;
import com.cts.repository.ProductRepository;
import com.cts.service.ProductServiceImpl;

@SpringBootTest
class ProductCrudOperationsUsingSpringJpaApplicationTests {
	@Mock
	ProductRepository repository;

	@InjectMocks
	ProductServiceImpl service;

	@Test
	void saveProductTest() {
		Product product = new Product("samsung", 23000, "electronics", 20);
		Mockito.when(repository.save(product)).thenReturn(product);

		String response = service.saveProduct(product);
		//assertEquals("Product Saved Successfully", response);

	}

}
