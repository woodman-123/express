package com.heima.express.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.entity.BcDecidedzone;
import com.heima.express.manage.entity.BcStaff;
import com.heima.express.manage.entity.BcSubarea;
import com.heima.express.manage.entity.QpNoticebill;
import com.heima.express.manage.entity.QpNoticebillExample;
import com.heima.express.manage.mapper.BcDecidedzoneMapper;
import com.heima.express.manage.mapper.BcStaffMapper;
import com.heima.express.manage.mapper.BcSubareaMapper;
import com.heima.express.manage.mapper.QpNoticebillMapper;
import com.heima.express.manage.service.QpService;

@Service
public class QpServiceImpl implements QpService{
	
	@Autowired
	private BcDecidedzoneMapper decidezoneMapper;
	@Autowired
	private BcStaffMapper staffMapper;
	@Autowired
	private BcSubareaMapper subareaMapper;
	@Autowired
	private QpNoticebillMapper qpNoticebillMapper;

	
	
	@Override
	public BcStaff addQp(QpNoticebill qp, String dzid) throws Exception {
		
		BcStaff staff=null;
		if(StringUtils.isEmpty(dzid)==false) {
			
			//1.根据承包区dzid去查查找承包区
			BcDecidedzone decidedzone=decidezoneMapper.selectByPrimaryKey(dzid);
			//2.找出承包区对用的取派员
			String staffId = decidedzone.getStaffId();
			 staff = staffMapper.selectByPrimaryKey(staffId);
	
			//3.完善信息
			qp.setOrdertype("1");//自动派单
			qp.setStaffId(staffId);//设置取派员
			qp.setUserId(0);//自动派单，默认为零
		}
			//如果staff为空(说明dzid为空，或者该承包区的取派员为空)说明需要根据地址关键字找到小区再找到承包区进而找到取派员，派单
			if(staff==null) {
				
				String address=qp.getPickaddress();
				
				List<String> keys=com.heima.express.utils.StringUtils.queryWords(address);
				
				List<BcSubarea> subareas=subareaMapper.selectSubareaByAddresskey(keys);
		
				if(subareas!=null&&subareas.size()==1) {
					
					
						BcSubarea subarea=subareas.get(0);
						String xqDzid=subarea.getDecidedzoneId();
						BcDecidedzone xqDecidedzone=decidezoneMapper.selectByPrimaryKey(xqDzid);
						String StaffId = xqDecidedzone.getStaffId();
						 staff = staffMapper.selectByPrimaryKey(StaffId);
				
						//3.完善信息
						qp.setOrdertype("1");//自动派单
						qp.setStaffId(StaffId);//设置取派员
						qp.setUserId(0);//自动派单，默认为零
				}
				
				
			}
			
		//id不是自动增长的自己指定id	
		qp.setId(UUID.randomUUID().toString());
		qpNoticebillMapper.insert(qp);
		return staff;//返回取派员信息(手机号码，名字),给取派员发取件信息，如果取派员为空就手动派单，(portal工程上面的qp对象有客户信息)，
		//，不要在这里发取件信息，让portal工程远程调用message发取件信息，因为如果有异常可以方便处理
		
		//如果在这里发取件信息的话，发送失败会回滚，也就是qp插入不成功，但是自动补偿机制消息第一次发送不成功的话会再发一次(也就是说消息一定会发送成功)，
		//那么如果在这里发取件信息就会存在一种情况，第一次发取件信息失败，qp回滚，第二次发送成功,取件人收到信息，但是qp插入失败的bug
		//所以说，只要保证在这里插入成功就行了，发信息交给portal(这条信息必须得发出去)
		
	}



	
	public DataGridResult<QpNoticebill> findAllNoAss(int page,int rows) {
		
		PageHelper.startPage(page, rows);
		QpNoticebillExample example=new QpNoticebillExample();
		example.createCriteria().andStaffIdIsNull();
		List<QpNoticebill> qps = qpNoticebillMapper.selectByExample(example);
		PageInfo<QpNoticebill> info = new PageInfo<>(qps);

		DataGridResult<QpNoticebill> model = new DataGridResult<QpNoticebill>(qps, (int) info.getTotal());
		return model;
	}



	@Override
	public List<BcStaff> findAllStaff() {
		return staffMapper.selectByExample(null);
	}



	@Override
	public void updateForAss(QpNoticebill qp) {
		qpNoticebillMapper.updateByPrimaryKeySelective(qp);
		
	}

}
