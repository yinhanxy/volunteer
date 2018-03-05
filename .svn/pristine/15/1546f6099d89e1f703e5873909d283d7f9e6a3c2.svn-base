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
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.service.SerTeamService;
import com.topstar.volunteer.service.TrRecordService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 服务队统计控制器
 */
@Controller
public class StatisticsServiceController {
	
	private static Logger logger = LoggerFactory.getLogger(StatisticsServiceController.class);

	@Autowired
	private SerTeamService serTeamService;
	
	@Autowired
	private TrRecordService trRecordService;
	
	/**
	 * 跳转到服务队统计页面
	 * @param request
	 * @param response
	 * @param map
	 * @throws TPSException 
	 * @throws SigarException 
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "/statisticsService/list.html")
	public String toStatisticsService(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "/statistics/serteam/list";
	}
	
	/**
	 * 服务队统计信息展示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/StatisticsService/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String StatisticsService(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		List<Statistics> serTeamList=serTeamService.stsServiceShow();
		List<Statistics> serNameHourList=serTeamService.getNameAndHour();
		if (serTeamList!=null && serTeamList.size()>0 &&serNameHourList!=null && serNameHourList.size()>0) {
			data.setSuccess(true);
			LoggerServer.info(ObjectType.SERTEAM, OperateType.SHOWSTATISTICSSERVICELIST, "成功读取服务队统计信息", null, null,true);
			data.put("serTeamList", serTeamList);
			data.put("serNameHourList", serNameHourList);
			return data.toJSONString();
		} else {
			data.setSuccess(false);
			data.setMessage("读取服务队统计信息失败");
			logger.error("读取服务队统计信息失败");
			LoggerServer.error(ObjectType.SERTEAM, OperateType.SHOWSTATISTICSSERVICELIST, "读取服务队统计信息失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 得到每年的培训数量 如："2017","4"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/GetTrRecordByYear/list.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String selTrRecordByYear(HttpServletRequest request, HttpServletResponse response){
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
			PageInfo<Statistics> pageInfo = trRecordService.selTrRecordByYear(orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询培训记录统计信息出错,pageIndex:{},pageSize:{}", currPage, pageSize,
					null, null, e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 查询培训统计信息,如：(培训名称,参加培训人数,培训时间)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/GetTrRecordStatis/list.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String getTrRecordStatis(HttpServletRequest request, HttpServletResponse response){
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
			PageInfo<TrRecord> pageInfo = trRecordService.getTrRecordStatis(orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询培训记录统计信息出错,pageIndex:{},pageSize:{}", currPage, pageSize,
					null, null, e);
		}
		return data.toJSONString();
	}
}
