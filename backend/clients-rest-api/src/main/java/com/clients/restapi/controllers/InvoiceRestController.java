package com.clients.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clients.restapi.models.entities.Invoice;
import com.clients.restapi.models.services.IInvoiceService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8089"})
@RequestMapping("/api")
public class InvoiceRestController {
	
	@Autowired IInvoiceService invoiceService;
	
	@Secured({"ROLE_USER"})
	@GetMapping("invoices/{id}")
	public ResponseEntity<Invoice> show(@PathVariable Long id){
		Invoice invoice = this.invoiceService.findInvoiceById(id);
		if(invoice==null){
			return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoice,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("invoices/{id}")
	public ResponseEntity<Invoice> delete(@PathVariable Long id) {
		this.invoiceService.deleteInvoiceById(id);
		return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/invoices")
	public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
		return new ResponseEntity<Invoice>(invoiceService.saveInvoice(invoice),HttpStatus.CREATED);
	}

}
