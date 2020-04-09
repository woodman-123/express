package com.heima.express.manage.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppResult;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.client.Role;
import com.heima.express.manage.client.RoleService;


@Controller
public class RoleAction {
	
	@Autowired
	private RoleService roleService;
	
	/*
	@RequestMapping(value="/role/findallroles",method=RequestMethod.POST)
	@ResponseBody
	public DataGridResult<Role> findAllRoles(Integer page,Integer rows) {
		
		DataGridResult<Role> model=roleService.findAllRole(page, rows);
		return model;
	}*/
	
	
	
	//去xml中开启shiro框架注解支持,自动跟放在info中的权利集合进行比对，没有的话报授权异常
	//UnauthorizedException: Subject does not have permission [角色管理:查询权限树],去异常处理类添加这个异常
	@RequiresPermissions("角色管理:查询")//（要执行这个方法要求允许该权利）
	@RequestMapping(value="/role/findrolesbyinput",method=RequestMethod.POST)
	@ResponseBody//page rows一定要对上啊
	public DataGridResult<Role> finrolesbyinput(String rolename,Integer page,Integer rows) {
		
		DataGridResult<Role> model=roleService.findRolesByInput(rolename, page, rows);
		
		return model;
	}
	
	@RequiresPermissions("角色管理:删除")
	@RequestMapping(value="/role/delrolesbycheck",method=RequestMethod.POST)
	@ResponseBody
	//页面传上来的字符串"1,3,4",action用数组接收，会自动分隔成数组
	public AppResult delrolesbycheck(int[] rolesid) {
		/*
		for (int i = 0; i < rolesid.length; i++) {
			int j = rolesid[i];
			System.out.println(j);
		}*/
		
		roleService.delRolesByRoleids(rolesid);
		
		AppResult result=new AppResult(200,"删除成功",null);
		return result;
		
	}
	
	
	@RequiresPermissions("角色管理:添加/修改")
	@RequestMapping(value="/right/savaorupdaterole",method=RequestMethod.POST)
	@ResponseBody
	//页面传上来的字符串"1,3,4",action用数组接收，会自动分隔成数组
	public AppResult savaOrUpdateRole(Role role) {
		
		AppResult result=null;
		if(role.getRoleid()==null) {
			result=roleService.addRole(role);
		}else {
			result=roleService.updRole(role);
		}
		
		
		return result;
		
	}

}
