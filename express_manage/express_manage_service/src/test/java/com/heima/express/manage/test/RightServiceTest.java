package com.heima.express.manage.test;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.sql.visitor.functions.Right;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.Role;
import com.heima.express.manage.entity.Rright;
import com.heima.express.manage.service.RoleService;
import com.heima.express.manage.service.RrightService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class RightServiceTest {

	
	
	@Autowired
	private RrightService rightService;
	
	@Test
	public void testRoleService() {
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		
		rightService= (RrightService) context.getBean("rrightServiceImpl");
	List<Rright> rights=rightService.findRrightsForCurd(1,0);
	for(Rright right:rights) {
		System.out.println(right);
		
	}
		
	}
}
