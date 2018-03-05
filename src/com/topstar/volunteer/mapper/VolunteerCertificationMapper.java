package com.topstar.volunteer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.BaseMapper;

public interface VolunteerCertificationMapper{
	

	/**
	 * 通过志愿者实体字段过滤查询志愿者证书年检的信息列表
	 * @param volunteer
	 * @return
	 */
	List<VolunteerView> findVolunteerCertByEntity(@Param("volunteer")Volunteer volunteer);
	
	/**
	 * 通过志愿者实体字段过滤查询没有证书的志愿者的信息列表
	 * @param volunteer
	 * @return
	 */
	List<VolunteerView> findVolunteerWithoutCertByEntity(@Param("volunteer")Volunteer volunteer);
	
	/**
	 * 根据志愿者ID查询指定志愿者证书信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	VolunteerView findVolunteerCertByVolunteerId(@Param("volunteerId")Long volunteerId);
}