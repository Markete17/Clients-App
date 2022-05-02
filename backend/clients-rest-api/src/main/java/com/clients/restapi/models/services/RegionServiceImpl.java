package com.clients.restapi.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.restapi.models.dao.IRegionDAO;
import com.clients.restapi.models.entities.Region;

@Service
public class RegionServiceImpl implements IRegionService {
	
	@Autowired
	private IRegionDAO regionDAO;

	@Override
	public List<Region> findAllRegions() {
		return this.regionDAO.findAllRegions();
	}

}
