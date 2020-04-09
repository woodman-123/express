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
@ControllerAdvice//所有action抛出而且未处理的异常都被这个类来，被以下注解的方法解决
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
			result=new AppResult(500,"系统异常,请联系管理员",null);
		}
		return result;*/
		
		
		//重新处理一下shiro抛出来的异常，不改的话会被当成系统异常处理
		
		
		
		AppResult result=null;
		
		ex.printStackTrace();
		
		if(ex instanceof AppException) {
			AppException appEx=(AppException) ex;
			
			result=new AppResult(appEx.getKeyCode(),appEx.getMessage(),null);
		}else if(ex instanceof UnknownAccountException ){
			result=new AppResult(201,"账号不存在",null);
		}else if(ex instanceof IncorrectCredentialsException){
			result=new AppResult(202,"密码错误",null);
		}else if(ex instanceof AuthorizationException){
			result=new AppResult(202,"没有该授权",null);
		}else {
			result=new AppResult(500,"系统异常,请联系管理员",null);
		}
		return result;

	}
	
	
}
