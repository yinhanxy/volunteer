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
import com.topstar.volunteer.entity.Certification;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerBlack;
import com.topstar.volunteer.exception.TPSClientException;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.VolunteerBlackService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 志愿者黑名单控制器
 * @author TRS
 *
 */
@Controller
public class BlackListController { 
	
	private static Logger logger = LoggerFactory.getLogger(BlackListController.class);
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private VolunteerBlackService volunteerBlackService;
	
	//跳转到志愿者黑名单列表页面
	@RequestMapping(value="/blackList/list.html")
	public String toBlackList(HttpServletRequest request){
		return "/volunteer/blackList/list";
	}
	
	//添加黑名单
	@RequestMapping(value = "/blackList/addBlack.html")
	public String toAddBlack(HttpServletRequest request,ModelMap map){
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser!=null){
			map.addAttribute("userId", crtUser.getId());
		}
		return "/volunteer/blackList/addBlack";
	}
		
	/*
	 * 跳转到证书授予的志愿者的选择页面
	 */
	@RequestMapping(value = "/blackList/selectVolunteer.html")
	public String toSelectVolunteer(HttpServletRequest request,ModelMap map){
		return "/volunteer/blackList/selectVolunteer";
	}
	
	/**
	 * 跳转到志愿者黑名单的编辑页面
	 * @param request
	 * @param map
	 * @return
	 * @throws TPSClientException 
	 */
	@RequestMapping(value = "/blackList/editBlack.html")
	public String toEditBlack(HttpServletRequest request,ModelMap map) throws TPSClientException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		Long volunteerId=null;
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser!=null){
			map.addAttribute("userId", crtUser.getId());
		}
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITBLACK, "跳转到志愿者黑名单的编辑页面参数信息【"+id+"】不合法",null,null);
			throw new TPSClientException("不合法");
		}
		try{
			VolunteerView volunteerView=volunteerBlackService.getVolunteerBlackByVolunteerId(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITBLACK, "成功跳转到志愿者黑名单的编辑页面的黑名单信息", volunteerId, volunteerView.getUserName(),true);
			map.addAttribute("volunteerView", volunteerView);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITBLACK, "跳转到志愿者黑名单的编辑页面的黑名单信息查询失败【"+e.getMessage()+"】",volunteerId,null);
		}
		return "/volunteer/blackList/editBlack";
	}
	
	/**
	 * 志愿者黑名单的编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/blackList/editBlack.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String editBlack(HttpServletRequest request,HttpServletResponse response,VolunteerBlack volunteerBlack){
		ResultData data = new ResultData();
		
		volunteerBlack.setOpertTime(new Timestamp(new Date().getTime()));
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		volunteerBlack.setOpertUser(crtUser.getUserName());
		List<ValidateMessage> errors =ValidatorUtil.validate(volunteerBlack, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		try {
			int updateVolunteerBlack=volunteerBlackService.updateNotNull(volunteerBlack);
			if(updateVolunteerBlack>0){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITBLACK, "编辑志愿者黑名单信息保存操作成功", volunteerBlack.getVolunteerId(), null,true);
				data.setSuccess(true);
				data.setMessage("保存成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITBLACK, "编辑志愿者黑名单信息保存操作失败", volunteerBlack.getVolunteerId(), null,false);
				data.setSuccess(false);
				data.setMessage("保存失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITBLACK, "编辑志愿者黑名单信息更新操作失败【"+e.getMessage()+"】", volunteerBlack.getVolunteerId(), null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加志愿者黑名单记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/blackList/addBlack.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addBlack(HttpServletRequest request,HttpServletResponse response,VolunteerBlack volunteerBlack){
		ResultData data = new ResultData();
		
		volunteerBlack.setOpertTime(new Timestamp(new Date().getTime()));
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		volunteerBlack.setOpertUser(crtUser.getUserName());
		
		List<ValidateMessage> errors =ValidatorUtil.validate(volunteerBlack, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		try {
			int addVolunteerBlack=volunteerBlackService.insert(volunteerBlack);
			if(addVolunteerBlack>0){
				Volunteer volunteer=new Volunteer();
				volunteer.setId(volunteerBlack.getVolunteerId());
				volunteer.setStatus(Volunteer.StatusType.black.getValue());
				volunteerService.updateNotNull(volunteer);
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CREATEBLACK, "志愿者黑名单信息新增操作成功", volunteerBlack.getVolunteerId(), null,true);
				data.setSuccess(true);
				data.setMessage("志愿者黑名单添加成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CREATEBLACK, "志愿者黑名单信息新增操作失败", volunteerBlack.getVolunteerId(), null,false);
				data.setSuccess(false);
				data.setMessage("志愿者黑名单添加失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CREATEBLACK, "志愿者黑名单信息新增操作失败【"+e.getMessage()+"】", volunteerBlack.getVolunteerId(), null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 查看志愿者黑名单信息
	 * @param request
	 * @param map
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value = "/blackList/viewBlack.html")
	public String toViewBlack(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String serName= ActionContext.getActionContext().getParameterAsString("serName");
		Long volunteerId=null;
		try {
			volunteerId=Long.valueOf(id);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWBLACK, "跳转到志愿者黑名单的查看页面参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			VolunteerView volunteerView=volunteerBlackService.getVolunteerBlackByVolunteerId(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWBLACK, "成功跳转到志愿者黑名单的查看页面",null,null,true);
			map.addAttribute("volunteerView", volunteerView);
			map.addAttribute("serName", serName);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWBLACK, "跳转到志愿者黑名单的查看页面失败【"+e.getMessage()+"】",null,null);
		}
		
		return "/volunteer/blackList/viewBlack";
	}
	
	/**
	 * 志愿者黑名单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/blackList/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String blackList(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String userName = ActionContext.getActionContext().getParameterAsString("userName");
		String idCard = ActionContext.getActionContext().getParameterAsString("idCard");
		String tele = ActionContext.getActionContext().getParameterAsString("tele");
		Long serviceTeam = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		volunteer.setUserName(userName);
		volunteer.setMobile(tele);
		volunteer.setServiceTeam(serviceTeam);
		volunteer.setIdcard(idCard);
		
		String orderBy = "opert_time DESC,USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<VolunteerView> pageInfo = volunteerBlackService.getVolunteerBlackByEntity(volunteer, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWBLACKLIST, "志愿者黑名单信息列表【第"+currPage+"页】查询操作成功,查询条件:"+volunteer,null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWBLACKLIST, "志愿者黑名单信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 删除黑名单信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/blackList/delblackList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delblackList(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("blackIds");
		String volunteerIds = ActionContext.getActionContext().getParameterAsString("vIds");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELBLACKLIST, "批量删除志愿者黑名单参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "移除黑名单信息的操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] blackKeys=new Long[keys.length];
		String[] volKeys=volunteerIds.split(",");
		Long[] volunteerKeys=new Long[volKeys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long blackKey=Long.parseLong(keys[i]);
				blackKeys[i]=blackKey;
				
				long volunteerKey=Long.parseLong(volKeys[i]);
				volunteerKeys[i]=volunteerKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELBLACKLIST, "批量删除志愿者黑名单参数信息ids:【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				return data.toJSONString();
			}
		}
		try {
			boolean delvolunteerBlacks=volunteerBlackService.deleleVolunteerBlacks(blackKeys,volunteerKeys);
			if(delvolunteerBlacks){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.DELBLACKLIST, "成功批量删除志愿者黑名单信息",null,null,true);
				data.setSuccess(true);
				data.setMessage("黑名单信息已删除");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.DELBLACKLIST, "批量删除志愿者黑名单信息失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("黑名单信息删除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELBLACKLIST, "批量删除志愿者黑名单信息发生错误:【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	//获取尚无黑名单信息记录的志愿者信息列表
	@RequestMapping(value="/blackList/selectVolunteer.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String selectVolunteer(HttpServletRequest request,HttpServletResponse response){
		/*Volunteer volunteer= new Volunteer();*/
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		/*String selectType = ActionContext.getActionContext().getParameterAsString("selectType");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		String serviceTeam = ActionContext.getActionContext().getParameterAsString("serviceTeam");*/
		String userName=ActionContext.getActionContext().getParameterAsString("userName");
		if(currPage == 0){
			currPage = 1;
		}
		int pageSize=0;
		if(pageSize< 1){
			pageSize = 10;
		}
		/*if("2".equals(selectType)){
			volunteer.setIdcard(selectName);
		}else{
			volunteer.setUserName(selectName);
		}
		volunteer.setServiceTeam(serviceTeam);*/
		
		String orderBy = "REG_TIME DESC,USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<VolunteerView> pageInfo = volunteerBlackService.getVolunteersWithoutBlack(userName, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.VOLUNTEERSWITHOUTBLACK, "未有黑名单记录的志愿者信息列表【第"+currPage+"页】查询操作成功",null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.VOLUNTEERSWITHOUTBLACK, "未有黑名单记录的志愿者信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
}
