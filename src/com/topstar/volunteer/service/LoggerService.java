package com.topstar.volunteer.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.model.LoggerView;

public interface LoggerService extends BaseService<Logger>{
	
	/**
	 * 根据编号查找日志信息
	 * @param id
	 * @return
	 */
	public Logger findById(long id);
	
	/**
	 * 查询日志信息
	 * @param logger 日志实体类
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param pageNo 当前页码
	 * @param pageSize 每页数据条数
	 * @return
	 */
	public PageInfo<LoggerView> selectLogger(Logger logger,Date startTime,Date endTime,int pageNo,int pageSize);
	
	public LoggerView findLoggerViewById(long loggerId);
	
	/**
	 * 删除3个月前的数据
	 * @return
	 */
	public int delLoggersThree();
}
