package com.topstar.volunteer.web.controller;

import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.ScheduleJob;
import com.topstar.volunteer.entity.ScheduleJobLog;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.schedule.QuartzJob;
import com.topstar.volunteer.service.ScheduleJobLogService;
import com.topstar.volunteer.service.ScheduleJobService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class ScheduleJobController {
	
	@Autowired
	private ScheduleJobService scheduleJobService;
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	private Logger logger=LoggerFactory.getLogger(ScheduleJobController.class);
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping(value="/schedule/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String search(){
		ResultData result=new ResultData();
		int pageIndex = ActionContext.getActionContext().getParameterAsInt("pageNo",1);
		int pageSize = ActionContext.getActionContext().getParameterAsInt("pageSize",10);
		String jobName= ActionContext.getActionContext().getParameterAsString("jobName");
		
		try{
			ScheduleJob job=new ScheduleJob();
			if(StringUtils.isNotBlank(jobName)){
				job.setJobName(jobName);
			}
			PageInfo<ScheduleJob> pageInfo=scheduleJobService.queryList(job, pageSize, pageIndex);
			result.put("page", pageInfo);
			result.setSuccess(true);
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOBLIST, "参数值【pageNo="+pageIndex+",pageSize="+pageSize+",jobName="+jobName+"】", null, null,true);
			return result.toJSONStringWithDateFormat("yyyy-MM-dd HH:mm:ss");
		}catch (Exception e) {
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOBLIST, "获取定时任务列表出错："+e.getMessage(), null, null);
			logger.error("获取定时任务出错",e);
			return ResultData.fail("获取定时任务列表出错").toJSONString();
		}
	}
	
	@RequestMapping("/schedule/list.html")
	public String list(){
		return "/sys/schedule/list";
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/schedule/view.html")
	public String detail(HttpServletRequest request){
		long jobId=ActionContext.getActionContext().getParameterAsLong("jobId");
		if(jobId<=0){
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOB, "非法的操作", jobId, null);
			return "/sys/schedule/detail";
		}
		ScheduleJob schedule=null;
		try{
			schedule=scheduleJobService.findById(jobId);
			request.setAttribute("schedule", schedule);
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOB, "获取定时任务信息成功", jobId, schedule.getJobName(),true);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOB, "获取定时任务信息失败："+e.getMessage(), jobId, null);
		}
		return "/sys/schedule/detail";
	}

	
	/**
	 * 保存定时任务
	 */
	@RequestMapping(value="/schedule/add.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String save(){
		String jobName=ActionContext.getActionContext().getParameterAsString("jobName");
		String beanName=ActionContext.getActionContext().getParameterAsString("beanName");
		String params=ActionContext.getActionContext().getParameterAsString("params");
		String cronExpression=ActionContext.getActionContext().getParameterAsString("cronExpression");
		String remark=ActionContext.getActionContext().getParameterAsString("remark");
		List<ValidateMessage> errors=new ArrayList<ValidateMessage>();
		//验证任务名称是否存在
		if(StringUtils.isBlank(jobName)){
			ValidateMessage message=new ValidateMessage();
			message.setMessage("任务名称不能为空");
			message.setPropertyName("jobName");
			errors.add(message);
		}else{
			try{
				boolean isExit=scheduleJobService.exitJobName(jobName);
				if(isExit){
					ValidateMessage message=new ValidateMessage();
					message.setMessage("任务名称已存在");
					message.setPropertyName("jobName");
					errors.add(message);
				}
			}catch (Exception e) {
				logger.error("验证任务名称合法性失败",e);
				return ResultData.fail("验证任务名称合法性失败").toJSONString();
			}
		}
		
		//验证javabean 是否存在
		Class<?> bean=null;
		if(StringUtils.isBlank(beanName)){
			ValidateMessage message=new ValidateMessage();
			message.setMessage("java bean不能为空");
			message.setPropertyName("beanName");
			errors.add(message);
		}else{
			try {
				bean=Class.forName(beanName);
				boolean isAbs = Modifier.isAbstract(bean.getModifiers()) ;
				if(bean.isInterface()||isAbs){
					ValidateMessage message=new ValidateMessage();
					message.setMessage("java bean不能是接口类或抽象类");
					message.setPropertyName("beanName");
					errors.add(message);
				}
				//判断当前java bean 是否继承了QuartzJob
				boolean isAssignable=QuartzJob.class.isAssignableFrom(bean);
				if(!isAssignable){
					ValidateMessage message=new ValidateMessage();
					message.setMessage("java bean必须继承com.topstar.volunteer.schedule.QuartzJob");
					message.setPropertyName("beanName");
					errors.add(message);
				}
				
			} catch (ClassNotFoundException e) {
				ValidateMessage message=new ValidateMessage();
				message.setMessage("java bean不存在");
				message.setPropertyName("beanName");
				errors.add(message);
			}
			
		}
		
		//验证cron表达式
		if(StringUtils.isBlank(cronExpression)){
			ValidateMessage message=new ValidateMessage();
			message.setMessage("cron表达式不能为空");
			message.setPropertyName("cronExpression");
			errors.add(message);
		}else{
			if(!CronExpression.isValidExpression(cronExpression)){
				ValidateMessage message=new ValidateMessage();
				message.setMessage("cron表达式有误");
				message.setPropertyName("cronExpression");
				errors.add(message);
			}
		}
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setBeanName(beanName);
		scheduleJob.setJobName(jobName);
		scheduleJob.setCronExpression(cronExpression);
		scheduleJob.setParams(params);
		scheduleJob.setRemark(remark);
		try {
			scheduleJobService.save(scheduleJob);
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.CREATESCHEDULEJOB, "创建任务成功", scheduleJob.getJobId(), scheduleJob.getJobName(),true);
		} catch (Exception e) {
			logger.error("创建任务失败",e);
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.CREATESCHEDULEJOB, "创建任务失败："+e.getMessage(), null, null);
			return ResultData.fail("创建任务失败").toJSONString();
		}
		return ResultData.ok("创建任务成功").toJSONString();
	}
	
	/**
	 * 添加定时任务
	 */
	@RequestMapping("/schedule/add.html")
	public String add(){
		return "/sys/schedule/add";
	}
	
	/**
	 * 修改定时任务
	 */
	@RequestMapping(value="/schedule/edit.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String editScheule(){
		long jobId=ActionContext.getActionContext().getParameterAsLong("jobId");
		String params=ActionContext.getActionContext().getParameterAsString("params");
		String cronExpression=ActionContext.getActionContext().getParameterAsString("cronExpression");
		String remark=ActionContext.getActionContext().getParameterAsString("remark");
		List<ValidateMessage> errors=new ArrayList<ValidateMessage>();
		//验证cron表达式
		if(StringUtils.isBlank(cronExpression)){
			ValidateMessage message=new ValidateMessage();
			message.setMessage("cron表达式不能为空");
			message.setPropertyName("cronExpression");
			errors.add(message);
		}else{
			if(!CronExpression.isValidExpression(cronExpression)){
				ValidateMessage message=new ValidateMessage();
				message.setMessage("cron表达式有误");
				message.setPropertyName("cronExpression");
				errors.add(message);
			}
		}
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		ScheduleJob scheduleJob=null;
		ScheduleJob scheduleJobBefore=null;
		try{
			scheduleJob=scheduleJobService.findById(jobId);
			scheduleJobBefore=scheduleJob;
		}catch (Exception e) {
			logger.error("查询任务信息失败");
		}
		if(scheduleJob==null){
			return ResultData.fail("没有找到对应的任务").toJSONString();
		}
		scheduleJob.setCronExpression(cronExpression);
		scheduleJob.setParams(params);
		scheduleJob.setRemark(remark);
		try {
			scheduleJobService.update(scheduleJob);
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.EDITSCHEDULEJOB, "修改任务成功【修改前："+scheduleJobBefore.toString()+",修改后："+scheduleJob.toString()+"】", scheduleJob.getJobId(), scheduleJob.getJobName(),true);
		} catch (Exception e) {
			logger.error("修改任务失败",e);
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.EDITSCHEDULEJOB, "修改任务失败："+e.getMessage(), scheduleJob.getJobId(), scheduleJob.getJobName());
			return ResultData.fail("修改任务失败").toJSONString();
		}
		return ResultData.ok("修改任务成功").toJSONString();
	}
	
	
	
	/**
	 * 修改定时任务
	 */
	@RequestMapping("/schedule/edit.html")
	public  String edit(HttpServletRequest request){
		long jobId=ActionContext.getActionContext().getParameterAsLong("jobId");
		if(jobId<=0){
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOB, "非法的操作", jobId, null);
			return  "/sys/schedule/editSchedule";
		}
		ScheduleJob schedule=null;
		try{
			schedule=scheduleJobService.findById(jobId);
			request.setAttribute("schedule", schedule);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOB, "获取定时任务信息失败："+e.getMessage(), jobId, null);
		}
		LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.SHOWSCHEDULEJOB, "获取定时任务信息成功", jobId, schedule.getJobName(),true);
		return  "/sys/schedule/edit";
	}
	
	/**
	 * 删除定时任务
	 */
	@RequestMapping(value="/schedule/delete.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delete(){
		try{
			scheduleJobService.deleteBatch(getJobIds());
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.DELETECHEDULEJOB, "批量删除任务成功【jobIds="+StringUtils.join(getJobIds(),",")+"】", null, null,true);
			return ResultData.ok("删除任务成功").toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.DELETECHEDULEJOB, "批量删除任务失败【jobIds="+StringUtils.join(getJobIds(),",")+"】，错误详情："+e.getMessage(), null, null);
			return ResultData.fail("删除任务失败").toJSONString();
		}
	}
	
	/**
	 * 立即执行任务
	 */
	@RequestMapping(value="/schedule/start.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String run(){
		try{
			scheduleJobService.run(getJobIds());
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.STARTCHEDULEJOB, "批量启动任务成功【jobIds="+StringUtils.join(getJobIds(),",")+"】", null, null,true);
			return ResultData.ok("执行任务成功").toJSONString();
		}catch (Exception e) {
			logger.error("任务执行失败",e);
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.STARTCHEDULEJOB, "批量启动任务失败【jobIds="+StringUtils.join(getJobIds(),",")+"】，错误详情："+e.getMessage(), null, null);
			return ResultData.fail("执行任务失败").toJSONString();
		}
	}
	
	/**
	 * 暂停定时任务
	 */
	@RequestMapping(value="/schedule/pause.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String pause(){
		try{
			scheduleJobService.pause(getJobIds());
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.PAUSECHEDULEJOB, "批量暂停任务成功【jobIds="+StringUtils.join(getJobIds(),",")+"】", null, null,true);
			return ResultData.ok("暂停任务成功").toJSONString();
		}catch (Exception e) {
			logger.error("暂停任务失败",e);
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.PAUSECHEDULEJOB, "批量暂停任务失败【jobIds="+StringUtils.join(getJobIds(),",")+"】,错误详情："+e.getMessage(), null, null);
			return ResultData.fail("暂停任务失败").toJSONString();
		}
	}
	
	/**
	 * 恢复定时任务
	 */
	@RequestMapping(value="/schedule/resume.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String resume(){
		try{
			scheduleJobService.resume(getJobIds());
			LoggerServer.info(ObjectType.SCHEDULEJOB, OperateType.RESUMECHEDULEJOB, "批量恢复任务成功【jobIds="+StringUtils.join(getJobIds(),",")+"】", null, null,true);
			return ResultData.ok("恢复任务成功").toJSONString();
		}catch (Exception e) {
			logger.error("恢复任务失败",e);
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.RESUMECHEDULEJOB, "批量恢复任务失败【jobIds="+StringUtils.join(getJobIds(),",")+"】,错误详情："+e.getMessage(), null, null);
			return ResultData.fail("恢复任务失败").toJSONString();
		}
	}
	
	/**
	 * 修改定时任务
	 */
	@RequestMapping("/schedule/logs.html")
	public  String logList(HttpServletRequest request){
		Long jobId=ActionContext.getActionContext().getParameterAsLong("jobId");
		request.setAttribute("jobId", jobId);
		return  "/sys/schedule/log/list";
	}
	
	/**
	 * 修改定时任务
	 */
	@RequestMapping("/schedule/log.html")
	public  String logDetail(HttpServletRequest request){
		Long logId=ActionContext.getActionContext().getParameterAsLong("logId");
		try{
			ScheduleJobLog scheduleJobLog=scheduleJobLogService.selectByKey(logId);
			request.setAttribute("log", scheduleJobLog);
			LoggerServer.debug(ObjectType.SCHEDULEJOB, OperateType.SHOWCHEDULEJOBLOGDETAIL, "获取任务日志详情成功", scheduleJobLog.getLogId(), null,true);
		}catch (Exception e) {
			logger.error("获取任务详情失败",e);
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.SHOWCHEDULEJOBLOGDETAIL, "获取任务日志详情失败："+e.getMessage(), logId, null);
		}
		return  "/sys/schedule/log/detail";
	}
	
	@RequestMapping(value="/schedule/logs.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String logs(HttpServletRequest request){
		Long jobId=ActionContext.getActionContext().getParameterAsLong("jobId");
		ScheduleJobLog log=new ScheduleJobLog();
		if(jobId>0){
			log.setJobId(jobId);
		}else{
			log.setJobId(0L);
		}
		int success=ActionContext.getActionContext().getParameterAsInt("success",-1);
		if(success==0||success==1){
			log.setStatus(success);
		}
		String[] timeRange=getTimeRange();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginTime=null;
		Date endTime=null;
		try{
			beginTime=format.parse(timeRange[0]);
			endTime=format.parse(timeRange[1]);
		}catch (Exception e) {
			logger.error("转换时间格式出错",e);
		}
		ResultData result=new ResultData();
		int pageIndex = ActionContext.getActionContext().getParameterAsInt("pageNo",1);
		int pageSize = ActionContext.getActionContext().getParameterAsInt("pageSize",10);
		try{
			PageInfo<ScheduleJobLog> pageInfo=scheduleJobLogService.queryList(log, pageSize, pageIndex, beginTime, endTime);
			result.put("page", pageInfo);
			result.setSuccess(true);
			LoggerServer.debug(ObjectType.SCHEDULEJOB, OperateType.SHOWCHEDULEJOBLOGLIST, "获取任务日志成功", null, null,true);
			return result.toJSONStringWithDateFormat("yyyy-MM-dd HH:mm:ss");
		}catch (Exception e) {
			LoggerServer.error(ObjectType.SCHEDULEJOB, OperateType.SHOWCHEDULEJOBLOGLIST, "获取任务日志失败："+e.getMessage(), null, null);
			logger.error("获取任务日志出错",e);
			return ResultData.fail("获取任务日志列表出错").toJSONString();
		}
	}
	
	private Long[] getJobIds(){
		List<String> jobs=ActionContext.getActionContext().getParameterAsList("jobIds", ",");
		String [] sJobIds=jobs.toArray(new String[jobs.size()]);
		List<Long> ids=new ArrayList<Long>();
		for(int i=0;i<sJobIds.length;i++){
			try{
				ids.add(Long.parseLong(sJobIds[i].trim()));
			}catch (Exception e) {
				logger.error("转换任务Id失败【JobId="+sJobIds[i].trim()+"】",e);
			}
		}
		Long [] jobIds=new Long[ids.size()];
		jobIds=ids.toArray(new Long[ids.size()]);
		return jobIds;
	}
	
	public String[] getTimeRange(){
		String sStartTime =ActionContext.getActionContext().getParameterAsString("StartTime");
		String sEndTime = ActionContext.getActionContext().getParameterAsString("EndTime");
		String sTimeItem = ActionContext.getActionContext().getParameterAsString("TimeItem");
		// 如果没有传入TimeItem，默认是无限
		if(StringUtils.isBlank(sTimeItem))
			sTimeItem = "0";
		Calendar dtQueryStart = Calendar.getInstance(),dtQueryEnd=Calendar.getInstance();
		if(StringUtils.isBlank(sStartTime)){
			if("0".equals(sTimeItem)){//不限
				sStartTime = "2000-01-01 00:00:00";
			}else if("1".equals(sTimeItem)){//今天
				Date dayTime =dtQueryStart.getTime() ;
				sStartTime =new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(dayTime);
				sEndTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dayTime);
			}else if("2".equals(sTimeItem)){//昨日
				dtQueryStart.add(Calendar.DATE, -1);
			    Date dayTime =dtQueryStart.getTime() ;
				sStartTime =new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(dayTime);
				sEndTime =new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(dayTime);
			}else if("3".equals(sTimeItem)){//本周(从周一开始)
				int day_of_week = dtQueryStart.get(Calendar.DAY_OF_WEEK) - 1;
				if (day_of_week == 0)
				   day_of_week = 7;
				dtQueryStart.add(Calendar.DATE, -day_of_week + 1);
				sStartTime =new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(dtQueryStart.getTime());
				sEndTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dtQueryEnd.getTime());
				System.out.println(sStartTime);
				System.out.println(sEndTime);
			}else if("4".equals(sTimeItem)){//当月
				sStartTime =new SimpleDateFormat("yyyy-MM-01 00:00:00").format(dtQueryStart.getTime());
			}else if("5".equals(sTimeItem)){//当季
				int month = dtQueryStart.get(Calendar.MONTH);
				String sMonth="";
				if(month>=10)sMonth="10";
				else if(month>=7)sMonth="07";
				else if(month>=4)sMonth="04";
				else sMonth="01";
				sStartTime =new SimpleDateFormat("yyyy-"+sMonth+"-01 00:00:00").format(dtQueryStart.getTime());
			}else if("6".equals(sTimeItem)){//当年
				sStartTime =new SimpleDateFormat("yyyy-01-01 00:00:00").format(dtQueryStart.getTime());
			}
		}
		if(StringUtils.isBlank(sEndTime))
			sEndTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dtQueryEnd.getTime());
		return new String[]{sStartTime,sEndTime};
	}
	

}
