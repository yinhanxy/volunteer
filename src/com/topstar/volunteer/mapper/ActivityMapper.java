package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.util.BaseMapper;

public interface ActivityMapper extends BaseMapper<Activity> {

	/**
	 * 根据活动实体字段信息过滤查询活动登记信息列表
	 * @param activity
	 * @param serviceTeam
	 * @return
	 */
	List<Activity> findByEntity(@Param("activity")Activity activity);

	/**
	 * 根据活动实体字段信息过滤查询待进行,正在进行和已完成的活动信息列表
	 * @param activity
	 * @param serviceTeam
	 * @return
	 */
	List<Activity> findDoingAndCompletedByEntity(@Param("activity")Activity activity);
	
	/**
	 * 根据活动实体字段信息过滤查询处于招募报名状态中的活动列表
	 * @param activity
	 * @param serviceTeam
	 * @return
	 */
	List<Activity> findApplyingActivityByEntity(@Param("activity")Activity activity);
	
	/**
	 * 根据活动实体过滤条件查询长期类型且处于已完成和已撤销状态的活动列表
	 * @param activity
	 * @param serviceTeam
	 * @return
	 */
	List<Activity> findRecruitActivityListByEntity(@Param("activity")Activity activity);
	
	/**
	 * 得到各区域中的活动数量
	 * 如："洪山区","4"
	 * @param statistics
	 * @return
	 */
	List<Statistics> getActivityArea(@Param("statistics")Statistics statistics);
	
	/**
	 * 得到各服务队中的活动数量
	 * 如："武汉市群艺大队","4"
	 * @param statistics
	 * @return
	 */
	List<Statistics> getActivitySer(@Param("statistics")Statistics statistics);
	
	/**
	 * 得到业务机构中每年的活动数量
	 * 如："2017","4"
	 * @param statistics
	 * @return
	 */
	List<Statistics> selActivityByYear(@Param("statistics")Statistics statistics);
	
	/**
	 * 活动进入"招募中"状态，即status=4
	 * @return
	 */
	public int toRecruitStatus();
	
	/**
	 * 活动进入"待进行"状态，即status=5
	 * @return
	 */
	public int toWaitDoStatus();
	
	/**
	 * 活动进入"进行中"状态，即status=6
	 * @return
	 */
	public int toDoingStatus();
	
	/**
	 * 活动进入"已完成"状态，即status=7
	 * @return
	 */
	public int toEndStatus();
}