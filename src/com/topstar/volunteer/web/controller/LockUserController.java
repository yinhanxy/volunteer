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
import com.topstar.volunteer.model.LockUser;
import com.topstar.volunteer.service.LockUserService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 登录锁定用户管理控制器
 * @author Administrator
 */
@Controller
public class LockUserController {
	
	private static Logger logger = LoggerFactory.getLogger(LockUserController.class);

	@Autowired
	private LockUserService lockUserService;
	
	/**
	 * 锁定用户列表页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/lockuser/list.html")
	public String toList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "/sys/lockuser/list";
	}
	
	/**
	 * 锁定用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lockuser/list.do",produces="text/plain;charset=UTF-8")
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
			PageInfo<LockUser> pageInfo = lockUserService.getList(userName, currPage, pageSize);
			data.setSuccess(true);
			if(pageInfo != null){
				data.put("page",pageInfo);
			}
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("分页显示锁定的用户信息出错");
			logger.error("分页显示锁定的用户信息出错",e);
			LoggerServer.error(ObjectType.LOCKUSER, OperateType.SHOWLOCKUSERLIST, "分页显示锁定的用户信息出错,"+e.getMessage(), null, "");
		}
		return data.toJSONString();
	}
	
	/**
	 * 解锁用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lockuser/unlock.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String unlock(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String userName = ActionContext.getActionContext().getParameterAsString("userNames");
		List<String> userNameList = new ArrayList<String>();
		if(StringUtils.isNotBlank(userName)){
			try {
				String[] userNames = userName.split(",");
				if(userNames != null && userNames.length > 0){
					for(String s : userNames){
						if(StringUtils.isNotBlank(s)){
							userNameList.add(s);
						}
					}
				}
			} catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("解锁用户出错");
				logger.error("解锁用户出错",e);
				return data.toJSONString();
			}
		}
		try {
			if(userNameList != null && !userNameList.isEmpty()){
				int row = lockUserService.unlockUser(userNameList);
				data.put("row", row);
				if(row == userNameList.size()){
					data.setMessage("解锁用户成功！");
				}
			}
			data.setSuccess(true);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("解锁用户出错");
			logger.error("解锁用户出错",e);
		}
		return data.toJSONString();
	}
}
