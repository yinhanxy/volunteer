package com.topstar.volunteer.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.entity.ActivityClass;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.annotations.Token;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.service.ActivityVolunteerService;
import com.topstar.volunteer.service.TeamUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.TokenUtil;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;


/**
 * 活动登记控制器
 * @author TRS
 *
 */
@Controller
public class ActivityRegistController {
	
	private static Logger logger = LoggerFactory.getLogger(ActivityRegistController.class);
	
	@Autowired
	private ConfigCache configCache;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private TeamUserService teamUserService;
	
	@Autowired
	private ActivityVolunteerService activityVolunteerService;
	
	/**
	 * 跳转到活动登记的列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/regist.html")
	public String toRegist(HttpServletRequest request){
		return "/activity/regist/list";
	}
	
	/**
	 * 跳转到活动登记的列表页面,改变活动状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/SubmitActs.html")
	public String toSubmitActs(HttpServletRequest request,ModelMap map){
		String selected= ActionContext.getActionContext().getParameterAsString("selected");
		if (selected.equals("submited")) {
			//活动状态为待提交
			map.addAttribute("submited", true);
		}
		if (selected.equals("released")) {
			//活动状态为待发布
			map.addAttribute("released", true);
		}
		return "/activity/regist/list";
	}
	
	/**
	 * 跳转到添加活动页面
	 * @param request
	 * @return
	 */
	@Token(save=true,customKey = "ActivityRegistController_addActivity")
	@RequestMapping(value="/activity/addActivity.html")
	public String toAddActivity(HttpServletRequest request){
		
		return "/activity/regist/addActivity";
	}	
	
	/**
	 * 跳转到添加招募页面
	 * @param request
	 * @return
	 */
	@Token(save=true,customKey = "ActivityRegistController_addRecruit")
	@RequestMapping(value="/activity/addRecruit.html")
	public String toAddRecruit(HttpServletRequest request){
		
		return "/activity/regist/addRecruit";
	}
	
	/**
	 * 跳转到选择招募的活动页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/recruitActivityList.html")
	public String toRecruitActivity(HttpServletRequest request){
		
		return "/activity/regist/selectActivity";
	}
	
	/**
	 * 跳转到编辑活动页面
	 * @param request
	 * @param map
	 * @return
	 * @throws TPSException
	 */
	@RequestMapping(value = "/activity/edit.html")
	public String toEditActivity(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("aid");
		Long activityId=null;
		try{
			activityId=Long.valueOf(id);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.EDITACTIVITY, "跳转到编辑活动信息页面展示的参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Activity activity=activityService.selectByKey(activityId);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.EDITACTIVITY, "成功跳转到编辑活动信息页面展示的信息", activityId, activity.getName(),true);
			map.addAttribute("activity", activity);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.EDITACTIVITY, "跳转到编辑活动信息页面展示查询失败【"+e.getMessage()+"】",activityId,"活动ID");
		}
		return "/activity/regist/editActivity";
	}
	
	/**
	 * 添加活动
	 * @param request
	 * @param response
	 * @return
	 */
	@Token(remove=true,customKey = "ActivityRegistController_addActivity")
	@RequestMapping(value="/activity/addActivity.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addActivity(HttpServletRequest request,HttpServletResponse response,Activity activity){
		String cType= ActionContext.getActionContext().getParameterAsString("cType");
		ResultData data = new ResultData();
		if(!(StringUtils.isBlank(cType) || "ss".equals(cType) || "sp".equals(cType))){
			data.setSuccess(false);
			data.setMessage("访问不合法");
			data.setToken(request, "ActivityRegistController_addActivity");
			return data.toJSONString();
		}
		switch (cType) {
		case "ss":
			activity.setStatus(2);
			break;
		case "sp":
			activity.setStatus(3);
			break;
		default:
			activity.setStatus(null);
			break;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser!=null){
			activity.setPublisher(crtUser.getUserName());
			SerTeam serTeam =teamUserService.getSerTeamByUserId(crtUser.getId());
			if(serTeam!=null){
				activity.setSerId(serTeam.getId());
			}else{
				data.setSuccess(false);
				data.setMessage("用户必须属于服务队组织才可添加活动");
				data.setToken(request, "ActivityRegistController_addActivity");
				return data.toJSONString();
			}
		}
		
		List<ValidateMessage> errors =ValidatorUtil.validate(activity, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).setToken(request, "ActivityRegistController_addActivity").toJSONString();
		}
		try {
			int addActivity=activityService.insertNotNull(activity);
			if(addActivity>0){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.CREATEACTIVITY, "成功添加活动", activity.getId(),activity.getName(),true);
				data.setSuccess(true);
				data.setMessage("活动成功添加");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.CREATEACTIVITY, "添加活动失败", activity.getId(),activity.getName(),false);
				data.setSuccess(false);
				data.setMessage("活动添加失败");
				data.setToken(request, "ActivityRegistController_addActivity");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.CREATEACTIVITY, "添加活动发生错误,错误信息【"+e.getMessage()+"】", activity.getId(), activity.getName());
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加活动招募
	 * @param request
	 * @param response
	 * @return
	 */
	@Token(remove=true,customKey = "ActivityRegistController_addRecruit")
	@RequestMapping(value="/activity/addRecruit.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addRecruit(HttpServletRequest request,HttpServletResponse response,Activity activity){
		String cType= ActionContext.getActionContext().getParameterAsString("cType");
		ResultData data = new ResultData();
		
		Activity existsActivity=activityService.selectByKey(activity.getId());
		if(existsActivity == null || existsActivity.getType()!=2 || (existsActivity.getStatus()!=7 && existsActivity.getStatus()!=8)){
			LoggerServer.error(ObjectType.ACTIVE,OperateType.CREATERECRUIT, "添加活动招募操作非法", activity.getId(), activity.getName());
			data.setSuccess(false);
			data.setMessage("添加活动招募操作非法");
			data.setToken(request, "ActivityRegistController_addRecruit");
			return data.toJSONString();
		}
		if(!(StringUtils.isBlank(cType) || "ss".equals(cType) || "sp".equals(cType))){
			data.setSuccess(false);
			data.setMessage("访问不合法");
			data.setToken(request, "ActivityRegistController_addRecruit");
			return data.toJSONString();
		}
		switch (cType) {
		case "ss":
			activity.setStatus(2);
			break;
		case "sp":
			activity.setStatus(3);
			break;
		default:
			activity.setStatus(null);
			break;
		}
		activity.setType(existsActivity.getType());
		activity.setSerId(existsActivity.getSerId());
		activity.setPublisher(existsActivity.getPublisher());
		List<ValidateMessage> errors =ValidatorUtil.validate(activity, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).setToken(request, "ActivityRegistController_addRecruit").toJSONString();
		}
		
		try {
			int addActivity=activityService.updateNotNull(activity);
			if(addActivity>0){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.CREATERECRUIT, "成功添加招募活动", activity.getId(),activity.getName(),true);
				data.setSuccess(true);
				data.setMessage("活动招募成功添加");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.CREATERECRUIT, "添加活动招募失败", activity.getId(),activity.getName(),false);
				data.setSuccess(false);
				data.setMessage("活动招募添加失败");
				data.setToken(request, "ActivityRegistController_addRecruit");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.CREATERECRUIT, "添加活动招募发生错误,错误信息【"+e.getMessage()+"】", activity.getId(), activity.getName());
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 活动的编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/editActivity.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String editActivity(HttpServletRequest request,HttpServletResponse response,Activity activity){
		String cType= ActionContext.getActionContext().getParameterAsString("cType");
		ResultData data = new ResultData();
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser!=null){
			activity.setPublisher(crtUser.getUserName());
			SerTeam serTeam =teamUserService.getSerTeamByUserId(crtUser.getId());
			if(serTeam!=null){
				activity.setSerId(serTeam.getId());
			}else{
				data.setSuccess(false);
				data.setMessage("用户必须属于服务队组织才可编辑活动");
				return data.toJSONString();
			}
		}
		if(!(StringUtils.isBlank(cType) || "ss".equals(cType) || "sp".equals(cType))){
			data.setSuccess(false);
			data.setMessage("访问不合法");
			return data.toJSONString();
		}
		switch (cType) {
		case "ss":
			activity.setStatus(2);
			break;
		case "sp":
			activity.setStatus(3);
			break;
		default:
			activity.setStatus(null);
			break;
		}
		List<ValidateMessage> errors =ValidatorUtil.validate(activity, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		try {
			int editActivity=activityService.updateNotNull(activity);
			if(editActivity>0){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.EDITACTIVITY, "成功编辑活动", activity.getId(),activity.getName(),true);
				data.setSuccess(true);
				data.setMessage("保存成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.EDITACTIVITY, "编辑活动失败", activity.getId(),activity.getName(),false);
				data.setSuccess(false);
				data.setMessage("保存失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.EDITACTIVITY, "编辑活动发生错误,错误信息【"+e.getMessage()+"】", activity.getId(), activity.getName());
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("编辑活动操作内部出现出错",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 分页查看长期类型且处于已完成和已撤销状态的活动信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/recruitActivityList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String recruitActivityList(HttpServletRequest request,HttpServletResponse response){
		Activity activity= new Activity();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String activityName = ActionContext.getActionContext().getParameterAsString("activityName");
		String publisher = ActionContext.getActionContext().getParameterAsString("publisher");
		Long serviceTeam = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 5;
		}
		
		String orderBy = "create_time DESC";
		ResultData data = new ResultData();
		activity.setName(activityName);
		activity.setPublisher(publisher);
		try {
			PageInfo<Activity> pageInfo = activityService.getRecruitActivityList(activity,orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWRECRUITACTIVITYLIST, "成功查看长期类型且处于已完成和已撤销状态的活动情况信息", null, null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWRECRUITACTIVITYLIST, "查看长期类型且处于已完成和已撤销状态的活动情况发生错误,错误信息【"+e.getMessage()+"】", null, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到显示活动信息页面
	 * @param request
	 * @param map
	 * @return
	 * @throws TPSException
	 */
	@RequestMapping(value ="/activity/view.html")
	public String toViewActivity(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("aId");
		Long activityId=null;
		try{
			activityId=Long.valueOf(id);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITY, "活动信息页面展示的参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Activity activity=activityService.selectByKey(activityId);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITY, "查看活动信息页面展示信息", activity.getId(), activity.getName(),true);
			map.addAttribute("activity", activity);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITY, "活动信息展示页面的查询操作失败["+e.getMessage()+"]", activityId, null);
		}
		
		return "/activity/regist/viewActivity";
	}
	
	/**
	 * 分页查看参加指定活动的志愿者信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/activityVolunteerInfo.do",produces="text/plain;charset=UTF-8")
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
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITY, "查看参加指定活动的志愿者信息列表参数信息不合法【"+id+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			PageInfo<VolunteerView> pageInfo = activityVolunteerService.getVolunteersInfoByActivityId(activityId, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.ACTIVE,OperateType.SHOWACTIVITY, "成功查看参加指定活动的志愿者信息列表", activityId, "活动ID",true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITY, "查看参加指定活动的志愿者信息列表发生错误,错误信息【"+e.getMessage()+"】", activityId, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 从系统配置中获取活动类别信息
	 * @param reauest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/activityClass.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String activityClass(HttpServletRequest reauest, HttpServletResponse response){
		ResultData data = new ResultData();
		List<ActivityClass> activityClassList=null;
		List<Config> activityClassConfig=configCache.getConfigValuesByType("activityClass");
		if(activityClassConfig!=null && !activityClassConfig.isEmpty()){
			activityClassList= new ArrayList<ActivityClass>(); 
			for (Config config : activityClassConfig) {
				ActivityClass activityClass=new ActivityClass();
				activityClass.setId(config.getId());
				activityClass.setName(config.getContent());
				activityClassList.add(activityClass);
			}
		}
		if(activityClassList!=null && !activityClassList.isEmpty()){
			data.setSuccess(true);
			data.put("activityClassList", activityClassList);
		}else{
			data.setSuccess(false);
		}
		return data.toJSONString();
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
	 * 获取活动登记列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/regist.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String registList(HttpServletRequest request,HttpServletResponse response){
		Activity activity= new Activity();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String activityName = ActionContext.getActionContext().getParameterAsString("activityName");
		String publisher = ActionContext.getActionContext().getParameterAsString("publisher");
		Long serviceTeamId = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		Integer status = ActionContext.getActionContext().getParameterAsInt("status");
		String startTime = ActionContext.getActionContext().getParameterAsString("startTime");
		String endTime = ActionContext.getActionContext().getParameterAsString("endTime");
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
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				return data.toJSONString();
			}
		}
		if(StringUtils.isNotBlank(endTime)){
			try {
				activity.setActivityEt(format.parse(endTime));
			} catch (ParseException e) {
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				return data.toJSONString();
			}
		}
		String orderBy = "CREATE_TIME DESC,NAME";
		
		try {
			PageInfo<Activity> pageInfo =activityService.findByEntity(activity, orderBy, currPage, pageSize);
			LoggerServer.debug(ObjectType.ACTIVE,OperateType.SHOWACTIVITYLIST, "活动登记列表【第"+currPage+"页】查询操作成功,查询条件:"+activity,null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.SHOWACTIVITYLIST, "活动登记信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
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
	/**
	 * 批量撤销活动
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/cancel.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String batchCancelActivitys(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("aIds");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCANCELACTIVITYS, "批量撤销活动操作参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "批量撤销活动操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] activityIds=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				Long activityId=Long.parseLong(keys[i]);
				activityIds[i]=activityId;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCANCELACTIVITYS, "批量撤销活动操作参数信息【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				return data.toJSONString();
			}
		}
		try{
			boolean batchCancelActivitys=activityService.batchCancelActivitys(activityIds);
			if(batchCancelActivitys){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHCANCELACTIVITYS, "批量撤销活动操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("批量撤销成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHCANCELACTIVITYS, "批量撤销活动操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("批量撤销失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			if(e instanceof TPSException){
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCANCELACTIVITYS, "批量撤销活动操作不合法,操作id:【"+activityIds+"】",null,null);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("批量撤销活动操作内部发生错误",e);
			}else{
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCANCELACTIVITYS, "批量撤销活动操作失败【"+e.getMessage()+"】",null,null);
				data.setSuccess(false);
				data.setMessage("发生错误");
				logger.error("批量撤销活动操作内部发生错误",e);
			}
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量删除活动
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/del.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String batchDelActivitys(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("aIds");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHDELACTIVITYS, "批量删除活动操作参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "批量删除活动操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] activityIds=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				Long activityId=Long.parseLong(keys[i]);
				activityIds[i]=activityId;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHDELACTIVITYS, "批量删除活动操作参数信息【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				return data.toJSONString();
			}
		}
		try{
			boolean batchCancelActivitys=activityService.batchDelActivitys(activityIds);
			if(batchCancelActivitys){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHDELACTIVITYS, "批量删除活动操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("批量删除成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHDELACTIVITYS, "批量删除活动操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("批量删除失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			if(e instanceof TPSException){
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHDELACTIVITYS, "批量删除活动操作不合法,操作id:【"+activityIds+"】",null,null);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("批量删除活动操作非法",e);
			}else{
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHDELACTIVITYS, "批量删除活动操作失败【"+e.getMessage()+"】",null,null);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("批量删除活动操作内部发生错误",e);
			}
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量发布活动
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/publication.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String batchPubActivitys(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("aIds");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHPUBACTIVITYS, "批量发布活动操作参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "批量发布活动操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] activityIds=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				Long activityId=Long.parseLong(keys[i]);
				activityIds[i]=activityId;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHPUBACTIVITYS, "批量发布活动操作参数信息【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				return data.toJSONString();
			}
		}
		try{
			boolean batchCancelActivitys=activityService.batchPubActivitys(activityIds);
			if(batchCancelActivitys){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHPUBACTIVITYS, "批量发布活动操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("批量发布成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHPUBACTIVITYS, "批量发布活动操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("批量发布失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			if(e instanceof TPSException){
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHPUBACTIVITYS, "批量发布活动操作不合法,操作id:【"+activityIds+"】",null,null);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("批量发布活动操作非法",e);
			}else{
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHPUBACTIVITYS, "批量发布活动操作失败【"+e.getMessage()+"】",null,null);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("批量发布活动操作内部发生错误",e);
			}
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量提交活动
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/submitActivity.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String batchCommitActivitys(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("aIds");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCOMMITACTIVITYS, "批量提交活动操作参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "批量提交活动操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] activityIds=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				Long activityId=Long.parseLong(keys[i]);
				activityIds[i]=activityId;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCOMMITACTIVITYS, "批量提交活动操作参数信息【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				return data.toJSONString();
			}
		}
		try{
			boolean batchCancelActivitys=activityService.batchCommitActivitys(activityIds);
			if(batchCancelActivitys){
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHCOMMITACTIVITYS, "批量提交活动操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("批量提交成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ACTIVE,OperateType.BATCHCOMMITACTIVITYS, "批量提交活动操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("批量提交失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			if(e instanceof TPSException){
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCOMMITACTIVITYS, "批量提交活动操作不合法,操作id:【"+activityIds+"】",null,null);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("批量提交活动操作非法",e);
			}else{
				LoggerServer.error(ObjectType.ACTIVE,OperateType.BATCHCOMMITACTIVITYS, "批量提交活动操作失败【"+e.getMessage()+"】",null,null);
				data.setSuccess(false);
				data.setMessage(e.getMessage());
				logger.error("批量提交活动操作内部发生错误",e);
			}
		}
		return data.toJSONString();
	}
	
}
