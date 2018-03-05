package com.topstar.volunteer.schedule.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.schedule.QuartzJob;
import com.topstar.volunteer.service.StarEvaluateService;
import com.topstar.volunteer.web.context.SpringContextHolder;

public class VolunteerStarScheduleTask extends QuartzJob{

	Logger logger=LoggerFactory.getLogger(VolunteerStarScheduleTask.class);
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	
	public void updateVolunteerStar(){
		StarEvaluateService starEvaluateService = (StarEvaluateService ) SpringContextHolder.getBean(StarEvaluateService .class);
		List<VolunteerView> voluteerViews=starEvaluateService.getVolunteerServiceHoursAndStar();
		for (VolunteerView volunteerView : voluteerViews) {
			String s=volunteerView.getStar();
			if(s==null){
				s="0";
			}
			int star=Integer.parseInt(s);
			int hour=volunteerView.getServiceHour().intValue();
			String is_manual=volunteerView.getIsManual();
			if(hour>=100 && hour<300){
				if(star!=1 && "0".equals(is_manual)){
					StarEvaluate starEvaluate=new StarEvaluate();
					starEvaluate.setVolunteerId(volunteerView.getId());
					starEvaluate.setStar("1");
					starEvaluate.setEvaluateUser("system");
					starEvaluate.setEvaluateTime(new Date());
					starEvaluate.setEvaluateContent("系统评定");
					starEvaluateService.insertNotNull(starEvaluate);
				}
			}else if(hour>=300 && hour<600){
				if(star!=2 && "0".equals(is_manual)){
					StarEvaluate starEvaluate=new StarEvaluate();
					starEvaluate.setVolunteerId(volunteerView.getId());
					starEvaluate.setStar("2");
					starEvaluate.setEvaluateUser("system");
					starEvaluate.setEvaluateTime(new Date());
					starEvaluate.setEvaluateContent("系统评定");
					starEvaluateService.insertNotNull(starEvaluate);
				}
			}else if(hour>=600 && hour<1000){
				if(star!=3 && "0".equals(is_manual)){
					StarEvaluate starEvaluate=new StarEvaluate();
					starEvaluate.setVolunteerId(volunteerView.getId());
					starEvaluate.setStar("3");
					starEvaluate.setEvaluateUser("system");
					starEvaluate.setEvaluateTime(new Date());
					starEvaluate.setEvaluateContent("系统评定");
					starEvaluateService.insertNotNull(starEvaluate);
				}
			}else if(hour>=1000 && hour<1500){
				if(star!=4 && "0".equals(is_manual)){
					StarEvaluate starEvaluate=new StarEvaluate();
					starEvaluate.setVolunteerId(volunteerView.getId());
					starEvaluate.setStar("4");
					starEvaluate.setEvaluateUser("system");
					starEvaluate.setEvaluateTime(new Date());
					starEvaluate.setEvaluateContent("系统评定");
					starEvaluateService.insertNotNull(starEvaluate);
				}
			}else if(hour>=1500){
				if(star!=5 && "0".equals(is_manual)){
					StarEvaluate starEvaluate=new StarEvaluate();
					starEvaluate.setVolunteerId(volunteerView.getId());
					starEvaluate.setStar("5");
					starEvaluate.setEvaluateUser("system");
					starEvaluate.setEvaluateTime(new Date());
					starEvaluate.setEvaluateContent("系统评定");
					starEvaluateService.insertNotNull(starEvaluate);
				}
			}
		}
	};
	
	@Override
	protected void runJob() throws Exception {
		try{
			updateVolunteerStar();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "定时更新志愿者星级状态任务发生错误【"+e.getMessage()+"】",null,null);
			logger.error("定时更新志愿者星级评定信息操作出现内部错误",e);
		}
	};

	
}
