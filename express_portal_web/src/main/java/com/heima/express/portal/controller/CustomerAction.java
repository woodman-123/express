package com.heima.express.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppResult;
import com.heima.express.crm.client.CustomerService;
import com.heima.express.crm.client.TCustomer;
import com.heima.express.message.client.MsgService;

@Controller
public class CustomerAction {

	@Autowired
	private MsgService msgService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/customer/getcheckcode")
	@ResponseBody
	public AppResult getcheckcode(String tel,HttpSession session) throws Exception {
		String code=msgService.sendCheckSms(tel);
		System.out.println(code);
		//把返回来的验证码放在session中
		session.setAttribute("checkcode", code);
		//设置session的存活时间（验证码的有效时间）
		session.setMaxInactiveInterval(60);
		
		return new AppResult(200,"发送成功",null);
	}
	

	@RequestMapping(value="/customer/register")
	@ResponseBody
	public AppResult Register(TCustomer customer,String inputcode,HttpSession session) throws Exception {
		
		AppResult result=null;
		
		System.out.println("页面输入的验证码"+inputcode);
		System.out.println(customer);
		
		String code=(String) session.getAttribute("checkcode");
		
		
		if(code==null) {
			result=new AppResult(200,"验证码失效",null);
		}
		
		if(code.equals(inputcode)==false) {
			result=new AppResult(200,"验证码错误",null);
		}else {
			customerService.addCustomerForRegister(customer);
		}
		
		return new AppResult(200,"注册成功",null);
	}
	
	
	
	
	@RequestMapping(value="/customer/findcustomerbytel")
	@ResponseBody
	public TCustomer findCustomerByTel(String tel,HttpSession session) throws Exception {
		
		return customerService.findCustomerByTel(tel);
		
	}
	
	
}
