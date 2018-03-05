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
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.ChannelView;
import com.topstar.volunteer.model.MenuView;
import com.topstar.volunteer.service.ChannelService;
import com.topstar.volunteer.service.MenuService;
import com.topstar.volunteer.service.RoleService;
import com.topstar.volunteer.service.RoleUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.PropertiesUtil;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class RoleController {

	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleUserService roleUserService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private ChannelService channelService;
	
	/**
	 * 角色列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/list.html")
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "/role/role/list";
	}
	
	/**
	 * 角色列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/roleList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String roleList(HttpServletRequest request,HttpServletResponse response){
		Role role= new Role();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String roleName = ActionContext.getActionContext().getParameterAsString("roleName");
		String crName = ActionContext.getActionContext().getParameterAsString("crName");
		int roleType = ActionContext.getActionContext().getParameterAsInt("roleType");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		role.setRoleName(roleName);
		role.setCrUser(crName);
		role.setRoleType(roleType);
		String orderBy = "";
		ResultData data = new ResultData();
		try {
			PageInfo<Role> pageInfo = roleService.findByEntity(role, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLELIST, "查询角色列表操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
			logger.error("分页查询后台用户出错,pageIndex:{},pageSize:{},userName:{},nickName:{}",currPage,pageSize,roleName,crName,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加角色页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/addRole.html")
	public String toAddRole(HttpServletRequest request,HttpServletResponse response){
		return "/role/role/addRole";
	}
	
	/**
	 * 跳转到查看角色页面
	 * @param request
	 * @param response
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value="/role/viewRole.html")
	public String toViewRole(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws TPSException{
		String id = ActionContext.getActionContext().getParameterAsString("vid");
		Long key=null;
		try{
			key=Long.valueOf(id);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLE, "查询角色信息操作参数不合法【"+id+"】",null,null);
			logger.error("查看角色信息的参数vid:{}不合法",id,e);
			throw new TPSException("不合法");
		}
		try{
			Role role=roleService.selectByKey(key);
			map.addAttribute("role", role);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLE, "查询角色信息时发生错误【"+e.getMessage()+"】",null,null);
		}
		return "/role/role/viewRole";
	}
	
	/**
	 * 跳转到编辑角色页面
	 * @param request
	 * @param response
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value="/role/editRole.html")
	public String toEditRole(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws TPSException{
		String id = ActionContext.getActionContext().getParameterAsString("vid");
		Long key=null;
		try{
			key=Long.valueOf(id);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.EDITROLE, "跳转到编辑角色页面操作参数不合法【"+id+"】",null,null);
			logger.error("跳转到编辑角色页面的参数vid:{}不合法",id,e);
			throw new TPSException("不合法");
		}
		Role role=roleService.selectByKey(key);
		map.addAttribute("role", role);
		return "/role/role/editRole";
	}
	
	/**
	 * 角色的添加
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/addRole.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addRole(HttpServletRequest request,HttpServletResponse response){
		Role role= new Role();
		ResultData data = new ResultData();
		String roleName = ActionContext.getActionContext().getParameterAsString("roleName");
		int roleType = ActionContext.getActionContext().getParameterAsInt("roleType");
		String roleDesc = ActionContext.getActionContext().getParameterAsString("roleDesc");

		role.setRoleName(roleName);
		role.setRoleType(roleType);
		role.setRoleDesc(roleDesc);
		role.setStatus(1);
		BaseUser user = ShiroSessionMgr.getLoginUser();
		role.setCrUser(user.getUserName());
		role.setCrTime(new Timestamp(new Date().getTime()));
		
		List<ValidateMessage> errors =ValidatorUtil.validate(role, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		role.setRoleName(StringTools.FormatStrFromInput(roleName));
		role.setRoleType(roleType);
		role.setRoleDesc(StringTools.FormatStrFromInput(roleDesc));
		try {
			int exists=roleService.existsWithRoleName(roleName, null);
			if(exists>0){
				data.setSuccess(false);
				data.setMessage("角色名已存在");
				return data.toJSONString();
			}else{
				boolean result=roleService.addRole(role);
				if(result){
					data.setSuccess(true);
					data.setMessage("添加成功");
					LoggerServer.info(ObjectType.ROLE,OperateType.CREATEROLE, "成功添加角色",null,null,true);
					return data.toJSONString();
				}else{
					data.setSuccess(false);
					data.setMessage("添加失败");
					LoggerServer.info(ObjectType.ROLE,OperateType.CREATEROLE, "添加角色操作失败",null,null,false);
					return data.toJSONString();
				}
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.CREATEROLE, "添加角色操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
			logger.error("添加角色操作出现出错,roleName:{}",roleName,e);
		}
		return data.toJSONString();
	}
	
	
	/**
	 * 角色的批量删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/delRole.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delRole(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.ROLE,OperateType.DELROLE, "批量删除角色操作参数不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("删除角色参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		long[] roleKeys=new long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long roleKey=Long.parseLong(keys[i]);
				roleKeys[i]=roleKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ROLE,OperateType.DELROLE, "批量删除角色操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("角色删除参数不合法,roleKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult=roleService.deleteRoles(roleKeys);
			if(delResult){
				LoggerServer.info(ObjectType.ROLE,OperateType.DELROLE, "成功批量删除角色",null,null,true);
				data.setSuccess(true);
				data.setMessage("角色删除成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ROLE,OperateType.DELROLE, "批量删除角色操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("角色删除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.DELROLE, "批量删除角色操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("内部错误");
			logger.error("删除角色操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	
	/**
	 * 角色的编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/editRole.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String editRole(HttpServletRequest request,HttpServletResponse response){
		Role role= new Role();
		ResultData data = new ResultData();
		//String roleName = ActionContext.getActionContext().getParameterAsString("roleName");
		String key = ActionContext.getActionContext().getParameterAsString("id");
		int roleType = ActionContext.getActionContext().getParameterAsInt("roleType");
		String roleDesc = ActionContext.getActionContext().getParameterAsString("roleDesc");
		
		Long roleId=null;
		try{
			roleId=Long.valueOf(key);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.EDITROLE, "修改角色信息操作参数key【"+key+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("修改角色信息参数id不合法");
			logger.error("修改角色信息的参数roleKey:{}不合法",key,e);
			return data.toJSONString();
		}
		role.setId(roleId);
		//role.setRoleName(roleName);
		role.setRoleType(roleType);
		role.setRoleDesc(roleDesc);
		role.setStatus(1);
		
		List<ValidateMessage> errors =ValidatorUtil.validate(role, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		role.setRoleDesc(StringTools.FormatStrFromInput(roleDesc));
		
		try {
			/*int exists=roleService.existsWithRoleName(roleName, key);
			if(exists>0){
				data.setSuccess(false);
				data.setMessage("角色名已存在");
				return data.toJSONString();
			}else{*/
				boolean updateRole=roleService.updateRole(role);
				if(updateRole){
					LoggerServer.info(ObjectType.ROLE,OperateType.EDITROLE, "成功修改角色信息",null,null,true);
					data.setSuccess(true);
					data.setMessage("角色保存成功");
					return data.toJSONString();
				}else{
					LoggerServer.info(ObjectType.ROLE,OperateType.EDITROLE, "修改角色信息操作失败",null,null,false);
					data.setSuccess(false);
					data.setMessage("角色保存失败");
					return data.toJSONString();
				}
			/*}*/
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.EDITROLE, "修改角色信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("内部错误");
			logger.error("修改角色操作出现出错");
		}
		return data.toJSONString();
	}
	
	/**
	 * 获取所有角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/getRoles.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String loadRoles(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		try {
			List<Role> roles=roleService.getAllRoles();
			data.setSuccess(true);
			data.put("roles", roles);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLELIST, "查询所有角色信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("查询所有角色信息操作出现出错");
		}
		return data.toJSONString();
	}
	
	/**
	 * 对应角色下的用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/roleUsers.html")
	public String toRoleUser(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = ActionContext.getActionContext().getParameterAsString("rId");
		model.addAttribute("roleId", id);
		return "/role/role/roleUsers";
	}
	
	//获取被赋予对应角色下的用户信息
	@RequestMapping(value="/role/roleUsers.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String toViewRoleUsers(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String roleId=ActionContext.getActionContext().getParameterAsString("roleId");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize=PropertiesUtil.getInstance().getPropertyInt("RoleUserPageSize", 5);
		ResultData data=new ResultData();
		Long id=null;
		try{
			id=Long.parseLong(roleId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLEUSER, "查询指定角色下的用户信息列表操作参数【"+roleId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try{
			if(selectType<0 || selectType >2){
				LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLEUSER, "查询指定角色下的用户信息列表操作查询条件不合法【"+selectType+"】",id,null);
				data.setSuccess(false);
				data.setMessage("查询的条件不合法");
				return data.toJSONString();
			}
			Role role=roleService.selectByKey(id);
			PageInfo<User> users=roleService.getUsersByGivenRoleId(roleId, selectType,selectName, pageSize, currPage);
			data.setSuccess(true);
			data.put("page", users);
			data.put("role", role);
			return data.toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLEUSER, "查询指定角色下的用户信息列表操作发生错误【"+e.getMessage()+"】",id,null);
			logger.error("查看被赋予该角色的用户列表出现错误",e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
			
		return data.toJSONString();
	}
	
	//在分配用户页面删除具有该角色的用户
	@RequestMapping(value="/role/delRoleUsers.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delRoleUsers(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		Long roleId= ActionContext.getActionContext().getParameterAsLong("roleId");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.ROLE,OperateType.DELROLEUSER, "移除指定角色下的用户信息操作参数不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("删除该角色下的用户所带有的参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		List<Long> userKeys=new ArrayList<Long>();
		for (int i=0;i<keys.length;i++) {
			try{
				long roleKey=Long.parseLong(keys[i]);
				userKeys.add(roleKey);
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ROLE,OperateType.DELROLEUSER, "移除指定角色下的用户信息操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("移除角色下的用户所带有的参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult=roleUserService.deleteRoleUsersUnderRole(roleId, userKeys);
			if(delResult){
				LoggerServer.info(ObjectType.ROLE,OperateType.DELROLEUSER, "成功移除指定角色下的用户信息",null,null,true);
				data.setSuccess(true);
				data.setMessage("移除成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ROLE,OperateType.DELROLEUSER, "成功移除指定角色下的用户信息操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("移除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.DELROLEUSER, "移除指定角色下的用户信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("内部错误");
			logger.error("将用户在角色中移除的操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	@RequestMapping("/role/selectRoleUser.html")
	public String toSelectRoleUser(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = ActionContext.getActionContext().getParameterAsString("rId");
		model.addAttribute("roleId", id);
		return "/role/role/selectUsers";
	}
	
	//获取需要添加角色的用户列表
	@RequestMapping(value="/role/usersRequredRole.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String  getUsersRequredRole(HttpServletRequest request,HttpServletResponse response){
		String id=ActionContext.getActionContext().getParameterAsString("roleId");
		Long orgId=ActionContext.getActionContext().getParameterAsLong("orgId");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize=PropertiesUtil.getInstance().getPropertyInt("roleUserPageSize", 5);
		ResultData data=new ResultData();
		if(selectType<0 || selectType >2){
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWADDROLEUSER, "查询角色添加页面下的用户信息操作参数selectType【"+selectType+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("查询的条件不合法");
			return data.toJSONString();
		}
		Long roleId=null;
		if(!StringUtils.isBlank(id)){
			try{
				roleId=Long.parseLong(id);
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ROLE,OperateType.SHOWADDROLEUSER, "查询角色添加页面下的用户信息操作参数id【"+id+"】不合法",null,null);
				logger.error("获取需要添加角色的用户列表的角色参数RoleId:{}不合法",id,e);
				data.setSuccess(false);
				data.setMessage("查询的条件不合法");
				return data.toJSONString();
			}
		}
		if(currPage<0){
			currPage=1;
		}
		try{
			PageInfo<User> users=roleService.getAllUsersIncludeRoleIds(orgId,roleId, selectType, selectName, pageSize, currPage);
			data.setSuccess(true);
			data.put("page", users);
			return data.toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWADDROLEUSER, "查询角色添加页面下的用户信息操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("查看被赋予该角色的用户列表出现错误",e);
			data.setSuccess(false);
			data.setMessage("内部错误");
		}
			
		return data.toJSONString();
	}
	
	//给角色分配用户
	@RequestMapping(value="/role/addRoleUsers.do",produces="text/plain;charset=UTF-8")
	public  @ResponseBody String addRoleUsers(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		Long roleId= ActionContext.getActionContext().getParameterAsLong("roleId");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLEUSERS, "分配用户角色操作参数ids不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("添加的用户参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		List<Long> userKeys=new ArrayList<Long>();
		for (int i=0;i<keys.length;i++) {
			try{
				long roleKey=Long.parseLong(keys[i]);
				userKeys.add(roleKey);
			}catch (Exception e) {
				LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLEUSERS, "分配用户角色操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("添加的用户参数不合法,userKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean addResult=roleService.addUsersWithRoleId(roleId, userKeys);
			if(addResult){
				LoggerServer.info(ObjectType.ROLE,OperateType.ADDROLEUSERS, "成功分配用户角色",null,null,true);
				data.setSuccess(true);
				data.setMessage("添加成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.ROLE,OperateType.ADDROLEUSERS, "分配用户角色操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("添加失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLEUSERS, "分配用户角色操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("内部错误");
			logger.error("将用户添加到角色中的操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	@RequestMapping(value="role/roleAccess.html")
	public String toRoleAccess(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws TPSException{
		ResultData resultData=new ResultData();
		Long roleId= ActionContext.getActionContext().getParameterAsLong("rId");
		if(roleId<1){
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLEMENUS, "获取菜单列表操作参数roleId【"+roleId+"】不合法",null,null);
			logger.error("角色菜单分配操作的参数roleId:{}不合法",roleId);
			throw new TPSException("不合法");
		}
		try{
			List<MenuView> menuList=menuService.findMenus();
			List<Long> menuIds=roleService.getMenuIdsByRoleId(roleId);
			resultData.setSuccess(true);
			resultData.put("data", menuList);
			resultData.put("menuIds", menuIds);
			map.put("data", resultData.toJSONString());
			map.put("roleId", roleId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.SHOWROLEMENUS, "获取菜单列表操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("获取菜单列表出错",e);
			map.put("data", ResultData.fail("获取菜单列表出错").toJSONString());
		}
		return "/role/role/selectMenu";
	}
	
	@RequestMapping(value="role/addRoleMenus.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addRoleMenus(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		Long roleId= ActionContext.getActionContext().getParameterAsLong("roleId");
		String menuIds= ActionContext.getActionContext().getParameterAsString("menuIds");
		if(roleId==null || roleId<1){
			LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLEMENUS, "添加角色菜单操作参数roleId【"+roleId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("角色菜单分配操作的参数为空");
			logger.info("角色菜单分配参数roleId为空");
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
					LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLEMENUS, "添加角色菜单操作参数menuIds【"+menuIds+"】不合法",null,null);
					data.setSuccess(false);
					data.setMessage("参数不合法");
					logger.error("角色菜单分配操作的菜单参数不合法,menuKey:{}",menuIds,e);
					return data.toJSONString();
				}
			}
		}
		try{
			
			Boolean addRoleMenu=roleService.addRoleMenus(roleId, menuKeys);
			if(addRoleMenu){
				LoggerServer.info(ObjectType.ROLE,OperateType.ADDROLEMENUS, "成功添加角色菜单",null,null,true);
				data.setSuccess(true);
				data.setMessage("分配成功");
			}else{
				LoggerServer.info(ObjectType.ROLE,OperateType.ADDROLEMENUS, "添加角色菜单操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("分配失败");
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLEMENUS, "添加角色菜单操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("角色菜单分配失败",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到角色的栏目访问控制页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/role/selectChannel.html")
	public String toSelectChannel(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		ResultData data=new ResultData();
		try {
			Long id = ActionContext.getActionContext().getParameterAsLong("rId");
			if(id != null && id.longValue() > 0){
				model.addAttribute("roleId", id);
				List<Long> channelIds = roleService.getChannelIds(id);
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
			logger.error("跳转到角色的栏目访问控制页出错",e);
			data.setSuccess(false);
			data.setMessage("访问角色的栏目控制页出错!");
		}
		model.put("data", data.toJSONString());
		return "/role/role/selectChannel";
	}
	
	/**
	 * 为角色添加可访问的栏目信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/role/addRoleChannels.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addRoleChannels(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		Long roleId= ActionContext.getActionContext().getParameterAsLong("roleId");
		String channelIds= ActionContext.getActionContext().getParameterAsString("channelIds");
		if(roleId==null || roleId<1){
			LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLECHANNELS, "添加角色栏目操作参数roleId【"+roleId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("角色栏目分配操作的参数为空");
			logger.info("角色栏目分配参数roleId为空");
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
					LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLECHANNELS, "添加角色栏目操作参数channelIds【"+channelIds+"】不合法",null,null);
					data.setSuccess(false);
					data.setMessage("参数不合法");
					logger.error("角色栏目分配操作的栏目参数不合法,channelKey:{}",channelIds,e);
					return data.toJSONString();
				}
			}
		}
		try{
			Boolean addRoleMenu=roleService.addRoleChannels(roleId, channelKeys);
			if(addRoleMenu){
				data.setSuccess(true);
				data.setMessage("操作成功");
				LoggerServer.info(ObjectType.ROLE,OperateType.ADDROLECHANNELS, "成功添加角色栏目",null,null,true);
			}else{
				data.setSuccess(false);
				data.setMessage("操作失败");
				LoggerServer.info(ObjectType.ROLE,OperateType.ADDROLECHANNELS, "添加角色栏目操作失败",null,null,false);
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.ROLE,OperateType.ADDROLECHANNELS, "添加角色栏目操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("角色栏目分配失败",e);
		}
		return data.toJSONString();
	}
}
