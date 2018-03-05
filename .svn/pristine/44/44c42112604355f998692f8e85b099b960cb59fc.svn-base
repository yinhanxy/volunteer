package com.topstar.volunteer.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerUtils;
import org.quartz.spi.OperableTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

@JsonIgnoreProperties
@Controller
public class CronController {
	

	@RequestMapping(value="/cron/index.html")
	public String cron(HttpServletRequest request){
		return "/cron/index";
	}
	
	@RequestMapping(value="/cron/preview.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String preview(){
		ResultData result=new ResultData();
		String expression=ActionContext.getActionContext().getParameterAsString("expression");
		boolean isValid=CronExpression.isValidExpression(expression);
		if(!isValid){
			return ResultData.fail("Cron表达式有误").toJSONString();
		}
		try{
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
			Trigger trigger=TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
			List<Date> list=TriggerUtils.computeFireTimes((OperableTrigger)trigger, null, 5);
			result.put("data", list);
			result.setSuccess(true);
		}catch (Exception e) {
			result.setMessage("Cron表达式有误");
			result.setSuccess(false);
		}
		return result.toJSONStringWithDateFormat("yyyy-MM-dd HH:mm:ss");
	}

}
