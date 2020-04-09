package com.heima.express.manage.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heima.express.manage.entity.BcDecidedzone;
import com.heima.express.manage.entity.BcStaff;
import com.heima.express.manage.entity.BcSubarea;
import com.heima.express.manage.mapper.BcDecidedzoneMapper;
import com.heima.express.manage.mapper.BcStaffMapper;
import com.heima.express.manage.mapper.BcSubareaMapper;
import com.heima.express.manage.mapper.QpNoticebillMapper;
import com.heima.express.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class SubareaMapperTest {

	
	
	@Autowired
	private BcDecidedzoneMapper decidezoneMapper;
	@Autowired
	private BcStaffMapper staffMapper;
	@Autowired
	private BcSubareaMapper subareaMapper;
	@Autowired
	private QpNoticebillMapper qpNoticebillMapper;

	@Test
	public void testSubareaMapper() throws IOException {
		
		List<String> keys=StringUtils.queryWords("广西壮族自治区玉林市北流市凯旋广场桃源");
		
		
		//List<String> keys=StringUtils.queryWords("广东省深圳市宝安区西部硅谷");
		
		System.out.println(keys);
		
		List<BcSubarea> subareas=subareaMapper.selectSubareaByAddresskey(keys);
		
		BcSubarea subarea=subareas.get(0);
		System.out.println(subarea);
		String xqDzid=subarea.getDecidedzoneId();
		System.out.println(xqDzid);
		BcDecidedzone xqDecidedzone=decidezoneMapper.selectByPrimaryKey(xqDzid);
		String StaffId = xqDecidedzone.getStaffId();
		 BcStaff staff = staffMapper.selectByPrimaryKey(StaffId);
		// 数据库中所有字段都有值，查询后返回的java对象部分属性却为null
		// 把方法对应的Mapper.xml文件里的resultType删掉，改为resultMap="BaseResultMap",并配置resultMap

		// 作者：东方舵手
		// 链接：https://www.jianshu.com/p/8208074ed521
		// 来源：简书
		// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
		
	}
	
	
	
	
}
