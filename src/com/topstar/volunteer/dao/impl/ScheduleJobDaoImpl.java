package com.topstar.volunteer.dao.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topstar.volunteer.dao.ScheduleJobDao;
import com.topstar.volunteer.entity.ScheduleJob;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.mapper.ScheduleJobMapper;

@Component
public class ScheduleJobDaoImpl extends BaseDaoImpl<ScheduleJob> implements ScheduleJobDao {
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;

	@Override
	public int updateBatch(Map<String, Object> map) {
		return scheduleJobMapper.updateBatch(map);
	}

	@Override
	public boolean exitJobName(String jobName) throws TPSException {
		if(StringUtils.isNotBlank(jobName)){
			LinkedHashMap<String,Object> param=new LinkedHashMap<String,Object>();
			String sql="SELECT COUNT(*) FROM SCHEDULE_JOB WHERE JOB_NAME=?";
			param.put("JOBNAME", jobName);
			return sqlExecuteIntQuery(sql, param)>0;
		}
		return true;
	}


}
