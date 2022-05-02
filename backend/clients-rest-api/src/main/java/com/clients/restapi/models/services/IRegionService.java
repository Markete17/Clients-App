package com.clients.restapi.models.services;

import java.util.List;

import com.clients.restapi.models.entities.Region;

public interface IRegionService {
	
	public List<Region> findAllRegions();

}
