package com.clients.restapi.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clients.restapi.models.entities.Invoice;

public interface IInvoiceDAO extends JpaRepository<Invoice, Long> {

}
