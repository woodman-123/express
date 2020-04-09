package com.heima.express.manage.test;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.Role;
import com.heima.express.manage.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class RoleServiceTest {

	
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void testRoleService() {
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		
		roleService=(RoleService) context.getBean("roleServiceImpl");
		DataGridResult<Role> model=roleService.findRolesByInput("人力",1, 3);
		System.out.println(model);
	}
}
