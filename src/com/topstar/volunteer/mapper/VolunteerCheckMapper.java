package com.topstar.volunteer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerCheck;
import com.topstar.volunteer.util.BaseMapper;

public interface VolunteerCheckMapper extends BaseMapper<VolunteerCheck>{
	
	/**
	 * 查找志愿者对应的审核信息
	 * @param params
	 * @return
	 */
	public VolunteerCheck findVolunteerCheckByVolunteerId(@Param("volunteerId")Long volunteerId);
}