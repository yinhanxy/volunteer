package com.topstar.volunteer.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.FileUtils;
import com.topstar.volunteer.util.MimeTypeUtils;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * SpringMVC中的文件上传
 * @ClassName: FileController
 * @Description: TODO(图片,文件控制器)
 * @see 第一步：由于SpringMVC使用的是commons-fileupload实现，故将其组件引入项目中
 * @see 这里用到的是commons-fileupload-1.2.2.jar和commons-io-2.0.1.jar
 * @see 第二步：在springmvc.xml中配置MultipartResolver处理器。可在此加入对上传文件的属性限制
 * @see 第三步：在Controller的方法中添加MultipartFile参数。该参数用于接收表单中file组件的内容
 * @see 第四步：编写前台表单。注意enctype="multipart/form-data"以及<input type="file" name=
 *      "****"/>
 * @date 2017年7月3日 下午3:18:10
 */
@Controller
public class FileController {
	
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * 跳转到上传头像页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/file/uploadAvatar.html")
	public String toUploadAvatar(HttpServletRequest request, HttpServletResponse response) {
		return "/serviceteam/serviceteam/uploadAvatar";
	}
	
	/**
	 * @Title: uploadFile   
	 * @Description: TODO( 图片上传,文件上传)   
	 * @param request
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws TPSException
	 */
	@RequestMapping(value = "/file/upload.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String uploadFile(HttpServletRequest request,@RequestParam(value="file") MultipartFile file,HttpServletResponse response)throws IOException, TPSException{
		ResultData data = new ResultData();
		if (file.isEmpty()) {
			data.setSuccess(false);
			data.setMessage("文件内容不允许为空");
			return data.toJSONString();
		}
		byte[] bytes = file.getBytes();
		String fileExt =FileUtils.getFileSuffix(file.getOriginalFilename());
		if (!fileCanUpload(fileExt)) {
			data.setSuccess(false);
			data.setMessage("不允许上传的文件类型"+fileExt);
			return data.toJSONString();
		}
		long fileSize = bytes.length/1024;
		if (fileSize > Integer.valueOf(ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE"))) {
			data.setSuccess(false);
			data.setMessage("文件大小不能超过"+ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE")+"KB");
			return data.toJSONString();
		}
		//得到临时路径
		String uploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) dirPath.mkdirs();
		//得到文件结构
		String tempName =FileUtils.getFilePath();
	    uploadDir +=File.separator+tempName;
		dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		//得到存放路径并见文件夹
		String uploadRealDir =ConfigUtils.getConfigContentByName("UPLOADPATH");
		File dirRealPath = new File(uploadRealDir);
		if (!dirRealPath.exists()) dirRealPath.mkdirs();
	    uploadRealDir +=File.separator+tempName;
		dirRealPath = new File(uploadRealDir);
		if (!dirRealPath.exists()) dirRealPath.mkdirs();
		
		//得到生成的文件名称
		String fileName =FileUtils.generateFileName(file.getOriginalFilename());
		String picPath =tempName +File.separator+fileName;
		logger.debug("上传文件的相对路径picPath路径为:{}",picPath);
		File uploaderFile = new File(uploadDir+ File.separator+ fileName);
		FileCopyUtils.copy(bytes,uploaderFile);
		data.setSuccess(true);
		data.setMessage("上传成功");
		data.put("picPath", picPath);
		data.put("picName", file.getOriginalFilename());
		data.put("fileSize", file.getSize());
		return data.toJSONString();
	}
	
	/**
	 * @Title: uploadImage   
	 * @Description: 图片上传   
	 * @param request
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws TPSException
	 */
	@RequestMapping(value = "/file/uploadImage.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String uploadImage(HttpServletRequest request,@RequestParam(value="file") MultipartFile file,HttpServletResponse response)throws IOException, TPSException{
		ResultData data = new ResultData();
		if (file.isEmpty()) {
			data.setSuccess(false);
			data.setMessage("文件内容不允许为空");
			return data.toJSONString();
		}
		byte[] bytes = file.getBytes();
		String fileExt =FileUtils.getFileSuffix(file.getOriginalFilename());
		if (!imageCanUpload(fileExt)) {
			data.setSuccess(false);
			data.setMessage("不允许上传的文件类型"+fileExt);
			return data.toJSONString();
		}
		long fileSize = bytes.length/1024;
		if (fileSize > Integer.valueOf(ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE"))) {
			data.setSuccess(false);
			data.setMessage("文件大小不能超过"+ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE")+"KB");
			return data.toJSONString();
		}
		//得到临时路径
		String uploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) dirPath.mkdirs();
		//得到文件结构
		String tempName =FileUtils.getFilePath();
	    uploadDir +=File.separator+tempName;
		dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		//得到存放路径并见文件夹
		String uploadRealDir =ConfigUtils.getConfigContentByName("UPLOADPATH");
		File dirRealPath = new File(uploadRealDir);
		if (!dirRealPath.exists()) dirRealPath.mkdirs();
	    uploadRealDir +=File.separator+tempName;
		dirRealPath = new File(uploadRealDir);
		if (!dirRealPath.exists()) dirRealPath.mkdirs();
		
		//得到生成的文件名称
		String fileName =FileUtils.generateFileName(file.getOriginalFilename());
		String picPath =tempName +File.separator+fileName;
		logger.debug("上传文件的相对路径picPath路径为:{}",picPath);
		File uploaderFile = new File(uploadDir+ File.separator+ fileName);
		FileCopyUtils.copy(bytes,uploaderFile);
		data.setSuccess(true);
		data.setMessage("上传成功");
		data.put("picPath", picPath);
		data.put("picName", file.getOriginalFilename());
		data.put("fileSize", file.getSize());
		return data.toJSONString();
	}
	
	/**
	 * @Title: fileCanUpload   
	 * @Description: TODO(判断文件类型是否是设置的类型)   
	 * @param fileExt
	 * @return 
	 */
	private boolean fileCanUpload(String fileExt){
		//取得设置的文件类型
		String [] allowUploadFileExts=ConfigUtils.getConfigContentByName("ALLOWUPLOAD_FILEEXT").split(",");
		for(int i=0;i<allowUploadFileExts.length;i++){
			if(allowUploadFileExts[i].trim().equalsIgnoreCase(fileExt)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @Title: imageCanUpload   
	 * @Description: TODO(判断图片类型是否是设置的类型)   
	 * @param fileExt
	 * @return 
	 */
	private boolean imageCanUpload(String fileExt){
		//取得设置的文件类型
		String [] allowUploadFileExts=ConfigUtils.getConfigContentByName("ALLOWUPLOAD_IMAGEEXT").split(",");
		for(int i=0;i<allowUploadFileExts.length;i++){
			if(allowUploadFileExts[i].trim().equalsIgnoreCase(fileExt)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @Title: readPic   
	 * @Description: TODO(读取本地图片)   
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/file/readPic.do")
	private @ResponseBody String readPic(HttpServletRequest request, HttpServletResponse response){
		String ataUrl = ActionContext.getActionContext().getParameterAsString("ataUrl");
		String storage = ConfigUtils.getConfigContentByName("UPLOADPATH");
		String imagePath = storage+File.separator+ataUrl;
		FileInputStream in = null;
		ServletOutputStream os = null;
		try {
			if(StringUtils.isBlank(ataUrl)){
				return null;
			}
			if(ataUrl.contains("../")||ataUrl.contains("..\\")){
				return null;
			}
			logger.debug("读取上传文件的真实路径ataUrl:{}",ataUrl);
			File file = new File(imagePath);
			if(file.exists() && file.isFile()){
				in = new FileInputStream(new File(imagePath));
				response.setContentType("multipart/form-data");  
				int i = in.available();//得到文件大小
				byte[] data = new byte[i];
				in.read(data);   //读数据
				response.setContentType("image/*"); //设置返回的文件类型
				os = response.getOutputStream(); //得到向客户端输出二进制数据的对象
				os.write(data); //输出数据
			}else{
				logger.error( "要查看的文件"+imagePath+"不存在!");
			}
		} catch (Exception e) {
			logger.error("读取真实路径图片["+imagePath+"]错误",e);
		}finally {
			try {
				if (in!=null) in.close();
				if (os!=null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * @Title: readPic   
	 * @Description: TODO(读取临时路径图片)   
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/file/readTempPic.do")
	private @ResponseBody String readTempPic(HttpServletRequest request, HttpServletResponse response){
		String ataUrl = ActionContext.getActionContext().getParameterAsString("ataUrl");
		String storage = ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		String imagePath = storage+File.separator+ataUrl;
		FileInputStream in = null;
		ServletOutputStream os = null;
		try {
			if(StringUtils.isBlank(ataUrl)){
				return null;
			}
			if(ataUrl.contains("../")||ataUrl.contains("..\\")){
				return null;
			}
			
			logger.debug("读取上传文件的临时路径ataUrl:{}",imagePath);
			File file = new File(imagePath);
			if(file.exists() && file.isFile()){
				in = new FileInputStream(file);
				response.setContentType("multipart/form-data");  
				int i = in.available();//得到文件大小
				byte[] data = new byte[i];
				in.read(data);   //读数据
				response.setContentType("image/*"); //设置返回的文件类型
				os = response.getOutputStream(); //得到向客户端输出二进制数据的对象
				os.write(data); //输出数据
			}else{
				logger.error( "要查看的文件"+imagePath+"不存在!");
			}
		} catch (Exception e) {
			logger.error("读取临时路径图片["+imagePath+"]错误",e);
		}finally {
			try {
				if (in!=null) in.close();
				if (os!=null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * 读取临时路径图片
	 * @param request name 文件名称
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/file/showTempPic.do")
	private @ResponseBody String showTempPic(HttpServletRequest request, HttpServletResponse response){
		String name = ActionContext.getActionContext().getParameterAsString("name");
		String storage = ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		String filePath = FileUtils.getUploadFilePath(name);
		String imagePath = storage+File.separator+filePath;
		FileInputStream in = null;
		ServletOutputStream os = null;
		try {
			if(StringUtils.isBlank(filePath)){
				return null;
			}
			if(filePath.contains("../")||filePath.contains("..\\")){
				return null;
			}
			
			logger.debug("读取上传文件的临时路径ataUrl:{}",imagePath);
			File file = new File(imagePath);
			if(file.exists() && file.isFile()){
				in = new FileInputStream(file);
				response.setContentType("multipart/form-data");  
				int i = in.available();//得到文件大小
				byte[] data = new byte[i];
				in.read(data);   //读数据
				response.setContentType("image/*"); //设置返回的文件类型
				os = response.getOutputStream(); //得到向客户端输出二进制数据的对象
				os.write(data); //输出数据
			}else{
				logger.error( "要查看的文件"+imagePath+"不存在!");
			}
		} catch (Exception e) {
			logger.error("读取临时路径图片["+imagePath+"]错误",e);
		}finally {
			try {
				if (in!=null) in.close();
				if (os!=null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * 读取临时路径图片
	 * @param request name 文件名称
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/file/showPic.do")
	private @ResponseBody String showPic(HttpServletRequest request, HttpServletResponse response){
		String name = ActionContext.getActionContext().getParameterAsString("name");
		String storage = ConfigUtils.getConfigContentByName("UPLOADPATH");
		String filePath = FileUtils.getUploadFilePath(name);
		String imagePath = storage+File.separator+filePath;
		FileInputStream in = null;
		ServletOutputStream os = null;
		try {
			if(StringUtils.isBlank(filePath)){
				return null;
			}
			if(filePath.contains("../")||filePath.contains("..\\")){
				return null;
			}
			
			logger.debug("读取上传文件的临时路径ataUrl:{}",imagePath);
			File file = new File(imagePath);
			if(file.exists() && file.isFile()){
				in = new FileInputStream(file);
				response.setContentType("multipart/form-data");  
				int i = in.available();//得到文件大小
				byte[] data = new byte[i];
				in.read(data);   //读数据
				response.setContentType("image/*"); //设置返回的文件类型
				os = response.getOutputStream(); //得到向客户端输出二进制数据的对象
				os.write(data); //输出数据
			}else{
				logger.error( "要查看的文件"+imagePath+"不存在!");
			}
		} catch (Exception e) {
			logger.error("读取正式路径图片["+imagePath+"]错误",e);
		}finally {
			try {
				if (in!=null) in.close();
				if (os!=null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @throws TPSException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/file/download.html")
	public void download(HttpServletRequest request,HttpServletResponse response) throws TPSException, UnsupportedEncodingException{
		String fileName=request.getParameter("fileName");
		if(fileName.contains("../")||fileName.contains("..\\")){
			throw new TPSException("非法的文件名");
		}
		fileName=FileUtils.getUploadFilePath(fileName);
		String sourceName = StringTools.getParameter(request,"sourceName","");
		String uploadDir = ConfigUtils.getConfigContentByName("UPLOADPATH");
		File file=new File(uploadDir+File.separator+fileName);
		if(!file.exists()){
			throw new TPSException("文件不存在");
		}
		downloadFile(request,response,uploadDir, fileName,sourceName);
	}
	
	/**
	 * 临时文件下载
	 * @param request
	 * @param response
	 * @throws TPSException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/file/tempDownload.html")
	public void tempDownload(HttpServletRequest request,HttpServletResponse response) throws TPSException, UnsupportedEncodingException{
		String fileName=request.getParameter("fileName");
		if(fileName.contains("../")||fileName.contains("..\\")){
			throw new TPSException("非法的文件名");
		}
		fileName=FileUtils.getUploadFilePath(fileName);
		String sourceName = StringTools.getParameter(request,"sourceName","");
		String uploadDir = ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		File file=new File(uploadDir+File.separator+fileName);
		if(!file.exists()){
			throw new TPSException("文件不存在");
		}
		downloadFile(request,response,uploadDir, fileName,sourceName);
	}
	
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @param uploadDir 文件存放配置根路径
	 * @param fileName 文件存储相对路径
	 * @param sourceName 原文件名
	 * @throws TPSException
	 * @throws UnsupportedEncodingException
	 */
	private void downloadFile(HttpServletRequest request,HttpServletResponse response,String uploadDir,String fileName,String sourceName) throws TPSException, UnsupportedEncodingException{
		String sep = System.getProperty("file.separator");
		if(StringUtils.isBlank(fileName)){
			return;
		}
		if(fileName.contains("../")||fileName.contains("..\\")){
			throw new TPSException("非法的文件名");
		}
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("multipart/form-data");
		String userAgent = request.getHeader("User-Agent"); 
		//针对IE或者以IE为内核的浏览器：
		 if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
			 sourceName = java.net.URLEncoder.encode(sourceName, "UTF-8");
		 } else {
		 //非IE浏览器的处理：
			 sourceName = new String(sourceName.getBytes("UTF-8"),"ISO-8859-1");
		 }
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",sourceName));
		File file=new File(uploadDir+sep+fileName);
		try{
			FileInputStream inputStream = new FileInputStream(file);
	        byte[] data = new byte[(int)file.length()];
			inputStream.read(data);
	        inputStream.close();
	        String mimeType=MimeTypeUtils.getMimeType(fileName);
	        response.setContentType(mimeType+";charset=utf-8");
	        OutputStream stream = response.getOutputStream();
	        stream.write(data);
	        stream.flush();
	        stream.close();
		}catch(Exception ex){
			throw new TPSException("读取文件异常");
		}
	}
}
