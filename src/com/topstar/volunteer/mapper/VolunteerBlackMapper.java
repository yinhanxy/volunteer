package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerBlack;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.BaseMapper;

public interface VolunteerBlackMapper extends BaseMapper<VolunteerBlack>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者黑名单的信息列表 
	 * @param volunteerView
	 * @return
	 */
	List<VolunteerView> findVolunteerBlackByEntity(@Param("volunteer")Volunteer volunteer);
	
	/**
	 * 根据志愿者ID查询指定志愿者黑名单组合信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	VolunteerView findVolunteerBlackByVolunteerId(@Param("volunteerId")Long volunteerId);
	
	/**
	 * 查询没有黑名单记录的志愿者信息列表
	 * @return
	 */
	List<VolunteerView> findVolunteersWithoutBlack(@Param("volunteer") Volunteer volunteer);
	
	/**
	 * 删除指定的志愿者黑名单信息记录
	 * @param volunteerBlackIds
	 * @return
	 */
	int delVolunteerBlacks(Long[] volunteerBlackIds);
}