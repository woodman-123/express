package com.heima.express.manage.controller;



import java.util.ArrayList;
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
public class DecidedZoneAction {
	
	
	@Autowired//spring根据接口自动注入实现类
	private CustomerService customerService;
	

	@RequestMapping(value = "/crm/findCustomersNoAss", method = RequestMethod.POST)
	@ResponseBody
	public List<TCustomer> findCustomersNoAss() {
		return customerService.findCustomersNoAss();
	}

	@RequestMapping(value = "/crm/findCustomersByDzoneid", method = RequestMethod.POST)
	@ResponseBody
	public List<TCustomer> findCustomersByDzoneid(String dzid) {
		return customerService.findCustomersByDzoneid(dzid);
	}

	@RequestMapping(value = "/crm/addAssCustomers", method = RequestMethod.POST)
	@ResponseBody
	public AppResult addAssCustomers(String dzid,int[] customerIds) {
		
		List<Integer> cids=new ArrayList<>();
		for(int customerId:customerIds) {
			cids.add(customerId);
		}
		
		
		
		customerService.addAssCustomers(dzid, cids);
		return new AppResult(200,"添加成功",null);
	}

}
