package com.topstar.volunteer.web.controller;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.AreaService;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.SerTeamService;
import com.topstar.volunteer.service.TeamUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.FileUtils;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 服务队管理控制器
 */
@Controller
public class ServiceTeamController {

	private static Logger logger = LoggerFactory.getLogger(ServiceTeamController.class);

	@Autowired
	private SerTeamService serTeamService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private TeamUserService teamUserService;

	/**
	 * 跳转到服务队管理页面
	 * @param request
	 * @param response
	 * @param map
	 */
	@RequestMapping(value = "/serviceteam/list.html")
	public String toServiceTeam(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		return "/serviceteam/serviceteam/list";
	}

	/**
	 * 服务队列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/serTeamList.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String serTeamList(HttpServletRequest request, HttpServletResponse response) {
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		//服务队名称
		String serTeamName = ActionContext.getActionContext().getParameterAsString("serTeamName");
		//所属部门
		String orgName = ActionContext.getActionContext().getParameterAsString("orgName");
		//服务队状态
		int status = ActionContext.getActionContext().getParameterAsInt("status");
		String orderBy = "";
		if (currPage == 0) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		//设置查询条件
		SerTeam serTeam = new SerTeam();
		serTeam.setName(serTeamName);
		serTeam.setOrgName(orgName);
		serTeam.setStatus(status);
		ResultData data = new ResultData();
		try {
			PageInfo<SerTeam> pageInfo = serTeamService.findByEntity(serTeam, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询后台用户出错,pageIndex:{},pageSize:{},serTeamName:{},orgName:{}", currPage, pageSize,
					serTeamName, orgName, e);
			LoggerServer.error(ObjectType.SERTEAM, OperateType.SHOWSERTEAMLIST, "查看服务队列表出错", null, null);
		}
		return data.toJSONString();
	}

	/**
	 * 跳转到添加服务队页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/addServiceTeam.html")
	public String toAddSerTeam(HttpServletRequest request, HttpServletResponse response) {
		return "/serviceteam/serviceteam/addServiceTeam";
	}

	/**
	 * 跳转到查看服务队页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/viewSerTeam.html")
	public String toViewSerTeam(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		//服务队的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		Long key = null;
		try {
			key = Long.valueOf(id);
		} catch (Exception e) {
			logger.error("查看服务队信息的参数id:{}不合法", id, e);
		}
		try {
			SerTeam serTeam = serTeamService.selectByKey(key);
			//所属机构名称
			Org org = orgService.selectByKey(serTeam.getOrgId());
			//区域名称
			Area area = areaService.selectByKey(serTeam.getAreaId());
			map.addAttribute("serTeam", serTeam);
			map.addAttribute("orgName", org.getName());
			map.addAttribute("areaName", area.getName());
			LoggerServer.debug(ObjectType.SERTEAM, OperateType.SHOWSERTEAM,"获取服务队信息成功【参数id值为:"+id+"】", null, null, true);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.SERTEAM, OperateType.SHOWSERTEAM, "获取服务队信息失败【参数id值为:"+id+"】", null, null);
		}
		return "/serviceteam/serviceteam/viewServiceTeam";
	}

	/**
	 * 服务队用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/viewSerTeam.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String viewTrRecord(HttpServletRequest request, HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		//服务队的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		String orderBy="";
		if (currPage == 0) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		ResultData data = new ResultData();
		TeamUser teamUser = new TeamUser();
		User user =new User();
		try {
			Long key = Long.valueOf(id);
			teamUser.setTeamId(key);
			PageInfo<User> pageInfo = teamUserService.findByTeamUser(user, teamUser, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询后台用户出错,pageIndex:{},pageSize:{},id:{}", currPage, pageSize,
					id, e);
			LoggerServer.error(ObjectType.TRAINVOl, OperateType.SHOWTRAINVOLLIST,"查看培训记录志愿者列表失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到编辑服务队页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/editSerTeam.html")
	public String toeditSerTeam(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		//服务队的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		Long key = null;
		try {
			key = Long.valueOf(id);
		} catch (Exception e) {
			logger.error("查看服务队信息的参数id:{}不合法", id, e);
		}
		try {
			SerTeam serTeam = serTeamService.selectByKey(key);
			Org org = orgService.selectByKey(serTeam.getOrgId());
			Area area = areaService.selectByKey(serTeam.getAreaId());
			map.addAttribute("serTeam", serTeam);
			map.addAttribute("orgName", org.getName());
			map.addAttribute("areaName", area.getName());
			LoggerServer.info(ObjectType.SERTEAM, OperateType.SHOWSERTEAM,"获取服务队信息成功【参数id值为:"+id+"】", null, null, true);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.SERTEAM, OperateType.SHOWSERTEAM, "获取服务队信息失败【参数id值为:"+id+"】", null, null);
		}
		return "/serviceteam/serviceteam/editServiceTeam";
	}

	/**
	 * 跳转到服务队用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/serTeam/serTeamUsers.html")
	public String toSerTeamUsers(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		//服务队的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		Long key = null;
		try {
			key = Long.valueOf(id);
		} catch (Exception e) {
			logger.error("查看服务队信息的参数id:{}不合法", id, e);
		}
		try {
			SerTeam serTeam = serTeamService.selectByKey(key);
			Org org = orgService.selectByKey(serTeam.getOrgId());
			Area area = areaService.selectByKey(serTeam.getAreaId());
			map.addAttribute("serTeam", serTeam);
			map.addAttribute("orgName", org.getName());
			map.addAttribute("areaName", area.getName());
			LoggerServer.info(ObjectType.TEAMUSER, OperateType.SHOWTEAMUSERLIST, "获取服务队用户列表信息成功", null, null, true);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.TEAMUSER, OperateType.SHOWTEAMUSERLIST, "获取服务队用户列表信息失败", null,null);
		}
		return "/serviceteam/serviceteam/serTeamUsers";
	}

	/**
	 * 服务队用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/serTeam/serTeamUsers.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String serTeamUsers(HttpServletRequest request, HttpServletResponse response) {
		String teamId = ActionContext.getActionContext().getParameterAsString("teamId");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		String orderBy = "";
		String userName=null;
		String realName=null;
		ResultData data = new ResultData();
		TeamUser teamUser = new TeamUser();
		User user = new User();
		try {
			if (currPage == 0) {
				currPage = 1;
			}
			if (pageSize < 1) {
				pageSize = 10;
			}
			if (selectType < 0 || selectType > 2 || StringUtils.isBlank(teamId)) {
				data.setSuccess(false);
				data.setMessage("查询的条件不合法");
				return data.toJSONString();
			}
			if(selectType==1){
				userName=selectName;
				user.setUserName(userName);
			}else if(selectType==2){
				realName=selectName;
				user.setRealName(realName);
			}
			Long id = Long.parseLong(teamId);
			teamUser.setTeamId(id);
			PageInfo<User> pageInfo = teamUserService.findByTeamUser(user, teamUser, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询后台用户出错,pageIndex:{},pageSize:{},selectName:{},teamId:{}", currPage, pageSize, selectName,
					teamId, e);
			LoggerServer.error(ObjectType.TEAMUSER, OperateType.SHOWTEAMUSERLIST, "查看服务队的用户列表失败", null,null);
		}
		return data.toJSONString();
	}

	/**
	 * 添加服务队
	 * @param request
	 * @param response
	 * @param serTeam
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/addSerTeam.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String addSerTeam(HttpServletRequest request, HttpServletResponse response, SerTeam serTeam) {
		String avatarurl= serTeam.getAvatarUrl();
		String temporary= ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		String storage = ConfigUtils.getConfigContentByName("UPLOADPATH");
		ResultData data = new ResultData();
		BaseUser user = ShiroSessionMgr.getLoginUser();
		serTeam.setCrUser(user.getUserName());
		serTeam.setCrTime(new Date());
		serTeam.setStatus(1);
		//判断组织机构下是否已有服务队
		boolean judgeRes=serTeamService.judgeSerByOrg(serTeam.getOrgId());
		if (judgeRes) {
			return ResultData.fail("组织机构下已有服务队").toJSONString();
		}
		//后台验证
		List<ValidateMessage> errors = ValidatorUtil.validate(serTeam, Groups.Add.class);
		if (null != errors && errors.size() > 0) {
			return ResultData.fail(errors).toJSONString();
		}
		//去除服务队名称中的特殊字符
		serTeam.setName(StringTools.FormatStrFromInput(serTeam.getName()));
		try {
			//判断服务队名称是否已经存在于数据库中
			int exists = serTeamService.existsWithSerTeamName(serTeam.getName(), null);
			if (exists > 0) {
				data.setSuccess(false);
				data.setMessage("服务队名称已存在");
				return data.toJSONString();
			}
			else {
				boolean result = serTeamService.addSerTeam(serTeam);
				if (result) {
					FileUtils.copyFile(temporary+File.separator+avatarurl, storage+File.separator+avatarurl);
					data.setSuccess(true);
					data.setMessage("添加成功");
					LoggerServer.info(ObjectType.SERTEAM, OperateType.CREATESERTEAM, "新建服务队信息成功", serTeam.getId(), serTeam.getName(), true);
					return data.toJSONString();
				} else {
					data.setSuccess(false);
					data.setMessage("添加失败");
					LoggerServer.error(ObjectType.SERTEAM, OperateType.CREATESERTEAM, "新建服务队信息失败", serTeam.getId(), serTeam.getName());
					return data.toJSONString();
				}
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("添加服务队操作出现出错,name:{}", serTeam.getName(), e);
			LoggerServer.error(ObjectType.SERTEAM, OperateType.CREATESERTEAM, "新建服务队信息失败", serTeam.getId(), serTeam.getName());
		}
		return data.toJSONString();
	}

	/**
	 * 批量删除服务队
	 * @param reauest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/SerTeam.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String delSerteam(HttpServletRequest reauest, HttpServletResponse response) {
		ResultData data = new ResultData();
		//要删除的服务队ids
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		if (StringUtils.isBlank(ids)) {
			data.setSuccess(false);
			data.setMessage("删除服务队参数不能为空");
			return data.toJSONString();
		}
		String[] keys = ids.split(",");
		long[] serTeamKeys = new long[keys.length];
		for (int i = 0; i < keys.length; i++) {
			try {
				long serTeamKey = Long.parseLong(keys[i]);
				serTeamKeys[i] = serTeamKey;
			} catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("服务队删除参数不合法,serTeamKeys:{}", ids, e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult = serTeamService.deleteSerTeamKeys(serTeamKeys);
			if (delResult) {
				data.setSuccess(true);
				data.setMessage("服务队删除成功");
				LoggerServer.info(ObjectType.SERTEAM, OperateType.DELSERTEAM, "删除服务队成功", null, null, true);
				return data.toJSONString();
			} else {
				data.setSuccess(false);
				data.setMessage("服务队删除失败");
				LoggerServer.error(ObjectType.SERTEAM, OperateType.DELSERTEAM, "删除服务队失败", null, null);
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("删除用户操作出现出错", e);
			LoggerServer.error(ObjectType.SERTEAM, OperateType.DELSERTEAM, "删除服务队失败", null, null);
		}
		return data.toJSONString();
	}

	/**
	 * 编辑服务队页面
	 * @param request
	 * @param response
	 * @param serTeam
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/editSerTeam.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String editSerTeam(HttpServletRequest request, HttpServletResponse response, SerTeam serTeam) {
		String key = ActionContext.getActionContext().getParameterAsString("id");
		ResultData data = new ResultData();
		Long id = null;
		try {
			id = Long.valueOf(key);
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("修改服务队信息参数id不合法");
			logger.error("修改服务队信息的参数id:{}不合法", key, e);
			return data.toJSONString();
		}
		//设置服务队Id
		serTeam.setId(id);
		SerTeam preSerTeam=serTeamService.selectByKey(id);
		//后台验证
		List<ValidateMessage> errors = ValidatorUtil.validate(serTeam, Groups.Update.class);
		if (null != errors && errors.size() > 0) {
			return ResultData.fail(errors).toJSONString();
		}
		serTeam.setName(StringTools.FormatStrFromInput(serTeam.getName()));
		try {
			//判断修改的服务队名称是否存在
			int exists = serTeamService.existsWithSerTeamName(serTeam.getName(), key);
			if (exists > 0) {
				data.setSuccess(false);
				data.setMessage("服务队名称已存在");
				return data.toJSONString();
			} else {
				boolean judge =	serTeamService.judgeAuthority(serTeam);
				if (judge) {
					boolean result = serTeamService.updateSerTeam(serTeam);
					if (result) {
						data.setSuccess(true);
						data.setMessage("修改成功");
						LoggerServer.info(ObjectType.SERTEAM, OperateType.EDITSERTEAM, "修改服务队信息成功【修改前："+preSerTeam.toString()+",修改后"+serTeam.toString()+"】", serTeam.getId(), serTeam.getName(), true);
						return data.toJSONString();
						} else {
						data.setSuccess(false);
						data.setMessage("修改失败");
						LoggerServer.error(ObjectType.SERTEAM, OperateType.EDITSERTEAM, "修改服务队信息失败",serTeam.getId(), serTeam.getName());
						return data.toJSONString();
						}
				} else {
					data.setSuccess(false);
					data.setMessage("没有修改服务队权限");
					LoggerServer.error(ObjectType.SERTEAM, OperateType.EDITSERTEAM, "没有修改服务队权限",serTeam.getId(), serTeam.getName());
					return data.toJSONString();
				}
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("修改服务队操作出现出错,name:{}", serTeam.getName(), e);
			LoggerServer.error(ObjectType.SERTEAM, OperateType.EDITSERTEAM, "修改服务队信息失败",serTeam.getId(), serTeam.getName());
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加用户页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/serviceteam/addTeamUser.html")
	public String toAddTeamUser(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = ActionContext.getActionContext().getParameterAsString("tId");
		model.addAttribute("teamId", id);
		return "/serviceteam/serviceteam/addTeamUser";
	}
	
	/**
	 * 显示可以被添加到服务队的用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/serTeam/showUsers.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String showUsers(HttpServletRequest request,HttpServletResponse response){
		String teamId = ActionContext.getActionContext().getParameterAsString("teamId");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		ResultData data=new ResultData();
		//判断查询的条件
		if(selectType<0 || selectType >2){
			data.setSuccess(false);
			data.setMessage("查询的条件不合法");
			return data.toJSONString();
		}
		Long tId=null;
		String userName=null;
		String realName=null;
		if(!StringUtils.isBlank(teamId)){
			try{
				tId=Long.parseLong(teamId);
			}catch (Exception e) {
				logger.error("获取需要添加服务队的用户列表的参数teamId:{}不合法",teamId,e);
				data.setSuccess(false);
				data.setMessage("查询的条件不合法");
				return data.toJSONString();
			}
		}
		if(currPage<0){
			currPage=1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		User user =new User();
		if(selectType==1){
			userName=selectName;
			user.setUserName(userName);
		}else if(selectType==2){
			realName=selectName;
			user.setRealName(realName);
		}
		try{
			PageInfo<User> users=teamUserService.getUserTeams(user, tId, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", users);
			return data.toJSONString();
		}catch (Exception e) {
			logger.error("查看可以被添加的用户列表出现错误",e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加用户到服务队
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/serviceteam/addTeamUser.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addTeamUser(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		//要被添加到该服务队的用户ids
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		Long teamId= ActionContext.getActionContext().getParameterAsLong("teamId");
		if(StringUtils.isBlank(ids)){
			data.setSuccess(false);
			data.setMessage("添加的用户参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		List<Long> userKeys=new ArrayList<Long>();
		for (int i=0;i<keys.length;i++) {
			try{
				long userKey=Long.parseLong(keys[i]);
				userKeys.add(userKey);
			}catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("添加的用户参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean addResult=teamUserService.addUsersWithTeamId(teamId, userKeys);
			if(addResult){
				data.setSuccess(true);
				data.setMessage("添加成功");
				LoggerServer.info(ObjectType.TEAMUSER, OperateType.CREATETEAMUSER, "添加用户到服务队成功", null, null, true);
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("添加失败");
				LoggerServer.error(ObjectType.TEAMUSER, OperateType.CREATETEAMUSER, "添加用户到服务队失败", null, null);
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("将用户添加到服务队中的操作出现出错",e);
			LoggerServer.error(ObjectType.TEAMUSER, OperateType.CREATETEAMUSER, "将用户添加到服务队中的操作出现出错", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量从服务队中删除用户
	 * @param reauest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/serviceteam/delTeamUser.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String delTeamUser(HttpServletRequest reauest, HttpServletResponse response) {
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		Long teamId= ActionContext.getActionContext().getParameterAsLong("teamId");
		if (StringUtils.isBlank(ids)) {
			data.setSuccess(false);
			data.setMessage("删除用户参数不能为空");
			return data.toJSONString();
		}
		String[] keys = ids.split(",");
		List<Long> userKeys=new ArrayList<Long>();
		for (int i=0;i<keys.length;i++) {
			try{
				long userKey=Long.parseLong(keys[i]);
				userKeys.add(userKey);
			}catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("添加的用户参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult = teamUserService.delTeamUser(userKeys, teamId);
			if (delResult) {
				data.setSuccess(true);
				data.setMessage("用户删除成功");
				LoggerServer.info(ObjectType.TEAMUSER, OperateType.DROPTEAMUSER, "删除服务队中用户成功", null, null, true);
				return data.toJSONString();
			} else {
				data.setSuccess(false);
				data.setMessage("用户删除失败");
				LoggerServer.error(ObjectType.TEAMUSER, OperateType.DROPTEAMUSER, "删除服务队中用户失败", null, null);
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			LoggerServer.error(ObjectType.TEAMUSER, OperateType.DROPTEAMUSER, "删除服务队中用户失败", null, null);
			logger.error("删除用户操作出现出错", e);
		}
		return data.toJSONString();
	}
}
