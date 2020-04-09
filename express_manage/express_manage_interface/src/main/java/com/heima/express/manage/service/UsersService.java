package com.heima.express.manage.service;

import java.util.List;

import javax.jws.WebService;

import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.Role;
import com.heima.express.manage.entity.Users;

@WebService
public interface UsersService {

	Users findUserForLogin(String uname,String upassword);

	DataGridResult<Users> findUsersByInput(String uname, Integer page, Integer rows);

	int delusersbycheck(int[] uids);

	int addRright(Users users);

	int updateRright(Users users);
	
	//shiro,登录验证
	List<Users> findUserdByName(String username);
}
