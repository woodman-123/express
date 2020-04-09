package com.heima.express.manage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppResult;
import com.heima.express.common.DataGridResult;
import com.heima.express.manage.client.BcStaff;
import com.heima.express.manage.client.QpNoticebill;
import com.heima.express.manage.client.QpService;
import com.heima.express.manage.client.Users;
import com.heima.express.message.client.MsgService;

@Controller
public class QpAction {

	
	@Autowired
	private QpService qpService;
	@Autowired
	private  MsgService msgService;
	

	@RequestMapping(value = "/qp/findnoass", method = RequestMethod.POST)
	@ResponseBody
	public DataGridResult<QpNoticebill> findNoAss(int page,int rows) {
			return qpService.findAllNoAss(page,rows);
	}
	
	

	@RequestMapping(value = "/qp/findallstaff", method = RequestMethod.POST)
	@ResponseBody
	public List<BcStaff> findAllStaff() {
			return qpService.findAllStaff();
	}
	
	
	
	@RequestMapping(value = "/qp/updateqp", method = RequestMethod.POST)
	@ResponseBody
	public AppResult updateQp(QpNoticebill qp,String staffName,String staffPhone,HttpSession session) throws Exception {
		System.out.println(qp);
		System.out.println(staffName+":"+staffPhone);
		
		
		//设置取派单的手动派单操作人员id
		//Users user=(Users) session.getAttribute("userinfo");
		Subject subject=SecurityUtils.getSubject();
		Users user=(Users) subject.getPrincipal();
		qp.setUserId(new Integer(user.getUid()));//(int)强转只能长变短（溢出），不能短变长所以用所示方法
		qp.setOrdertype("0");//人工派单为0
		
		qpService.updateForAss(qp);
		
		//由于阿里云的通用短信申请不了，所以用验证码代替一下，实际上应该传派件人名字手机号码，客户的手机号码地址过去
		msgService.sendCheckSms(staffPhone);
		
			//qpService.updateForAss(qp);
			return new AppResult(200,"手动派单完成",null);
	}
	
	
}
