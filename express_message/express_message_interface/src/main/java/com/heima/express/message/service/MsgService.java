package com.heima.express.message.service;

import javax.jws.WebService;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;




@WebService
public interface MsgService {
	
	
	//发验证码短信
	String sendCheckSms(String tel)throws Exception ;

	//发普通短信
	void sendQjCms(String tel, String name, String address) throws Exception ;	
	

	//发推送
	void sendTs(String channel,String message);
	
	//发邮件
}
