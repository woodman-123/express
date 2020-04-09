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
		result=new AppResult(200,"登录成功",null);
		return result;
		*/
		//使用shiro
		
		//1.创建当前对象
		Subject subject=SecurityUtils.getSubject();
		
		//2.创建当前对象的令牌
		UsernamePasswordToken token=new UsernamePasswordToken(uname,upassword);
		
		//3.登录，这里实际上就是调用了MyReal中的认证方法
		subject.login(token);
		
		//4.如果没有抛出异常就获取当前用户
		Users user=(Users) subject.getPrincipal();
		
		//5.保存到session中（方便以后拿来用）
		session.setAttribute("userinfo",user);
		
		return new AppResult(200,"登录成功",null);
		

	}
	
	
	
	
	@RequestMapping(value = "/users/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		/**
		 * session是一种保存在服务端的，用于记录用户状态的对象。 
	用户状态可能有很多种，如登录用户名，登录时间等等，每一种信息都可以以键值对的形式存储在session中。 
	每一种信息都是session的一个属性，即attribute。
		 */
		/*
		session.invalidate();//把session里面的所有信息都删掉了
		//session.removeAttribute("message");这个只是将session的某个信息删掉，不影响存在session的其他信息
		return "login";*/
		//使用shiro
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		//session.invalidate();logout其实已经把session清空了，再执行该方法会抛出invalidate: Session already invalidated
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
		AppResult result=new AppResult(200,"删除成功",null);
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
				throw new AppException(201, "添加失败");
			} else {
				users.setRolename(roleService.findRoleById(users.getRoleid()).getRolename());
				result = new AppResult(200, "添加成功", users);
			}

		} else {
			count = usersService.updateRright(users);
			if (count <= 0) {
				throw new AppException(202, "更新失败");
			} else {
				
				users.setRolename(roleService.findRoleById(users.getRoleid()).getRolename());
				result = new AppResult(200, "更新成功", users);
			}
		}

		return result;
	}

}
