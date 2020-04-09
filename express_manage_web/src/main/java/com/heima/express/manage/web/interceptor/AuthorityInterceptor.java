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
		
		//1.��ȡҳ�������·��
		String path=request.getServletPath();
		System.out.println(path);
		//2.��ȡ��ǰ��¼�û�ͨ���û���ȡ��ɫid
		Users user=(Users) request.getSession().getAttribute("userinfo");
		
		//һЩӵ������Ȩ�޵ģ����ǲ���Ҫ�����ݿ⣨role_authority�����������ô�������ݿ������жϷſ�����Ȩ��
		if(user.getUname().equals("admin")) {
			return true;
		}
		
		
		//3.ͨ����ɫid��ȡ�ý�ɫ�Ĳ���Ȩ��url����
		List<String> urls=roleService.findAuthorityByRoleid(user.getRoleid());
		
		//4.�жϸ�ҳ�������Ƿ��ڸý�ɫ����Ȩ��,�ھ�ͨ�������ھ��׳��쳣��ҳ�浯����ʾ
		if(urls.contains(path)==false) {
			throw new AppException(201,"�޴�Ȩ��");
		}

		return true;
	}

}
