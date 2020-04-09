package com.heima.express.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppResult;
import com.heima.express.crm.client.CustomerService;
import com.heima.express.crm.client.TCustomer;

@Controller
public class CustomerAction {
	
	
	@Autowired//spring根据接口自动注入实现类
	private CustomerService customerService;
	

	@RequestMapping(value = "/test1", method = RequestMethod.POST)
	@ResponseBody
	public List<TCustomer> findCustomersNoAss() {
		return customerService.findCustomersNoAss();
	}

	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	@ResponseBody
	public List<TCustomer> findCustomersByDzoneid(String dzid) {
		return customerService.findCustomersByDzoneid("369");
	}

	@RequestMapping(value = "/test3", method = RequestMethod.POST)
	@ResponseBody
	public AppResult addAssCustomers(String dzid, List<Integer> cids) {
		
		customerService.addAssCustomers(dzid, cids);
		return new AppResult(200,"添加成功",null);
	}

}
