package com.clients.restapi.models.services;

import com.clients.restapi.models.entities.Invoice;

public interface IInvoiceService {
	
	public Invoice findInvoiceById(Long id);
	
	public Invoice saveInvoice(Invoice invoice);
	
	public void deleteInvoiceById(Long id);

}
