package com.topstar.volunteer.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.VolunteerCheckDao;
import com.topstar.volunteer.entity.VolunteerCheck;
import com.topstar.volunteer.mapper.VolunteerCheckMapper;


@Repository
public class VolunteerCheckDaoImpl extends BaseDaoImpl<VolunteerCheck> implements VolunteerCheckDao{

	@Autowired
	private VolunteerCheckMapper volunteerCheckMapper;

	@Override
	public VolunteerCheck findVolunteerCheckByVolunteerId(Long volunteerId) {
		VolunteerCheck volunteerCheck=volunteerCheckMapper.findVolunteerCheckByVolunteerId(volunteerId);
		if(volunteerCheck!=null){
			return volunteerCheck;
		}
		return null;
	}
	
}
