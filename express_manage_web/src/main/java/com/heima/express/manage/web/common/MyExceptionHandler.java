package com.heima.express.manage.web.common;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppException;
import com.heima.express.common.AppResult;

@Component
@ControllerAdvice//����action�׳�����δ������쳣�������������������ע��ķ������
public class MyExceptionHandler {

	
	
	@ExceptionHandler//
	@ResponseBody
	public AppResult exceptionHandler(Exception ex) {
		
		/*
		AppResult result=null;
		
		ex.printStackTrace();
		
		if(ex instanceof AppException) {
			AppException appEx=(AppException) ex;
			
			result=new AppResult(appEx.getKeyCode(),appEx.getMessage(),null);
		}else {
			result=new AppResult(500,"ϵͳ�쳣,����ϵ����Ա",null);
		}
		return result;*/
		
		
		//���´���һ��shiro�׳������쳣�����ĵĻ��ᱻ����ϵͳ�쳣����
		
		
		
		AppResult result=null;
		
		ex.printStackTrace();
		
		if(ex instanceof AppException) {
			AppException appEx=(AppException) ex;
			
			result=new AppResult(appEx.getKeyCode(),appEx.getMessage(),null);
		}else if(ex instanceof UnknownAccountException ){
			result=new AppResult(201,"�˺Ų�����",null);
		}else if(ex instanceof IncorrectCredentialsException){
			result=new AppResult(202,"�������",null);
		}else if(ex instanceof AuthorizationException){
			result=new AppResult(202,"û�и���Ȩ",null);
		}else {
			result=new AppResult(500,"ϵͳ�쳣,����ϵ����Ա",null);
		}
		return result;

	}
	
	
}
