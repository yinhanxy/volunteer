package com.topstar.volunteer.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.RetreatTeamDeal;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.RetreatTeamView;
import com.topstar.volunteer.service.RetreatTeamService;
import com.topstar.volunteer.service.TeamUserService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;


/**
 * 志愿者退队控制器
 * @author TRS
 *
 */
@Controller
public class RetreatTeamController {
	
	private static Logger logger = LoggerFactory.getLogger(RetreatTeamController.class);
	
	@Autowired
	private RetreatTeamService retreatTeamService;
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private TeamUserService teamUserService;
	
	/**
	 * 跳转到志愿者退队申请信息列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/retreatTeam/list.html")
	public String toturnTeam(HttpServletRequest request){
		return "/volunteer/retreatTeam/list";
	}
	
	/**
	 * 跳转到志愿者退队申请处理页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/retreatTeam/deal.html")
	public String toDealTurnTeam(HttpServletRequest request,ModelMap map){
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		Long volunteerId=null;
		RetreatTeamView retreatTeam=null;
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("查看指定志愿者退队申请信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWRETREATTEAM, "获取志愿者信息失败【参数id值为:"+id+"】", null, null);
		}
		try {
			retreatTeam=retreatTeamService.getRetreatTeamByVolunteerId(volunteerId);
			map.addAttribute("retreatTeam", retreatTeam);
		} catch (Exception e) {
			logger.error("查询的指定志愿者退队申请信息不合法:{}",retreatTeam,e);
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWRETREATTEAM, "获取志愿者退队信息失败", null, null);
		}
		return "/volunteer/retreatTeam/deal";
	}	
	
	/**
	 * 查看志愿者退队申请处理结果页面信息
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/retreatTeam/view.html")
	public String toviewTurnTeam(HttpServletRequest request,ModelMap map){
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		Long volunteerId=null;
		RetreatTeamView retreatTeam=null;
		try{
			volunteerId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("查看指定志愿者退队申请信息参数id不合法:{}",id,e);
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWRETREATTEAM, "获取志愿者信息失败【参数id值为:"+id+"】", null, null);
		}
		try {
			retreatTeam=retreatTeamService.getRetreatTeamByVolunteerId(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER, OperateType.SHOWRETREATTEAM, "成功获取志愿者退队信息", null, null,true);
			map.addAttribute("retreatTeam", retreatTeam);
		} catch (Exception e) {
			logger.error("查询的指定志愿者退队申请信息不合法:{}",retreatTeam,e);
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWRETREATTEAM, "获取志愿者退队信息失败", null, null);
		}
		return "/volunteer/retreatTeam/view";
	}
	
	
	/**
	 * 志愿者退队申请信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/retreatTeam/volunteerList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerList(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String userName = ActionContext.getActionContext().getParameterAsString("userName");
		String idCard = ActionContext.getActionContext().getParameterAsString("idCard");
		String status = ActionContext.getActionContext().getParameterAsString("status");
		String tele = ActionContext.getActionContext().getParameterAsString("tele");
		String teamName = ActionContext.getActionContext().getParameterAsString("teamName");
		BaseUser user = ShiroSessionMgr.getLoginUser();
		SerTeam userTeam = teamUserService.getSerTeamByUserId(user.getId());
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		volunteer.setUserName(userName);
		volunteer.setMobile(tele);
		volunteer.setTeamName(teamName);
		volunteer.setIdcard(idCard);
		volunteer.setDealReasult(status);
		if (userTeam != null) {
			volunteer.setServiceTeam(userTeam.getId());
		}
		if (user.isAdmin()) {
			volunteer.setServiceTeam(-1l);
		} 
		String orderBy = "apply_time DESC,USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<RetreatTeamView> pageInfo = retreatTeamService.getRetreatTeamApplysByEntity(volunteer, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者退队申请信息出错,currPage:{},pageSize:{},userName:{},idCard:{},tele:{},serviceTeam:{}",currPage,pageSize,userName,idCard,tele,teamName,e);
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWRETREATTEAMLIST, "查看志愿者退队申请信息列表失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 处理指定志愿者的退队申请
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/retreatTeam/deal.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerReviewList(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String dealOpinion = ActionContext.getActionContext().getParameterAsString("dealOpinion");
		String dealStatus = ActionContext.getActionContext().getParameterAsString("dealStatus");
		String id = ActionContext.getActionContext().getParameterAsString("id");
		String volunteerId = ActionContext.getActionContext().getParameterAsString("volunteerId");
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Long dealId=null;
		Long volId=null;
		try{
			dealId=Long.valueOf(id);
		}catch (Exception e) {
			logger.error("查看指定志愿者退队申请信息参数id不合法:{}",id,e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			return data.toJSONString();
		}
		try{
			volId=Long.valueOf(volunteerId);
		}catch (Exception e) {
			logger.error("查看指定志愿者退队申请信息参数volunteerId不合法:{}",volunteerId,e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			return data.toJSONString();
		}
		
		RetreatTeamDeal prertd= retreatTeamService.selectByKey(dealId);
		RetreatTeamDeal rtd=new RetreatTeamDeal();
		rtd.setId(dealId);
		rtd.setDealOpinion(dealOpinion);
		rtd.setDealResult(dealStatus);
		rtd.setDealUser(user.getUserName());
		rtd.setDealTime(new Date());
		rtd.setVolId(volId);
		
		try {
			int dealRes= retreatTeamService.editVolAndRetreatResult(rtd);
			if (dealRes>0) {
				data.setSuccess(true);
				data.setMessage("处理成功！");
				LoggerServer.info(ObjectType.VOLUNTEER, OperateType.EDITRETREATTEAM, "修改退队队信息成功【修改前："+prertd.toString()+",修改后"+rtd.toString()+"】", rtd.getId(), null, true);
				return data.toJSONString();
			} else {
				data.setSuccess(false);
				data.setMessage("处理失败！");
				LoggerServer.error(ObjectType.VOLUNTEER, OperateType.EDITRETREATTEAM, "修改退队队信息失败", rtd.getId(), rtd.getDealResult());
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.EDITRETREATTEAM, "修改退队队信息失败", rtd.getId(), e.getMessage());
		}
		return data.toJSONString();
	}
	
	
}
