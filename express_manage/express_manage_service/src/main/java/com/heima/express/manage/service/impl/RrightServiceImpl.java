package com.heima.express.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heima.express.common.AppException;
import com.heima.express.manage.entity.Rright;
import com.heima.express.manage.entity.RrightExample;
import com.heima.express.manage.mapper.RrightMapper;
import com.heima.express.manage.service.RrightService;


@Service
public class RrightServiceImpl implements RrightService{

	@Autowired
	private RrightMapper rightMapper;
	
	

	public List<Rright> findRrightForMenu(Integer roleid, Integer parentid) {
		List<Rright> rights= rightMapper.findRRightForMenu(roleid, parentid);
		
		for(Rright right:rights) {
			List<Rright> child=rightMapper.findRRightForMenu(roleid,right.getRightid());
			right.setChild(child);
		}
		return rights;
		
		
		
		
	}



	@Override
	public int updateRright(Rright right) {
		return rightMapper.updateByPrimaryKeySelective(right);
	}



	@Override
	public int addRright(Rright right) {
		
			right.setIsmenu(false);//因为设置数据库的时候弄反了，所以将错就错，false代表是menv
			right.setRighttype(1);
			if(right.getParentid()==0) {
				right.setRighttype(0);
			}
			return rightMapper.insertSelective(right);
	}



	@Override
	public int deleteRright(Integer rightid) {
		int count=-1;
		count=rightMapper.deleteByPrimaryKey(rightid);
		if(count<=0) {
			throw new AppException(203,"删除失败");
		}
		return count;
	}



	@Override
	public int updaterightdnd(Rright right) {
	int count=-1;
		count =rightMapper.updateByPrimaryKeySelective(right);
		
		if(count<=0) {
			throw new AppException(201,"拖动失败");
		}
		return count;
	}



	@Override
	public int deleteFolderRight(Integer rightid) {
		int a,b,count=-1;
		a=rightMapper.deleteByPrimaryKey(rightid);
		RrightExample example=new RrightExample();
		example.createCriteria().andParentidEqualTo(rightid);
		b=rightMapper.deleteByExample(example);
		count=a+b;
		
		
		if(count<=1) {
			throw new AppException(201,"删除文件夹失败");
		}
		
		
		return count;
	}



	@Override
	public List<Rright> findRrightsForCurd(Integer roleid, Integer parentid) {
		return rightMapper.findRrightsForCurd(roleid, parentid);
	}



	@Override
	public boolean findCheckResultByrighttext(String righttext) {
		boolean result=false;
		
		RrightExample example=new RrightExample();
		example.createCriteria().andRighttextEqualTo(righttext);
		List<Rright> list=rightMapper.selectByExample(example);
		if(list==null||list.size()==0) {
			result=true;//找不到证明页面输入不重复，可是使用，返回true
		}
		return result;
		
	}

}
