package com.topstar.volunteer.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerBlack;
import com.topstar.volunteer.model.VolunteerView;

public interface VolunteerBlackDao extends BaseDao<VolunteerBlack>{

	/**
	 * 根据志愿者实体字段的值过滤查询志愿者黑名单的信息列表 
	 * @param volunteerView
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<VolunteerView> findVolunteerBlackByEntity(Volunteer volunteer,String orderBy, int page, int rows);

	/**
	 * 根据志愿者ID查询指定志愿者黑名单信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public VolunteerView findVolunteerBlackByVolunteerId(Long volunteerId);
	
	/**
	 * 查询没有黑名单记录的志愿者信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<VolunteerView> findVolunteersWithoutBlack(Volunteer volunteer,String orderBy, int page, int rows);
	
	/**
	 * 删除指定的志愿者黑名单信息记录
	 * @param volunteerBlackIds
	 * @return
	 */
	int delVolunteerBlacks(Long[] volunteerBlackIds);
	
}
