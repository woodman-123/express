package com.heima.express.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.express.common.AppException;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.Role;
import com.heima.express.manage.entity.Users;
import com.heima.express.manage.entity.UsersExample;
import com.heima.express.manage.mapper.UsersMapper;
import com.heima.express.manage.service.UsersService;

@Controller
public class UsersServiceImpl implements UsersService {

	
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public Users findUserForLogin(String uname, String upassword) {

		UsersExample example = new UsersExample();
		example.createCriteria().andUnameEqualTo(uname);
		List<Users> list = usersMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			throw new AppException(201,"账号不存在");
		}
		if (list.get(0).getUpassword().equals(upassword) == false) {
			throw new AppException(202,"密码有误");
		}

		return list.get(0);

	}

	@Override
	public DataGridResult<Users> findUsersByInput(String uname, Integer page, Integer rows) {
		
		DataGridResult<Users> result=null;
		
		
		PageHelper.startPage(page,rows);
		
		List<Users> users=usersMapper.findUsersByInput(uname);
		
		PageInfo<Users> info =new PageInfo<Users>(users);
		
		
		result=new DataGridResult<Users>(users,(int)info.getTotal());
		
		
		return result;
	}

	@Override
	public int delusersbycheck(int[] uids) {
		
		
		int count=0;
		for (int i = 0; i < uids.length; i++) {
			count+=usersMapper.deleteByPrimaryKey((short) uids[i]);
		}
		if(count<=0) {
			throw new AppException(201,"删除失败");
		}
		return count;
		
		
		
	}

	@Override
	public int addRright(Users users) {
		return usersMapper.insertSelective(users);
	}

	@Override
	public int updateRright(Users users) {
		return usersMapper.updateByPrimaryKeySelective(users);
	}

	@Override
	public List<Users> findUserdByName(String username) {
		UsersExample example=new UsersExample();
		example.createCriteria().andUnameEqualTo(username);
		return usersMapper.selectByExample(example);
	}

}
