package com.topstar.volunteer.dao;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Monitor;

public interface MonitorDao extends BaseDao<Monitor> {

	/**
	 * 添加系统监控的cpu,内存,硬盘信息
	 * @param monitor
	 * @return
	 */
	public int addMonitorInfo(Monitor monitor);

	/**
	 * 删除系统监控的信息
	 * @return
	 */
	public int delMonitorInfo();

	/**
	 * 查询前n条系统监控信息
	 * @param size 设置查询的条数
	 * @param orderBy 设置查询的条件
	 * @return
	 */
	public PageInfo<Monitor> getMonitors(int size, String orderBy);
}
