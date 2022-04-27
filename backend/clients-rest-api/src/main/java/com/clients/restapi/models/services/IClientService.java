package com.clients.restapi.models.services;

import java.util.List;

import com.clients.restapi.models.entities.Client;

public interface IClientService {
	
	public List<Client> findAll();

}
