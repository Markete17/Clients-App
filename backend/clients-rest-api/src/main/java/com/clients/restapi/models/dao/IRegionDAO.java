package com.clients.restapi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clients.restapi.models.entities.Region;

public interface IRegionDAO extends JpaRepository<Region, Long> {
	
	@Query("from Region")
	public List<Region> findAllRegions();

}
