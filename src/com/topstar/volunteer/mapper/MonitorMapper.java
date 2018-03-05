package com.topstar.volunteer.mapper;

import java.util.List;

import com.topstar.volunteer.entity.Monitor;
import com.topstar.volunteer.util.BaseMapper;

public interface MonitorMapper extends BaseMapper<Monitor> {

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
	 * @return
	 */
	public List<Monitor> getMonitors();
}
