package com.topstar.volunteer.schedule.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.schedule.QuartzJob;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.web.context.SpringContextHolder;

public class ActivityScheduleTask extends QuartzJob{

	Logger logger=LoggerFactory.getLogger(ActivityScheduleTask.class);
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	
	
	public void updateActivityStatus(){
		ActivityService activityService = (ActivityService) SpringContextHolder.getBean(ActivityService.class);
		int toRecruitResult=activityService.toRecruitStatus();
		if(toRecruitResult>0){
			logger.debug("当前有{}个志愿者活动进入'招募中'状态",toRecruitResult);
		}
		int toWaitDoResult=activityService.toWaitDoStatus();
		if(toWaitDoResult>0){
			logger.debug("当前有{}个志愿者活动进入'待进行'状态",toWaitDoResult);
		}
		int toDoingResult=activityService.toDoingStatus();
		if(toDoingResult>0){
			logger.debug("当前有{}个志愿者活动进入'进行中'状态",toDoingResult);
		}
		int toEndResult=activityService.toEndStatus();
		if(toEndResult>0){
			logger.info("当前有{}个志愿者活动进入'已完成'状态",toEndResult);
		}
	};
	
	@Override
	protected void runJob() throws Exception {
		try{
			updateActivityStatus();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.UPDATEACTIVITYSTATUS, "更新活动状态的定时任务出错【"+e.getMessage()+"】",null,null);
			logger.error("定时更新活动状态出现内部错误",e);
		}
	};

	
}
