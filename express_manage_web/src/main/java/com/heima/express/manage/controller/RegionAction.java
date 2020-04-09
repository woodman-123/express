package com.heima.express.manage.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.heima.express.common.AppResult;
import com.heima.express.manage.client.BcRegion;
import com.heima.express.manage.client.RegionService;
import com.heima.express.utils.FileUtils;
import com.heima.express.utils.PinYin4jUtils;

@Controller
public class RegionAction {

	@Autowired
	private RegionService regionService;
	
	/**
	 * 在用restclient测试的时候由于没有登录用户，没有session，shiro或者手写拦截器的作用，页面会被打回登录页面
	 * 我们可以把配置文件的一段注释掉（手写拦截器的把mvc.xml中的拦截器注释掉，shiro框架的把的shiro.xml	<!--  
				/static/**=anon
				/=anon
				/users/login=anon
				/**=authc   -->注释掉）
	 * 一般shiro都是等项目基本万成我们才加进去的，这有利于开发测试等
	 * @param q
	 * @return
	 */
	
	
	
	
	@RequestMapping(value = "/region/findregionbykey", method = RequestMethod.POST)
	@ResponseBody
	public List<BcRegion> findRegionByKey(String q) {
		
		 return regionService.findRegionByKey(q);

		
	}
	
	
	
	
	

	@RequestMapping(value = "/region/uploadregion", method = RequestMethod.POST)
	@ResponseBody
	public AppResult uploadRegion(MultipartFile myfile) throws IOException {
		System.out.println(myfile.getOriginalFilename());

		// excel
		HSSFWorkbook workbook=null;
		try {
			workbook = new HSSFWorkbook(myfile.getInputStream());

			// 获取第一个sheet
			HSSFSheet sheet = workbook.getSheetAt(0);

			List<BcRegion> regions = new ArrayList<>();
			for (Row row : sheet) { // sheet--rows

				if (row.getRowNum() == 0) {
					continue;// 跳过第一行
				}

				BcRegion region = new BcRegion();

				String shortcode = "";
				for (Cell cell : row) { // row--cells

					int index = cell.getColumnIndex();
					String value = cell.getStringCellValue();

					if (index == 0) {
						region.setId(value);
					} else if (index == 1) {
						region.setProvince(value);
					} else if (index == 2) {
						region.setCity(value.substring(0, value.length() - 1));
						shortcode += PinYin4jUtils.getHeadByString1(region.getCity(), false);
					} else if (index == 3) {
						region.setDistrict(value);
						shortcode += PinYin4jUtils.getHeadByString1(value.substring(0, value.length() - 1), false);
					} else if (index == 4) {
						region.setPostcode(value);
						;
					}
				}

				region.setShortcode(shortcode);
				region.setCitycode(PinYin4jUtils.hanziToPinyin(region.getCity(), ""));
				System.out.println(region);
				regions.add(region);
			}

			regionService.addRegions(regions);
		} catch (IOException e) {
			throw e;
		}finally {
			workbook.close();//用完要关
		}
		AppResult result = new AppResult(200, "导入成功", null);
		return result;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/region/downloadregion", method = RequestMethod.GET)//get页面发的是同步请求
	public void downloadRegion(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//到数据库查找下载的数据
		List<BcRegion> regions=regionService.findAll();
		
		//创建excel
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		//创建sheet
		HSSFSheet sheet=workbook.createSheet("行政区域管理");
		
		
		//创建标题行
		Row headRow=sheet.createRow(0);
		headRow.createCell(0).setCellValue("区域编号");
		headRow.createCell(1).setCellValue("省份");																											
		headRow.createCell(2).setCellValue("城市");																											
		headRow.createCell(3).setCellValue("区域");																											
		headRow.createCell(4).setCellValue("邮编");																											

		
		//一行一行地插入数据
		for(BcRegion region:regions) {
			Row row=sheet.createRow(sheet.getLastRowNum()+1);//在最后一行后面创建新行
			row.createCell(0).setCellValue(region.getId());
			row.createCell(1).setCellValue(region.getProvince());
			row.createCell(2).setCellValue(region.getCity());
			row.createCell(3).setCellValue(region.getDistrict());
			row.createCell(4).setCellValue(region.getPostcode());
		}
		
		/**
		 * 下面这段面试经常会问哦
		 */

		String filename="区域.xls";//默认文件名是请求地址(下载地址)，需要改名字！！！！！！！！！！！
		
		//设置返回字符集
		response.setCharacterEncoding("utf-8");
		//设置返回格式类型
		//response.setContentType("application/vnd.ms-excel");//自己百度 response excel
		String contentType=request.getServletContext().getMimeType(filename);
		response.setContentType(contentType);
		
		//中文名子不行会出现缺失或者乱码
		//设置附件名字
		//response.addHeader("Content-Disposition", "attachment;filename="+filename);// 添加文件名，格式键值对百度‘response 响应头 附件名字’!!!!!!!!!!!!!!
		
		//产生中文缺失乱码原因，文件名字通过url传输。地址不能有中文
		
		
		//想通过地址传回，需要用编码器进行转码
		//URLEncoder.encode(filename, "utf-8");
		//该方法已经解决了绝大部分浏览器中文乱码的问题，但是唯独火狐浏览器还是会出现乱码
		//response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
		
		//所以我们可以通过请求头获得客户端浏览器类型
		String agent=request.getHeader("User-Agent");
		//System.out.println(agent);
		//再通过工具类处理不同浏览器的问题，返回相应的字符串(火狐和其他浏览器)
		response.addHeader("Content-Disposition", "attachment;filename="+FileUtils.encodeDownloadFilename(filename, agent));
		
		
		
		//写回去
		workbook.write(response.getOutputStream());
		
		
	}
	
	
	
	

}
