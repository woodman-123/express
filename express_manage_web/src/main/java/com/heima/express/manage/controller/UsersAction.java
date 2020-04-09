package com.heima.express.manage.controller;


import java.util.List;


import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppException;
import com.heima.express.common.AppResult;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.client.Role;
import com.heima.express.manage.client.RoleService;
import com.heima.express.manage.client.Users;
import com.heima.express.manage.client.UsersService;


@Controller
public class UsersAction {

	@Autowired
	private UsersService usersService;
	@Autowired
	private RoleService roleService;
	
	
	
	

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	@ResponseBody
	public AppResult login(String uname, String upassword, HttpSession session) {

		/*
		AppResult result=null;
		Users user = usersService.findUserForLogin(uname, upassword);
		session.setAttribute("userinfo", user);
		result=new AppResult(200,"��¼�ɹ�",null);
		return result;
		*/
		//ʹ��shiro
		
		//1.������ǰ����
		Subject subject=SecurityUtils.getSubject();
		
		//2.������ǰ���������
		UsernamePasswordToken token=new UsernamePasswordToken(uname,upassword);
		
		//3.��¼������ʵ���Ͼ��ǵ�����MyReal�е���֤����
		subject.login(token);
		
		//4.���û���׳��쳣�ͻ�ȡ��ǰ�û�
		Users user=(Users) subject.getPrincipal();
		
		//5.���浽session�У������Ժ������ã�
		session.setAttribute("userinfo",user);
		
		return new AppResult(200,"��¼�ɹ�",null);
		

	}
	
	
	
	
	@RequestMapping(value = "/users/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		/**
		 * session��һ�ֱ����ڷ���˵ģ����ڼ�¼�û�״̬�Ķ��� 
	�û�״̬�����кܶ��֣����¼�û�������¼ʱ��ȵȣ�ÿһ����Ϣ�������Լ�ֵ�Ե���ʽ�洢��session�С� 
	ÿһ����Ϣ����session��һ�����ԣ���attribute��
		 */
		/*
		session.invalidate();//��session�����������Ϣ��ɾ����
		//session.removeAttribute("message");���ֻ�ǽ�session��ĳ����Ϣɾ������Ӱ�����session��������Ϣ
		return "login";*/
		//ʹ��shiro
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		//session.invalidate();logout��ʵ�Ѿ���session����ˣ���ִ�и÷������׳�invalidate: Session already invalidated
		return "login";
	}
	
	
	

	@RequestMapping(value = "/users/findusersbyinput", method = RequestMethod.POST)
	@ResponseBody
	public DataGridResult<Users> finrolesbyinput(String uname,Integer page,Integer rows) {

		
		DataGridResult<Users> model=usersService.findUsersByInput(uname, page, rows);
		
		return model;
	}
	
	
	@RequestMapping(value = "/users/findAllRolesForCombobox", method = RequestMethod.POST)
	@ResponseBody
	public List<Role> findAllRolesForCombobox() {
		
		List<Role> roles=null;
		
		roles=roleService.findAllRoles();
		
		return roles;
	}
	
	@RequestMapping(value = "/users/delusersbycheck", method = RequestMethod.POST)
	@ResponseBody
	public AppResult delUsersByCheck(int[] uids) {
		
		/*
		for (int i = 0; i < uids.length; i++) {
			System.out.println(uids[i]);
		}*/
		usersService.delusersbycheck(uids);
		AppResult result=new AppResult(200,"ɾ���ɹ�",null);
		return result;
	}
	
	
	@RequestMapping(value = "/users/savaorupdateusers", method = RequestMethod.POST)
	@ResponseBody
	public AppResult savaOrUpdateUsers(Users users) {

		System.out.println(users);
		AppResult result = null;
		int count = -1;
		if (users.getUid() == null) {
			count = usersService.addRright(users);
			if (count <= 0) {
				throw new AppException(201, "���ʧ��");
			} else {
				users.setRolename(roleService.findRoleById(users.getRoleid()).getRolename());
				result = new AppResult(200, "��ӳɹ�", users);
			}

		} else {
			count = usersService.updateRright(users);
			if (count <= 0) {
				throw new AppException(202, "����ʧ��");
			} else {
				
				users.setRolename(roleService.findRoleById(users.getRoleid()).getRolename());
				result = new AppResult(200, "���³ɹ�", users);
			}
		}

		return result;
	}

}
