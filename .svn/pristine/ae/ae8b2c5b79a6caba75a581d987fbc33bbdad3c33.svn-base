package com.topstar.volunteer.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.StarEvaluateService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;


/**
 * 志愿者星级评价控制器
 * @author TRS
 *
 */
@Controller
public class StarEvaluateController {
	
	private static Logger logger = LoggerFactory.getLogger(StarEvaluateController.class);
	
	@Autowired
	private StarEvaluateService starEvaluateService;
	
	@Autowired
	private VolunteerService volunteerService;
	
	@RequestMapping(value="/star/list.html")
	public String toVolunteer(HttpServletRequest request){
		return "/volunteer/star/list";
	}
	
	@RequestMapping(value="/star/starEvaluateList.html")
	public String toStarEvaluateList(HttpServletRequest request,ModelMap map){
		map.put("volunteerId", ActionContext.getActionContext().getParameterAsString("vId"));
		map.put("serName", ActionContext.getActionContext().getParameterAsString("serName"));
		return "/volunteer/star/viewHistoryStar";
	}
	
	//查看志愿者所有历史星级评定中的某条评定详细信息
	@RequestMapping(value = "/star/viewDetailStar.html")
	public String viewDetailStar(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("seId");
		String serName= ActionContext.getActionContext().getParameterAsString("serName");
		Long starEvaluateId=null;
		try{
			starEvaluateId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("跳转到查看志愿者星级评定信息页面参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERSTAR, "跳转到查看志愿者星级评定页面参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			VolunteerView  volunteerView=starEvaluateService.getStarEvaluateByStarEvaluateId(starEvaluateId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERSTAR, "跳转到查看志愿者星级评定的信息", volunteerView.getId(), volunteerView.getUserName(),true);
			map.addAttribute("volunteerView", volunteerView);
			map.addAttribute("serName", serName);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERSTAR, "跳转到查看志愿者星级评定信息查询失败【"+e.getMessage()+"】",starEvaluateId,null);
		}
		
		return "/volunteer/star/viewHistoryDetailStar";
	}
	
	//查看志愿者星级评定信息
	@RequestMapping(value = "/star/viewVolunteerStar.html")
	public String toViewVolunteer(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String serName= ActionContext.getActionContext().getParameterAsString("serName");
		Long volunteerId=null;
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("跳转到查看志愿者星级评定信息页面参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERSTAR, "跳转到查看志愿者星级评定页面参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			VolunteerView  volunteerView=starEvaluateService.getLatestVolunteerStarByVolunteerId(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERSTAR, "跳转到查看志愿者星级评定的信息", volunteerId, volunteerView.getUserName(),true);
			map.addAttribute("volunteerView", volunteerView);
			map.addAttribute("serName", serName);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERSTAR, "跳转到查看志愿者星级评定信息查询失败【"+e.getMessage()+"】",volunteerId,null);
		}
		
		return "/volunteer/star/viewVolunteerStar";
	}
	
	//跳转到志愿者星级评定页面
	@RequestMapping(value = "/star/starEvaluate.html")
	public String toEditCertCheck(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		Long volunteerId=null;
		try{
			volunteerId=Long.parseLong(id);
		}catch (Exception e) {
			logger.error("跳转到志愿者星级评定页面参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "跳转到志愿者星级评定页面参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Volunteer volunteer=volunteerService.selectByKey(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "跳转到志愿者星级评定页面的信息", volunteerId, volunteer.getUserName(),true);
			map.addAttribute("volunteer", volunteer);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "跳转到志愿者星级评定页面查询失败【"+e.getMessage()+"】",volunteerId,null);
		}
		return "/volunteer/star/starEvaluate";
	}
	
	/**
	 * 添加志愿者星级评定的信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/star/addStarEvaluate.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addCertCheck(HttpServletRequest request,HttpServletResponse response,StarEvaluate starEvaluate){
		ResultData data = new ResultData();
		
		try {
			starEvaluate.setEvaluateTime(new Timestamp(new Date().getTime()));
			BaseUser crtUser=ShiroSessionMgr.getLoginUser();
			starEvaluate.setEvaluateUser(crtUser.getUserName());
			
			List<ValidateMessage> errors =ValidatorUtil.validate(starEvaluate, Groups.Add.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			starEvaluate.setIsManual("1");
			LoggerServer.debug(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "志愿者星级评定新增信息为"+starEvaluate,null, null,true);
			int starEvaluateResult=starEvaluateService.insertNotNull(starEvaluate);
			if(starEvaluateResult>0){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "志愿者星级评定信息新增成功",null, null,true);
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "志愿者星级评定信息新增失败",null, null,false);
			}
			if(starEvaluateResult>0){
				data.setSuccess(true);
				data.setMessage("志愿者星级评定成功");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("志愿者星级评定失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.STAREVALUATE, "志愿者星级评定失败【"+e.getMessage()+"】",starEvaluate.getVolunteerId(),null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("志愿者星级评定操作内部出现出错",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 分页查看志愿者星级评定的信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/star/starEvaluateList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String certChecklist(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String id= ActionContext.getActionContext().getParameterAsString("vId");
		if(currPage <1){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		String orderBy = "evaluate_time DESC";
		ResultData data = new ResultData();
		Long volunteerId=null;
		try {
			volunteerId=Long.parseLong(id); 
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWSTAREVALUATELIST, "查看志愿者星级评定记录列表信息参数不合法【"+id+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			PageInfo<VolunteerView> pageInfo = starEvaluateService.getAllVolunteerStarByVolunteerId(volunteerId, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWSTAREVALUATELIST, "成功查看志愿者星级评定记录列表信息", volunteerId, null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWSTAREVALUATELIST, "查看志愿者星级评定记录列表信息发生错误,错误信息【"+e.getMessage()+"】", volunteerId, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者星级评定记录列表信息出错,currPage:{},pageSize:{},volunteerId:{}",currPage,pageSize,id,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 志愿者服务星级信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/star/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerStarList(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String realName = ActionContext.getActionContext().getParameterAsString("realName");
		String idCard = ActionContext.getActionContext().getParameterAsString("idCard");
		String star = ActionContext.getActionContext().getParameterAsString("star");
		String tele = ActionContext.getActionContext().getParameterAsString("tele");
		Long serviceId = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		volunteer.setRealName(realName);
		volunteer.setMobile(tele);
		volunteer.setServiceTeam(serviceId);
		volunteer.setIdcard(idCard);
		volunteer.setStar(star);
		
		String orderBy = "evaluate_time DESC,v.USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<VolunteerView> pageInfo = starEvaluateService.getVolunteerStarByEntity(volunteer, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWSTARLIST, "志愿者服务星级信息列表【第"+currPage+"页】查询操作成功",null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWSTARLIST, "志愿者服务星级信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询服务星级列表信息出错,currPage:{},pageSize:{},userName:{},idCard:{},tele:{},serviceTeam:{},star:{}",currPage,pageSize,realName,idCard,tele,serviceId,star,e);
		}
		return data.toJSONString();
	}
	
	
}
