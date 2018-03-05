package com.topstar.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.MonitorDao;
import com.topstar.volunteer.entity.Monitor;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.service.MonitorService;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.SigarUtils;

@Service
public class MonitorServiceImpl extends BaseServiceImpl<Monitor> implements MonitorService{
	
	@Autowired
	private MonitorDao monitordao;
	
	@Override
	public boolean addMonitorInfo() throws TPSException{
		try {
			String res=ConfigUtils.getConfigContentByName("SYSTEM_MONITOR");
			if (res!=null && res.equals("true")) {
				Monitor monitor=new Monitor();
		     	monitor.setCpurate(SigarUtils.getCpuPercNum());
		     	monitor.setDiskrate(SigarUtils.getFilePercNum());
		     	monitor.setMemoryrate(SigarUtils.getMemoryPercNum());
		     	monitor.setCrTime(new Timestamp(new Date().getTime()));
				int mir=monitordao.addMonitorInfo(monitor);
				if (mir>0) {
					return true;
				}
			}
		} catch (Exception e) {
			throw new TPSException("更新的角色信息参数不合法",e);
		}
     	return false;
	}
	
	@Override
	public boolean delMonitorInfo() {
		int mot=monitordao.delMonitorInfo();
		if (mot>0) {
			return true;
		}
		return false;
	}

	@Override
	public PageInfo<Monitor> getMonitors() {
		int size=7;
		String orderBy="cr_Time desc";
		PageInfo<Monitor> list = monitordao.getMonitors(size, orderBy);
		return list;
	}

}
