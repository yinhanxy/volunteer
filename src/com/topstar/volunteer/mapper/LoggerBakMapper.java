package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.entity.LoggerBak;
import com.topstar.volunteer.util.BaseMapper;

public interface LoggerBakMapper extends BaseMapper<LoggerBak> {
	
	/**
	 * 将logger表中3个月之前的数据添加至loggerBak
	 * @return
	 */
	int addLoggerBak(@Param("loggerList") List<Logger> loggerList);
}