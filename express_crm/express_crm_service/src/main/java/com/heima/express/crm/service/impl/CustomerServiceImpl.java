package com.heima.express.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heima.express.common.AppException;
import com.heima.express.crm.entity.TCustomer;
import com.heima.express.crm.entity.TCustomerExample;
import com.heima.express.crm.mapper.TCustomerMapper;

import express.crm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private TCustomerMapper customerMapper;

	public List<TCustomer> findCustomersNoAss() {
		TCustomerExample example = new TCustomerExample();
		example.createCriteria().andDecidedzoneIdIsNull();
		return customerMapper.selectByExample(example);
	}

	public List<TCustomer> findCustomersByDzoneid(String dzid) {

		TCustomerExample example = new TCustomerExample();
		example.createCriteria().andDecidedzoneIdEqualTo(dzid);
		return customerMapper.selectByExample(example);
	}

	
	
	
	
	//先把该承包区关联的客户的承包区id清空，再重新为该承包区添加页面页面传上来的多个客户添加该承包区id
	public void addAssCustomers(String dzid,int[] cids) {
		
		
		//清空这些客户的承包区id
		
		TCustomerExample example=new TCustomerExample();
		example.createCriteria().andDecidedzoneIdEqualTo(dzid);
		List<TCustomer> customers=customerMapper.selectByExample(example);
		
		for(TCustomer customer: customers) {
			customer.setDecidedzoneId(null);
			customerMapper.updateByPrimaryKeySelective(customer);
		}
		
		
		
		for (int cid : cids) {
			TCustomer customer = customerMapper.selectByPrimaryKey(cid);
			customer.setDecidedzoneId(dzid);
			customerMapper.updateByPrimaryKeySelective(customer);//设置好id后更新!!!!!!!!!
		}

	}

	@Override
	public void addCustomerForRegister(TCustomer customer) {
		
		TCustomerExample example=new TCustomerExample();
		example.createCriteria().andTelephoneEqualTo(customer.getTelephone());
		List<TCustomer> list=customerMapper.selectByExample(example);
		
		if(list==null||list.size()==0) {
			customerMapper.insertSelective(customer);
		}else {
			throw new AppException(201,"账号已经被注册");
		}
	}

	@Override
	public TCustomer findCustomerByTel(String tel) {
	
		TCustomer customer=null;
		
		TCustomerExample example=new TCustomerExample();
		example.createCriteria().andTelephoneEqualTo(tel);
		List<TCustomer> list=customerMapper.selectByExample(example);
		
		if(list!=null&&list.size()!=0) {
			customer=list.get(0);
		}

		return customer;	
	}

}
