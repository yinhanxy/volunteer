package com.topstar.volunteer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.util.secrecy.DESUtil;


public class FileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 获取文件名前缀
     * @param fileName 文件名
     * @return
     */
    public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
    }

    /**
     * 获取文件大写的后缀名
     * @param fileName
     * @return
     */
    public static String getFileSuffixToUpper(String fileName) {
    	return getFileSuffix(fileName).toUpperCase();
    }
    
    /**
	 * 通过当前时间，生成目录结构<br/>
	 * 结构为：yyyyMM/yyyyMMdd<br/>
	 * 比如今天为20150111,目录生成为:201501\20150111
	 * @return 目录结构
	 */
	public static String getFilePath(){
		Date date = new Date();
		String d = DateUtil.format(date, "yyyyMMdd");
		
		String month = d.substring(0,6);
		String day = d.substring(0,8);
		
		StringBuffer filePath = new StringBuffer(month);
		filePath.append(File.separator);
		filePath.append(day);
		
		return filePath.toString();
	}
    
	/**
	 * @Title: generateFileName   
	 * @Description: TODO(生成文件名称)   
	 * @return
	 */
	public static String generateFileName(String fileName) {
		int uuid = new Random().nextInt(89999)+10000;
		return "U0" + DateUtil.format(new Date(), "yyyyMMddHHmmddsss") + uuid
				+ "." + getFileSuffix(fileName)
				;
	}
	
	/**
	 * 通过文件名获取已上传的文件的相对路径
	 * @param fileName 文件名
	 */
	public static String getUploadFilePath(String fileName){
		if(StringUtils.isBlank(fileName)){
			return null;
		}
		String start = getFilePrefix(fileName);
		if(start == null || start.trim().length() < 10){
			return null;
		}
		String filePath = start.substring(2, 8) + File.separator + start.substring(2, 10) + File.separator + fileName;
		return filePath;
	}
	
	
	/**
	 * @Title: copyFile   
	 * @Description: TODO(复制文件)   
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath,String newPath){
		try {
//			int bytesum = 0;
			int byteread = 0;
			File oldFile =new File(oldPath);
			if (oldFile.exists()) { //文件存在时 
				InputStream is= new FileInputStream(oldPath); //读入原文件 
				FileOutputStream os = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = is.read(buffer))!=-1) {
//					   bytesum += byteread;            //字节数 文件大小 
					   os.write(buffer, 0, byteread); 
				}
				os.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    /**
     * 复制文件
     * @param inputFile 源文件
     * @param outputFile 复制的文件
     * @throws FileNotFoundException
     */
//    public static void copyFile(String inputFile, String outputFile)
//	    throws FileNotFoundException {
//		File sFile = new File(inputFile);
//		File tFile = new File(outputFile);
//		FileInputStream fis = new FileInputStream(sFile);
//		FileOutputStream fos = new FileOutputStream(tFile);
//		int temp = 0;
//		try {
//		    while ((temp = fis.read()) != -1) {
//		    	fos.write(temp);
//		    }
//		} catch (IOException e) {
//		    e.printStackTrace();
//		} finally {
//		    try {
//				fis.close();
//				fos.close();
//		    } catch (IOException e) {
//		    	e.printStackTrace();
//		    }
//		}
//    }
    
	/**
	 * 根据给出路径自动选择删除文件或整个文件夹
	 * @param path :文件或文件夹路径
	 * */
	public static void deleteFiles(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (file.isFile()) {
			file.delete();// 删除文件
		} else {
			File[] subFiles = file.listFiles();
			for (File subfile : subFiles) {
				deleteFiles(subfile.getAbsolutePath());// 删除当前目录下的子目录
			}
			file.delete();// 删除当前目录
		}
	}
	
	
	public static void deleteFile(String filePath){
		File file = new File(filePath);
		if (!file.exists() || file.isDirectory()) {
			return;
		}
		file.delete();
	}

	/**
	 * 根据给出路径自动选择复制文件或整个文件夹
	 * @param src :源文件或文件夹路径
	 * @param dest :目标文件或文件夹路径
	 * @throws TPSException 
	 * */
	public static void copyFiles(String src, String dest) throws TPSException {
		File srcFile = new File(src);
		if (srcFile.exists()) {
			if (srcFile.isFile()) {
				writeFileFromInputStream(readFileToInputStream(src), dest);
			} else {
				File[] subFiles = srcFile.listFiles();
				if (subFiles.length == 0) {
					File subDir = new File(dest);
					subDir.mkdirs();
				} else {
					for (File subFile : subFiles) {
						String subDirPath = dest + File.separator + subFile.getName();
						copyFiles(subFile.getAbsolutePath(), subDirPath);
					}
				}
			}
		}
	}
	
	/**
	 * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则删除旧文件
	 * @param path :待创建文件路径
	 * @throws TPSException 
	 * */
	public static File createNewFile(String path) throws TPSException {
		File file = new File(path);
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		} catch (IOException e) {
			logger.debug("错误文件路径："+path);
			throw new TPSException("创建文件错误", e);
		}
		return file;
	}
	
	/**
	 * 将文件输入流写入文件
	 * @throws TPSException 
	 * */
	public static boolean writeFileFromInputStream(InputStream inStream, String path) throws TPSException {
		boolean result = true;
		FileOutputStream out = null;
		try {
			File file = new File(path);
			if(!file.exists()){
				synchronized (file) {
					if(!file.exists()){
						file.createNewFile();
					}
				}
			}
			out = new FileOutputStream(file);
			byte[] data = new byte[1024];
			int num = 0;
			while ((num = inStream.read(data, 0, data.length)) != -1) {
				out.write(data, 0, num);
			}
			data = null;
		} catch (Exception e) {
			result = false;
			throw new TPSException("写入文件["+path+"]错误", e);
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					logger.error("关闭流出错！");
				}
				out = null;
			}
		}
		return result;
	}
	
	/**
	 * 获取文件输入流
	 * @throws TPSException 
	 * */
	public static InputStream readFileToInputStream(String path) throws TPSException {
		InputStream inputStream = null;
		try {
			File file = new File(path);
			inputStream = new FileInputStream(file);
		} catch (IOException e) {
			throw new TPSException("获取文件输入流错误", e);
		}
		return inputStream;
	}
	
	
    /**
     * 加密文件路径
     * @param fileName
     * @return
     * @throws TPSException
     */
    public static String encodeFileName(String fileName) throws TPSException{
    	if(StringUtils.isBlank(fileName))
    		return fileName;
    	return PropertiesUtil.getInstance().getProperty("FILEPREFIX")+DESUtil.encode(decodeFileName(fileName));
    }
    
    /**
     * 解密文件路径
     * @param fileName
     * @return
     * @throws TPSException
     */
    public static String decodeFileName(String fileName) throws TPSException{
    	if(StringUtils.isBlank(fileName))
    		return fileName;
    	String decodeString=PropertiesUtil.getInstance().getProperty("FILEPREFIX");
    	if(fileName.startsWith(decodeString)){
    		fileName= DESUtil.decode(fileName.substring(decodeString.length(), fileName.length()));
    		fileName=decodeFileName(fileName);
    	}
    	return fileName;
    }
    
    public static void main(String[] args) {
		String fileName = "U02017081715221703483108.jpg";
		String filePath = getUploadFilePath(fileName);
		System.out.println(filePath);
	}
}
