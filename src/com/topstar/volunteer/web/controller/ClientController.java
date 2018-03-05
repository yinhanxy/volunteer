package com.topstar.volunteer.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.util.LeadingInExcel;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 上传下载Excel控制器
 */
@Controller
public class ClientController {
	
	private static Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	private List<Volunteer> uploadAndRead=null;
	
	@Autowired
	private VolunteerService volunteerService;
	
	public List<Volunteer> getUploadAndRead() {
		return uploadAndRead;
	}

	public void setUploadAndRead(List<Volunteer> uploadAndRead) {
		this.uploadAndRead = uploadAndRead;
	}

	/**
	 * @Title: toShowVols   
	 * @Description: TODO(展示将要上传的表格信息)   
	 * @param file
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/record/toShowVols.html",method = RequestMethod.POST)
	public String toShowVols(@RequestParam(value="filename") MultipartFile file,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		//局部变量
		LeadingInExcel<Volunteer> testExcel=null;
        boolean judgement = false;
        //定义需要读取的数据
        String formart = "yyyy-MM-dd";
        Map<String, String> titleAndAttribute=null;
        Class<Volunteer> clazz=Volunteer.class;
        //定义对应的标题名与对应属性名
        titleAndAttribute=new HashMap<String, String>();
        titleAndAttribute.put("姓名", "userName");
        titleAndAttribute.put("性别", "sex");
        titleAndAttribute.put("手机号", "mobile");
        titleAndAttribute.put("身份证号", "idcard");
        //调用解析工具包
        testExcel=new LeadingInExcel<Volunteer>(formart);
        try {
			uploadAndRead= testExcel.upLoadAndRead(file, 0, titleAndAttribute, clazz);
		} catch (Exception e) {
			logger.error("读取Excel文件错误！",e);
		}
        
        if(uploadAndRead != null && !"[]".equals(uploadAndRead.toString()) && uploadAndRead.size()>=1){
            judgement = true;
        }
        if (judgement) {
        	List<Volunteer> volList = uploadAndRead;
        	model.addAttribute("volList", volList);
        }
		return "/record/train/showVols";
	}
	
	
	/**
	 * Excel上传
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/record/upload.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String upLoad(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) throws IOException{
			ResultData data = new ResultData();
			//局部变量
			LeadingInExcel<Volunteer> testExcel=null;
	        boolean judgement = false;
	        //定义需要读取的数据
	        String formart = "yyyy-MM-dd";
	        int sheetIndex = 0;
	        Map<String, String> titleAndAttribute=null;
	        Class<Volunteer> clazz=Volunteer.class;
	        //定义对应的标题名与对应属性名
            titleAndAttribute=new HashMap<String, String>();
            titleAndAttribute.put("姓名", "userName");
            titleAndAttribute.put("性别", "sex");
            titleAndAttribute.put("联系方式", "mobile");
            titleAndAttribute.put("身份证号", "idcard");
            //调用解析工具包
            testExcel=new LeadingInExcel<Volunteer>(formart);
            try {
				uploadAndRead= testExcel.upLoadAndRead(file, sheetIndex, titleAndAttribute, clazz);
				List<Volunteer> volList = uploadAndRead;
				data.put("volList", volList);
            } catch (Exception e) {
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("读取Excel文件错误！",e);
				return data.toJSONString();
			}
            if(uploadAndRead != null && !"[]".equals(uploadAndRead.toString()) && uploadAndRead.size()>=1){
                judgement = true;
            }else{
            	data.setSuccess(false);
				data.setMessage("上传Excel出错，数据为空");
				return data.toJSONString();
            }
            if (judgement) {
            	List<Volunteer> volList = uploadAndRead;
            	data.setSuccess(true);
            	data.put("volList", volList);
            	return data.toJSONString();
//            	
            	//把信息分为每100条数据为一组迭代添加（注：将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。
//            	int listSize=uploadAndRead.size();
//            	int toIndex=100;
//            	int count =0;
//            	for (int i = 0; i < listSize; i+=100) {
//					if (i+100>listSize) {
//						 toIndex=listSize-i;
//					}
//					List<Volunteer> volList = uploadAndRead.subList(i, i+toIndex);
//					
//					count = count + volList.size();
//					System.out.println("volList长度："+volList.size()+"\t总长度："+count);
//					for (Volunteer volunteer : volList) {
//						System.out.println("姓名："+volunteer.getUserName()
//						+"\t性别："+volunteer.getSex()+"\t联系方式："+volunteer.getMobile()+"\t身份证号："+volunteer.getIdcard()+"\t出身日期："+volunteer.getBirthday()+"\t邮政编码："+volunteer.getPostcode());
//					}
					
//					data.setSuccess(true);
//	            	data.put("volList", volList);
//				}
            	
            }
			return data.toJSONString();
	}
	
	@RequestMapping(value ="/record/upLoadVols.do",method = RequestMethod.POST)
	public @ResponseBody String upLoadVols(HttpServletRequest request,HttpServletResponse response){
		Long trainId= ActionContext.getActionContext().getParameterAsLong("trainId");
		ResultData data = new ResultData();
		List<Volunteer> volList = uploadAndRead;
		data.put("volList", volList);
		if (volList!=null && volList.size()>0) {
			boolean res = volunteerService.addVols(volList,trainId);
			System.out.println("res:"+res+",volList:"+volList);
			if (res) {
				data.setSuccess(true);
				data.setMessage("上传成功");
				return data.toJSONString();
			}
			data.setSuccess(false);
			data.setMessage("上传失败");
			return data.toJSONString();
		}else{
			data.setSuccess(false);
			data.setMessage("上传数据为空");
			return data.toJSONString();
		}
	}
}
