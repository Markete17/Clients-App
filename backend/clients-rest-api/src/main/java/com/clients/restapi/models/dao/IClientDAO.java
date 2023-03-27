package com.clients.restapi.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.clients.restapi.models.entities.Client;

public interface IClientDAO extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

}
