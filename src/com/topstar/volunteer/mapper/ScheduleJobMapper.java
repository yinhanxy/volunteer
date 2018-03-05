package com.topstar.volunteer.mapper;

import java.util.Map;

import com.topstar.volunteer.entity.ScheduleJob;
import com.topstar.volunteer.util.BaseMapper;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob>{
	
	public int updateBatch(Map<String, Object> map);

}
