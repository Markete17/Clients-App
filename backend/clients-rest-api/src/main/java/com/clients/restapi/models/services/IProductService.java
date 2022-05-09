package com.clients.restapi.models.services;

import java.util.List;

import com.clients.restapi.models.entities.Product;

public interface IProductService {
	
	public List<Product> findByName(String name);
}
