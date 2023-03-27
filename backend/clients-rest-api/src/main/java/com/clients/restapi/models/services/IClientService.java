package com.clients.restapi.models.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.clients.restapi.models.entities.Client;
import com.clients.restapi.models.entities.ClientFilter;

public interface IClientService {
	
	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public Client save(Client client);
	
	public void delete(Long id);
	
	public Client findById(Long id);

	public Page<Client> findAll(Specification<Client> specification, Pageable pageable);
	
}
