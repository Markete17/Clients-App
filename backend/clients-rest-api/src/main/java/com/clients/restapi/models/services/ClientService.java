package com.clients.restapi.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clients.restapi.models.dao.IClientDAO;
import com.clients.restapi.models.entities.Client;

@Service
public class ClientService implements IClientService {

	@Autowired
	private IClientDAO clientDAO;

	@Override
	@Transactional(readOnly = true) //No hace falta, find all ya es transactional
	public List<Client> findAll() {
		
		return (List<Client>) clientDAO.findAll();
	}	

}
