package com.topstar.volunteer.service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.VolunteerView;

public interface VolunteerCertficationService{
	
	
	/**
	 * 根据志愿者实体字段信息过滤得到志愿者证书的年检信息集合
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> getVolunteerCertsByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 根据志愿者实体字段信息过滤得到志愿者证书的年检信息集合
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> getVolunteerWithoutCertByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 根据志愿者ID查询指定志愿者证书信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public VolunteerView getVolunteerCertByVolunteerId(Long volunteerId);
	
}
