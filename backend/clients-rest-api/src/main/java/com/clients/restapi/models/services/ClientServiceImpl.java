package com.clients.restapi.models.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clients.restapi.models.dao.IClientDAO;
import com.clients.restapi.models.entities.Client;
import com.clients.restapi.models.entities.ClientFilter;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDAO clientDAO;

	@Override
	@Transactional(readOnly = true) //No hace falta, find all ya es transactional
	public List<Client> findAll() {
		
		return (List<Client>) clientDAO.findAll();
	}
	
	@Override
	public Page<Client> findAll(Pageable pageable) {
		
		return clientDAO.findAll(pageable);
	}

	@Override
	public Client save(Client client) {
		return clientDAO.save(client);
	}

	@Override
	public void delete(Long id) {
		clientDAO.deleteById(id);
		
	}

	@Override
	public Client findById(Long id) {
		return this.clientDAO.findById(id).orElse(null);
	}

	@Override
	public Page<Client> findAll(Specification<Client> specification, Pageable pageable) {
		return this.clientDAO.findAll(specification, pageable);
	}



}
