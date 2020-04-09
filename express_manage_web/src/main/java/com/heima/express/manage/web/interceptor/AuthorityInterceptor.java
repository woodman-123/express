package com.heima.express.manage.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.heima.express.common.AppException;
import com.heima.express.manage.client.RoleService;
import com.heima.express.manage.client.Users;


public class AuthorityInterceptor implements HandlerInterceptor{

	@Autowired
	private RoleService roleService;
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		//1.获取页面的请求路径
		String path=request.getServletPath();
		System.out.println(path);
		//2.获取当前登录用户通过用户获取角色id
		Users user=(Users) request.getSession().getAttribute("userinfo");
		
		//一些拥有所有权限的，我们不必要往数据库（role_authority）里面插入那么多条数据可以用判断放开所有权限
		if(user.getUname().equals("admin")) {
			return true;
		}
		
		
		//3.通过角色id获取该角色的操作权限url集合
		List<String> urls=roleService.findAuthorityByRoleid(user.getRoleid());
		
		//4.判断该页面请求是否在该角色的授权内,在就通过，不在就抛出异常，页面弹出提示
		if(urls.contains(path)==false) {
			throw new AppException(201,"无此权限");
		}

		return true;
	}

}
