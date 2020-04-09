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
	
	
	
	//ȥxml�п���shiro���ע��֧��,�Զ�������info�е�Ȩ�����Ͻ��бȶԣ�û�еĻ�����Ȩ�쳣
	//UnauthorizedException: Subject does not have permission [��ɫ����:��ѯȨ����],ȥ�쳣�������������쳣
	@RequiresPermissions("��ɫ����:��ѯ")//��Ҫִ���������Ҫ�������Ȩ����
	@RequestMapping(value="/role/findrolesbyinput",method=RequestMethod.POST)
	@ResponseBody//page rowsһ��Ҫ���ϰ�
	public DataGridResult<Role> finrolesbyinput(String rolename,Integer page,Integer rows) {
		
		DataGridResult<Role> model=roleService.findRolesByInput(rolename, page, rows);
		
		return model;
	}
	
	@RequiresPermissions("��ɫ����:ɾ��")
	@RequestMapping(value="/role/delrolesbycheck",method=RequestMethod.POST)
	@ResponseBody
	//ҳ�洫�������ַ���"1,3,4",action��������գ����Զ��ָ�������
	public AppResult delrolesbycheck(int[] rolesid) {
		/*
		for (int i = 0; i < rolesid.length; i++) {
			int j = rolesid[i];
			System.out.println(j);
		}*/
		
		roleService.delRolesByRoleids(rolesid);
		
		AppResult result=new AppResult(200,"ɾ���ɹ�",null);
		return result;
		
	}
	
	
	@RequiresPermissions("��ɫ����:���/�޸�")
	@RequestMapping(value="/right/savaorupdaterole",method=RequestMethod.POST)
	@ResponseBody
	//ҳ�洫�������ַ���"1,3,4",action��������գ����Զ��ָ�������
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
