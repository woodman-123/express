package com.heima.express.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heima.express.manage.entity.BcSubarea;
import com.heima.express.manage.mapper.BcSubareaMapper;
import com.heima.express.manage.service.SubareaService;

@Service
public class SubareaServiceImpl implements SubareaService{

	@Autowired
	private BcSubareaMapper subareaMapper;
	
	@Override
	public List<BcSubarea> findSubareaByAddresskey(List<String> keys) {
		return subareaMapper.selectSubareaByAddresskey(keys);
	}

}
