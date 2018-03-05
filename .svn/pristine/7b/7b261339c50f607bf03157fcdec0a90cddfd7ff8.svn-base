package com.topstar.volunteer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.VolunteerView;

public interface VolunteerCertificationDao{
	
	/**
	 * 通过志愿者实体字段过滤分页查询志愿者证书年检的信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<VolunteerView> findVolunteerCertByEntity(Volunteer volunteer,String orderBy, int page, int rows);
	
	/**
	 * 通过志愿者实体字段过滤分页查询没有证书的志愿者的信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<VolunteerView> findVolunteerWithoutCertByEntity(Volunteer volunteer,String orderBy, int page, int rows);

	/**
	 * 根据志愿者ID查询指定志愿者证书信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public VolunteerView findVolunteerCertByVolunteerId(Long volunteerId);

}
