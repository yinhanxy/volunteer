package com.topstar.volunteer.web.controller;

import java.util.ArrayList;
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
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.LoginUser;
import com.topstar.volunteer.service.LoginUserService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 在线用户管理控制类
 * @author Administrator
 *
 */
@Controller
public class LoginManageController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginManageController.class);
	
	@Autowired
	private LoginUserService loginUserService;

	/**
	 * 在线用户列表页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/loginuser/list.html")
	public String toList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "/sys/loginuser/list";
	}
	
	/**
	 * 在线用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/loginuser/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String userList(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String userName = ActionContext.getActionContext().getParameterAsString("userName");
		
		if(currPage <= 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		try {
			PageInfo<LoginUser> pageInfo = loginUserService.getList(userName, currPage, pageSize);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("分页显示在线的用户信息出错");
			logger.error("分页显示在线的用户信息出错",e);
			LoggerServer.error(ObjectType.LOGINUSER, OperateType.SHOWLOGINUSERLIST, "分页显示在线的用户信息出错,"+e.getMessage(), null, "");
		}
		return data.toJSONString();
	}
	
	/**
	 * 强制下线
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/loginuser/logout.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String logout(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String id = ActionContext.getActionContext().getParameterAsString("ids");
		List<Long> userIds = new ArrayList<Long>();
		if(StringUtils.isNotBlank(id)){
			try {
				String[] ids = id.split(",");
				if(ids != null && ids.length > 0){
					for(String s : ids){
						Long userId = Long.parseLong(s);
						userIds.add(userId);
					}
				}
			} catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("强制下线用户出错");
				logger.error("强制下线用户出错",e);
				return data.toJSONString();
			}
		}
		try {
			if(userIds != null && !userIds.isEmpty()){
				int row = loginUserService.logoutUser(userIds);
				data.put("row", row);
				if(row < userIds.size()){
					data.setMessage("不能强制让自己下线，请执行退出登录操作");
				}else{
					data.setMessage("强制下线用户成功");
				}
			}
			data.setSuccess(true);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("强制下线用户出错");
			logger.error("强制下线用户出错",e);
		}
		return data.toJSONString();
	}
	
}
