package com.topstar.volunteer.web.controller;

import java.util.List;
import java.util.regex.Pattern;

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

import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.RoleService;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.util.secrecy.MD5Util;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;

/**
 * 用户信息管理控制类
 */
@Controller
public class UserInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 跳转到当前用户信息页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userInfo/userInfo.html")
	public String toUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		BaseUser baseUser = ShiroSessionMgr.getLoginUser();
		User user = userService.selectByKey(baseUser.getId());
		List<Role> roles=roleService.findRolesByUserId(user.getId());
		StringBuilder sf = new StringBuilder();
		if (roles!=null && roles.size()!=0) {
			int size = roles.size();
			for(int i = 0 ; i < size;i++){
				Role role = roles.get(i);
				sf.append(role.getRoleName());
				if(i < size - 1){
					sf.append(",");
				}
			}
		}else{
			logger.debug("当前用户没有角色信息");
		}
		map.addAttribute("user", user);
		map.addAttribute("rolesName", sf.toString());
		return "/userInfo/userInfo";
	}

	/**
	 * 跳转到编辑用户页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userInfo/editUserInfo.html")
	public String toEditUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		BaseUser baseUser = ShiroSessionMgr.getLoginUser();
		try {
			User user = userService.selectByKey(baseUser.getId());
			map.addAttribute("user", user);
			LoggerServer.info(ObjectType.USER, OperateType.SHOWUSER,  "获取用户信息成功【参数id值为:"+baseUser.getId()+"】", null, null, true);
		} catch (Exception e) {
			logger.error("获取用户信息出错",e);
			LoggerServer.error(ObjectType.USER, OperateType.SHOWUSER, "获取用户信息出错【参数id值为:"+baseUser.getId()+"】", null, null);
		}
		return "/userInfo/editUserInfo";
	}

	/**
	 * 跳转到修改用户密码页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userInfo/editUserPwd.html")
	public String toEditUserPwd(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		return "/userInfo/editUserPwd";
	}

	/**
	 * 用户的编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userInfo/editUserInfo.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String editUserInfo(HttpServletRequest request, HttpServletResponse response, User user) {
		ResultData data = new ResultData();
		BaseUser crtUser = ShiroSessionMgr.getLoginUser();
		try {
			user = userService.selectByKey(user.getId());
		} catch (Exception e) {
			logger.error("获取用户信息失败",e);
			return ResultData.fail("获取用户信息失败").toJSONString();
		}
		User preUser = user;
		try {
			if (user==null) {
				return ResultData.fail("编辑的用户不存在").toJSONString();
			}
			user.setUserName(crtUser.getUserName());
			user.setNickName(StringTools.FormatStrFromInput(user.getNickName()));
			user.setRealName(StringTools.FormatStrFromInput(user.getRealName()));
			
			List<ValidateMessage> errors =ValidatorUtil.validate(user,Groups.Update.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			int updateUser = userService.updateNotNull(user);
			if (updateUser > 0) {
				data.setSuccess(true);
				data.setMessage("用户修改成功");
				LoggerServer.info(ObjectType.USER, OperateType.EDITUSER, "修改用户信息成功【修改前："+preUser.toString()+",修改后"+user.toString()+"】", user.getId(), user.getUserName(), true);
				return data.toJSONString();
			} else {
				data.setSuccess(true);
				data.setMessage("用户修改失败");
				LoggerServer.error(ObjectType.USER, OperateType.EDITUSER, "用户信息修改失败", user.getId(), user.getUserName());
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("编辑用户操作出现出错", e);
			LoggerServer.error(ObjectType.USER, OperateType.EDITUSER, "编辑用户操作出现出错", user.getId(), user.getUserName());
		}
		return data.toJSONString();
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userInfo/editUserPwd.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String editUserPwd(HttpServletRequest request, HttpServletResponse response) {
		String userPwd = request.getParameter("userPwd");
		String userNewPwd = request.getParameter("userNewPwd");
		String userNewPwdAgain = request.getParameter("userNewPwdAgain");
		Pattern p = Pattern.compile("^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{5,10}$");//5-10字母数字下画线
		ResultData data = new ResultData();
		ValidateMessage message=new ValidateMessage();
		if (StringUtils.isBlank(userPwd)) {
			message.setMessage("旧密码不能为空!");
			message.setPropertyName("userPwd");
			return ResultData.fail(message).toJSONString();
		}
		if (StringUtils.isBlank(userNewPwd)) {
			message.setMessage("新密码不能为空");
			message.setPropertyName("userNewPwd");
			return ResultData.fail(message).toJSONString();
		}
		if (!(p.matcher(userNewPwd).find())) {
			message.setMessage("密码由长度为5-12个字母数字或特殊字符组成!");
			message.setPropertyName("userNewPwd");
			return ResultData.fail(message).toJSONString();
		}
		if (StringUtils.isBlank(userNewPwdAgain)) {
			message.setMessage("请输入重复密码");
			message.setPropertyName("userNewPwdAgain");
			return ResultData.fail(message).toJSONString();
		}
		if (!(p.matcher(userNewPwdAgain).find())) {
			message.setMessage("密码由长度为5-12个字母数字或特殊字符组成!");
			message.setPropertyName("userNewPwdAgain");
			return ResultData.fail(message).toJSONString();
		}
		if (!userNewPwd.equals(userNewPwdAgain)) {
			message.setMessage("重复密码输入不正确");
			message.setPropertyName("userNewPwdAgain");
			return ResultData.fail(message).toJSONString();
		}
		User user=null;
		try {
			BaseUser baseUser = ShiroSessionMgr.getLoginUser();
			user = userService.selectByKey(baseUser.getId());
		} catch (Exception e) {
			logger.error("获取用户信息失败",e);
			return ResultData.fail("获取用户信息失败").toJSONString();
		}
		User preUser =user;
		try {
			boolean result = MD5Util.checkPassword(userPwd, user.getUserPwd());
			if (result) {
				user.setUserPwd(MD5Util.encode(userNewPwd));
				int num = userService.editUserPwd(user.getId(), user.getUserPwd());
				if (num > 0) {
					data.setSuccess(true);
					data.setMessage("密码修改成功");
					LoggerServer.info(ObjectType.USER, OperateType.EDITUSER, "修改密码成功【修改前："+preUser.getUserPwd()+",修改后"+user.getUserPwd()+"】", user.getId(), user.getUserName(), true);
					
					return data.toJSONString();
				}
			} else {
				message.setMessage("输入正确的旧密码");
				message.setPropertyName("userPwd");
				LoggerServer.error(ObjectType.USER, OperateType.EDITUSER, "旧密码输入错误", user.getId(),user.getUserPwd());
				return ResultData.fail(message).toJSONString();
			}
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("修改用户密码操作出现出错", e);
			LoggerServer.error(ObjectType.USER, OperateType.EDITUSER, "修改用户密码操作出现出错", user.getId(), user.getUserName());
		}
		return data.toJSONString();
	}
}
