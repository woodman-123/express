package com.heima.express.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.express.common.AppException;
import com.heima.express.common.AppResult;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.Role;
import com.heima.express.manage.entity.RoleExample;
import com.heima.express.manage.entity.RoleRight;
import com.heima.express.manage.entity.RoleRightExample;
import com.heima.express.manage.mapper.RoleMapper;
import com.heima.express.manage.mapper.RoleRightMapper;
import com.heima.express.manage.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleRightMapper roleRightMapper;
	
	
	public DataGridResult<Role> findAllRole(Integer pageNo, Integer pageSize) {

		PageHelper.startPage(pageNo, pageSize);
		List<Role> roles = roleMapper.selectByExample(null);
		PageInfo<Role> info = new PageInfo<>(roles);

		DataGridResult<Role> model = new DataGridResult<Role>(roles, (int) info.getTotal());
		return model;

	}

	@Override
	public DataGridResult<Role> findRolesByInput(String rolename, Integer page, Integer pageSize) {

		PageHelper.startPage(page, pageSize);
		
		
		RoleExample example = null;
		if (StringUtils.isEmpty(rolename) == false) {// Spring里面的工具类
			example=new RoleExample();
			example.createCriteria().andRolenameLike(rolename);
		}
		List<Role> roles = roleMapper.selectByExample(example);
		
		
		for (Role role : roles) {
			String rightNames="";//每开始遍历一个角色的时候要清空权利字符串，否则如果在外面定义的话会全部拼在一起
			
			List<String> rights=roleMapper.findRightsByRoleId(role.getRoleid());
			for(String right:rights) {
				rightNames+=right+",";
			}
			//把最后面的“,”去掉
			if(rightNames.length()>=1) {
			rightNames.substring(0, rightNames.length()-1);
			}
			role.setRightNames(rightNames);
		}

		PageInfo<Role> info = new PageInfo<>(roles);

		DataGridResult<Role> model = new DataGridResult<Role>(roles, (int) info.getTotal());
		return model;
		
	}

	@Override
	public int delRolesByRoleids(int[] rolesid) {
		int count=0;
		for (int i = 0; i < rolesid.length; i++) {
			count+=roleMapper.deleteByPrimaryKey( rolesid[i]);
		}
		if(count<=0) {
			throw new AppException(201,"删除失败");
		}
		return count;
	}

	@Override
	public AppResult addRole(Role role) {
	
		int count=roleMapper.insertSelective(role);
		if(count<1) {
			throw new AppException(201,"添加角色失败");
		}
		
		//记得设置role要设置使用自动增长的主键，并且指定自动增长的主键是什么
		
		for(Integer rightid:role.getRights()) {
			RoleRight record=new RoleRight();
			record.setRoleid(role.getRoleid());
			record.setRightid(rightid);
			count=roleRightMapper.insertSelective(record);
			if(count<1) {
				throw new AppException(201,"添加角色该角色的权利失败");
			}
		}
		
		return new AppResult(200,"添加成功",role);
	
	}

	@Override
	public AppResult updRole(Role role) {
		int count;
		count=roleMapper.updateByPrimaryKeySelective(role);
		if(count<1) {
			throw new AppException(201,"更新角色失败");
		}

		//先清空权利再增加权利（覆盖）
		RoleRightExample example=new RoleRightExample();
		example.createCriteria().andRoleidEqualTo(role.getRoleid());
		roleRightMapper.deleteByExample(example);
		
		for(Integer rightid:role.getRights()) {
			RoleRight record=new RoleRight();
			record.setRoleid(role.getRoleid());
			record.setRightid(rightid);
			count=roleRightMapper.insertSelective(record);//!!!是新增不是更新哦
			if(count<1) {
				throw new AppException(201,"更新该角色的权利失败");
			}
		}
		
		return new AppResult(200,"更新成功",role);
	
	}

	@Override
	public List<Role> findAllRoles() {
		return roleMapper.selectByExample(null);
	}

	@Override
	public Role findRoleById(Integer roleid) {
		return roleMapper.selectByPrimaryKey(roleid);
	}

	@Override
	public List<String> findAuthorityByRoleid(Integer roleid) {
		return roleMapper.selectAuthorityByRoleid(roleid);
	}

	@Override
	public List<String> findAuthorityTextByRoleid(Integer roleid) {
		return roleMapper.selectAuthorityTextByRoleid(roleid);
	}

}
