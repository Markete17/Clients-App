package com.clients.restapi.models.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.clients.restapi.models.dao.IRegionDAO;
import com.clients.restapi.models.entities.Region;

class RegionServiceImplTest {
	
	@Mock
	private IRegionDAO regionDAO;
	
	@InjectMocks
	private RegionServiceImpl regionService;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAllRegions() {
		
		List<Region> regions = new ArrayList<Region>();
		Region r1 = new Region(1L,"Spain");
		Region r2 = new Region(2L,"Germany");
		Region r3 = new Region(3L,"England");
		
		regions.add(r1);regions.add(r2);regions.add(r3);
		
		//When
		when(regionDAO.findAllRegions()).thenReturn(regions);
		
		//verify
		List<Region> regionList = this.regionService.findAllRegions();
		
		assertEquals(3, regionList.size());
		verify(regionDAO,times(1)).findAllRegions(); //Comprobar que el service llama al DAO 1 sola vez

	}

}
