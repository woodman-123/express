package com.heima.express.manage.service;


import javax.jws.WebService;

import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.BcStaff;


@WebService
public interface StaffService {

	DataGridResult<BcStaff> findStaffByInput(Integer id, String name, String telephone, Integer haspda, Integer deltag,
			String standard, String station, Integer page, Integer rows);

	int addStaff(BcStaff staff);

	int   delStaffsByCheck(int[] ids);

}
