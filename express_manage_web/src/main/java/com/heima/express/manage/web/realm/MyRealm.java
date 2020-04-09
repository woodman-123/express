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
 * 说明：																																																														
参数：封装了页面输入的用户名和密码																																																														
这个方法如果返回null 意味着没有查询到用户 会自动抛出	UnknownAccountException																												
最后如果验证通过 要返回 AuthenticationInfo 对象																																																														
创建AuthenticationInfo对象的3个参数：1：名字验证通过的对象  2：名字验证通过对象的密码  3：一般就是类名																																																														
创建这个对象的过程 其实也包含了验证密码的过程 如果没有通过会抛出  IncorrectCredentialsException																															

 */
public class MyRealm extends AuthorizingRealm{

	//一般注入的是service，一些企业也会注入mapper
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RoleService roleService;
	
	
	
	
	//授权 AuthorizationInfo
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
	
	
		
	Users user=	(Users) SecurityUtils.getSubject().getPrincipal();
		
	
	List<String> texts=roleService.findAuthorityTextByRoleid(user.getRoleid());
	
	//AuthorizationInfo info=new SimpleAuthorizationInfo();父类没有该方法addStringPermissions
	SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
	
	//把当前用户的权利集合加入到授权对象中进行比对
	info.addStringPermissions(texts);
	
	return info;
		
	}

	
	
	//认证 AuthenticationInfo
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		
		System.out.println("开始执行认证。。。");
		
		UsernamePasswordToken token=(UsernamePasswordToken) arg0;
		
	
		List<Users> list=usersService.findUserdByName(token.getUsername());
		
		if(list==null||list.size()==0) {
			return null;//return null 会自动抛出一个账户不存在异常
		}
		
		
		//参数1.当前对象 2.当前对象密码 3.该方法所在类名
		//账户存在的话，在通过框架比对数据库密码和页面传上来的密码是否一致
		AuthenticationInfo info=new SimpleAuthenticationInfo(list.get(0),list.get(0).getUpassword(),"MyRealm");
		
		
		return info;
	}

}
