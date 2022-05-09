package com.clients.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clients.restapi.models.entities.Product;
import com.clients.restapi.models.services.IProductService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8089"})
@RequestMapping("/api")
public class ProductRestController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/products/filter/{name}")
	public ResponseEntity<List<Product>> filterProducts(@PathVariable String name){
		List<Product> products = this.productService.findByName(name);
		if(!products.isEmpty() && products!=null) {
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
	}

}
