package com.clients.restapi.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.clients.restapi.models.entities.Client;

public interface IClientDAO extends CrudRepository<Client, Long> {

}
