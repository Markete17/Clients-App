package com.clients.restapi.controllers;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clients.restapi.models.entities.Client;
import com.clients.restapi.models.services.IClientService;

@CrossOrigin(origins = {"http://localhost:4200"}) //Con el Cross Origins se da permiso al servidor frontend a recoger los datos
@RestController
@RequestMapping("/api")
public class ClientRestController {
	
	@Autowired
	private IClientService clientService;
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> index(){
		return new ResponseEntity<List<Client>>(clientService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> show(@PathVariable Long id) {
		Client client = clientService.findById(id);
		return client!=null ? new ResponseEntity<Client>(client,HttpStatus.OK) : new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/clients")
	public ResponseEntity<Client> create(@RequestBody Client client) {
		return new ResponseEntity<Client>(clientService.save(client),HttpStatus.CREATED);
	}
	
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id,@RequestBody Client client){
		Client updatedClient = this.clientService.findById(id);
		if(updatedClient==null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		updatedClient.setEmail(client.getEmail());
		updatedClient.setFirstName(client.getFirstName());
		updatedClient.setLastName(client.getLastName());
		return new ResponseEntity<Client>(clientService.save(updatedClient),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Client> delete(@PathVariable Long id){
		Client client = this.clientService.findById(id);
		if(client==null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		this.clientService.delete(id);
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}
}
