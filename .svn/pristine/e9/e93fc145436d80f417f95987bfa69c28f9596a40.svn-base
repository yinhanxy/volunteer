package com.topstar.volunteer.web.controller;

import java.sql.Timestamp;
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
import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.entity.ActivityClass;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgSystem;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.AreaView;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.OrgView;
import com.topstar.volunteer.service.AreaService;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.PropertiesUtil;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class OrgController {
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private ConfigCache configCache;
	
	private static Logger logger=LoggerFactory.getLogger(OrgController.class);
	
	@RequestMapping("/org/orgList.html")
	public String toOrgList(HttpServletRequest request,HttpServletResponse response){
		return "/org/list";
	}
	
	//查看机构列表
	@RequestMapping(value="/org/orgList.do",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String orgList(HttpServletRequest request,HttpServletResponse response){
		Long currAreaId=ActionContext.getActionContext().getParameterAsLong("id");
		ResultData data=new ResultData();
		try{
			List<OrgView> orgs=orgService.getOrgsListByAreaId(currAreaId);
			data.setSuccess(true);
			data.put("list", orgs);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.SHOWGROUPLIST, "组织机构信息列表查询操作失败【"+e.getMessage()+"】",null,null);
			logger.error("获取机构信息列表出现内部错误",e);
			data.setSuccess(false);
			data.setMessage("获取机构数据出现内部错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加机构页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/org/addOrg.html")
	public String toAddOrg(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long id=ActionContext.getActionContext().getParameterAsLong("id");
		if(id!=null && id>0){
			Org org=orgService.selectByKey(id);
			if(org!=null){
				model.addAttribute("org", org);
			}
		}
		return "/org/addOrg";
	}
	
	
	//查看机构信息
	@RequestMapping(value="/org/viewOrg.html")
	public String viewOrg(HttpServletRequest reauest,HttpServletResponse response,ModelMap model){
		Long orgId=ActionContext.getActionContext().getParameterAsLong("id");
		if(orgId!=null && orgId>0){
			Org org=orgService.selectByKey(orgId);
			if(org!=null){
				Long parentId=org.getParentId();
				Long areaId=org.getAreaId();
				Org parentOrg=null;
				Area area=null;
				if(org!=null && parentId > 0){
					parentOrg=orgService.selectByKey(parentId);
				}
				if(areaId!=null && areaId>0){
					area=areaService.selectByKey(areaId);
				}
				OrgView orgView=new OrgView(org);
				if(parentOrg!=null){
					orgView.setParentName(parentOrg.getName());
				}
				if(area!=null){
					orgView.setAreaName(area.getName());
				}
				model.addAttribute("org", orgView);
			}
		}
		return "/org/viewOrg";
	}
		
	//跳转到修改组织机构页面
	@RequestMapping(value="/org/editOrg.html")
	public String toEditOrg(HttpServletRequest reauest,HttpServletResponse response,ModelMap model){
		Long orgId=ActionContext.getActionContext().getParameterAsLong("id");
		if(orgId!=null && orgId>0){
			Org org=orgService.selectByKey(orgId);
			if(org!=null){
				Long parentId=org.getParentId();
				Long areaId=org.getAreaId();
				Org parentOrg=null;
				Area area=null;
				if(org!=null && parentId > 0){
					parentOrg=orgService.selectByKey(parentId);
				}
				if(areaId!=null && areaId>0){
					area=areaService.selectByKey(areaId);
				}
				OrgView orgView=new OrgView(org);
				if(parentOrg!=null){
					orgView.setParentName(parentOrg.getName());
				}
				if(area!=null){
					orgView.setAreaName(area.getName());
				}
				model.addAttribute("org", orgView);
			}
		}	
		return "/org/editOrg";
	}
	
	/**
	 * 跳转到选择上级区域页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/org/selectOrg.html")
	public String toSelectOrg(HttpServletRequest request,HttpServletResponse response){
		return "/org/selectOrg";
	}
	
	/**
	 * 获取指定的上级的下级机构列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/org/loadOrg.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String loadArea(HttpServletRequest request,HttpServletResponse response){
		Long currAreaId=ActionContext.getActionContext().getParameterAsLong("id");
		ResultData data=new ResultData();
		try{
			List<OrgView> orgViews=orgService.getOrgsListByAreaId(currAreaId);
			data.setSuccess(true);
			data.put("orgViews", orgViews);
			return data.toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.SHOWSUBGROUPLIST, "指定机构的下级机构列表信息列表查询操作失败【"+e.getMessage()+"】",null,null);
			logger.error("获取指定机构的下级机构信息列表失败",e);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加机构
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/org/addOrg.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String addOrg(HttpServletRequest request,HttpServletResponse response,Org org){
		ResultData data=new ResultData();
		try{
			org.setCrtTime(new Timestamp(new Date().getTime()));
			List<ValidateMessage> errors =ValidatorUtil.validate(org, Groups.Add.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			
			if(orgService.existsWithOrgName(org.getName(), null)){
				data.setSuccess(false);
				data.setMessage("机构已存在");
				return data.toJSONString();
			}
			
			org.setSummary(StringTools.FormatStrFromInput(org.getSummary()));
			boolean addOrg=orgService.addOrg(org);
			if(addOrg){
				LoggerServer.info(ObjectType.GROUP,OperateType.CREATEGROUP, "添加机构操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("机构添加成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.GROUP,OperateType.CREATEGROUP, "添加机构操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("机构添加失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.CREATEGROUP, "添加机构操作失败【"+e.getMessage()+"】",null,null);
			logger.error("添加机构信息失败",e);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}
	
	//保存修改机构信息
	@RequestMapping(value="/org/saveOrg.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String updateOrg (HttpServletRequest reauest,HttpServletResponse response,Org org){
		ResultData data=new ResultData();
		try{
			
			List<ValidateMessage> errors =ValidatorUtil.validate(org, Groups.Update.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			
			if(orgService.existsWithOrgName(org.getName(), org.getId())){
				data.setSuccess(false);
				data.setMessage("机构已存在");
				return data.toJSONString();
			}
			
			org.setSummary(StringTools.FormatStrFromInput(org.getSummary().trim()));
			org.setAddress(org.getAddress().trim());
			org.setContact(org.getContact().trim());
			int updateOrg=orgService.saveOrg(org);
			if(updateOrg>0){
				LoggerServer.info(ObjectType.GROUP,OperateType.EDITGROUP, "保存机构信息操作成功",null,null,true);
				data.setSuccess(true);
				data.setMessage("机构信息保存成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.GROUP,OperateType.EDITGROUP, "保存机构信息操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("机构信息保存失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.EDITGROUP, "保存机构信息操作失败【"+e.getMessage()+"】",null,null);
			logger.error("机构信息更新发生错误",e);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}
	
	//删除机构
	@RequestMapping(value="/org/delOrg.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delOrg(HttpServletRequest reauest,HttpServletResponse response){
		ResultData data=new ResultData();
		Long orgId=ActionContext.getActionContext().getParameterAsLong("id");
		if(orgId>0){
			try{
				boolean delOrg=orgService.delOrgAndSubOrg(orgId);
				if(delOrg){
					LoggerServer.info(ObjectType.GROUP,OperateType.DELGROUP, "删除组织机构操作成功",null,null,true);
					data.setSuccess(true);
					data.setMessage("删除成功");
					return data.toJSONString();
				}else{
					LoggerServer.info(ObjectType.GROUP,OperateType.DELGROUP, "删除组织机构操作失败",null,null,false);
					data.setSuccess(false);
					data.setMessage("删除失败");
					return data.toJSONString();
				}
			}catch (Exception e) {
				LoggerServer.error(ObjectType.GROUP,OperateType.DELGROUP, "删除组织机构操作失败【"+e.getMessage()+"】",null,null);
				logger.error("删除机构信息发生错误",e);
				data.setSuccess(false);
				data.setMessage("发生内部错误");
			}	
		}	
		return data.toJSONString();
	}
	
	/**
	 * 对应区域下的用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/org/orgUsers.html")
	public String toOrgUser(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long id = ActionContext.getActionContext().getParameterAsLong("oId");
		if(id>0){
			Org org=orgService.selectByKey(id);
			OrgView orgView=null;
			if(org!=null){
				orgView=new OrgView(org);
			}
			model.addAttribute("org", orgView);
			model.addAttribute("orgId", id);
		}
		return "/org/orgUsers";
	}
	
	//获取对应机构下的用户信息
	@RequestMapping(value="/org/orgUsers.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String viewOrgUsers(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long orgId=ActionContext.getActionContext().getParameterAsLong("orgId");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize=PropertiesUtil.getInstance().getPropertyInt("orgUserPageSize", 8);
		ResultData data=new ResultData();
		try{
			if(selectType<0 || selectType >2 ){
				data.setSuccess(false);
				data.setMessage("查询的条件不合法");
				return data.toJSONString();
			}
			PageInfo<User> users=orgService.getUsersByGivenOrgId(orgId, selectType,selectName, pageSize, currPage);
			data.setSuccess(true);
			data.put("page", users);
			return data.toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.SHOWORGUSERS, "指定机构下的用户信息列表查询操作失败【"+e.getMessage()+"】",null,null);
			logger.error("查看该机构下的用户列表出现错误",e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
			
		return data.toJSONString();
	}
	
	@RequestMapping("/org/selectOrgUser.html")
	public String toSelectOrgUser(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = ActionContext.getActionContext().getParameterAsString("oId");
		model.addAttribute("orgId", id);
		return "/org/selectUsers";
	}
	
	//获取添加机构的用户列表
	@RequestMapping(value="/org/usersRequredOrg.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String  getUsersRequredOrg(HttpServletRequest request,HttpServletResponse response){
		String id=ActionContext.getActionContext().getParameterAsString("orgId");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize=PropertiesUtil.getInstance().getPropertyInt("orgUserPageSize", 6);
		ResultData data=new ResultData();
		if(selectType<0 || selectType >2){
			LoggerServer.error(ObjectType.GROUP,OperateType.SHOWUSERSWITHOUTORG, "添加机构的用户信息查询操作参数不合法",null,null);
			data.setSuccess(false);
			data.setMessage("查询的条件不合法");
			return data.toJSONString();
		}
		Long orgId=null;
		if(!StringUtils.isBlank(id)){
			try{
				orgId=Long.parseLong(id);
			}catch (Exception e) {
				LoggerServer.error(ObjectType.GROUP,OperateType.SHOWUSERSWITHOUTORG, "添加机构的用户信息查询操作参数id【"+id+"】不合法",null,null);
				logger.error("获取添加机构的用户列表的机构id参数orgId:{}不合法",id,e);
				data.setSuccess(false);
				data.setMessage("查询的条件不合法");
				return data.toJSONString();
			}
		}
		if(currPage<0){
			currPage=1;
		}
		try{
			PageInfo<User> users=orgService.getAllUsersIncludeOrgId(orgId, selectType, selectName, pageSize, currPage);
			data.setSuccess(true);
			data.put("page", users);
			return data.toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.SHOWUSERSWITHOUTORG, "添加机构的用户查询操作失败【"+e.getMessage()+"】",null,null);
			logger.error("查看要添加机构的用户列表出现错误",e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
			
		return data.toJSONString();
	}
	
	//从机构下移除指定的用户
	@RequestMapping(value="/org/delOrgUsers.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delOrgUsers(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		Long orgId= ActionContext.getActionContext().getParameterAsLong("orgId");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.GROUP,OperateType.DELGROUPUSER, "移除机构下的用户操作参数不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("移除该机构下的用户的所需参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		List<Long> userKeys=new ArrayList<Long>();
		for (int i=0;i<keys.length;i++) {
			try{
				long orgKey=Long.parseLong(keys[i]);
				userKeys.add(orgKey);
			}catch (Exception e) {
				LoggerServer.error(ObjectType.GROUP,OperateType.DELGROUPUSER, "移除机构下的用户操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("移除机构下的用户所带有的参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult=orgUserService.deleteOrgUsersUnderOrg(orgId, userKeys);
			if(delResult){
				LoggerServer.info(ObjectType.GROUP,OperateType.DELGROUPUSER, "成功移除机构下的用户",null,null,true);
				data.setSuccess(true);
				data.setMessage("移除成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.GROUP,OperateType.DELGROUPUSER, "成功移除机构下的用户",null,null,false);
				data.setSuccess(false);
				data.setMessage("移除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.DELGROUPUSER, "移除机构下的用户操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("内部错误");
			logger.error("将用户从组织机构中移除的操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	//给机构添加用户
	@RequestMapping(value="/org/addOrgUsers.do",produces="text/plain;charset=UTF-8")
	public  @ResponseBody String addOrgUsers(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		Long orgId= ActionContext.getActionContext().getParameterAsLong("orgId");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.GROUP,OperateType.CREATEGROUPUSER, "添加机构用户操作参数不能为空",null,null);
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
				LoggerServer.error(ObjectType.GROUP,OperateType.CREATEGROUPUSER, "添加机构用户操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("添加的用户参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean addResult=orgUserService.addUsersWithOrgId(orgId, userKeys);
			if(addResult){
				LoggerServer.info(ObjectType.GROUP,OperateType.CREATEGROUPUSER, "成功添加机构用户操作",null,null,true);
				data.setSuccess(true);
				data.setMessage("添加成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.GROUP,OperateType.CREATEGROUPUSER, "添加机构用户操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("添加失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.CREATEGROUPUSER, "添加机构用户操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("内部错误");
			logger.error("在机构中添加用户的操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 组织机构的查询操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/org/orgSelect.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String orgSelect(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		ResultData data = new ResultData();
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 如果不是超级管理员。获取用户所在的机构，（机构分为业务机构和管理机构）
		OrgUser orgUser = orgUserService.getOrgUserByUserId(user.getId());
		Org org1 = orgService.selectByKey(orgUser.getOrgId());
		// 获取机构类型
		Integer orgType = org1.getType();
		if (!user.isAdmin()) {
			if (orgType == 2) {
				data.setSuccess(false);
				data.setMessage("您没有权限查询其他机构！");
				return data.toJSONString();
			}
		}
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		String orgName=null;
		String summary=null;
		if(selectType==1){
			orgName=selectName;
		}
		if(selectType==2){
			summary=selectName;
		}
		String orderBy = "CRT_TIME";
		try {
			PageInfo<OrgView> pageInfo = orgService.getOrgsListByFilter(orgName, summary, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.GROUP,OperateType.SHOWGROUPLIST, "对组织机构的模糊操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
			logger.error("分页查询机构出错,pageIndex:{},pageSize:{},selectType:{},selectName:{}",currPage,pageSize,selectType,selectName,e);
		}
		return data.toJSONString();
		
	}
	
	/**
	 * 从系统配置中获取机构系统信息
	 * @param reauest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/org/orgSystem.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String activityClass(HttpServletRequest reauest, HttpServletResponse response){
		ResultData data = new ResultData();
		List<OrgSystem> orgSystemList=null;
		List<Config> orgSystemConfig=configCache.getConfigValuesByType("orgSystem");
		if(orgSystemConfig!=null && !orgSystemConfig.isEmpty()){
			orgSystemList= new ArrayList<OrgSystem>(); 
			for (Config config : orgSystemConfig) {
				OrgSystem orgSystem=new OrgSystem();
				orgSystem.setId(config.getId());
				orgSystem.setName(config.getContent());
				orgSystemList.add(orgSystem);
				logger.debug("获取的机构系统为{}",orgSystem);
			}
		}
		if(orgSystemList!=null && !orgSystemList.isEmpty()){
			data.setSuccess(true);
			data.put("orgSystemList", orgSystemList);
		}else{
			data.setSuccess(false);
		}
		return data.toJSONString();
	}
}
