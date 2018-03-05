package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.VolunteerCertificationDao;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.mapper.VolunteerCertificationMapper;
import com.topstar.volunteer.model.VolunteerView;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class VolunteerCertificationDaoImpl implements VolunteerCertificationDao{

	@Autowired
	private VolunteerCertificationMapper volunteerCertificationMapper;
	
	public PageInfo<VolunteerView> findVolunteerCertByEntity(Volunteer volunteer,String orderBy, int page, int rows){
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> volunteers = volunteerCertificationMapper.findVolunteerCertByEntity(volunteer);
		PageInfo<VolunteerView> pageVolunteer = new PageInfo<VolunteerView>(volunteers);
		return pageVolunteer;
	}
	
	public PageInfo<VolunteerView> findVolunteerWithoutCertByEntity(Volunteer volunteer,String orderBy, int page, int rows){
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> volunteers = volunteerCertificationMapper.findVolunteerWithoutCertByEntity(volunteer);
		PageInfo<VolunteerView> pageVolunteer = new PageInfo<VolunteerView>(volunteers);
		return pageVolunteer;
	}
	
	/**
	 * 根据志愿者ID查询指定志愿者证书信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public VolunteerView findVolunteerCertByVolunteerId(Long volunteerId){
		VolunteerView  volunteerView=volunteerCertificationMapper.findVolunteerCertByVolunteerId(volunteerId);
		if(volunteerView!=null){
			return volunteerView;
		}else{
			return null;
		}
	}
}
