package com.clients.restapi.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clients.restapi.models.entities.Client;

public interface IClientService {
	
	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public Client save(Client client);
	
	public void delete(Long id);
	
	public Client findById(Long id);
	
}
