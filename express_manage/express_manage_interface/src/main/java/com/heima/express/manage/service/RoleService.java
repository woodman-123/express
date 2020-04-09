package com.heima.express.manage.service;

import java.util.List;

import javax.jws.WebService;

import com.heima.express.common.AppResult;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.Role;


@WebService
public interface RoleService {

	
	DataGridResult<Role> findAllRole(Integer pageNo,Integer pageSize);
	DataGridResult<Role> findRolesByInput(String rolename,Integer pageNo,Integer pageSize);
	int delRolesByRoleids(int[] rolesid);
	AppResult addRole(Role role);
	AppResult updRole(Role role);
	
	
	List<Role> findAllRoles();
	Role findRoleById(Integer roleid);
	
	
	List<String> findAuthorityByRoleid(Integer roleid);
	
	
	List<String> findAuthorityTextByRoleid(Integer roleid);
	             
}
