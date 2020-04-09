package com.heima.express.manage.mapper;

import com.heima.express.manage.entity.Rright;
import com.heima.express.manage.entity.RrightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RrightMapper {
    int countByExample(RrightExample example);

    int deleteByExample(RrightExample example);

    int deleteByPrimaryKey(Integer rightid);

    int insert(Rright record);

    int insertSelective(Rright record);

    List<Rright> selectByExample(RrightExample example);

    Rright selectByPrimaryKey(Integer rightid);

    int updateByExampleSelective(@Param("record") Rright record, @Param("example") RrightExample example);

    int updateByExample(@Param("record") Rright record, @Param("example") RrightExample example);

    int updateByPrimaryKeySelective(Rright record);

    int updateByPrimaryKey(Rright record);
    
    List<Rright> findRRightForMenu(@Param("roleid") Integer roleid,@Param("parentid") Integer parentid);
    
    
    List<Rright>  findRrightsForCurd(@Param("roleid") Integer roleid,@Param("parentid") Integer parentid);
   
    
}