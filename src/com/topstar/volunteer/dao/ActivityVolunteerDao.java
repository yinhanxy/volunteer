package com.topstar.volunteer.dao;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.ActivityVolunteer;
import com.topstar.volunteer.model.VolunteerView;

/**
 * 报名及参加活动的志愿者相关信息的数据库查询接口
 * @author TRS
 *
 */
public interface ActivityVolunteerDao extends BaseDao<ActivityVolunteer> {
	
	/**
	 * 根据活动ID查询报名该活动的志愿者信息列表
	 * @param activityId 活动ID
	 * @param orderBy 排序规则
	 * @param page 页码
	 * @param rows 每页显示记录数
	 * @return
	 */
	PageInfo<VolunteerView> findActivityVolunteersByActivityId(Long activityId, String orderBy, int currPage, int pageSize);

	/**
	 * 根据活动ID查询已参加该活动的志愿者服务情况列表
	 * @param activityId 活动ID
	 * @param orderBy 排序规则
	 * @param page 页码
	 * @param rows 每页显示记录数
	 * @return
	 */
	PageInfo<VolunteerView> findJoinVolunteersByActivityId(Long activityId, String orderBy, int currPage, int pageSize);

	/**
	 * 根据活动ID查询参加该活动的志愿者信息列表
	 * @param activityId 活动ID
	 * @param orderBy 排序规则
	 * @param page 页码
	 * @param rows 每页显示记录数
	 * @return
	 */
	PageInfo<VolunteerView> findVolunteersInfoByActivityId(Long activityId, String orderBy, int currPage, int pageSize);

	/**
	 * 根据志愿者ID查询该志愿者参加过的活动情况列表
	 * @param volunteerId
	 * @param orderBy
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	PageInfo<VolunteerView> findJoinedActivityByVolunteerId(Long volunteerId, String orderBy, int currPage,int pageSize);
	
	/**
	 * 根据志愿者ID查询该志愿者服务总时长
	 * @param volunteerId
	 * @return
	 */
	Double selectVolSumHour(Long volunteerId);
}
