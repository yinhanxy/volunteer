package com.topstar.volunteer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ActivityVolunteerDao;
import com.topstar.volunteer.dao.VolunteerDao;
import com.topstar.volunteer.entity.ActivityVolunteer;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSClientException;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.ActivityVolunteerService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ActivityVolunteerServiceImpl extends BaseServiceImpl<ActivityVolunteer> implements ActivityVolunteerService{

	@Autowired
	private ActivityVolunteerDao activityVolunteerDao;
	
	@Autowired
	private VolunteerDao volunteerDao;

	@Override
	public PageInfo<VolunteerView> getActivityVolunteersByActivityId(Long activityId, String orderBy, int currPage,
			int pageSize) {
		return activityVolunteerDao.findActivityVolunteersByActivityId(activityId, orderBy, currPage, pageSize);
	}

	@Override
	public ActivityVolunteer getActivityApplyByExample(Long vId, Long aId) {
		if(vId!=null && aId!=null){
			Example example=new Example(ActivityVolunteer.class);
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("volunteerId", vId);
			criteria.andEqualTo("activityId", aId);
			List<ActivityVolunteer> activityVolunteers= selectByExample(example);
			if(activityVolunteers!=null && !activityVolunteers.isEmpty() && activityVolunteers.size()==1){
				return activityVolunteers.get(0);
			}
		}
		return null;
	}

	@Override
	public PageInfo<VolunteerView> getJoinVolunteersByActivityId(Long activityId, String orderBy, int currPage,
			int pageSize) {
		return activityVolunteerDao.findJoinVolunteersByActivityId(activityId, orderBy, currPage, pageSize);
	}

	@Override
	public PageInfo<VolunteerView> getVolunteersInfoByActivityId(Long activityId, String orderBy, int currPage,
			int pageSize) {
		return activityVolunteerDao.findVolunteersInfoByActivityId(activityId, orderBy, currPage, pageSize);
	}

	@Override
	public PageInfo<VolunteerView> getJoinedActivityByVolunteerId(Long volunteerId, String orderBy, int currPage,
			int pageSize) {
		return activityVolunteerDao.findJoinedActivityByVolunteerId(volunteerId, orderBy, currPage, pageSize);
	} 
	
	/**
	 * 保存指定活动下的志愿者服务情况信息列表
	 * @param activityVolunteers
	 * @return
	 */
	public boolean saveActivityVolunteerHours(List<ActivityVolunteer> activityVolunteers) throws Exception {
		try{
			if(activityVolunteers!=null && !activityVolunteers.isEmpty()){
				for (ActivityVolunteer activityVolunteer : activityVolunteers) {
					Long activityVolunterId=activityVolunteer.getId();
					ActivityVolunteer existedActivityVol=activityVolunteerDao.selectByKey(activityVolunterId);
					if(existedActivityVol==null || !existedActivityVol.getActivityId().equals(activityVolunteer.getActivityId()) || !existedActivityVol.getVolunteerId().equals(activityVolunteer.getVolunteerId())){
						throw new TPSClientException("不合法");
					}
					if(activityVolunteerDao.updateNotNull(activityVolunteer)>0){
						Volunteer volunteer=volunteerDao.selectByKey(activityVolunteer.getVolunteerId());
						//volunteer.setServiceHour(volunteer.getServiceHour()+activityVolunteer.getSerHours());
						//查出志愿者的总服务时长
						Double serHours = activityVolunteerDao.selectVolSumHour(activityVolunteer.getVolunteerId());
						volunteer.setServiceHour(serHours);
						volunteerDao.updateNotNull(volunteer);
					}
				}
				return true;
			}
		}catch (Exception e) {
			throw e;
		}
		return false;
	}
}
