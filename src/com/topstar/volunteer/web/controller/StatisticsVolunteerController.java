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
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.service.StarEvaluateService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 志愿者统计分析控制器
 */
@Controller
public class StatisticsVolunteerController {
	
	private static Logger logger = LoggerFactory.getLogger(StatisticsVolunteerController.class);
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private StarEvaluateService starEvaluateService;
	
	/**
	 * 跳转到志愿者统计页面
	 * @param request
	 * @param response
	 * @param map
	 * @throws TPSException 
	 * @throws SigarException 
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "/statisticsVol/list.html")
	public String toStSVolunteer(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		List<Statistics> statistics=volunteerService.getVolInfoList();
		if (statistics!=null) {
			map.addAttribute("statisticsList", statistics);
		}else{
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWSTATISTICSVOLLIST, "读取统计信息失败", null, null);
		}
		return "/statistics/volunteer/list";
	}
	
	/**
	 * 统计页面信息展示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/statisticsVol/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String StSVolunteer(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		List<Statistics> volList=volunteerService.statisticsShow();
		if (volList!=null && volList.size()>0) {
			data.setSuccess(true);
			data.put("volList", volList);
		}
		List<Statistics> starList=starEvaluateService.starStatisticsShow();
		if (starList!=null && starList.size()>0) {
			LoggerServer.info(ObjectType.VOLUNTEER, OperateType.SHOWSTATISTICSVOLLIST, "成功读取统计信息", null, null,true);
			data.setSuccess(true);
			data.put("starList", starList);
		} 
		if (starList==null && volList==null){
			data.setSuccess(false);
			data.setMessage("读取统计信息失败");
			logger.error("读取统计信息失败");
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWSTATISTICSVOLLIST, "读取统计信息失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到业务机构信息页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/TreatAgency/list.html")
	public String toTreatAgency(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		String orgId = ActionContext.getActionContext().getParameterAsString("orgId");
		Long key = null;
		try {
			key = Long.valueOf(orgId);
		} catch (Exception e) {
			logger.error("组织机构的参数id:{}不合法", orgId, e);
		}
		map.addAttribute("orgId", key);
		Statistics statistics=volunteerService.getVolInfo();
		if (statistics!=null) {
			map.addAttribute("statistics", statistics);
			LoggerServer.info(ObjectType.VOLUNTEER, OperateType.SHOWSTATISTICSVOLLIST, "成功读取统计信息", null, null,true);
		}else{
			LoggerServer.error(ObjectType.VOLUNTEER, OperateType.SHOWSTATISTICSVOLLIST, "读取统计信息失败", null, null);
		}
		return "/statistics/volunteer/treatAgency";
	}
	
	/**
	 * 业务机构志愿者信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/TreatAgency/list.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String treatAgencyList(HttpServletRequest request, HttpServletResponse response) {
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String orgId = ActionContext.getActionContext().getParameterAsString("orgId");
		String orderBy = "";
		Long key = null;
		try {
			key = Long.valueOf(orgId);
		} catch (Exception e) {
			logger.error("组织机构的参数id:{}不合法", orgId, e);
		}
		if (currPage == 0) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		ResultData data = new ResultData();
		try {
			PageInfo<Volunteer> pageInfo = volunteerService.getVols(key, orderBy, currPage, pageSize);
			data.setSuccess(true);
			logger.info("成功分页查询志愿者信息,pageIndex:{},pageSize:{}", currPage, pageSize,
					null, null, true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者信息出错,pageIndex:{},pageSize:{}", currPage, pageSize,
					null, null, e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 得到志愿者统计信息(名称,注册时间,服务时长,活动次数)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/GetVolStatis/list.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String getVolStatis(HttpServletRequest request, HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String orderBy = "";
		if (currPage == 0) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		ResultData data = new ResultData();
		try {
			PageInfo<Statistics> pageInfo = volunteerService.getVolStatis( orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询志愿者统计信息出错,pageIndex:{},pageSize:{}", currPage, pageSize,
					null, null, e);
		}
		return data.toJSONString();
	}
	
}
