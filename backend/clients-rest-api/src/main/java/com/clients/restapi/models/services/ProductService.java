package com.clients.restapi.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.restapi.models.dao.IProductDAO;
import com.clients.restapi.models.entities.Product;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDAO productDAO;
	
	@Override
	public List<Product> findByName(String name) {
		return this.productDAO.findByName(name);
	}

}
