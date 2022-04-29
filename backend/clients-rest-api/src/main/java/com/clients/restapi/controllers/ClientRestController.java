package com.clients.restapi.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

import net.bytebuddy.asm.Advice.Return;

@CrossOrigin(origins = {"http://localhost:4200"}) //Con el Cross Origins se da permiso al servidor frontend a recoger los datos
@RestController
@RequestMapping("/api")
public class ClientRestController {
	
	@Autowired
	private IClientService clientService;
	private Object err;
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> index(){
		return new ResponseEntity<List<Client>>(clientService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		try {
			client = clientService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(client == null) {
			response.put("message", "The client ID: ".concat(id.toString().concat(" does not exist in the database")));
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(client,HttpStatus.OK);
	}
	
	@PostMapping("/clients")
	public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			response.put("message", "An error was detected in the request body");
			
			/*List<String> errors = new ArrayList<>();
			for(FieldError err:result.getFieldErrors()) {
				errors.add("The field '"+err.getField()+"' "+err.getDefaultMessage());
			}
			
			*/
			//Con streams
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> {return "The field '"+err.getField()+"' "+err.getDefaultMessage();})
					.collect(Collectors.toList());
			response.put("errors",errors);	
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			response.put("message", "The client has created successfully");
			response.put("client", clientService.save(client));
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		} catch (DataAccessException e) {
			
			response.put("message", "Error when insert an element into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/clients/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Client client, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			response.put("message", "An error was detected in the request body");
			
			/*List<String> errors = new ArrayList<>();
			for(FieldError err:result.getFieldErrors()) {
				errors.add("The field '"+err.getField()+"' "+err.getDefaultMessage());
			}
			
			*/
			//Con streams
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> {return "The field '"+err.getField()+"' "+err.getDefaultMessage();})
					.collect(Collectors.toList());
			response.put("errors",errors);	
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			Client updatedClient = this.clientService.findById(id);

			if(updatedClient==null) {
				response.put("message", "The client ID: ".concat(id.toString().concat(" does not exist in the database")));
				return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			}
			updatedClient.setEmail(client.getEmail());
			updatedClient.setFirstName(client.getFirstName());
			updatedClient.setLastName(client.getLastName());
			response.put("message", "The client has updated successfully");
			response.put("client", clientService.save(updatedClient));
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
			
		} catch (DataAccessException e) {
			response.put("message", "Error when update an element into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			Client client = this.clientService.findById(id);
			if(client == null) {
				response.put("message", "The client ID: ".concat(id.toString().concat(" does not exist in the database")));
				return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			}
			this.clientService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (DataAccessException e) {
			response.put("message", "Error when delete an element into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
