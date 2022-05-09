package com.clients.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clients.restapi.models.entities.Region;
import com.clients.restapi.models.services.IRegionService;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8089"}) //Con el Cross Origins se da permiso al servidor frontend a recoger los datos
@RestController
@RequestMapping("/api")
public class RegionRestController {
	
	@Autowired
	private IRegionService regionService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/regions")
	public ResponseEntity<List<Region>> findAllRegions(){
		return new ResponseEntity<>(regionService.findAllRegions(),HttpStatus.OK);
		
	}

}
