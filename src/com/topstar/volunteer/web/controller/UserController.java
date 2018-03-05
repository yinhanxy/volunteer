package com.topstar.volunteer.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.RoleUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.ChannelView;
import com.topstar.volunteer.model.MenuView;
import com.topstar.volunteer.model.RoleView;
import com.topstar.volunteer.service.ChannelService;
import com.topstar.volunteer.service.MenuService;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.RoleService;
import com.topstar.volunteer.service.RoleUserService;
import com.topstar.volunteer.service.UserMenuService;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.util.secrecy.MD5Util;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 后台用户管理控制类
 */
@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleUserService roleUserService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserMenuService userMenuService;
	
	@Autowired
	private ChannelService channelService;
	
	/**
	 * 用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/list.html")
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "/role/user/list";
	}
	
	/**
	 * 用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/userList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String userList(HttpServletRequest request,HttpServletResponse response){
		User user = new User();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String userName = ActionContext.getActionContext().getParameterAsString("userName");
		String realName = ActionContext.getActionContext().getParameterAsString("realName");
		int status = ActionContext.getActionContext().getParameterAsInt("status");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		user.setUserName(userName);
		user.setRealName(realName);
		user.setStatus(status);
		
		String orderBy = "CR_TIME DESC,USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<User> pageInfo = userService.findByEntity(user, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.USER,OperateType.SHOWUSERLIST, "成功查询用户列表",null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.SHOWUSERLIST, "查询用户列表操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询后台用户出错,pageIndex:{},pageSize:{},userName:{},realName:{}",currPage,pageSize,userName,realName,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加用户页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/addUser.html")
	public String toAddUser(HttpServletRequest request,HttpServletResponse response){
		return "/role/user/addUser";
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/addUser.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addUser(HttpServletRequest request,HttpServletResponse response,User user){
		ResultData data = new ResultData();
		List<String> roleId = ActionContext.getActionContext().getParameterAsList("role");
		List<Long> roleIds=new ArrayList<Long>();
		if(roleId !=null && roleId.size()!=0){
			for (String rId : roleId) {
				try{
					roleIds.add(Long.parseLong(rId));
				}catch (Exception e) {
					LoggerServer.error(ObjectType.USER,OperateType.CREATEUSER, "添加用户操作参数roleId【"+roleId+"】不合法",null,null);
					data.setSuccess(false);
					data.setMessage("用户角色标识非法");
					return data.toJSONString();
				}
			}
		}
		
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		user.setCrTime(new Timestamp(new Date().getTime()));
		user.setCrUser(crtUser.getUserName());
		user.setRegTime(new Timestamp(new Date().getTime()));
		user.setStatus(1);
		user.setUseTime(new Timestamp(new Date().getTime()));
		
		List<ValidateMessage> errors =ValidatorUtil.validate(user, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		user.setRealName(StringTools.FormatStrFromInput(user.getRealName()));
		user.setNickName(StringTools.FormatStrFromInput(user.getNickName()));
		user.setUserPwd(MD5Util.encode(user.getUserPwd()));
		try {
			int exists=userService.existsWithUserName(user.getUserName(), null);
			if(exists==0){
				data.setSuccess(false);
				data.setMessage("该用户名为系统账户,请重新输入");
				return data.toJSONString();
			}
			if(exists>0){
				data.setSuccess(false);
				data.setMessage("用户名已存在");
				return data.toJSONString();
			}else{
				boolean result=userService.addUser(user, roleIds);
				if(result){
					LoggerServer.info(ObjectType.USER,OperateType.CREATEUSER, "成功添加用户操作",null,null,true);
					data.setSuccess(true);
					data.setMessage("用户添加成功");
					return data.toJSONString();
				}else{
					LoggerServer.error(ObjectType.USER,OperateType.CREATEUSER, "添加用户操作失败",null,null);
					data.setSuccess(false);
					data.setMessage("用户添加失败");
					return data.toJSONString();
				}
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.CREATEUSER, "添加用户操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("添加用户操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 用户的批量删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/delUser.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delUser(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		ResultData data = new ResultData();
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.USER,OperateType.DELUSER, "删除用户操作参数不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("删除参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		long[] userKeys=new long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long userKey=Long.parseLong(keys[i]);
				userKeys[i]=userKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.USER,OperateType.DELUSER, "删除用户操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("用户删除参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			boolean delUser=userService.deleteUsers(userKeys);
			if(delUser){
				LoggerServer.info(ObjectType.USER,OperateType.DELUSER, "成功删除用户",null,null,true);
				data.setSuccess(true);
				data.setMessage("用户删除成功");
				return data.toJSONString();
			}else{
				LoggerServer.error(ObjectType.USER,OperateType.DELUSER, "删除用户操作失败",null,null);
				data.setSuccess(false);
				data.setMessage("用户删除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.DELUSER, "删除用户操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("删除用户操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 帐号的批量状态操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/changeUser.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String operateUser(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		String type = ActionContext.getActionContext().getParameterAsString("type");
		if(StringUtils.isBlank(ids) || StringUtils.isBlank(type)){
			LoggerServer.error(ObjectType.USER,OperateType.CHANGEUSERSTATUS, "批量改变用户状态值的操作参数不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("操作用户参数不能为空");
			return data.toJSONString();
		}
		int status;
		try{
			status=Integer.parseInt(type);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.CHANGEUSERSTATUS, "批量改变用户状态值的操作参数type【"+type+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("type参数不合法,操作失败");
			return data.toJSONString();
		}
		if(status<1 || status>3){
			LoggerServer.error(ObjectType.USER,OperateType.CHANGEUSERSTATUS, "批量改变用户状态值的操作参数status【"+status+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("type参数不合法,操作失败");
			return data.toJSONString();
		}
		
		try {
			int row=userService.setUsersStatus(ids, status);
			if(row==0){
				LoggerServer.info(ObjectType.USER,OperateType.CHANGEUSERSTATUS, "成功批量改变用户状态值",null,null,true);
				data.setSuccess(true);
				data.setMessage("操作成功");
				return data.toJSONString();
			}else{
				LoggerServer.error(ObjectType.USER,OperateType.CHANGEUSERSTATUS, "批量改变用户状态值的操作失败",null,null);
				data.setSuccess(false);
				data.setMessage("操作失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.CHANGEUSERSTATUS, "批量改变用户状态值的操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("账户状态操作出错",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到编辑用户页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/editUser.html")
	public String toEditUser(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		String id = ActionContext.getActionContext().getParameterAsString("vid");
		Long key=null;
		key=Long.valueOf(id);
		User user=userService.selectByKey(key);
		Org org=orgService.getOrgByUserId(key);
		map.addAttribute("user", user);
		if(org!=null){
			map.addAttribute("org", org);
		}
		return "/role/user/editUser";
	}
	
	/**
	 * 跳转到查看用户页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/viewUser.html")
	public String toViewUser(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		String id = ActionContext.getActionContext().getParameterAsString("vid");
		Long key=null;
		try{
			key=Long.valueOf(id);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.SHOWUSER, "查看用户信息的操作参数id【"+id+"】不合法",null,null);
			logger.error("查看用户信息参数id不合法:{}",id,e);
		}
		try{
			User user=userService.selectByKey(key);
			Org org=orgService.getOrgByUserId(key);
			map.addAttribute("user", user);
			if(org!=null){
				map.addAttribute("org", org);
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.SHOWUSER, "查看用户信息的操作发生错误【"+e.getMessage()+"】",null,null);
		}
		
		return "/role/user/viewUser";
	}
	
	/**
	 * 用户的编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/editUser.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String editUser(HttpServletRequest request,HttpServletResponse response,User user){
		ResultData data = new ResultData();
		List<String> roleId = ActionContext.getActionContext().getParameterAsList("role");
		List<Long> roleIds=new ArrayList<Long>();
		if(roleId !=null && roleId.size()!=0){
			for (String rId : roleId) {
				try{
					roleIds.add(Long.parseLong(rId));
				}catch (Exception e) {
					LoggerServer.error(ObjectType.USER,OperateType.EDITUSER, "编辑用户信息操作参数roleId【"+roleId+"】不合法",null,null);
					data.setSuccess(false);
					data.setMessage("用户角色标识非法");
					return data.toJSONString();
				}
			}
		}
		
		BaseUser crtUser= ShiroSessionMgr.getLoginUser();
		user.setCrUser(crtUser.getUserName());
		
		List<ValidateMessage> errors =ValidatorUtil.validate(user, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		user.setNickName(StringTools.FormatStrFromInput(user.getNickName()));
		user.setRealName(StringTools.FormatStrFromInput(user.getRealName()));
		try {
			boolean updateUser=userService.updateUser(user, roleIds);
			if(updateUser){
				LoggerServer.info(ObjectType.USER,OperateType.EDITUSER, "成功编辑用户信息",null,null,true);
				data.setSuccess(true);
				data.setMessage("用户保存成功");
				return data.toJSONString();
			}else{
				LoggerServer.error(ObjectType.USER,OperateType.EDITUSER, "编辑用户信息操作失败",null,null);
				data.setSuccess(true);
				data.setMessage("用户保存失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.EDITUSER, "编辑用户信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("修改用户操作出现出错",e);
		}
		return data.toJSONString();
	}
	

	/**
	 * 获取用户角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/getUserRoles.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String loadRoles(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String userId= ActionContext.getActionContext().getParameterAsString("userId");
		if(StringUtils.isBlank(userId)){
			LoggerServer.error(ObjectType.USER,OperateType.SHOWUSERROLES, "显示用户角色列表信息操作参数userId不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("加载用户角色参数不能为空");
			return data.toJSONString();
		}
		Long key=null;
		try{
			key=Long.valueOf(userId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.SHOWUSERROLES, "显示用户角色列表信息操作参数userId【"+userId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("用户编号"+userId+"非法");
			logger.error("加载用户所属角色的用户编号:{}",userId,e);
			return data.toJSONString();
		}
		try {
			List<RoleUser> userRoles=roleUserService.getRoleUserByUser(key);
			Set<Long> userRoleIds=new HashSet<Long>();
			if(userRoles!=null && userRoles.size()!=0){
				for (RoleUser userRole : userRoles) {
					userRoleIds.add(userRole.getRoleId());
				}
			}
			List<Role> roles=roleService.getAllRoles();
			RoleView roleView=null;
			List<RoleView> roleViews=new ArrayList<RoleView>();
			if(roles!=null){
				for (Role role : roles) {
					roleView=new RoleView();
					Long roleId=role.getId();
					roleView.setRoleName(role.getRoleName());
					roleView.setId(role.getId());
					if(userRoleIds!=null && userRoleIds.size()!=0){
						if(userRoleIds.contains(roleId)){
							roleView.setIsOwn(1);
						}
					}
					roleViews.add(roleView);
				}
			}
			PageInfo<RoleView> page=new PageInfo<RoleView>(roleViews);
			data.setSuccess(true);
			data.put("roleViewsPage",page);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.SHOWUSERROLES, "显示用户角色列表信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("获取用户角色操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	
	/**
	 * 重置用户密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/resetPassword.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String resetUserPassword(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.USER,OperateType.RESETPASSWORD, "重置用户密码操作参数不能为空",null,null);
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
				LoggerServer.error(ObjectType.USER,OperateType.RESETPASSWORD, "重置用户密码操作参数userKeys【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("重置用户密码参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			int row=userService.resetUsersPassword(userKeys);
			if(row==0){
				LoggerServer.info(ObjectType.USER,OperateType.RESETPASSWORD, "成功重置用户密码",null,null,true);
				data.setSuccess(true);
				data.setMessage("重置密码成功");
				return data.toJSONString();
			}else{
				LoggerServer.error(ObjectType.USER,OperateType.RESETPASSWORD, "成功重置用户密码",null,null);
				data.setSuccess(false);
				data.setMessage("重置密码失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.RESETPASSWORD, "重置用户密码操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("重置用户密码操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	//跳转到用户权限配置菜单页面
	@RequestMapping(value="/user/userAccess.html")
	public String toUserAccess(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		ResultData resultData=new ResultData();
		Long userId= ActionContext.getActionContext().getParameterAsLong("uId");
		if(userId<1){
			LoggerServer.error(ObjectType.USER,OperateType.SHOWACCESSMENU, "显示用户权限菜单操作参数userId【"+userId+"】不合法",null,null);
			logger.error("用户菜单分配操作的参数userId:{}不合法",userId);
		}
		try{
			List<MenuView> menuList=menuService.findMenus();
			List<Long> menuIds=userMenuService.getMenuIdsByUserId(userId);
			resultData.setSuccess(true);
			resultData.put("data", menuList);
			resultData.put("menuIds", menuIds);
			map.put("data", resultData.toJSONString());
			map.put("userId", userId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.SHOWACCESSMENU, "显示用户权限菜单操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("获取用户权限列表出错",e);
			map.put("data", ResultData.fail("获取用户权限列表出错").toJSONString());
		}
		return "/role/user/selectMenu";
	}
	
	//查看用户所有权限(用户本身的权限和角色权限)
	@RequestMapping(value="/user/viewAccess.html")
	public String viewAccess(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		ResultData resultData=new ResultData();
		Long userId= ActionContext.getActionContext().getParameterAsLong("uId");
		if(userId<1){
			LoggerServer.error(ObjectType.USER,OperateType.SHOWAllACCESSMENU, "显示用户所有权限操作参数userId【"+userId+"】不合法",null,null);
			logger.error("显示用户所有权限的参数userId:{}不合法",userId);
		}
		try{
			List<MenuView> menuList=menuService.findMenus();
			List<Long> userMenuIds=userMenuService.getMenuIdsByUserId(userId);
			List<Long> userRolemenuIds=userMenuService.getUserRoleMenuIdsByUserId(userId);
			resultData.setSuccess(true);
			resultData.put("data", menuList);
			resultData.put("userMenuIds", userMenuIds);
			resultData.put("userRolemenuIds", userRolemenuIds);
			map.put("data", resultData.toJSONString());
			map.put("userId", userId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.SHOWAllACCESSMENU, "显示用户所有权限操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("获取用户所有权限菜单出错",e);
			map.put("data", ResultData.fail("获取用户所有权限菜单出错").toJSONString());
		}
		return "/role/user/userAllAccess";
	}
	
	@RequestMapping(value="/user/addUserMenus.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addUserMenus(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		Long userId= ActionContext.getActionContext().getParameterAsLong("userId");
		String menuIds= ActionContext.getActionContext().getParameterAsString("menuIds");
		if(userId==null || userId<1){
			LoggerServer.error(ObjectType.USER,OperateType.ADDUSERMENU, "添加用户菜单权限操作参数userId【"+userId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("用户菜单分配操作的参数为空");
			logger.info("用户菜单分配参数userId为空");
			return data.toJSONString();
		}
		List<Long> menuKeys=null;
		if(!StringUtils.isBlank(menuIds)){
			String[] keys=menuIds.split(",");
			menuKeys=new ArrayList<Long>();
			for (int i=0;i<keys.length;i++) {
				try{
					Long menuKey=Long.parseLong(keys[i]);
					menuKeys.add(menuKey);
				}catch (Exception e) {
					LoggerServer.error(ObjectType.USER,OperateType.ADDUSERMENU, "添加用户菜单权限操作参数menuKeys【"+menuIds+"】不合法",null,null);
					data.setSuccess(false);
					data.setMessage("参数不合法");
					logger.error("用户菜单分配操作的菜单参数不合法,menuKey:{}",menuIds,e);
					return data.toJSONString();
				}
			}
		}
		try{
			Boolean addUserMenu=userMenuService.addUserMenus(userId, menuKeys);
			if(addUserMenu){
				LoggerServer.info(ObjectType.USER,OperateType.ADDUSERMENU, "成功添加用户菜单权限",null,null,true);
				data.setSuccess(true);
				data.setMessage("分配成功");
			}else{
				LoggerServer.error(ObjectType.USER,OperateType.ADDUSERMENU, "添加用户菜单权限操作失败",null,null);
				data.setSuccess(false);
				data.setMessage("分配失败");
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.ADDUSERMENU, "添加用户菜单权限操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("用户菜单分配失败",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到用户的访问控制页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/selectChannel.html")
	public String toSelectChannel(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		ResultData data=new ResultData();
		try {
			Long id = ActionContext.getActionContext().getParameterAsLong("rId");
			if(id != null && id.longValue() > 0){
				model.addAttribute("userId", id);
				List<Long> channelIds = userService.getChannelIds(id);
				List<ChannelView> channels= channelService.getAllChannelBySiteIdInCache();
				if(channels!=null){
					data.setSuccess(true);
					data.put("channels",channels);
				}
				if(channelIds != null && channelIds.size() > 0){
					data.put("channelIds", channelIds);
				}
			}
		} catch (Exception e) {
			logger.error("跳转到用户的栏目访问控制页出错",e);
			data.setSuccess(false);
			data.setMessage("访问用户的栏目控制页出错!");
		}
		model.put("data", data.toJSONString());
		return "/role/user/selectChannel";
	}
	
	/**
	 * 为用户添加可访问的栏目信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/addUserChannels.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addRoleChannels(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		Long userId= ActionContext.getActionContext().getParameterAsLong("userId");
		String channelIds= ActionContext.getActionContext().getParameterAsString("channelIds");
		if(userId==null || userId.longValue()<1){
			LoggerServer.error(ObjectType.USER,OperateType.ADDUSERCHANNEL, "添加用户栏目操作参数userId【"+userId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("用户栏目分配操作的参数为空");
			logger.info("用户栏目分配参数roleId为空");
			return data.toJSONString();
		}
		List<Long> channelKeys=null;
		if(!StringUtils.isBlank(channelIds)){
			String[] keys=channelIds.split(",");
			channelKeys=new ArrayList<Long>();
			for (int i=0;i<keys.length;i++) {
				try{
					Long channelKey=Long.parseLong(keys[i]);
					channelKeys.add(channelKey);
				}catch (Exception e) {
					LoggerServer.error(ObjectType.USER,OperateType.ADDUSERCHANNEL, "添加用户栏目操作参数channelIds【"+channelIds+"】不合法",null,null);
					data.setSuccess(false);
					data.setMessage("参数不合法");
					logger.error("用户栏目分配操作的栏目参数不合法,channelKey:{}",channelIds,e);
					return data.toJSONString();
				}
			}
		}
		try{
			Boolean addRoleMenu=userService.addUserChannels(userId, channelKeys);
			if(addRoleMenu){
				data.setSuccess(true);
				LoggerServer.info(ObjectType.USER,OperateType.ADDUSERCHANNEL, "成功添加用户栏目",null,null,true);
				data.setMessage("操作成功");
			}else{
				LoggerServer.error(ObjectType.USER,OperateType.ADDUSERCHANNEL, "添加用户栏目操作失败",null,null);
				data.setSuccess(false);
				data.setMessage("操作失败");
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.USER,OperateType.ADDUSERCHANNEL, "添加用户栏目操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("用户栏目分配失败",e);
		}
		return data.toJSONString();
	}
	
	
}
