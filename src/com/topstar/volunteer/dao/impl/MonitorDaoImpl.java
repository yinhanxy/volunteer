package com.topstar.volunteer.dao.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.MonitorDao;
import com.topstar.volunteer.entity.Monitor;
import com.topstar.volunteer.mapper.MonitorMapper;
import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class MonitorDaoImpl extends BaseDaoImpl<Monitor> implements MonitorDao {
	
	@Autowired
	private MonitorMapper monitorMapper;

	@Override
	public int addMonitorInfo(Monitor monitor) {
		return monitorMapper.addMonitorInfo(monitor);
	}

	@Override
	public int delMonitorInfo() {
		return monitorMapper.delMonitorInfo();
	}

	@Override
	public PageInfo<Monitor> getMonitors(int size, String orderBy) {
		PageHelper.startPage(1, size);
		if (StringUtils.isNotBlank(orderBy)) {
			OrderByHelper.orderBy(orderBy);
		}
		List<Monitor> list = monitorMapper.getMonitors();
		PageInfo<Monitor> page = new PageInfo<Monitor>(list);
		return page;
	}

}
