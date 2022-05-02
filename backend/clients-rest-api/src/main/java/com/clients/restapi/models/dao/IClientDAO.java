package com.clients.restapi.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clients.restapi.models.entities.Client;

public interface IClientDAO extends JpaRepository<Client, Long> {

}
