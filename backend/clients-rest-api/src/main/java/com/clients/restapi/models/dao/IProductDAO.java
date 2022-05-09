package com.clients.restapi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clients.restapi.models.entities.Product;

public interface IProductDAO extends JpaRepository<Product, Long> {
	
	@Query("select p from Product p where p.name like %?1%")
	public List<Product> findByName(String name);
	
	public List<Product> findByNameContainingIgnoreCase(String name);
	
	public List<Product> findByNameStartingWithIgnoreCase(String name);

}
