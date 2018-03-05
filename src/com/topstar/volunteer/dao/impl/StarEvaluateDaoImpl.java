package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.StarEvaluateDao;
import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.mapper.StarEvaluateMapper;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class StarEvaluateDaoImpl extends BaseDaoImpl<StarEvaluate> implements StarEvaluateDao {
	
	@Autowired
	private StarEvaluateMapper starEvaluateMapper;
	
	@Override
	public PageInfo<VolunteerView> findVolunteerStarByEntity(Volunteer volunteer, String orderBy, int page,
			int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> volunteers = starEvaluateMapper.findVolunteerStarByEntity(volunteer);
		PageInfo<VolunteerView> pageVolunteer = new PageInfo<VolunteerView>(volunteers);
		return pageVolunteer;
	}

	@Override
	public List<VolunteerView> findLatestVolunteerStarByVolunteerId(Long volunteerId) {
		List<VolunteerView>  volunteerView=starEvaluateMapper.findLatestVolunteerStarByVolunteerId(volunteerId);
		if(volunteerView!=null && !volunteerView.isEmpty()){
			return volunteerView;
		}else{
			return null;
		}
	}
	
	@Override
	public List<VolunteerView> findAllVolunteerStarByVolunteerId(Long volunteerId) {
		List<VolunteerView>  volunteerView=starEvaluateMapper.findAllVolunteerStarByVolunteerId(volunteerId);
		if(volunteerView!=null && !volunteerView.isEmpty()){
			return volunteerView;
		}else{
			return null;
		}
	}

	@Override
	public List<Statistics> getStarByorgId(Statistics statistics) {
		return starEvaluateMapper.getStarByorgId(statistics);
	}
	
	@Override
	public VolunteerView findStarEvaluateByStarEvaluateId(Long starEvaluateId){
		VolunteerView  volunteerView=starEvaluateMapper.findStarEvaluateByStarEvaluateId(starEvaluateId);
		if(volunteerView!=null){
			return volunteerView;
		}else{
			return null;
		}
	}

	@Override
	public List<VolunteerView> findVolunteerServiceHoursAndStar() {
		List<VolunteerView>  volunteerViews=starEvaluateMapper.selectVolunteerServiceHoursAndStar();
		if(volunteerViews!=null && !volunteerViews.isEmpty()){
			return volunteerViews;
		}else{
			return null;
		}
	}
	
}
