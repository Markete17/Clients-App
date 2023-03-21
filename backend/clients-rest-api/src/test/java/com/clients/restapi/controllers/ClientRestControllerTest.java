package com.clients.restapi.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import com.clients.restapi.models.entities.Client;
import com.clients.restapi.models.services.ClientServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class ClientRestControllerTest {
	
	@MockBean
	private ClientServiceImpl clientService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private final String URL = "/api/clients/";

	@Test
	void getClient() throws Exception {
		
		Long id = 1L;
		
		Client client = new Client(id, "Jose", "Torralvo", "jose@gmail.com",new Date(),"photo.png",null,null);
		
		when(this.clientService.findById(id)).thenReturn(client);
		

		mockMvc.perform(get(URL+id))
			.andExpect(status().isUnauthorized())
			.andDo(print());
		
		
	}

}
