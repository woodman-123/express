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
		
		
		//����ȡ�ɵ����ֶ��ɵ�������Աid
		//Users user=(Users) session.getAttribute("userinfo");
		Subject subject=SecurityUtils.getSubject();
		Users user=(Users) subject.getPrincipal();
		qp.setUserId(new Integer(user.getUid()));//(int)ǿתֻ�ܳ���̣�����������̱ܶ䳤��������ʾ����
		qp.setOrdertype("0");//�˹��ɵ�Ϊ0
		
		qpService.updateForAss(qp);
		
		//���ڰ����Ƶ�ͨ�ö������벻�ˣ���������֤�����һ�£�ʵ����Ӧ�ô��ɼ��������ֻ����룬�ͻ����ֻ������ַ��ȥ
		msgService.sendCheckSms(staffPhone);
		
			//qpService.updateForAss(qp);
			return new AppResult(200,"�ֶ��ɵ����",null);
	}
	
	
}
