package com.topstar.volunteer.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.entity.ActivityVolunteer;
import com.topstar.volunteer.exception.TPSClientException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.service.ActivityVolunteerService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 活动记录管理控制器
 * @author TRS
 *
 */
@Controller
public class ActivityRecordController {
	
	private static Logger logger = LoggerFactory.getLogger(ActivityRecordController.class);
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityVolunteerService activityVolunteerService;
	
	/**
	 * 跳转到活动记录的列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/activityRecord.html")
	public String toActivityRecord(HttpServletRequest request){
		return "/activity/record/list";
	}
	
	/**
	 * 跳转到指定活动的活动记录页面
	 * @param request
	 * @return
	 * @throws TPSClientException 
	 */
	@RequestMapping(value="/activity/record.html")
	public String toRecord(HttpServletRequest request,ModelMap map) throws TPSClientException{
		Long activityId= ActionContext.getActionContext().getParameterAsLong("aId");
		if(activityId.equals(-1l)){
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYRECORD, "活动记录信息参数不合法",null,null);
			throw new TPSClientException("不合法");
		}
		Activity activity=activityService.selectByKey(activityId);
		if(activity!=null){
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITYRECORD, "活动记录信息查询成功",activityId,"活动ID",true);
			map.put("activity", activity);
		}else{
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYRECORD, "活动记录信息查询不合法",activityId,"活动ID");
		}
		return "/activity/record/activityRecord";
	}	
	
	/**
	 * 跳转到指定活动的时长管理页面
	 * @param request
	 * @return
	 * @throws TPSClientException 
	 */
	@RequestMapping(value="/activity/hourManagement.html")
	public String toHourManagement(HttpServletRequest request,ModelMap map) throws TPSClientException{
		Long activityId= ActionContext.getActionContext().getParameterAsLong("aId");
		if(activityId.equals(-1l)){
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYHOURS, "查看活动时长管理信息参数不合法",null,null);
			throw new TPSClientException("不合法");
		}
		Activity activity=activityService.selectByKey(activityId);
		if(activity!=null){
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITYHOURS, "活动时长管理信息查询成功",null,null,true);
			map.put("activity", activity);
		}else{
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYHOURS, "活动时长管理信息查询不合法",null,null);
		}
		return "/activity/record/activityHour";
	}
	
	/**
	 * 分页查看志愿者参加活动服务时长情况列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/activityHours.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String activityHours(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String id= ActionContext.getActionContext().getParameterAsString("aId");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 4;
		}
		
		String orderBy = "apply_time DESC";
		ResultData data = new ResultData();
		Long activityId=null;
		try {
			activityId=Long.parseLong(id); 
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYHOURS, "查看指定活动服务时长情况参数不合法【"+id+"】",null,"活动ID");
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			PageInfo<VolunteerView> pageInfo = activityVolunteerService.getJoinVolunteersByActivityId(activityId, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITYHOURS, "成功查看指定活动服务时长情况信息", activityId, "活动ID",true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYHOURS, "查看指定活动服务时长情况发生错误,错误信息【"+e.getMessage()+"】", activityId, "活动ID");
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页指定活动报名情况信息出错,currPage:{},pageSize:{},certId:{}",currPage,pageSize,id,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 获取活动记录管理列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/activityRecord.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String activityRecord(HttpServletRequest request,HttpServletResponse response){
		Activity activity= new Activity();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String activityName = ActionContext.getActionContext().getParameterAsString("activityName");
		String publisher = ActionContext.getActionContext().getParameterAsString("publisher");
		Long serviceTeamId = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		Integer status = ActionContext.getActionContext().getParameterAsInt("status");
		String startTime = ActionContext.getActionContext().getParameterAsString("StartTime");
		String endTime = ActionContext.getActionContext().getParameterAsString("EndTime");
		ResultData data = new ResultData();
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		if(status.equals(-1)){
			status=null;
		}
		activity.setName(activityName);
		activity.setPublisher(publisher);
		activity.setStatus(status);
		activity.setSerId(serviceTeamId);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(startTime)){
			try {
				activity.setActivitySt(format.parse(startTime));
			} catch (ParseException e) {
				logger.error("分页查询活动开始时间条件不合法startTime:{}",startTime);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				return data.toJSONString();
			}
		}
		if(StringUtils.isNotBlank(endTime)){
			try {
				activity.setActivityEt(format.parse(endTime));
			} catch (ParseException e) {
				logger.error("分页查询活动结束时间条件不合法endTime:{}",endTime);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				return data.toJSONString();
			}
		}
		
		String orderBy = "RECRUIT_ST DESC,NAME";
		
		try {
			PageInfo<Activity> pageInfo =activityService.getDoingAndCompletedByEntity(activity, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITYRECORDLIST, "活动记录列表【第"+currPage+"页】查询操作成功,查询条件:"+activity,null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYRECORDLIST, "活动记录信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询活动记录列表出错,currPage:{},pageSize:{},userName:{},idCard:{},tele:{},serviceTeam:{}",currPage,pageSize,activityName,publisher,status,serviceTeamId,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 活动记录的保存操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/saveActivityRecord.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String saveActivityRecord(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String  imgUrls= ActionContext.getActionContext().getParameterAsString("ius");
		String id= ActionContext.getActionContext().getParameterAsString("aId");
		String cType= ActionContext.getActionContext().getParameterAsString("ctype");
		if((StringUtils.isNotBlank(cType) && !"se".equals(cType)) || StringUtils.isBlank(imgUrls)){
			data.setSuccess(false);
			data.setMessage("访问不合法");
			return data.toJSONString();
		}
		Long activityId=null;
		try {
			activityId=Long.parseLong(id); 
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYHOURS, "查看指定活动服务时长情况参数不合法【"+id+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		LoggerServer.debug(ObjectType.ACTIVE,OperateType.SAVEACTIVITYRECORD, "保存的活动记录信息为【"+imgUrls+"】", activityId, null,true);
		try {
			Activity activity=activityService.selectByKey(activityId);
			if(activity==null || (!activity.getStatus().equals(5) && !activity.getStatus().equals(6) && !activity.getStatus().equals(7))){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.SAVEACTIVITYRECORD, "非法操作", activityId,"活动ID",false);
				data.setSuccess(false);
				data.setMessage("非法操作");
				return data.toJSONString();
			}
			switch (cType) {
			case "se":
				activity.setStatus(7);
				break;
			default:
				break;
			}
			int updateActivityRecords=activityService.saveActivityRecords(activity,imgUrls);
			if(updateActivityRecords>0){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.SAVEACTIVITYRECORD, "保存活动记录操作成功", activityId, "活动ID",true);
				data.setSuccess(true);
				data.setMessage("记录保存成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.SAVEACTIVITYRECORD, "保存活动记录操作失败", activityId,"活动ID",false);
				data.setSuccess(false);
				data.setMessage("记录保存失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SAVEACTIVITYRECORD, "志愿者信息更新操作失败【"+e.getMessage()+"】", activityId, "活动ID");
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("记录保存操作出现内部错误",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 保存志愿者参加活动服务时长情况列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/saveActivityHours.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String saveActivityHours(HttpServletRequest request,HttpServletResponse response){
		String activityHoursData = ActionContext.getActionContext().getParameterAsString("activityHoursData");
		ResultData data = new ResultData();
		Long activityId=null;
		List<ActivityVolunteer> activityVolunteers=null;
		try{
			if(StringUtils.isNotBlank(activityHoursData)){
				activityVolunteers=JSONObject.parseArray(activityHoursData, ActivityVolunteer.class);
				logger.debug("保存指定活动的志愿者服务情况实体信息:{}",activityVolunteers);
			}
			if(StringUtils.isBlank(activityHoursData) || activityVolunteers==null || activityVolunteers.isEmpty()){
				LoggerServer.error(ObjectType.ACTIVE,OperateType.SAVEACTIVITYHOURS, "保存指定活动中志愿者服务时长情况参数不合法,不能为空",null,null);
				data.setSuccess(false);
				data.setMessage("不合法");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SAVEACTIVITYHOURS, "保存指定活动中志愿者服务时长情况参数不合法【"+activityHoursData+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			boolean saveHours= activityVolunteerService.saveActivityVolunteerHours(activityVolunteers);
			if(saveHours){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.SAVEACTIVITYHOURS, "成功保存指定活动中志愿者服务时长情况信息", null, null,true);
				data.setMessage("保存成功");
				data.setSuccess(true);
			}
			return data.toJSONString();
		} catch (TPSClientException e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SAVEACTIVITYHOURS, "保存指定活动中志愿者服务时长情况发生错误,错误信息【"+e.getMessage()+"】", null, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			return data.toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SAVEACTIVITYHOURS, "保存指定活动中志愿者服务时长情况发生错误,错误信息【"+e.getMessage()+"】", null, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		LoggerServer.error(ObjectType.ACTIVE,OperateType.SAVEACTIVITYHOURS, "保存指定活动中志愿者服务时长情况操作结果失败", null, null);
		return data.toJSONString();
	}
	
	/**
	 * 志愿者的批量审核
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value="/vols/checkVolunteers.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String checkVolunteer(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("volunteerIds");
		String status= ActionContext.getActionContext().getParameterAsString("status");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CHECKVOLUNTEER, "志愿者审核操作参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "批量审核操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		long[] volunteerKeys=new long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long userKey=Long.parseLong(keys[i]);
				volunteerKeys[i]=userKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CHECKVOLUNTEER, "志愿者审核操作参数信息【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("批量审核操作参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try{
			VolunteerCheck volunteerCheck=new VolunteerCheck();
			volunteerCheck.setOperTime(new Timestamp(new Date().getTime()));
			
			BaseUser crtUser=ShiroSessionMgr.getLoginUser();
			volunteerCheck.setUserName(crtUser.getUserName());
			volunteerCheck.setStatus(status);
			LoggerServer.debug(ObjectType.VOLUNTEER,OperateType.CHECKVOLUNTEER, "志愿者审核操作信息为【"+volunteerCheck+"】",null,null,false);
			boolean batchVolunteerCheck=volunteerCheckService.batchCheckVolunteer(volunteerCheck,volunteerKeys);
			if(batchVolunteerCheck){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CHECKVOLUNTEER, "志愿者审核操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("审核操作成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CHECKVOLUNTEER, "志愿者审核操作失败功",null,null,false);
				data.setSuccess(false);
				data.setMessage("审核操作失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CHECKVOLUNTEER, "志愿者审核操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("批量审核志愿者信息内部发生错误",e);
		}
		return data.toJSONString();
	}
	
	*/
}
