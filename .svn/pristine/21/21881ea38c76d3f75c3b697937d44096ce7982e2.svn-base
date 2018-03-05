package com.topstar.volunteer.schedule.task;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.schedule.QuartzJob;
import com.topstar.volunteer.service.MonitorService;
import com.topstar.volunteer.web.context.SpringContextHolder;

/**
 * 
 * @ClassName:  MoniDelScheduleTask   
 * @Description:TODO(删除折线图数据定时任务)   
 * @date:   2017年9月4日 下午3:22:53   
 *
 */
public class MoniDelScheduleTask extends QuartzJob{

	Logger logger=LoggerFactory.getLogger(MonitorScheduleTask.class);
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	
	public void delMonitors() throws TPSException{
		MonitorService monitorService=(MonitorService)SpringContextHolder.getBean(MonitorService.class);
		try {
			boolean result= monitorService.delMonitorInfo();
			if (result) {
				logger.debug("删除系统监控折线图信息成功");
			} 
		} catch (Exception e) {
			logger.debug("删除系统监控折线图信息失败",e);
		}
		
	}

	
	@Override
	protected void runJob() throws Exception {
		try {
			delMonitors();
		} catch (Exception e) {
			logger.error("删除折线图数据出错",e);
		}
		
	}

}
