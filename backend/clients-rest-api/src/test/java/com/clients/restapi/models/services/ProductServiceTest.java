package com.clients.restapi.models.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.clients.restapi.models.dao.IProductDAO;
import com.clients.restapi.models.entities.Product;

class ProductServiceTest {
	
	@Mock
	private IProductDAO productDAO;
	
	@InjectMocks
	private ProductServiceImpl productService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findByName() {
		Product product1 = new Product(1L, "Sony", 12.50, new Date());
		
		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		
		//When
		
		when(this.productDAO.findByName("Sony")).thenReturn(products);
		
		// Verify
		List<Product> productList = this.productService.findByName("Sony");
		Assertions.assertEquals(1, productList.size());
		
		Product p = productList.get(0);
		
		Assertions.assertEquals(1, p.getId());
		Assertions.assertEquals("Sony", p.getName());
		Assertions.assertEquals(12.50, p.getPrice());
		
		verify(this.productDAO,times(1)).findByName("Sony");
	}

}
