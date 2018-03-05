package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.LoggerBakDao;
import com.topstar.volunteer.dao.LoggerDao;
import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.entity.LoggerBak;
import com.topstar.volunteer.mapper.LoggerBakMapper;

/**
 * 日志记录备份数据库层实现类
 * @author admin
 * 
 */
@Repository
public class LoggerBakDaoImpl extends BaseDaoImpl<LoggerBak> implements LoggerBakDao{
	
	@Autowired
	private LoggerBakMapper loggerBakMapper;
	
	@Autowired
	private LoggerDao loggerDao;

	@Override
	public int addLoggerBak(List<Logger> loggerList) {
		
		return loggerBakMapper.addLoggerBak(loggerList);
	}

}
