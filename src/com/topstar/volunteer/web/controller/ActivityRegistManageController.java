package com.topstar.volunteer.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.entity.ActivityVolunteer;
import com.topstar.volunteer.exception.TPSClientException;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.service.ActivityVolunteerService;
import com.topstar.volunteer.service.TeamUserService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;


/**
 * 活动报名管理控制器
 * @author TRS
 *
 */
@Controller
public class ActivityRegistManageController {
	
	private static Logger logger = LoggerFactory.getLogger(ActivityRegistManageController.class);
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityVolunteerService activityVolunteerService;
	
	@Autowired
	private TeamUserService teamUserService;
	
	/**
	 * 跳转到活动报名的列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/registrationManage.html")
	public String toRegistrationManage(HttpServletRequest request){
		return "/activity/registrationManage/list";
	}
	
	/**
	 * 跳转到指定活动报名管理页面
	 * @param request
	 * @return
	 * @throws TPSClientException 
	 */
	@RequestMapping(value="/activity/management.html")
	public String toManagement(HttpServletRequest request,ModelMap map) throws TPSClientException{
		Long activityId= ActionContext.getActionContext().getParameterAsLong("aId");
		if(activityId.equals(-1l)){
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLY, "活动报名信息参数不合法",null,null);
			throw new TPSClientException("不合法");
		}
		Activity activity=activityService.selectByKey(activityId);
		if(activity!=null){
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLY, "活动报名信息查询不合法",activityId,"活动ID",true);
			map.put("activity", activity);
		}else{
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLY, "活动报名信息查询不合法",null,null);
		}
		
		return "/activity/registrationManage/applyManage";
	}	
	
	/*//查看审核的志愿者信息
	@RequestMapping(value ="/vols/viewReviewVolunteer.html")
	public String toViewReviewVolunteer(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String serName= ActionContext.getActionContext().getParameterAsString("serName");
		Long volunteerId=null;
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("查看审核的志愿者信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWREVIEWVOLUNTEER, "审核志愿者信息页面展示的志愿者参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Volunteer volunteer=volunteerService.selectByKey(volunteerId);
			map.addAttribute("volunteer", volunteer);
			map.addAttribute("serName", serName);
			if(volunteer!=null){
				VolunteerCheck volunteerCheck=volunteerCheckService.getVolunteerCheckByVolunteerId(volunteer.getId());
				LoggerServer.debug(ObjectType.VOLUNTEER,OperateType.SHOWREVIEWVOLUNTEER, "志愿者审核信息页面展示result为"+volunteerCheck, volunteerId, volunteer.getUserName(),true);
				map.addAttribute("volunteerCheck",volunteerCheck);
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWREVIEWVOLUNTEER, "志愿者审核信息查询操作失败["+e.getMessage()+"]", volunteerId, null);
		}
		
		return "/volunteer/review/viewReviewVolunteer";
	}
	
	//跳转到审核志愿者页面
	@RequestMapping(value = "/vols/reviewVolunteer.html")
	public String toreviewVolunteer(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String serName= ActionContext.getActionContext().getParameterAsString("serName");
		Long volunteerId=null;
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("跳转到志愿者信息审核页面信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWREVIEWVOLUNTEER, "跳转到审核志愿者页面信息参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Volunteer volunteer=volunteerService.selectByKey(volunteerId);
			map.addAttribute("volunteer", volunteer);
			map.addAttribute("serName", serName);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWREVIEWVOLUNTEER, "跳转到审核志愿者页面信息查询操作失败【"+e.getMessage()+"】不合法",volunteerId,null);
		}
		
		return "/volunteer/review/reviewVolunteer";
	}
	
	//志愿者审核流程
	@RequestMapping(value = "/vols/reviewVolunteer.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String reviewVolunteer(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String checkContent= ActionContext.getActionContext().getParameterAsString("checkContent");
		String status= ActionContext.getActionContext().getParameterAsString("status");
		Long volunteerId=null;
		ResultData data=new ResultData();
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("志愿者信息审核操作参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者信息审核操作参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Volunteer volunteer=volunteerService.selectByKey(volunteerId);
			map.addAttribute("volunteer", volunteer);
			VolunteerCheck volunteerCheck=null;
			int volunteerCheckResult=0;
			
			volunteerCheck=volunteerCheckService.getVolunteerCheckByVolunteerId(volunteerId);
			if(volunteerCheck!=null){
				
				volunteerCheck.setOperTime(new Timestamp(new Date().getTime()));
				BaseUser crtUser=ShiroSessionMgr.getLoginUser();
				volunteerCheck.setUserName(crtUser.getUserName());
				volunteerCheck.setVolunteerId(volunteerId);
				volunteerCheck.setCheckContent(checkContent);
				volunteerCheck.setStatus("03");
				LoggerServer.debug(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息更改操作对象为【"+volunteerCheck+"】",volunteerId,volunteer.getUserName(),true);
				List<ValidateMessage> errors =ValidatorUtil.validate(volunteerCheck, Groups.Update.class);
				if(null!=errors&&errors.size()>0){
					return ResultData.fail(errors).toJSONString();
				}
				volunteerCheckResult=volunteerCheckService.updateByPrimaryKey(volunteerCheck);
				if(volunteerCheckResult>0){
					LoggerServer.info(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息更改操作成功",volunteerId,volunteer.getUserName(),true);
				}else{
					LoggerServer.info(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息更改操作失败",volunteerId,volunteer.getUserName(),false);
				}
			}else{
				
				volunteerCheck=new VolunteerCheck();
				volunteerCheck.setOperTime(new Timestamp(new Date().getTime()));
				BaseUser crtUser=ShiroSessionMgr.getLoginUser();
				volunteerCheck.setUserName(crtUser.getUserName());
				volunteerCheck.setVolunteerId(volunteerId);
				volunteerCheck.setCheckContent(checkContent);
				volunteerCheck.setStatus(status);
				LoggerServer.debug(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息新增操作对象为【"+volunteerCheck+"】",volunteerId,volunteer.getUserName(),true);
				List<ValidateMessage> errors =ValidatorUtil.validate(volunteerCheck, Groups.Add.class);
				if(null!=errors&&errors.size()>0){
					return ResultData.fail(errors).toJSONString();
				}
				
				volunteerCheckResult=volunteerCheckService.insertNotNull(volunteerCheck);
				if(volunteerCheckResult>0){
					LoggerServer.info(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息新增操作成功",volunteerId,volunteer.getUserName(),true);
				}else{
					LoggerServer.info(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息新增操作失败",volunteerId,volunteer.getUserName(),false);
				}
			}
			
			if(volunteerCheckResult>0){
				data.setSuccess(true);
				data.setMessage("审核操作成功");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("审核操作失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者信息审核操作失败【"+e.getMessage()+"】",volunteerId,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("审核志愿者信息发生错误:{}",e);
		}
		
		return data.toJSONString();
	}
	
	*/
	/**
	 * 获取活动报名管理列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/registrationManage.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String registrationManage(HttpServletRequest request,HttpServletResponse response){
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
			PageInfo<Activity> pageInfo =activityService.getApplyingActivityByEntity(activity, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLYLIST, "活动报名列表【第"+currPage+"页】查询操作成功,查询条件:"+activity,null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLYLIST, "活动报名信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 分页查看活动报名情况列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/activityApply.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String activityApply(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String id= ActionContext.getActionContext().getParameterAsString("aId");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 5;
		}
		
		String orderBy = "apply_time DESC";
		ResultData data = new ResultData();
		Long activityId=null;
		try {
			activityId=Long.parseLong(id); 
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLY, "查看指定活动报名情况参数信息不合法【"+id+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			PageInfo<VolunteerView> pageInfo = activityVolunteerService.getActivityVolunteersByActivityId(activityId, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLY, "查看指定活动报名情况信息", activityId, null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYAPPLY, "查看指定活动报名情况发生错误,错误信息【"+e.getMessage()+"】", activityId, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	//审核志愿者活动报名申请
	@RequestMapping(value = "/activity/volunteerApplyActivity.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String reviewVolunteerApplyActivity(HttpServletRequest request,ModelMap map) throws TPSException{
		Long vId= ActionContext.getActionContext().getParameterAsLong("vId");
		Long aId= ActionContext.getActionContext().getParameterAsLong("aId");
		Integer status= ActionContext.getActionContext().getParameterAsInt("status");
		ResultData data=new ResultData();
		if(vId.equals(-1l) || aId.equals(-1l)){
			LoggerServer.error(ObjectType.ACTIVE,OperateType.REVIEWACTIVITYAPPLY, "审核志愿者报名活动申请参数信息不合法",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		if(!status.equals(1) && !status.equals(2)){
			LoggerServer.error(ObjectType.ACTIVE,OperateType.REVIEWACTIVITYAPPLY, "审核志愿者报名活动申请status【"+status+"】信息不合法",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try{
			ActivityVolunteer activityVolunteer=activityVolunteerService.getActivityApplyByExample(vId,aId);
			if(activityVolunteer!=null && activityVolunteer.getApplyResult()==null){
				activityVolunteer.setApplyResult(status);
				if(activityVolunteerService.updateNotNull(activityVolunteer)>0){
					data.setSuccess(true);
					data.setMessage("操作成功");
					return data.toJSONString();
				}else{
					data.setSuccess(false);
					data.setMessage("操作失败");
					return data.toJSONString();
				}
			}else{
				data.setSuccess(false);
				data.setMessage("非法操作");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.REVIEWACTIVITYAPPLY, "审核志愿者报名活动申请失败【"+e.getMessage()+"】",vId,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		
		return data.toJSONString();
	}
	/*/**
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
	
	*//**
	 * 重置志愿者密码
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value="/vols/resetVolunteerPwd.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String resetUserPassword(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.RESETVOLUNTEERPWD, "志愿者密码重置操作参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "重置密码操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		long[] userKeys=new long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long userKey=Long.parseLong(keys[i]);
				userKeys[i]=userKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.VOLUNTEER,OperateType.RESETVOLUNTEERPWD, "志愿者密码重置操作参数信息【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("重置用户密码参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			boolean resetPwd=volunteerService.resetVolunteerPassword(userKeys);
			if(resetPwd){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.RESETVOLUNTEERPWD, "志愿者【"+userKeys+"】密码重置操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("重置密码成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.RESETVOLUNTEERPWD, "志愿者【"+userKeys+"】密码重置操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("重置密码失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.RESETVOLUNTEERPWD, "志愿者密码重置操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("重置用户密码操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	*//**
	 * 分页查看志愿者培训信息列表
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value="/vols/volTrRecordList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volTrRecordList(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 5;
		}
		
		String orderBy = "tr.CR_TIME DESC";
		ResultData data = new ResultData();
		Long volunteerId=null;
		try {
			volunteerId=Long.parseLong(id); 
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLTRRECORD, "查看志愿者培训信息不合法【"+id+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			PageInfo<TrRecord> trRecords = trRecordService.getTrRecordsByVolunteerId(volunteerId, orderBy, currPage, pageSize);
			LoggerServer.debug(ObjectType.VOLUNTEER,OperateType.SHOWVOLTRRECORD, "成功查看志愿者培训信息", volunteerId, null,true);
			data.setSuccess(true);
			data.put("page",trRecords);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLTRRECORD, "查看志愿者培训信息发生错误,错误信息【"+e.getMessage()+"】", volunteerId, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者培训信息出错,currPage:{},pageSize:{},volunteerId:{}",currPage,pageSize,id,e);
		}
		return data.toJSONString();
	}*/
}
