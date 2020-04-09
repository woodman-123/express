package com.heima.express.manage.service;

import java.util.List;

import javax.jws.WebService;

import com.heima.express.manage.entity.BcSubarea;

@WebService
public interface SubareaService {

	List<BcSubarea> findSubareaByAddresskey(List<String> keys);
}
