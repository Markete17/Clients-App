package com.clients.restapi.models.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.clients.restapi.models.dao.IClientDAO;
import com.clients.restapi.models.entities.Client;

class ClientServiceImplTest {
	
	@Mock
	private IClientDAO clientDAO;
	
	@InjectMocks
	private ClientServiceImpl clientService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Order(1)
	void findAllClients() {
		List<Client> clients = this.createClientList(1);
		
		//when
		
		when(this.clientDAO.findAll()).thenReturn(clients);
		
		//verify
		
		List<Client> clientsList = this.clientService.findAll();
		
		Assertions.assertEquals(1, clientsList.size());
		
		Client c = clientsList.get(0);
		
		Assertions.assertEquals(1, c.getId());
		Assertions.assertEquals("Jose", c.getFirstName());
		Assertions.assertEquals("Torralvo", c.getLastName());
		Assertions.assertEquals("jose@gmail.com", c.getEmail());
		Assertions.assertEquals("photo.png", c.getPhoto());
		
		verify(this.clientDAO,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void findAllClientsPage() {
		
		Page<Client> clients = this.createClientListPage(5);
		
		//when
		
		Integer page = 3;
		
		Integer maxRegistersInPage = 10;
		
		Pageable pageable = (Pageable) PageRequest.of(page, maxRegistersInPage);
		
		when(this.clientDAO.findAll(pageable)).thenReturn(clients);
		
		//verify
		
		Page<Client> clientsList = this.clientService.findAll(pageable);
		
		Assertions.assertEquals(5, clientsList.getContent().size());
		
		verify(this.clientDAO,times(1)).findAll(pageable);
	}
	
	@Test
	@Order(3)
	void createClient() {
		Client client = new Client(1L, "Jose", "Torralvo", "jose@gmail.com",new Date(),"photo.png",null,null);
		
		//when
		
		when(this.clientDAO.save(client)).thenReturn(client);
		
		Client c = this.clientService.save(client);
		
		//verify
		
		Assertions.assertEquals("Jose", c.getFirstName());
		
		verify(this.clientDAO,times(1)).save(client);
	}
	
	@Test
	@Order(4)
	void deleteClient() {
		Optional<Client> client = Optional.ofNullable(new Client(1L, "Jose", "Torralvo", "jose@gmail.com",new Date(),"photo.png",null,null));
		
		//when
		
		when(this.clientDAO.findById(1L)).thenReturn(client);
		
		this.clientService.delete(1L);
		
		//verify
		verify(this.clientDAO,times(1)).deleteById(1L);
	}
	
	public List<Client> createClientList(Integer count){
		
		List<Client> clients = new ArrayList<Client>();
		
		for (long i=0;i<count;i++) {
			Client client = new Client(i+1, "Jose", "Torralvo", "jose@gmail.com",new Date(),"photo.png",null,null);
			clients.add(client);
		}
		
		return clients;
	}
	
	public Page<Client> createClientListPage(Integer count){
		
		List<Client> clients = new ArrayList<Client>();
		
		for (long i=0;i<count;i++) {
			Client client = new Client(i+1, "Jose", "Torralvo", "jose@gmail.com",new Date(),"photo.png",null,null);
			clients.add(client);

		}
		
		return new PageImpl<>(clients);

	}


}
