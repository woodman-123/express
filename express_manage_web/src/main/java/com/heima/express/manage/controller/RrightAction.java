package com.heima.express.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heima.express.common.AppException;
import com.heima.express.common.AppResult;
import com.heima.express.common.TreeNode;
import com.heima.express.manage.client.Rright;
import com.heima.express.manage.client.RrightService;
import com.heima.express.manage.client.Users;


@Controller
public class RrightAction {

	@Autowired
	private RrightService rightService;

	//��ߵ��۵��˵�
	@RequestMapping(value = "/right/createmenu", method = RequestMethod.POST)
	@ResponseBody
	public AppResult findRRightForMenu(HttpSession session) {

		Users user = (Users) session.getAttribute("userinfo");
		List<Rright> rights = rightService.findRrightForMenu(user.getRoleid(), 0);
		AppResult result = new AppResult(null, null, rights);
		return result;

	}
	
	@RequiresPermissions("�˵�Ȩ�޹���:��ѯȨ����")
	@RequestMapping(value = "/right/createmenucurd", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> createmenucurd() {
		List<TreeNode> nodes = new ArrayList<>();

		List<Rright> rights = rightService.findRrightForMenu(null, 0);

		for (Rright right : rights) {

			TreeNode node = new TreeNode();
			node.setId(right.getRightid());
			node.setText(right.getRighttext());

			Map<String, Object> attributes = new HashMap<>();
			attributes.put("url", right.getRighturl());
			node.setAttributes(attributes);
			if (right.getChild() == null || right.getChild().size() == 0) {
				node.setState("closed");
			} // �����ӵ��Ǹ��ļ��У����ļ�������û�ж�����ʱ�����Ĭ��չ���Ļ�������һ���ļ���ͼ��������ļ���ͼ�꣬
				// ���Ե��ļ�������û������ʱ����������close�������Ͳ�����ʾ��һ���ļ�

			List<TreeNode> childNodes = new ArrayList<>();// �����Ҫ��������Ŷ����������Ļ����ڵ����������ӽڵ㣨�������˵��ӽڵ㣩
			for (Rright childRight : right.getChild()) {
				TreeNode nodec = new TreeNode();
				nodec.setId(childRight.getRightid());
				nodec.setText(childRight.getRighttext());
				nodec.setState("open");
				Map<String, Object> attributes1 = new HashMap<>();
				attributes1.put("url", childRight.getRighturl());
				nodec.setAttributes(attributes1);
				childNodes.add(nodec);
			}
			node.setChildren(childNodes);
			nodes.add(node);
		}

		TreeNode znode = new TreeNode();
		znode.setId(0);
		znode.setText("ϵͳ�˵�");
		znode.setChildren(nodes);

		List<TreeNode> znodes = new ArrayList<>();
		znodes.add(znode);

		return znodes;
	}
	
	//���������role.jsp�е�������
	@RequiresPermissions("��ɫ����:��ѯȨ����")
	@RequestMapping(value = "/right/createMenuForCurd", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> createMenuForCurd(Integer roleid, Integer id) {
			
		if(id==null) {
			id=0;
		}
		

		List<TreeNode> nodes = new ArrayList<>();
		List<Rright> rights = rightService.findRrightsForCurd(roleid, id);
		
		for (Rright right : rights) {

			TreeNode node = new TreeNode();
			node.setId(right.getRightid());
			node.setText(right.getRighttext());

			if (right.getRighttype()==0) {
				node.setState("closed");
			} else {
				node.setState("open");
			}

			
			if (right.getHasflag() == 2) {
				node.setChecked(true);
			} else {
				node.setChecked(false);
			}

			nodes.add(node);
		}

		return nodes;
	}

	
	@RequiresPermissions("�˵�Ȩ�޹���:���/�޸�")
	@RequestMapping(value = "/right/savaorupdateright", method = RequestMethod.POST)
	@ResponseBody
	public AppResult savaorupdateright(Rright right) {

		System.out.println(right);
		AppResult result = null;
		int count = -1;
		if (right.getRightid() == null) {
			count = rightService.addRright(right);
			if (count <= 0) {
				throw new AppException(201, "���ʧ��");
			} else {
				result = new AppResult(200, "��ӳɹ�", null);
			}

		} else {
			count = rightService.updateRright(right);
			if (count <= 0) {
				throw new AppException(202, "����ʧ��");
			} else {
				result = new AppResult(200, "���³ɹ�", null);
			}
		}

		return result;
	}

	@RequiresPermissions("�˵�Ȩ�޹���:ɾ��һ��")
	@RequestMapping(value = "/right/deleteOneright", method = RequestMethod.POST)
	@ResponseBody
	public AppResult deleteOneright(Integer rightid) {
		int count = -1;
		count = rightService.deleteRright(rightid);

		AppResult result = new AppResult(200, "ɾ���ɹ�", null);
		return result;

	}

	
	@RequiresPermissions("�˵�Ȩ�޹���:ɾ�����")
	@RequestMapping(value = "/right/deleteFolderRight", method = RequestMethod.POST)
	@ResponseBody
	public AppResult deleteFolderRight(Integer rightid) {

		rightService.deleteFolderRight(rightid);

		AppResult result = new AppResult(200, "ɾ���ļ��гɹ�", null);
		return result;

	}
	
	@RequiresPermissions("�˵�Ȩ�޹���:�϶�")
	@RequestMapping(value = "/right/updaterightdnd", method = RequestMethod.POST)
	@ResponseBody
	public AppResult updaterightdnd(Rright right) {
		System.out.println(right);
		rightService.updaterightdnd(right);
		AppResult result = new AppResult(200, "�϶��ɹ�", null);
		return result;

	}
	
	
	
	
	
	
	
	
	@RequiresPermissions("�˵�Ȩ�޹���:��֤Ȩ����")
	@RequestMapping(value = "/right/checkrightname", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkRightName(String righttext) {
		return rightService.findCheckResultByrighttext(righttext);
		
	}
	
	

}
