package com.heima.express.manage.web.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.heima.express.manage.client.RoleService;
import com.heima.express.manage.client.Users;
import com.heima.express.manage.client.UsersService;


/*
 * ˵����																																																														
��������װ��ҳ��������û���������																																																														
��������������null ��ζ��û�в�ѯ���û� ���Զ��׳�	UnknownAccountException																												
��������֤ͨ�� Ҫ���� AuthenticationInfo ����																																																														
����AuthenticationInfo�����3��������1��������֤ͨ���Ķ���  2��������֤ͨ�����������  3��һ���������																																																														
�����������Ĺ��� ��ʵҲ��������֤����Ĺ��� ���û��ͨ�����׳�  IncorrectCredentialsException																															

 */
public class MyRealm extends AuthorizingRealm{

	//һ��ע�����service��һЩ��ҵҲ��ע��mapper
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RoleService roleService;
	
	
	
	
	//��Ȩ AuthorizationInfo
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
	
	
		
	Users user=	(Users) SecurityUtils.getSubject().getPrincipal();
		
	
	List<String> texts=roleService.findAuthorityTextByRoleid(user.getRoleid());
	
	//AuthorizationInfo info=new SimpleAuthorizationInfo();����û�и÷���addStringPermissions
	SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
	
	//�ѵ�ǰ�û���Ȩ�����ϼ��뵽��Ȩ�����н��бȶ�
	info.addStringPermissions(texts);
	
	return info;
		
	}

	
	
	//��֤ AuthenticationInfo
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		
		System.out.println("��ʼִ����֤������");
		
		UsernamePasswordToken token=(UsernamePasswordToken) arg0;
		
	
		List<Users> list=usersService.findUserdByName(token.getUsername());
		
		if(list==null||list.size()==0) {
			return null;//return null ���Զ��׳�һ���˻��������쳣
		}
		
		
		//����1.��ǰ���� 2.��ǰ�������� 3.�÷�����������
		//�˻����ڵĻ�����ͨ����ܱȶ����ݿ������ҳ�洫�����������Ƿ�һ��
		AuthenticationInfo info=new SimpleAuthenticationInfo(list.get(0),list.get(0).getUpassword(),"MyRealm");
		
		
		return info;
	}

}
