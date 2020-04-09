package com.heima.express.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyAction {
	
	
	
	
	
	@RequestMapping(value="/test",method=RequestMethod.POST)
	@ResponseBody
	public String myMethod() {
		return "我是胖虎我怕谁heheda";
	}

}
