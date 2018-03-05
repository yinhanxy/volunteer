package com.topstar.volunteer.service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Monitor;
import com.topstar.volunteer.exception.TPSException;

/**
 * 系统监控Service接口
 */
public interface MonitorService extends BaseService<Monitor> {

	/**
	 * 添加系统监控的cpu,内存,硬盘信息
	 * @throws TPSException
	 */
	public boolean addMonitorInfo() throws TPSException;

	/**
	 * 删除系统监控的信息
	 */
	public boolean delMonitorInfo();

	/**
	 * 查询前n条系统监控信息
	 */
	public PageInfo<Monitor> getMonitors();
}
