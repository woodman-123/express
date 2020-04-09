package com.heima.express.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppResult;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.client.BcStaff;
import com.heima.express.manage.client.StaffService;


@Controller
public class StaffAction {
	
	@Autowired
	private StaffService staffService;
	
	
	@RequestMapping(value="/staff/findstaffbyinput",method = RequestMethod.POST)
	@ResponseBody
	public DataGridResult<BcStaff> findStaffByInput(Integer id, String name, String telephone, Integer haspda, Integer deltag,
			String standard, String station,Integer page,Integer rows){
		
		
			return staffService.findStaffByInput(id, name, telephone, haspda, deltag, standard, station,page,rows);	
		
	}
	
	
	
	@RequestMapping(value="/staff/addstaff",method = RequestMethod.POST)
	@ResponseBody
	public AppResult findStaffByInput(BcStaff staff){
		
		System.out.println(staff);
		
		staffService.addStaff(staff);
		
		
		AppResult result=new AppResult(200,"添加成功",null);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/staff/delstaffsbycheck",method = RequestMethod.POST)
	@ResponseBody
	public AppResult delStaffsByCheck(int[] ids){
		
		staffService.delStaffsByCheck(ids);
		
		
		AppResult result=new AppResult(200,"刪除成功",null);
		return result;
	}
	
	
	
}
