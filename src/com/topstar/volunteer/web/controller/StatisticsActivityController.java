package com.topstar.volunteer.web.controller;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 活动统计分析控制器
 */
@Controller
public class StatisticsActivityController {
	
	private static Logger logger = LoggerFactory.getLogger(StatisticsActivityController.class);
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 跳转到活动统计页面
	 * @param request
	 * @param response
	 * @param map
	 * @throws TPSException 
	 * @throws SigarException 
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "/statisticsActivity/list.html")
	public String toStatisticsActivity(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "/statistics/activity/list";
	}
	
	/**
	 * 活动统计信息展示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/statisticsActivity/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String statisticsActivity(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		List<Statistics> activityList=activityService.stsActivityShow();
		List<Statistics> serActList=activityService.getSerAct();
		if (activityList!=null && activityList.size()>0 &&serActList!=null && serActList.size()>0) {
			LoggerServer.info(ObjectType.SERTEAM, OperateType.SHOWSTATISTICSSERVICELIST, "成功读取服务队统计信息", null, null,true);
			data.setSuccess(true);
			data.put("activityList", activityList);
			data.put("serActList", serActList);
			return data.toJSONString();
		} else {
			data.setSuccess(false);
			data.setMessage("读取活动统计信息失败");
			logger.error("读取活动统计信息失败");
			LoggerServer.error(ObjectType.SERTEAM, OperateType.SHOWSTATISTICSSERVICELIST, "读取服务队统计信息失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 业务机构的信息列表("2017年","5个")
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activityNumAndYear/list.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String activityNumAndYear(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String orgId = ActionContext.getActionContext().getParameterAsString("orgId");
		Long key = null;
		try {
			key = Long.valueOf(orgId);
		} catch (Exception e) {
			logger.error("组织机构信息的参数id:{}不合法", orgId, e);
		}
		if (currPage == 0) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		Statistics statistics = new Statistics();
		statistics.setOrgId(key);
		try{
			PageInfo<Statistics> pages =activityService.selActivityByYear(statistics, currPage, pageSize);
			LoggerServer.info(ObjectType.ACTIVE, OperateType.SHOWSTATISTICSACTIVITYLIST, "成功查看组织机构活动信息", null, null,true);
			data.setSuccess(true);
			data.put("page", pages);
			return data.toJSONString();
		}catch (Exception e) {
			logger.error("查看组织机构信息的列表出现错误",e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			LoggerServer.error(ObjectType.ACTIVE, OperateType.SHOWSTATISTICSACTIVITYLIST, "查看组织机构活动信息失败", null, null);
		}
		return data.toJSONString();
	}
}
