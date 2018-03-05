package com.topstar.volunteer.web.controller;

import java.sql.Timestamp;
import java.util.Date;
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
import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerCheck;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.ActivityVolunteerService;
import com.topstar.volunteer.service.SerTeamService;
import com.topstar.volunteer.service.TrRecordService;
import com.topstar.volunteer.service.VolunteerCertficationService;
import com.topstar.volunteer.service.VolunteerCheckService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;


@Controller
public class VolunteerController {
	
	private static Logger logger = LoggerFactory.getLogger(VolunteerController.class);
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private SerTeamService serTeamService;
	
	@Autowired
	private TrRecordService trRecordService;
	
	@Autowired
	private VolunteerCheckService volunteerCheckService;
	
	@Autowired
	private VolunteerCertficationService volunteerCertificationService;
	
	@Autowired
	private ActivityVolunteerService activityVolunteerService;
	
	@RequestMapping(value="/vols/list.html")
	public String toVolunteer(HttpServletRequest request){
		return "/volunteer/list/list";
	}
	
	@RequestMapping(value="/vols/reviewList.html")
	public String toVolunteerReview(HttpServletRequest request){
		
		return "/volunteer/review/list";
	}	
	
	@RequestMapping(value="/vols/PendingVols.html")
	public String toPendingVols(HttpServletRequest request,ModelMap map){
		map.addAttribute("selected", true);
		return "/volunteer/review/list";
	}	
	
	@RequestMapping(value = "/vols/editVolunteer.html")
	public String toEditVolunteer(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		Long volunteerId=null;
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("查看志愿者信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITVOLUNTEER, "跳转到编辑志愿者信息页面展示的志愿者参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Volunteer volunteer=volunteerService.selectByKey(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITVOLUNTEER, "跳转到编辑志愿者信息页面展示的志愿者信息", volunteerId, volunteer.getUserName(),true);
			map.addAttribute("volunteer", volunteer);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITVOLUNTEER, "跳转到编辑志愿者信息页面展示的志愿者信息查询失败【"+e.getMessage()+"】",volunteerId,null);
		}
		return "/volunteer/list/editVolunteer";
	}
	
	/**
	 * 志愿者的编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/vols/editVolunteer.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String editVolunteer(HttpServletRequest request,HttpServletResponse response,Volunteer volunteer){
		ResultData data = new ResultData();
		List<ValidateMessage> errors =ValidatorUtil.validate(volunteer, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		volunteer.setRemark(StringTools.FormatStrFromInput(volunteer.getRemark()));
		volunteer.setServiceExperiment(StringTools.FormatStrFromInput(volunteer.getServiceExperiment()));
		volunteer.setSpecility(StringTools.FormatStrFromInput(volunteer.getSpecility() ));
		try {
			int updateVolunteer=volunteerService.updateVolsInfo(volunteer);
			if(updateVolunteer>0){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITVOLUNTEER, "志愿者信息更新操作成功", volunteer.getId(), volunteer.getUserName(),true);
				data.setSuccess(true);
				data.setMessage("志愿者保存成功");
				return data.toJSONString();
			}else{
				LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITVOLUNTEER, "志愿者信息更新操作失败", volunteer.getId(), volunteer.getUserName());
				data.setSuccess(true);
				data.setMessage("志愿者保存失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITVOLUNTEER, "志愿者信息更新操作失败【"+e.getMessage()+"】", volunteer.getId(), volunteer.getUserName());
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("修改志愿者信息操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	@RequestMapping(value = "/vols/viewVolunteer.html")
	public String toViewVolunteer(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String teamName=ActionContext.getActionContext().getParameterAsString("teamName");
		Long volunteerId=null;
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("查看志愿者信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEER, "志愿者信息页面展示的志愿者参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			VolunteerView  volunteerView=volunteerCertificationService.getVolunteerCertByVolunteerId(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEER, "志愿者信息页面展示的志愿者信息", volunteerId, volunteerView.getUserName(),true);
			map.addAttribute("volunteerView", volunteerView);
			map.addAttribute("teamName", teamName);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEER, "志愿者信息展示页面的志愿者信息查询操作失败["+e.getMessage()+"]", volunteerId, null);
		}
		
		return "/volunteer/list/viewVolunteer";
	}
	
	//查看审核的志愿者信息
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
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWREVIEWVOLUNTEER, "志愿者审核信息页面展示result为"+volunteerCheck, volunteerId, volunteer.getUserName(),true);
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
			if(null==volunteer){
				LoggerServer.error(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者信息不存在",volunteerId,null);
				data.setSuccess(false);
				data.setMessage("非法操作");
				return data.toJSONString();
			}
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
				volunteerCheck.setStatus(status);
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息更改操作对象为【"+volunteerCheck+"】",volunteerId,volunteer.getUserName(),true);
				List<ValidateMessage> errors =ValidatorUtil.validate(volunteerCheck, Groups.Update.class);
				if(null!=errors&&errors.size()>0){
					return ResultData.fail(errors).toJSONString();
				}
				volunteerCheckResult=volunteerCheckService.updateVolunteerCheck(volunteerCheck, volunteer);
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
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEER, "志愿者审核信息新增操作对象为【"+volunteerCheck+"】",volunteerId,volunteer.getUserName(),true);
				List<ValidateMessage> errors =ValidatorUtil.validate(volunteerCheck, Groups.Add.class);
				if(null!=errors&&errors.size()>0){
					return ResultData.fail(errors).toJSONString();
				}
				volunteerCheckResult=volunteerCheckService.saveVolunteerCheck(volunteerCheck, volunteer);
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
	
	/**
	 * 志愿者名单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/vols/volunteerList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerList(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String realName = ActionContext.getActionContext().getParameterAsString("realName");
		String idCard = ActionContext.getActionContext().getParameterAsString("idCard");
		String tele = ActionContext.getActionContext().getParameterAsString("tele");
		Long serviceTeam = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		int status = ActionContext.getActionContext().getParameterAsInt("status");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		volunteer.setRealName(realName);
		volunteer.setMobile(tele);
		volunteer.setServiceTeam(serviceTeam);
		volunteer.setIdcard(idCard);
		volunteer.setStatus(status);
		String orderBy = "reg_time DESC,USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<VolunteerView> pageInfo = volunteerService.findByEntity(volunteer, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERLIST, "志愿者信息列表【第"+currPage+"页】查询操作成功,查询条件:"+volunteer,null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERLIST, "志愿者信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者名单信息出错,currPage:{},pageSize:{},userName:{},idCard:{},tele:{},serviceTeam:{}",currPage,pageSize,realName,idCard,tele,serviceTeam,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 志愿者审核名单信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/vols/volunteerReviewList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerReviewList(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String realName = ActionContext.getActionContext().getParameterAsString("realName");
		String idCard = ActionContext.getActionContext().getParameterAsString("idCard");
		Integer status = ActionContext.getActionContext().getParameterAsInt("status");
		String tele = ActionContext.getActionContext().getParameterAsString("tele");
		Long serviceTeam = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		volunteer.setRealName(realName);
		volunteer.setMobile(tele);
		volunteer.setServiceTeam(serviceTeam);
		volunteer.setIdcard(idCard);
		volunteer.setStatus(status);
		
		String orderBy = "oper_time DESC,v.USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<VolunteerView> pageInfo = volunteerService.findVolunteerCheckByEntity(volunteer, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEERLIST, "志愿者审核信息列表【第"+currPage+"页】查询操作成功",null,realName+"-"+idCard,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.REVIEWVOLUNTEERLIST, "志愿者审核信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,realName+"-"+idCard);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者审核名单信息出错,currPage:{},pageSize:{},userName:{},idCard:{},tele:{},serviceTeam:{},status:{}",currPage,pageSize,realName,idCard,tele,serviceTeam,status,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 志愿者的批量审核
	 * @param request
	 * @param response
	 * @return
	 */
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
		Long[] volunteerKeys=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				Long userKey=Long.parseLong(keys[i]);
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
	
	/**
	 * 重置志愿者密码
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/**
	 * 分页查看志愿者培训信息列表
	 * @param request
	 * @param response
	 * @return
	 */
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
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWVOLTRRECORD, "成功查看志愿者培训信息", volunteerId, null,true);
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
	}
	
	/**
	 * 根据志愿者ID查询该志愿者参加过的活动情况列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/vols/joinedActivityList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String joinedActivityList(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 5;
		}
		
		String orderBy = "APPLY_TIME DESC";
		ResultData data = new ResultData();
		Long volunteerId=null;
		try {
			volunteerId=Long.parseLong(id); 
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWJOINEDACTIVITY, "查看志愿者参加过的活动情况信息不合法【"+id+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			PageInfo<VolunteerView> joinedActivitys = activityVolunteerService.getJoinedActivityByVolunteerId(volunteerId, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWJOINEDACTIVITY, "成功查看志愿者参加过的活动情况信息", volunteerId, null,true);
			data.setSuccess(true);
			data.put("page",joinedActivitys);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWJOINEDACTIVITY, "查看志愿者参加过的活动情况信息发生错误,错误信息【"+e.getMessage()+"】", volunteerId, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者参加过的活动情况信息出错,currPage:{},pageSize:{},volunteerId:{}",currPage,pageSize,id,e);
		}
		return data.toJSONString();
	}
}
