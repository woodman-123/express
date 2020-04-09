package com.heima.express.manage.mapper;

import com.heima.express.manage.entity.BcStaff;
import com.heima.express.manage.entity.BcStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BcStaffMapper {
    int countByExample(BcStaffExample example);

    int deleteByExample(BcStaffExample example);

    int deleteByPrimaryKey(String id);

    int insert(BcStaff record);

    int insertSelective(BcStaff record);

    List<BcStaff> selectByExample(BcStaffExample example);

    BcStaff selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BcStaff record, @Param("example") BcStaffExample example);

    int updateByExample(@Param("record") BcStaff record, @Param("example") BcStaffExample example);

    int updateByPrimaryKeySelective(BcStaff record);

    int updateByPrimaryKey(BcStaff record);

    List<BcStaff> findStaffByInput(
    @Param("id") Integer id,
    @Param("name") String name,
    @Param("telephone") String telephone,
    @Param("haspda") Integer haspda,
    @Param("deltag") Integer deltag,
    @Param("standard") String standard,
    @Param("station") String station
    );
}