package com.heima.express.manage.mapper;

import com.heima.express.manage.entity.Role;
import com.heima.express.manage.entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
	List<String> findRightsByRoleId(Integer roleid);
	
	//手写拦截器，查找权利url
	List<String> selectAuthorityByRoleid(Integer roleid);
	
	//shiro验证，查找权利text
	List<String> selectAuthorityTextByRoleid(Integer roleid);
}