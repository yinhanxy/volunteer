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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Monitor;
import com.topstar.volunteer.entity.SigarInfoEntity;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.service.MonitorService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.SigarUtils;

/**
 * 系统监控控制器
 */
@Controller
public class MonitorController {
	
	private static Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Autowired
	private MonitorService monitorService;
	
	/**
	 * 跳转到系统监控页面
	 * @param request
	 * @param response
	 * @param map
	 * @throws TPSException 
	 * @throws SigarException 
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "/monitor/view.html")
	public String toSysMonitor(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		List<SigarInfoEntity> sysInfoList = null;
		try {
			sysInfoList = SigarUtils.getSysInfo();
			map.addAttribute("sysInfoList", sysInfoList);
		} catch (TPSException e) {
			logger.error("获取服务器信息列表出现内部错误",sysInfoList,e);
		}
		return "/sys/monitor/view";
	}
	
	/**
	 * 获取cpu,磁盘,内存使用率
	 * @param request
	 * @param response
	 * @throws TPSException 
	 * @throws SigarException
	 */
	@RequestMapping(value = "/monitor/Perc.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String getPerc(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		//CPU使用率
		SigarInfoEntity cpu;
		//磁盘使用率
		SigarInfoEntity file;
		//内存使用率
		SigarInfoEntity memory;
		try {
			cpu = SigarUtils.getCpuPerc();
			data.put("cpuPerc", cpu);
			file = SigarUtils.getFilePerc();
			data.put("filePerc", file);
			memory = SigarUtils.getMemoryPerc();
			data.put("memoryPerc", memory);
			data.setSuccess(true);
		} catch (TPSException e) {
			logger.error("获取使用率出现内部错误",e);
			data.setSuccess(false);
			data.setMessage("获取使用率出现内部错误");
		}
		//系统监控折线图信息
		PageInfo<Monitor> monitorList= monitorService.getMonitors();
		data.put("monitorList", monitorList);
		return data.toJSONString();
	}
}
