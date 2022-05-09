package com.clients.restapi.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.restapi.models.dao.IInvoiceDAO;
import com.clients.restapi.models.entities.Invoice;

@Service
public class IInvoiceServiceImpl implements IInvoiceService {
	
	@Autowired
	private IInvoiceDAO invoiceDAO;

	@Override
	public Invoice findInvoiceById(Long id) {
		return this.invoiceDAO.findById(id).orElse(null);
	}

	@Override
	public Invoice saveInvoice(Invoice invoice) {
		return this.invoiceDAO.save(invoice);

	}

	@Override
	public void deleteInvoiceById(Long id) {
		this.invoiceDAO.deleteById(id);
	}

}
