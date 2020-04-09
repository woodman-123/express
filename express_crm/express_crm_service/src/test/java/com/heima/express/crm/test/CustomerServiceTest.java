package com.heima.express.crm.test;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import express.crm.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class CustomerServiceTest {
	
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testCustomerService() {
		
		/*
		List<TCustomer> customers=customerService.findCustomersNoAss();
		for(TCustomer customer:customers) {
			System.out.println(customers);
		}
		*/
		/*
		List<TCustomer> customers=customerService.findCustomersByDzoneid("10000");
		for(TCustomer customer:customers) {
			System.out.println(customers);
		}*/
		
		String dzid="369";
		int[] cids= {6,7,8};
		customerService.addAssCustomers(dzid, cids);
	
	}

}
