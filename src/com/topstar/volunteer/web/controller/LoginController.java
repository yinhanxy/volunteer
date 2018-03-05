package com.topstar.volunteer.web.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.shiro.session.ShiroSession;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.VerifyCodeUtils;

@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login.html")
	public String index(HttpServletRequest request){
		if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
		return "/login";
	}
	
	/**
	 * 获取登录的用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getLoginUser.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getLoginUser(HttpServletRequest request, HttpServletResponse response)  {
		ShiroSession msession = ShiroSessionMgr.getMSessionBySessionId(request.getSession().getId());
		ResultData data = new ResultData(true);
		if(msession != null){
			BaseUser user = msession.getUser();
			if(user != null){
				data.put("user",user);
			}
		}
		return data.toJSONString();
	}
	

	/**
	 * 登录请求
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String login(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			data.setSuccess(false);
			data.put("message", "用户名或密码不能为空");
			return data.toJSONString();
		}
		boolean isValidate = true;
		if(isValidate){
			String validateCode = request.getParameter("validateCode");
			if(StringUtils.isBlank(validateCode)){
				data.setSuccess(false);
				data.put("message", "验证码不能为空");
				return data.toJSONString();
			}
			String codeType = request.getParameter("codeType");
			boolean isValidata = VerifyCodeUtils.validateVerifyCode(request, validateCode, codeType);
			if (!isValidata) {
				data.setSuccess(false);
				data.put("message", "验证码错误或已过期");
				return data.toJSONString();
			}
		}
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			subject.login(token);
			boolean isAuthenticated = subject.isAuthenticated();
			if(isAuthenticated){
				data.setSuccess(true);
				User user = userService.findByUserName(userName);
				user.setUserPwd(null);
				userService.addUserToCache(user);
				
				BaseUser baseUser = (BaseUser) subject.getPrincipal();
				Set<String> roleNames = userService.findRoleNames(baseUser.getId());
				if(roleNames != null && !roleNames.isEmpty()){
					baseUser.setRoles(roleNames);
				}
				Set<String> permissions = userService.findPermissions(baseUser.getId());
		        if(permissions != null && !permissions.isEmpty()){
		        	baseUser.setPermissions(permissions);
				}
		        ShiroSessionMgr.createSession(baseUser);
			}
		} catch (UnknownAccountException e) {
			//帐号不存在的异常
			logger.error(userName+"用户登录失败,帐号不存在",e);
			data.setSuccess(false);
			data.setMessage("用户名或密码错误");
        } catch (DisabledAccountException e) {
        	//帐号未启用的异常
        	logger.error(userName+"用户登录失败，帐号未启用",e);
			data.setSuccess(false);
			data.setMessage("帐号已禁用");
        } catch (IncorrectCredentialsException e) {
        	//密码错误的异常
        	logger.error(userName+"用户登录失败,密码错误",e);
			data.setSuccess(false);
			data.setMessage("用户名或密码错误");
        }catch(ExcessiveAttemptsException e){
        	//密码错误五次的异常
        	logger.error(userName+"用户登录失败,密码已错误五次",e);
			data.setSuccess(false);
			data.setMessage("由于密码错误次数过多，帐号已被锁定，请联系系统管理员解锁");
        }catch (Exception e) {
			logger.error(userName+"用户登录失败",e);
			data.setSuccess(false);
			data.setMessage("用户名或密码错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	@RequestMapping({"/logout.html"})
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if(subject != null){
			String sessionId = (String) subject.getSession().getId();
			BaseUser baseUser = (BaseUser) subject.getPrincipal();
			try {
				if(baseUser != null){
					userService.removeUserToCache(baseUser.getId());
					ShiroSessionMgr.remove(sessionId,false);
					logger.debug("[userId={}]用户退出",baseUser.getId());
				}
				subject.logout();
			} catch (Exception e) {
				logger.error("用户[{}]退出登录时出错",baseUser,e);
			}
		}
		return "redirect:/index.html";
	}
}
