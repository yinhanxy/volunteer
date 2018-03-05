package com.topstar.volunteer.dao;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Logger;
/**
 * 记录日志数据库操作层接口类
 * @author admin
 *
 */
public interface LoggerDao extends BaseDao<Logger>{
	
	/**
	 * 记录日志信息
	 * @param logType 日志类型
	 * @param objectType 对象类型
	 * @param operateType 操作类型
	 * @param message 日志信息
	 * @param success 操作是否成功
	 */
	public void log(Logger logger);
	
	public PageInfo<Logger> selectLogger(Logger logger,Date startTime,Date endTime,int pageIndex,int pageSize);
	
	/**
	 * 查询3个月前的数据
	 * @return
	 */
	public List<Logger> getLoggersThree();
	
	/**
	 * 删除3个月前的数据
	 * @return
	 */
	public int delLoggersThree();
}
