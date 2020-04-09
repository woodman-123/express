package com.heima.express.manage.service;

import java.util.List;

import javax.jws.WebService;

import com.heima.express.manage.entity.Rright;

@WebService
public  interface RrightService{
	
	List<Rright> findRrightForMenu(Integer roleid,Integer parentid);
	
	int updateRright(Rright right);
	
	
	int addRright(Rright right);
	
	
	int deleteRright(Integer rightid);
	
	int updaterightdnd(Rright right);

	int deleteFolderRight(Integer rightid);
	List<Rright> findRrightsForCurd(Integer roleid,Integer parentid);

	boolean findCheckResultByrighttext(String righttext);
	
	
}
