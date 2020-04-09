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
	 * ����restclient���Ե�ʱ������û�е�¼�û���û��session��shiro������д�����������ã�ҳ��ᱻ��ص�¼ҳ��
	 * ���ǿ��԰������ļ���һ��ע�͵�����д�������İ�mvc.xml�е�������ע�͵���shiro��ܵİѵ�shiro.xml	<!--  
				/static/**=anon
				/=anon
				/users/login=anon
				/**=authc   -->ע�͵���
	 * һ��shiro���ǵ���Ŀ����������ǲżӽ�ȥ�ģ��������ڿ������Ե�
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

			// ��ȡ��һ��sheet
			HSSFSheet sheet = workbook.getSheetAt(0);

			List<BcRegion> regions = new ArrayList<>();
			for (Row row : sheet) { // sheet--rows

				if (row.getRowNum() == 0) {
					continue;// ������һ��
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
			workbook.close();//����Ҫ��
		}
		AppResult result = new AppResult(200, "����ɹ�", null);
		return result;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/region/downloadregion", method = RequestMethod.GET)//getҳ�淢����ͬ������
	public void downloadRegion(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//�����ݿ�������ص�����
		List<BcRegion> regions=regionService.findAll();
		
		//����excel
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		//����sheet
		HSSFSheet sheet=workbook.createSheet("�����������");
		
		
		//����������
		Row headRow=sheet.createRow(0);
		headRow.createCell(0).setCellValue("������");
		headRow.createCell(1).setCellValue("ʡ��");																											
		headRow.createCell(2).setCellValue("����");																											
		headRow.createCell(3).setCellValue("����");																											
		headRow.createCell(4).setCellValue("�ʱ�");																											

		
		//һ��һ�еز�������
		for(BcRegion region:regions) {
			Row row=sheet.createRow(sheet.getLastRowNum()+1);//�����һ�к��洴������
			row.createCell(0).setCellValue(region.getId());
			row.createCell(1).setCellValue(region.getProvince());
			row.createCell(2).setCellValue(region.getCity());
			row.createCell(3).setCellValue(region.getDistrict());
			row.createCell(4).setCellValue(region.getPostcode());
		}
		
		/**
		 * ����������Ծ�������Ŷ
		 */

		String filename="����.xls";//Ĭ���ļ����������ַ(���ص�ַ)����Ҫ�����֣���������������������
		
		//���÷����ַ���
		response.setCharacterEncoding("utf-8");
		//���÷��ظ�ʽ����
		//response.setContentType("application/vnd.ms-excel");//�Լ��ٶ� response excel
		String contentType=request.getServletContext().getMimeType(filename);
		response.setContentType(contentType);
		
		//�������Ӳ��л����ȱʧ��������
		//���ø�������
		//response.addHeader("Content-Disposition", "attachment;filename="+filename);// ����ļ�������ʽ��ֵ�԰ٶȡ�response ��Ӧͷ �������֡�!!!!!!!!!!!!!!
		
		//��������ȱʧ����ԭ���ļ�����ͨ��url���䡣��ַ����������
		
		
		//��ͨ����ַ���أ���Ҫ�ñ���������ת��
		//URLEncoder.encode(filename, "utf-8");
		//�÷����Ѿ�����˾��󲿷������������������⣬����Ψ�������������ǻ��������
		//response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
		
		//�������ǿ���ͨ������ͷ��ÿͻ������������
		String agent=request.getHeader("User-Agent");
		//System.out.println(agent);
		//��ͨ�������ദ��ͬ����������⣬������Ӧ���ַ���(��������������)
		response.addHeader("Content-Disposition", "attachment;filename="+FileUtils.encodeDownloadFilename(filename, agent));
		
		
		
		//д��ȥ
		workbook.write(response.getOutputStream());
		
		
	}
	
	
	
	

}
