package com.heima.express.message.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heima.express.message.service.MsgService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_sms.xml")//不能写成*.xml,这个文件要有扫描注解的操作
public class SmsTest {
	
	@Autowired
	private MsgService msgService;

	@Test
	public void testSms() {
		
		
		try {
			//String code=msgService.sendCheckSms("13257862835");
			msgService.sendQjCms("13257862835","李森林","凯旋广场桃源");
			//System.out.println(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	

}
