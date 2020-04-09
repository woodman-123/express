package com.heima.express.portal.controller;

import javax.jws.WebMethod;

//服务器端，新增方法后，懒得及时更新服务器的接口和返回值文件到客户端，可以直接在在客户端加上服务器端的这个方法，反正到时候还是一样会远程调用
// @WebMethod
//public void sendTs(String channel,String message);

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppResult;
import com.heima.express.manage.client.BcStaff;
import com.heima.express.manage.client.QpNoticebill;
import com.heima.express.manage.client.QpService;
import com.heima.express.message.client.MsgService;

@Controller
public class QpAction {
	@Autowired
	private QpService qpService;
	@Autowired
	private MsgService msService;
	

	@RequestMapping(value="/qp/add",method=RequestMethod.POST)
	@ResponseBody
	public AppResult addQq(QpNoticebill qp,String dzid) throws Exception {
		AppResult result=null;
		System.out.println(qp);
		System.out.println("dzid"+dzid);
		
		
		if(dzid==null) {
			dzid="";
		}//远程调用webservice,参数是null会有异常
		BcStaff staff=qpService.addQp(qp, dzid);
		
		if(staff==null) {
			System.out.println("手动派单");
			//告诉管理员手动派单，管理员根据ordertype=0并且user_id=null识别需要手动派单
			
			//后面加上的11，是业务员(角色)的id,该推送只发送给业务员，不加上这个限定的话，其他种登录用户也能看到
			msService.sendTs("ts_qj_11","请注意,您有一个没有分配的取件单，请及时处理");
			result=new AppResult(200,"[手动派单],管理员处理中",null);
			
		}else {
			//由于通用消息申请不了，所以取派信息用验证码信息代替
			msService.sendCheckSms(staff.getTelephone());
			result=new AppResult(200,"[自动派单],已经通知取件员取件",null);
			
			//还可以在这里设置客户的承包区id(可以根据staffid去承包区表里面找出承包区，进而找到承包区id)，下次就方便了。不用通过地址找取派员了
		}
		
		
		
		
		return result;
	}
	
}
