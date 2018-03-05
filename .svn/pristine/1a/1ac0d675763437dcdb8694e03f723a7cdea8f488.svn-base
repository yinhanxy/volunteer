package com.topstar.volunteer.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.log.LogType;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.LoggerView;
import com.topstar.volunteer.service.LoggerService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class LoggerController {
	
	@Autowired
	LoggerService loggerService;
	
	org.slf4j.Logger log=LoggerFactory.getLogger(LoggerController.class);
	
	@RequestMapping(value = "/log/view.html")
	public String view(Model model){
		int loggerId=ActionContext.getActionContext().getParameterAsInt("id",0);
		try{
			LoggerView loggerView= loggerService.findLoggerViewById(loggerId);
			model.addAttribute("logger", loggerView);
		}catch (Exception e) {
			log.error("获取日志详情失败",e);
		}
		return "/sys/log/view";
	}
	
	@RequestMapping(value = "/log/list.html")
	public String list(Model model){
		HashMap<Integer,String> logTypes=new HashMap<Integer,String>();
		for (LogType c : LogType.values()) {  
			 logTypes.put(c.getValue(),c.getName());
		}
		model.addAttribute("logTypes", logTypes);
		HashMap<Integer,String> ojbectTypes=new HashMap<Integer,String>();
		HashMap<String,List<OperateType>> operateTypes=new HashMap<String,List<OperateType>>();
		for (ObjectType c : ObjectType.values()) {  
			ojbectTypes.put(c.getValue(),c.getName());
			operateTypes.put(c.getName(), c.getOperateType());
		}
		model.addAttribute("objectTypes", ojbectTypes);
		model.addAttribute("operateTypes", operateTypes);
		return "/sys/log/list";
	}
	
	@RequestMapping(value = "/log/list.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String dataList(){
		int logType=ActionContext.getActionContext().getParameterAsInt("logType",0);
		int objType=ActionContext.getActionContext().getParameterAsInt("objectType",0);
		int operateType=ActionContext.getActionContext().getParameterAsInt("operateType",0);
		String message=ActionContext.getActionContext().getParameterAsStringWithFilter("message");
		int currPage = ActionContext.getActionContext().getParameterAsInt("pageNo",1);
		int pageSize = ActionContext.getActionContext().getParameterAsInt("pageSize",10);
		List<ValidateMessage> validateMessages=new ArrayList<ValidateMessage>();
		Logger logger=new Logger();
		boolean isValidate=false;
		for (LogType c : LogType.values()) {  
			 if(logType==0||logType==c.getValue()){
				 isValidate=true;
				 logger.setLoggerType(logType);
				 break;
			 }
		}
		if(!isValidate){
			ValidateMessage validateMessage=new ValidateMessage();
			validateMessage.setPropertyName("logType");
			validateMessage.setMessage("非法的日志类型值");
			validateMessages.add(validateMessage);
		}
		isValidate=false;
		for (ObjectType c : ObjectType.values()) {  
			 if(objType==0||objType==c.getValue()){
				 isValidate=true;
				 logger.setObjectType(objType);
				 break;
			 }
		}
		if(!isValidate){
			ValidateMessage validateMessage=new ValidateMessage();
			validateMessage.setPropertyName("objType");
			validateMessage.setMessage("非法的对象类型值");
			validateMessages.add(validateMessage);
		}
		
		isValidate=false;
		for (OperateType c : OperateType.values()) {  
			 if(operateType==0||operateType==c.getValue()){
				 isValidate=true;
				 logger.setOperateType(operateType);
				 break;
			 }
		}
		if(!isValidate){
			ValidateMessage validateMessage=new ValidateMessage();
			validateMessage.setPropertyName("operateType");
			validateMessage.setMessage("非法的操作类型值");
			validateMessages.add(validateMessage);
		}
		
		if(null!=validateMessages&&validateMessages.size()>0){
			return ResultData.fail(validateMessages).toJSONString();
		}
		
		String operateUser=ActionContext.getActionContext().getParameterAsStringWithFilter("operateUser");
		logger.setOperateUser(operateUser);
		logger.setMessage(message);
		int success=ActionContext.getActionContext().getParameterAsInt("success",-1);
		logger.setSuccess(success);
		Date [] operateTime= new Date[2];
		try{
			operateTime=getDateTime();
		}catch (Exception e) {
			return ResultData.fail("传入的时间格式有误").toJSONString();
		}
		try{
			ResultData data=new ResultData();
			data.setSuccess(true);
			data.put("page", loggerService.selectLogger(logger,operateTime[0],operateTime[1],currPage,pageSize));
			return  data.toJSONString();
		}catch (Exception e) {
			log.error("查询日志出错",e);
			return ResultData.fail("查询日志信息失败").toJSONString();
		}
	}
	
	private Date[] getDateTime()throws Exception{
		try{
			String sStartTime = ActionContext.getActionContext().getParameterAsString("StartTime");
			String sEndTime = ActionContext.getActionContext().getParameterAsString("EndTime");
			String sTimeItem = ActionContext.getActionContext().getParameterAsString("TimeItem");
			// 如果没有传入TimeItem，默认是无限
			if(StringUtils.isBlank(sTimeItem))
				sTimeItem = "0";
			Calendar startTime = Calendar.getInstance();
			Calendar endTime=Calendar.getInstance();
			if(StringUtils.isBlank(sStartTime)){
				if("0".equals(sTimeItem)){//不限
					sStartTime = "2000-01-01 00:00:00";
				}else if("1".equals(sTimeItem)){//近三天
					startTime.add(Calendar.DATE, -2);
					sStartTime =new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(startTime.getTime());
					sEndTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime.getTime());
				}else if("2".equals(sTimeItem)){//本周(从周一开始)
					int day_of_week = startTime.get(Calendar.DAY_OF_WEEK) - 1;
					if (day_of_week == 0)
					   day_of_week = 7;
					startTime.add(Calendar.DATE, -day_of_week + 1);
					sStartTime =new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(startTime.getTime());
					sEndTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime.getTime());
				}else if("3".equals(sTimeItem)){//本月
					sStartTime =new SimpleDateFormat("yyyy-MM-01 00:00:00").format(startTime.getTime());
				}else if("4".equals(sTimeItem)){//本季
					int month = startTime.get(Calendar.MONTH);
					String sMonth="";
					if(month>=10)sMonth="10";
					else if(month>=7)sMonth="07";
					else if(month>=4)sMonth="04";
					else sMonth="01";
					sStartTime =new SimpleDateFormat("yyyy-"+sMonth+"-01 00:00:00").format(startTime.getTime());
				}else if("5".equals(sTimeItem)){//本年
					sStartTime =new SimpleDateFormat("yyyy-01-01 00:00:00").format(startTime.getTime());
				}
			}
			
			if(StringUtils.isBlank(sEndTime))
				sEndTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime.getTime());
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return new Date[]{format.parse(sStartTime),format.parse(sEndTime)};
		}catch(Exception ex){
			throw new Exception("获取时间出现错误！",ex);
		}
	}
	


}
