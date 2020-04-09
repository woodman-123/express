package com.heima.express.manage.service;

import java.util.List;

import javax.jws.WebService;

import com.heima.express.manage.entity.BcRegion;


@WebService
public interface RegionService {

	int addRegions(List<BcRegion> regions);

	List<BcRegion> findAll();

	List<BcRegion> findRegionByKey(String q);
	
	

}
