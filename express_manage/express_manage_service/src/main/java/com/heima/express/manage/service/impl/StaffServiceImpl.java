package com.heima.express.manage.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.express.common.AppException;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.BcStaff;
import com.heima.express.manage.entity.Role;
import com.heima.express.manage.mapper.BcStaffMapper;
import com.heima.express.manage.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private BcStaffMapper staffMapper;
	
	
	

	@Override
	public DataGridResult<BcStaff> findStaffByInput(Integer id, String name, String telephone, Integer haspda, Integer deltag,
			String standard, String station, Integer page, Integer rows) {
		
		PageHelper.startPage(page,rows);
		
		List<BcStaff> staffs=staffMapper.findStaffByInput(id, name, telephone, haspda, deltag, standard, station);
		
		PageInfo<BcStaff> info=new PageInfo<>(staffs);
		
		
		DataGridResult<BcStaff> model = new DataGridResult<BcStaff>(staffs, (int)info.getTotal());
		return model;
		
	}




	@Override
	public int addStaff(BcStaff staff) {
		int count=staffMapper.insertSelective(staff);
		
		if(count<1) {
			throw new AppException(201,"添加失敗");
		}
		return count;
	}




	@Override
	public int delStaffsByCheck(int[] ids) {
		int count=0;
		for(Integer id:ids) {
			count=staffMapper.deleteByPrimaryKey(id.toString());
			if(count<1) {
				throw new AppException(201,"刪除失敗");
			}
		}
	
		
		
		return count;
	}

}
