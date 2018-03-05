package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.ActivityVolunteer;
import com.topstar.volunteer.exception.TPSClientException;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.BaseService;

/**
 * 报名指定活动的志愿者信息的业务查询接口
 * @author TRS
 *
 */
public interface ActivityVolunteerService extends BaseService<ActivityVolunteer>{

	/**
	 * 根据活动ID分页查询对应的报名该活动的志愿者信息列表
	 * @param activityId  活动ID
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageInfo<VolunteerView> getActivityVolunteersByActivityId(Long activityId, String orderBy, int currPage,
			int pageSize);

	/**
	 * 根据活动ID和志愿者ID获取指定志愿者报名指定活动的信息
	 * @param vId
	 * @param aId
	 * @return
	 */
	ActivityVolunteer getActivityApplyByExample(Long vId, Long aId);

	/**
	 * 根据活动ID查询已参加该活动的志愿者服务情况列表
	 * @param activityId  活动ID
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageInfo<VolunteerView> getJoinVolunteersByActivityId(Long activityId, String orderBy, int currPage, int pageSize);

	/**
	 * 根据活动ID查询参加该活动的志愿者信息列表
	 * @param activityId  活动ID
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageInfo<VolunteerView> getVolunteersInfoByActivityId(Long activityId, String orderBy, int currPage, int pageSize);
	
	/**
	 * 根据志愿者ID查询该志愿者参加过的活动情况列表
	 * @param volunteerId  志愿者ID
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageInfo<VolunteerView> getJoinedActivityByVolunteerId(Long volunteerId, String orderBy, int currPage, int pageSize);

	/**
	 * 保存指定活动下的志愿者服务情况信息列表
	 * @param activityVolunteers
	 * @return
	 */
	boolean saveActivityVolunteerHours(List<ActivityVolunteer> activityVolunteers) throws Exception;
}
