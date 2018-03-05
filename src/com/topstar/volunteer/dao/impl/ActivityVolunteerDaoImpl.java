package com.topstar.volunteer.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ActivityVolunteerDao;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.entity.ActivityVolunteer;
import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.mapper.ActivityMapper;
import com.topstar.volunteer.mapper.ActivityVolunteerMapper;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.web.controller.ActivityRecordController;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class ActivityVolunteerDaoImpl extends BaseDaoImpl<ActivityVolunteer> implements ActivityVolunteerDao {
	
	private static Logger logger = LoggerFactory.getLogger(ActivityVolunteerDaoImpl.class);
	
	@Autowired
	private ActivityVolunteerMapper activityVolunteerMapper;

	@Override
	public PageInfo<VolunteerView> findActivityVolunteersByActivityId(Long activityId, String orderBy, int currPage, int pageSize) {
		PageHelper.startPage(currPage, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> VolunteerViews = activityVolunteerMapper.selectActivityVolunteersByActivityId(activityId);
		logger.debug("根据活动ID["+activityId+"]查询报名该活动的志愿者信息列表:"+VolunteerViews);
		PageInfo<VolunteerView> pageActivityVolunteers= new PageInfo<VolunteerView>(VolunteerViews);
		return pageActivityVolunteers ;
	}

	@Override
	public PageInfo<VolunteerView> findJoinVolunteersByActivityId(Long activityId, String orderBy, int currPage,
			int pageSize) {
		PageHelper.startPage(currPage, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> VolunteerViews = activityVolunteerMapper.selectJoinVolunteersByActivityId(activityId);
		PageInfo<VolunteerView> pageActivityVolunteers= new PageInfo<VolunteerView>(VolunteerViews);
		return pageActivityVolunteers ;
	}

	@Override
	public PageInfo<VolunteerView> findVolunteersInfoByActivityId(Long activityId, String orderBy, int currPage,
			int pageSize) {
		PageHelper.startPage(currPage, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> VolunteerViews = activityVolunteerMapper.selectVolunteersInfoByActivityId(activityId);
		PageInfo<VolunteerView> pageVolunteersInfo= new PageInfo<VolunteerView>(VolunteerViews);
		return pageVolunteersInfo ;
	}

	@Override
	public PageInfo<VolunteerView> findJoinedActivityByVolunteerId(Long volunteerId, String orderBy, int currPage,
			int pageSize) {
		PageHelper.startPage(currPage, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> VolunteerViews = activityVolunteerMapper.selectJoinedActivityByVolunteerId(volunteerId);
		PageInfo<VolunteerView> pageJoinedActivity= new PageInfo<VolunteerView>(VolunteerViews);
		return pageJoinedActivity ;
	}
	
	public Double selectVolSumHour(Long volunteerId){
		
		return activityVolunteerMapper.selectVolSumHour(volunteerId);
	}
}
