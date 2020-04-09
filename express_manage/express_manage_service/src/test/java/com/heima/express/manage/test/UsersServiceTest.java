package com.heima.express.manage.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heima.express.manage.entity.Users;
import com.heima.express.manage.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class UsersServiceTest {

	@Autowired
	private UsersService usersService;
	
	
	@Test
	public void testLogin() {
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		
		usersService=(UsersService) context.getBean("usersServiceImpl");
				Users user=usersService.findUserForLogin("admin","123123");
				System.out.println(user);
	}
	
	
}
