package com.heima.express.manage.service;

import java.util.List;

import javax.jws.WebService;

import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.BcStaff;
import com.heima.express.manage.entity.QpNoticebill;

@WebService
public interface QpService {

	BcStaff addQp(QpNoticebill qp, String dzid) throws Exception;

	// 查询所有没有指派取派人员的单
	 DataGridResult<QpNoticebill> findAllNoAss(int page,int rows);

	// 查询所有的去派人员
	List<BcStaff> findAllStaff();

	// 更新去派单（手动分派）
	void updateForAss(QpNoticebill qp);

	
}
