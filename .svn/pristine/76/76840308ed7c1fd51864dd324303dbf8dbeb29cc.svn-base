package com.topstar.volunteer.dao;

import java.util.List;

import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.entity.LoggerBak;
/**
 * 记录日志备份数据库操作层接口类
 * @author admin
 *
 */
public interface LoggerBakDao extends BaseDao<LoggerBak>{

	/**
	 * 将logger表中3个月之前的数据添加至loggerBak
	 * @return
	 */
	public int addLoggerBak(List<Logger> loggerList);
}
