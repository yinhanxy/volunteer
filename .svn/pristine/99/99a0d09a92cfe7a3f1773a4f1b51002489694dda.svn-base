package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.model.Statistics;

/**
 * 活动信息管理数据库接口
 * @author TRS
 *
 */
public interface ActivityDao extends BaseDao<Activity>{

	/**
	 * 根据活动实体过滤条件查询活动登记信息
	 * @param activity
	 * @param serviceTeam
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageInfo<Activity> findByEntity(Activity activity, String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 根据活动实体字段信息过滤查询待进行,正在进行和已完成的活动信息列表
	 * @param activity
	 * @param serviceTeam
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Activity> findDoingAndCompletedByEntity(Activity activity, String orderBy, int pageIndex,
			int pageSize);
	
	/**
	 * 根据活动实体字段信息过滤查询处于招募报名状态中的活动列表
	 * @param activity
	 * @param serviceTeam
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Activity> findApplyingActivityByEntity(Activity activity, String orderBy, int pageIndex,
			int pageSize);
	
	/**
	 * 根据活动实体过滤条件查询长期类型且处于已完成和已撤销状态的活动列表
	 * @param activity
	 * @param serviceTeam
	 * @param orderBy
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	PageInfo<Activity> findRecruitActivityListByEntity(Activity activity, String orderBy,
			int currPage, int pageSize);
	
	
	/**
	 * 得到各区域中的活动数量
	 * 如："洪山区","4"
	 * @param statistics
	 * @return
	 */
	public List<Statistics> getActivityArea(Statistics statistics);
	
	/**
	 * 得到各服务队中的活动数量
	 * 如："武汉市群艺大队","4"
	 * @param statistics
	 * @return
	 */
	public List<Statistics> getActivitySer(Statistics statistics);
	
	/**
	 * 得到业务机构中每年的活动数量
	 * 如："2017","4"
	 * @param statistics
	 * @return
	 */
	public PageInfo<Statistics> selActivityByYear(Statistics statistics, String orderBy, int pageIndex, int pageSize);
	
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
