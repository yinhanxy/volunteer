package com.topstar.volunteer.schedule.task;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.schedule.QuartzJob;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.DateUtil;
import com.topstar.volunteer.web.context.SpringContextHolder;

public class TempFileScheduleTask extends QuartzJob{

	Logger logger=LoggerFactory.getLogger(TempFileScheduleTask.class);
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	
	
	public void deleteTempFile(){

		//得到临时路径
		String uploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");

		Calendar startTime=Calendar.getInstance();
		Calendar endTime=Calendar.getInstance();
		
		//保留临时文件的起始时间(前两个月)
		startTime.add(Calendar.MONTH, -2);
		
		//期间生成对应的上传临时文件的二级目录名称
		final String startTimeFile = DateUtil.format(startTime.getTime(), "yyyyMM");
		final String endTimeFile = DateUtil.format(endTime.getTime(), "yyyyMM");
		
		File uploadFile = new File(uploadDir);
		if(uploadFile.isDirectory() && uploadFile.exists()){
			//获取需要删除的临时文件上传二级目录列表
			File[] files=uploadFile.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if(Integer.valueOf(name) < Integer.valueOf(startTimeFile) || Integer.valueOf(name)>Integer.valueOf(endTimeFile))
						return true;
					return false;
				}
			});
			
			if(files!=null && files.length>0){
				for (File file: files) {
					deleteDirectory(file);
					logger.debug("删除两个月前的临时上传二级目录,路径为{}:",file.getAbsolutePath());
				}
			}
		}
		
	};
	
	public void deleteDirectory(File file){
		if(file.isDirectory()){
			File[] files=file.listFiles();
			for (File file2 : files) {
				deleteDirectory(file2);
			}
			file.delete();
		}
		file.delete();
	}
	
	
	@Override
	protected void runJob() throws Exception {
		try{
			deleteTempFile();
		}catch (Exception e) {
			logger.error("定时删除两月前的临时上传文件夹操作出现内部错误",e);
		}
	};

	
}
