package com.heima.express.message.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.heima.express.message.service.MsgService;
import com.heima.express.utils.StringUtils;

import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;

@Service
public class MsgServiceImpl implements MsgService {

	
	// 产品名称:云通信短信API产品,开发者无需替换

	@Value("${sms.product}")
	private String product;
	// 产品域名,开发者无需替换
	@Value("${sms.domain}")
	private String domain;
	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	@Value("${sms.accessKeyId}")
	private String accessKeyId;
	@Value("${sms.accessKeySecret}")
	private String accessKeySecret;
	@Value("${sms.profileym}")
	private String profileym;
	@Value("${sms.signname}")
	private String signname;
	
	public String sendCheckSms(String tel) throws Exception {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("profileym", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("profileym", "profileym", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(tel);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signname);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_181863614");
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为

		String code = StringUtils.getCheckCode();
		request.setTemplateParam("{\"code\":\"" + code + "\"}");

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return code;
	}

	@Override
	public void sendQjCms(String tel, String name, String address) throws Exception {
		// 可自助调整超时时间
				System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
				System.setProperty("sun.net.client.defaultReadTimeout", "10000");

				// 初始化acsClient,暂不支持region化
				IClientProfile profile = DefaultProfile.getProfile("profileym", accessKeyId, accessKeySecret);
				DefaultProfile.addEndpoint("profileym", "profileym", product, domain);
				IAcsClient acsClient = new DefaultAcsClient(profile);

				// 组装请求对象-具体描述见控制台-文档部分内容
				SendSmsRequest request = new SendSmsRequest();
				// 必填:待发送手机号
				request.setPhoneNumbers(tel);
				// 必填:短信签名-可在短信控制台中找到
				request.setSignName(signname);
				// 必填:短信模板-可在短信控制台中找到
				request.setTemplateCode("SMS_181859681");
				// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为

				String code = StringUtils.getCheckCode();
				 request.setTemplateParam("{\"name\":\""+name+"\", \"address\":\""+address+"\"}");

				// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
				// request.setSmsUpExtendCode("90997");

				// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
				// request.setOutId("yourOutId");

				// hint 此处可能会抛出异常，注意catch
				SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

				
		
	}

	

	@Override
	public void sendTs(String channel, String message) {
		GoEasy goEasy = new GoEasy( "rest-hangzhou.goeasy.io", "BC-e756491861e74c07a9b8742c139fad61");
	goEasy.publish(channel, message, new PublishListener() {
		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			System.out.println("消息发布成功");
		}
		
		@Override
		public void onFailed(GoEasyError error) {
			// TODO Auto-generated method stub
			System.out.println("消息发布失败,错误编码:"+error.getCode());
		}
	});
		
	}

}
