package com.topstar.volunteer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.entity.ActivityVolunteer;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.BaseMapper;

public interface ActivityVolunteerMapper extends BaseMapper<ActivityVolunteer>{

	/**
	 * 根据活动ID查询报名该活动的志愿者信息列表
	 * @param activityId 活动ID
	 * @return
	 */
	List<VolunteerView> selectActivityVolunteersByActivityId(Long activityId);

	/**
	 * 根据活动ID查询已参加该活动的志愿者服务情况列表
	 * @param activityId 活动ID
	 * @return
	 */
	List<VolunteerView> selectJoinVolunteersByActivityId(Long activityId);

	/**
	 * 根据活动ID查询参加该活动的志愿者信息列表
	 * @param activityId 活动ID
	 * @return
	 */
	List<VolunteerView> selectVolunteersInfoByActivityId(Long activityId);
	
	/**
	 * 根据志愿者ID查询该志愿者参加过的活动情况列表
	 * @param activityId 志愿者ID
	 * @return
	 */
	List<VolunteerView> selectJoinedActivityByVolunteerId(Long activityId);
	
	/**
	 * 根据志愿者id查出志愿者的总服务时长
	 * @param volunteerId 志愿者ID
	 * @return
	 */
	Double selectVolSumHour(@Param("volunteerId")Long volunteerId);
}
