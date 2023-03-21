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
		

		mockMvc.perform(get(URL+id).header("Authorization", "Bearer " + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJsYXN0X25hbWUiOiJBZG1pbiBMYXN0TmFtZSIsImlkIjoyLCJleHAiOjE2Nzk0MDA1MzEsInVzZXJuYW1lICI6ImFkbWluIiwiZmlyc3RfbmFtZSI6IkFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiJhZjY3ZWRjOC02MjYzLTQ3MzQtODVmOS01MjhmZGI0YmU5NzEiLCJlbWFpbCI6ImFkbWluQGFkbWluLmVzIiwiY2xpZW50X2lkIjoiYW5ndWxhciJ9.GJzgOIk2ymUPRM2SfrZgTXsG6STyZj5x4MiGAPS-Nf66zrw1rbHKHv_Bsvko50kYI_HaBpd__yQK6EyMm05BVNI536BjNg5cFG-y92O0cAolEsX9M28lCEOSrQef48OQaeoKsrsmJHcOGCUcGPo-QCplYE7sV1RqA6_JN4tJDkhpxmdMXRb9EOXqMYBNaPoHMnqOYfW0F4sWEhhE6azamRl7_tYZZMqARQe0AcBg2ND8JBYGzXnWPM40xhCyBJyjzp5qI5Pv7JRMN76F_IysI_Vmh-qegbq6BlCIVTAXvpaRk3Jdi5tiqtclYAT7eTrcOdpgxO6854s5bsiFkqpJtQ"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.firstName").value("Jose"))
			.andExpect(jsonPath("$.lastName").value("Torralvo"))
			.andExpect(jsonPath("$.email").value("jose@gmail"))
			.andDo(print());
		
		
	}

}
