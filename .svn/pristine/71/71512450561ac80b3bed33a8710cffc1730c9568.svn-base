package com.topstar.volunteer.web.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TurnTeam;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.TurnTeamView;
import com.topstar.volunteer.service.TeamUserService;
import com.topstar.volunteer.service.TurnTeamService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;


@Controller
public class TurnTeamController {
	
	private static Logger logger = LoggerFactory.getLogger(TurnTeamController.class);
	
	@Autowired
	private TurnTeamService turnTeamService;
	
	@Autowired
	private TeamUserService teamUserService;
	
	@Autowired
	private VolunteerService volunteerService;
	
	/**
	 * 跳转到志愿者转队申请信息列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/turnTeam/list.html")
	public String toturnTeam(HttpServletRequest request){
		
		return "/volunteer/turnTeam/list";
	}
	
	/**
	 * 跳转到志愿者转队申请处理页面
	 * @param request
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value="/turnTeam/deal.html")
	public String toDealTurnTeam(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("id");
		Long turnId=null;
		try{
			turnId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("跳转到志愿者转队申请处理页面信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DEALTURNTEAM, "跳转到志愿者转队申请处理页面参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			TurnTeamView turnTeamView=turnTeamService.getTurnTeamApplyByTurnId(turnId);
			map.addAttribute("turnTeam", turnTeamView);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DEALTURNTEAM, "跳转到志愿者转队申请处理页面信息查询失败【"+e.getMessage()+"】",turnId,null);
		}
		return "/volunteer/turnTeam/deal";
	}	
	
	/**
	 * 查看志愿者转队申请处理结果页面信息
	 * @param request
	 * @param map
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value = "/turnTeam/view.html")
	public String toviewTurnTeam(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("id");
		Long turnId=null;
		try{
			turnId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("查看指定志愿者转队申请信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWDEALTURNTEAM, "跳转到查看志愿者转队申请处理结果页面参数信息【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			TurnTeamView turnTeamView=turnTeamService.getTurnTeamApplyByTurnId(turnId);
			map.addAttribute("turnTeam", turnTeamView);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWDEALTURNTEAM, "跳转到查看志愿者转队申请处理结果页面信息查询失败【"+e.getMessage()+"】",turnId,null);
		}
		return "/volunteer/turnTeam/view";
	}
	
	
	/**
	 * 志愿者转队申请信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/turnTeam/volunteerList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerList(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String userName = ActionContext.getActionContext().getParameterAsString("userName");
		String idCard = ActionContext.getActionContext().getParameterAsString("idCard");
		Integer status = ActionContext.getActionContext().getParameterAsInt("status");
		String tele = ActionContext.getActionContext().getParameterAsString("tele");
		Long serviceTeam = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		BaseUser user = ShiroSessionMgr.getLoginUser();
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		SerTeam targetTeam = teamUserService.getSerTeamByUserId(user.getId());
		volunteer.setUserName(userName);
		volunteer.setMobile(tele);
		volunteer.setServiceTeam(serviceTeam);
		volunteer.setIdcard(idCard);
		volunteer.setStatus(status);
		if (targetTeam != null) {
			volunteer.setTargetId(targetTeam.getId());
		}
		//判断该用户是否是超级管理员
		if (user.isAdmin()) {
			volunteer.setTargetId(-1L);
		}
		String orderBy = "apply_time DESC,USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<TurnTeamView> pageInfo = turnTeamService.getTurnTeamApplysByEntity(volunteer, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWTURNTEAMLIST, "志愿者转队申请信息列表【第"+currPage+"页】查询操作成功,查询条件:"+volunteer,null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWTURNTEAMLIST, "志愿者转队申请信息列表【第"+currPage+"页】查询操作失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者转队申请信息出错,currPage:{},pageSize:{},userName:{},idCard:{},tele:{},serviceTeam:{}",currPage,pageSize,userName,idCard,tele,serviceTeam,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 处理指定志愿者的转队申请
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/turnTeam/deal.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerReviewList(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String content = ActionContext.getActionContext().getParameterAsString("content");
		String status = ActionContext.getActionContext().getParameterAsString("status");
		Long turnId = ActionContext.getActionContext().getParameterAsLong("id");
		if(StringUtils.isNotBlank(status) && !"1".equals(status)){
			data.setSuccess(false);
			data.setMessage("非法操作");
		}
		if(turnId.equals(-1l)){
			data.setSuccess(false);
			data.setMessage("非法操作");
		}
		try {
			content=StringTools.FormatStrFromInput(content);
			TurnTeam existedTurnTeam=turnTeamService.selectByKey(turnId);
			int updateTurnTeam=0;
			if(existedTurnTeam!=null){
				BaseUser user = ShiroSessionMgr.getLoginUser();
				if(user!=null){
					SerTeam serTeam=teamUserService.getSerTeamByUserId(user.getId());
					//处理退出服务队操作
					if(null==existedTurnTeam.getSourceResult()){
						if (!existedTurnTeam.getSourceTeamId().equals(serTeam.getId())) {
							//判断该用户是否是超级管理员
							if (!user.isAdmin()) {
								data.setSuccess(false);
								data.setMessage("您没有权限进行此操作！");
								return data.toJSONString();
							}
						}
						existedTurnTeam.setSuserId(user.getId());
						existedTurnTeam.setSdealUser(user.getUserName());
						existedTurnTeam.setSdealTime(new Date());
						if(serTeam!=null){
							existedTurnTeam.setSdealTeam(serTeam.getName());
						}
						existedTurnTeam.setSourceResult(null==status?2:1);
						existedTurnTeam.setSourceOpinion(content);
						List<ValidateMessage> errors = ValidatorUtil.validate(existedTurnTeam, Groups.Update.class);
						if (null != errors && errors.size() > 0) {
							return ResultData.fail(errors).toJSONString();
						}
						updateTurnTeam=turnTeamService.updateNotNull(existedTurnTeam);
					}else{
						if (!existedTurnTeam.getTargetId().equals(serTeam.getId())) {
							//判断该用户是否是超级管理员
							if (!user.isAdmin()) {
								data.setSuccess(false);
								data.setMessage("您没有权限进行此操作！");
								return data.toJSONString();
							}
						}
						//处理加入服务队操作
						existedTurnTeam.setTuserId(user.getId());
						existedTurnTeam.setTdealUser(user.getUserName());
						existedTurnTeam.setTdealTime(new Date());
						if(serTeam!=null){
							existedTurnTeam.setTdealTeam(serTeam.getName());
						}
						existedTurnTeam.setTargetResult(null==status?2:1);
						existedTurnTeam.setTargetOpinion(content);
						List<ValidateMessage> errors = ValidatorUtil.validate(existedTurnTeam, Groups.Update.class);
						if (null != errors && errors.size() > 0) {
							return ResultData.fail(errors).toJSONString();
						}
						updateTurnTeam=turnTeamService.updateNotNull(existedTurnTeam);
					}
				}
				if(updateTurnTeam>0){
					//修改记录成功后修改志愿者所属的服务队
						if (existedTurnTeam.getSourceResult() == null || existedTurnTeam.getTargetResult() == null) {
							data.setSuccess(true);
							data.setMessage("操作成功");
						}else{
							if (existedTurnTeam.getSourceResult() == 1 && existedTurnTeam.getTargetResult() == 1) {
								int updateVolTeam = 0;
								Volunteer vol = new Volunteer();
								vol.setId(existedTurnTeam.getVolunteerId());
								vol.setServiceTeam(existedTurnTeam.getTargetId());
								updateVolTeam = volunteerService.updateNotNull(vol);
								if (updateVolTeam > 0) {
									data.setSuccess(true);
									data.setMessage("操作成功");
								}else{
									data.setSuccess(false);
									data.setMessage("操作失败");
								}
							}else{
								data.setSuccess(true);
								data.setMessage("操作成功");
							}
						}
						
//					data.setSuccess(true);
//					data.setMessage("操作成功");
				}else{
					data.setSuccess(false);
					data.setMessage("操作失败");
				}
			}else{
				data.setSuccess(false);
				data.setMessage("非法操作");
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	
}
