package com.heima.express.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.heima.express.common.AppException;
import com.heima.express.common.AppResult;
import com.heima.express.manage.entity.BcRegion;
import com.heima.express.manage.entity.BcRegionExample;
import com.heima.express.manage.mapper.BcRegionMapper;
import com.heima.express.manage.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService{

	@Autowired
	private BcRegionMapper regionMapper;

	@Override
	public int addRegions(List<BcRegion> regions) {
		int count=0;
		for(BcRegion region:regions) {
		count=regionMapper.insert(region);//此处的主键已经指定，不能用自动增长主键.insertSelective(record)
		if(count<1) {//要告诉被人哪一条失败，方便查找，批量导入大量数据没说哪一条导入失败的话，很难去找
			throw new AppException(201,"导入失败,失败数据id["+region.getId()+"]");
		}
		}
		return count;
	
	}

	@Override
	public List<BcRegion> findAll() {
		return regionMapper.selectByExample(null);
	}

	@Override
	public List<BcRegion> findRegionByKey(String q) {
		
		BcRegionExample example=new BcRegionExample();
		
		if(StringUtils.isEmpty(q)==false) {
			
			//不能把‘%’去掉哦
			example.or().andProvinceLike("%"+q+"%");
			example.or().andCityLike("%"+q+"%");
			example.or().andDistrictLike("%"+q+"%");
			example.or().andShortcodeLike("%"+q+"%");
			example.or().andCitycodeLike("%"+q+"%");
		}
		
		return regionMapper.selectByExample(example);
	}
}
