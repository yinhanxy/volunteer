package com.topstar.volunteer.dao;

import java.util.Map;

import com.topstar.volunteer.entity.ScheduleJob;
import com.topstar.volunteer.exception.TPSException;

public interface ScheduleJobDao extends BaseDao<ScheduleJob> {
	
	/**
	 * 批量更新状态
	 */
	public int updateBatch(Map<String, Object> map);
	
	public  boolean exitJobName(String jobName) throws TPSException;
	
}
