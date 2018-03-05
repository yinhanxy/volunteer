package com.topstar.volunteer.schedule.task;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.schedule.QuartzJob;
import com.topstar.volunteer.service.MonitorService;
import com.topstar.volunteer.web.context.SpringContextHolder;

/**
 * 系统监控定时任务
 * @Date 2017-9-3 10:18:02
 */
public class MonitorScheduleTask extends QuartzJob{

	Logger logger=LoggerFactory.getLogger(MonitorScheduleTask.class);
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	
	public void addMonitors() throws TPSException{
		MonitorService monitorService=(MonitorService)SpringContextHolder.getBean(MonitorService.class);
		try {
			boolean result= monitorService.addMonitorInfo();
			if (result) {
				logger.debug("添加系统监控折线图信息成功");
			} 
		} catch (Exception e) {
			logger.debug("添加系统监控折线图信息失败",e);
		}
	}
	
	@Override
	protected void runJob() throws Exception {
		try {
			addMonitors();
		} catch (Exception e) {
			logger.error("添加折线图数据出错",e);
		}
	}

}
