package com.topstar.volunteer.dao.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ActivityDao;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.mapper.ActivityMapper;
import com.topstar.volunteer.model.Statistics;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements ActivityDao {
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Override
	public PageInfo<Activity> findByEntity(Activity activity, String orderBy, int pageIndex,
			int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Activity> activitys = activityMapper.findByEntity(activity);
		PageInfo<Activity> pageActivity = new PageInfo<Activity>(activitys);
		return pageActivity;
	}

	@Override
	public List<Statistics> getActivityArea(Statistics statistics) {
		return activityMapper.getActivityArea(statistics);
	}

	@Override
	public List<Statistics> getActivitySer(Statistics statistics) {
		return activityMapper.getActivitySer(statistics);
	}

	@Override
	public PageInfo<Statistics> selActivityByYear(Statistics statistics, String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Statistics> lists=activityMapper.selActivityByYear(statistics);
		PageInfo<Statistics> pages=new PageInfo<Statistics>(lists);
		return pages;
	}
	
	@Override
	public PageInfo<Activity> findDoingAndCompletedByEntity(Activity activity, String orderBy, int pageIndex,
			int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Activity> activitys = activityMapper.findDoingAndCompletedByEntity(activity);
		PageInfo<Activity> pageActivity = new PageInfo<Activity>(activitys);
		return pageActivity;
	}
	

	@Override
	public PageInfo<Activity> findApplyingActivityByEntity(Activity activity, String orderBy,
			int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Activity> activitys = activityMapper.findApplyingActivityByEntity(activity);
		PageInfo<Activity> pageActivity = new PageInfo<Activity>(activitys);
		return pageActivity;
	}

	@Override
	public PageInfo<Activity> findRecruitActivityListByEntity(Activity activity, String orderBy,
			int currPage, int pageSize) {
		PageHelper.startPage(currPage, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Activity> activitys = activityMapper.findRecruitActivityListByEntity(activity);
		PageInfo<Activity> pageActivity = new PageInfo<Activity>(activitys);
		return pageActivity;
	}

	@Override
	public int toRecruitStatus() {
		return activityMapper.toRecruitStatus();
	}

	@Override
	public int toWaitDoStatus() {
		return activityMapper.toWaitDoStatus();
	}

	@Override
	public int toDoingStatus() {
		return activityMapper.toDoingStatus();
	}

	@Override
	public int toEndStatus() {
		return activityMapper.toEndStatus();
	}
	
}
