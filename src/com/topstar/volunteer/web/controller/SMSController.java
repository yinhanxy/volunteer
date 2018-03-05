package com.topstar.volunteer.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.SMS;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.service.SMSService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 短信验证码
 */
@Controller
public class SMSController {

	private static Logger logger = LoggerFactory.getLogger(SMSController.class);
	
	@Autowired
	private SMSService smsService;
	
	/**
	 * 跳转到短信列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sms/list.html")
	public String toSMSList(HttpServletRequest request,HttpServletResponse response){
		
		return "sys/sms/list";
	}
	
	/**
	 * 获取短信验证码列表
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping(value = "/sms/list.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String SMSList(HttpServletRequest request,HttpServletResponse reponse){
		ResultData data = new ResultData();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		if (currPage == 0) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		try {
			PageInfo<SMS> pageInfo = smsService.getSMSLists(null, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询后台短信验证码出错,pageIndex:{},pageSize:{}", currPage, pageSize, e);
			LoggerServer.error(ObjectType.SMS, OperateType.SHOWSMSLIST, "查看短信验证码列表出错", null, null);
		}
		return data.toJSONString();
	};
}
