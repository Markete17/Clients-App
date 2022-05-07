package com.clients.restapi.controllers;


import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clients.restapi.models.entities.Client;
import com.clients.restapi.models.services.IClientService;
import com.clients.restapi.models.services.IImageService;

@CrossOrigin(origins = {"http://localhost:4200"}) //Con el Cross Origins se da permiso al servidor frontend a recoger los datos
@RestController
@RequestMapping("/api")
public class ClientRestController {
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IImageService imageService;
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> index(){
		return new ResponseEntity<List<Client>>(clientService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/clients/page/{page}")
	public ResponseEntity<Page<Client>> index(@PathVariable Integer page){
		Pageable pageable = (Pageable) PageRequest.of(page, 4);
		return new ResponseEntity<Page<Client>>(clientService.findAll(pageable),HttpStatus.OK);
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
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
	
	@Secured("ROLE_ADMIN")
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
	
	@Secured("ROLE_ADMIN")
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
			updatedClient.setRegion(client.getRegion());
			response.put("message", "The client has updated successfully");
			response.put("client", clientService.save(updatedClient));
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
			
		} catch (DataAccessException e) {
			response.put("message", "Error when update an element into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			Client client = this.clientService.findById(id);
			
			if(client == null) {
				response.put("message", "The client ID: ".concat(id.toString().concat(" does not exist in the database")));
				return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			}
			
			imageService.delete(client.getPhoto());

			this.clientService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (DataAccessException e) {
			response.put("message", "Error when delete an element into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("clients/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		Client client = clientService.findById(id);
		
		if(!file.isEmpty()) {
			String fileName = null;
			try {
				fileName = imageService.copy(file);
			} catch (IOException e) {
				response.put("message", "Error: The image "+fileName+ "has not upload");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String namePreviousPhoto = client.getPhoto();
			
			imageService.delete(namePreviousPhoto);
			
			client.setPhoto(fileName);
			clientService.save(client);
			
			response.put("client", client);
			response.put("message", "The image "+fileName+ " has upload successfully");
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@GetMapping("clients/upload/img/{namePhoto:.+}")
	public ResponseEntity<Resource> showPhotoEntity(@PathVariable String namePhoto){
	
		Resource resource = null;
		
		try {
			resource =  imageService.load(namePhoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders header = new HttpHeaders();
		//Para forzar a descargar la imagen
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
		
	}

}
