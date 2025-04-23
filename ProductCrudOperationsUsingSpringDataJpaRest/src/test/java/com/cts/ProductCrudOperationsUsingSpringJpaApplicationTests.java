package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.exceptions.ProductNotFound;
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
		assertEquals("Product Saved Successfully", response);
	}

	@Test
	void updateProductTest() {
		Product product = new Product("samsung", 23000, "electronics", 20);
		product.setProductId(1);

		Mockito.when(repository.save(product)).thenReturn(product);

		Product updatedProduct = service.updateProduct(product);
		assertEquals(product, updatedProduct);
	}

	@Test
	void removeProductTest() {
		int productId = 1;

		Mockito.doNothing().when(repository).deleteById(productId);

		String response = service.removeProduct(productId);
		assertEquals("Product Deleted", response);
	}

	@Test
	void getProductTest() throws ProductNotFound {
		int productId = 1;
		Product product = new Product("samsung", 23000, "electronics", 20);
		product.setProductId	(productId);

		Mockito.when(repository.findById(productId)).thenReturn(Optional.of(product));

		Product foundProduct = service.getProduct(productId);
		assertEquals(product, foundProduct);
	}

	@Test
	void getProductNotFoundTest() {
		int productId = 1;

		Mockito.when(repository.findById(productId)).thenReturn(Optional.empty());

		assertThrows(ProductNotFound.class, () -> {
			service.getProduct(productId);
		});
	}

	@Test
	void getAllProductsTest() {
		List<Product> products = Arrays.asList(new Product("samsung", 23000, "electronics", 20),
				new Product("iphone", 70000, "electronics", 10));

		Mockito.when(repository.findAll()).thenReturn(products);

		List<Product> allProducts = service.getAllProducts();
		assertEquals(products, allProducts);
	}

	@Test
	void getAllProductsBetweenTest() {
		List<Product> products = Arrays.asList(new Product("samsung", 23000, "electronics", 20),
				new Product("iphone", 70000, "electronics", 10));

		Mockito.when(repository.getAllProductsBetween(20000, 80000)).thenReturn(products);

		List<Product> productsBetween = service.getAllProductsBetween(20000, 80000);
		assertEquals(products, productsBetween);
	}

	@Test
	void getAllProductsByCategoryTest() {
		String category = "electronics";
		List<Product> products = Arrays.asList(new Product("samsung", 23000, "electronics", 20),
				new Product("iphone", 70000, "electronics", 10));

		Mockito.when(repository.getAllProductsByCategory(category)).thenReturn(products);

		List<Product> productsByCategory = service.getAllProductsByCategory(category);
		assertEquals(products, productsByCategory);
	}
}
