package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.VolunteerBlackDao;
import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerBlack;
import com.topstar.volunteer.mapper.VolunteerBlackMapper;
import com.topstar.volunteer.model.VolunteerView;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class VolunteerBlackDaoImpl extends BaseDaoImpl<VolunteerBlack> implements VolunteerBlackDao {
	
	@Autowired
	private VolunteerBlackMapper volunteerBlackMapper;
	
	@Override
	public PageInfo<VolunteerView> findVolunteerBlackByEntity(Volunteer volunteer, String orderBy, int page,
			int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> volunteers = volunteerBlackMapper.findVolunteerBlackByEntity(volunteer);
		PageInfo<VolunteerView> pageVolunteer = new PageInfo<VolunteerView>(volunteers);
		return pageVolunteer;
	}

	@Override
	public VolunteerView findVolunteerBlackByVolunteerId(Long volunteerId) {
		VolunteerView  volunteerView=volunteerBlackMapper.findVolunteerBlackByVolunteerId(volunteerId);
		if(volunteerView!=null){
			return volunteerView;
		}else{
			return null;
		}
	}

	@Override
	public PageInfo<VolunteerView> findVolunteersWithoutBlack(Volunteer volunteer, String orderBy, int page, int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> volunteers = volunteerBlackMapper.findVolunteersWithoutBlack(volunteer);
		PageInfo<VolunteerView> pageVolunteer = new PageInfo<VolunteerView>(volunteers);
		return pageVolunteer;
	}

	@Override
	public int delVolunteerBlacks(Long[] volunteerBlackIds) {
		return volunteerBlackMapper.delVolunteerBlacks(volunteerBlackIds);
	}
	
}
