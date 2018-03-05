package com.topstar.volunteer.service;

import com.topstar.volunteer.entity.LoggerBak;

public interface LoggerBakService extends BaseService<LoggerBak>{
	
	/**
	 * 将logger表中3个月之前的数据添加至loggerBak
	 * @return
	 */
	public int addLoggerBak();
}
