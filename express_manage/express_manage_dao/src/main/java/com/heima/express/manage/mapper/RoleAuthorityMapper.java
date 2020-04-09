package com.heima.express.manage.mapper;

import com.heima.express.manage.entity.RoleAuthority;
import com.heima.express.manage.entity.RoleAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleAuthorityMapper {
    int countByExample(RoleAuthorityExample example);

    int deleteByExample(RoleAuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    List<RoleAuthority> selectByExample(RoleAuthorityExample example);

    RoleAuthority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleAuthority record, @Param("example") RoleAuthorityExample example);

    int updateByExample(@Param("record") RoleAuthority record, @Param("example") RoleAuthorityExample example);

    int updateByPrimaryKeySelective(RoleAuthority record);

    int updateByPrimaryKey(RoleAuthority record);
}