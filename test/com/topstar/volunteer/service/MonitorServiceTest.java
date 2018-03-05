package com.topstar.volunteer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.util.ConfigUtils;

public class MonitorServiceTest extends BaseTest{
	@Autowired
	private MonitorService monitorService;
	@Test
	public void addMonitor() throws TPSException{
		String res=ConfigUtils.getConfigContentByName("SYSTEM_MONITOR");
		boolean result=res.equals("true");
		
		monitorService.addMonitorInfo();
		System.out.println(result);
		}
	
}
