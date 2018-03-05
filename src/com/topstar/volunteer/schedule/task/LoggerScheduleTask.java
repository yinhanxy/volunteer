package com.topstar.volunteer.schedule.task;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.schedule.QuartzJob;
import com.topstar.volunteer.service.LoggerBakService;
import com.topstar.volunteer.service.LoggerService;
import com.topstar.volunteer.web.context.SpringContextHolder;

public class LoggerScheduleTask extends QuartzJob{

	Logger logger=LoggerFactory.getLogger(LoggerScheduleTask.class);
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	
	public void logger(){
		LoggerBakService loggerBakService = (LoggerBakService) SpringContextHolder.getBean(LoggerBakService.class);
		int addResult= loggerBakService.addLoggerBak();
		if(addResult>0){
			logger.debug("向loggerBak添加了{}个日志",addResult);
		}
		LoggerService loggerService = (LoggerService) SpringContextHolder.getBean(LoggerService.class);
		int delResult = loggerService.delLoggersThree();
		if(delResult>0){
			logger.debug("logger删除了{}个日志",delResult);
		}
	}
	
	@Override
	protected void runJob() throws Exception {
		try{
			logger();
		}catch (Exception e) {
			logger.error("将日志移动到日志备份表出错",e);
		}
	}

}
