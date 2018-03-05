package com.topstar.volunteer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.dao.LoggerBakDao;
import com.topstar.volunteer.dao.LoggerDao;
import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.entity.LoggerBak;
import com.topstar.volunteer.service.LoggerBakService;

@Service("loggerBakService")
public class LoggerBakServiceImpl extends BaseServiceImpl<LoggerBak> implements LoggerBakService {
	
	@Autowired
	private LoggerDao loggerDao;
	
	@Autowired
	LoggerBakDao loggerBakDao;

	@Override
	public int addLoggerBak() {
		int res = 0;
		List<Logger> loggerList  =	loggerDao.getLoggersThree();
		if (loggerList!=null && loggerList.size()>0) {
			res =loggerBakDao.addLoggerBak(loggerList);
		}
		return res;
	}
	
}
