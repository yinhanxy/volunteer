package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.service.BaseService;

public interface ActivityService extends BaseService<Activity>{

	/**
	 * 根据过滤条件查询活动登记信息列表
	 * @param activity 活动查询过滤条件
	 * @param serviceTeam 活动登记信息查询的志愿者服务队过滤条件
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Activity> findByEntity(Activity activity,String orderBy, int pageIndex, int pageSize);

	/**
	 * 根据活动实体字段信息过滤查询待进行,正在进行和已完成的活动信息列表
	 * @param activity 活动查询过滤条件
	 * @param serviceTeam 活动记录信息查询的志愿者服务队过滤条件
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Activity> getDoingAndCompletedByEntity(Activity activity,String orderBy, int pageIndex,
			int pageSize);
	
	/**
	 * 根据活动实体字段信息过滤查询处于招募报名状态中的活动列表
	 * @param activity 活动查询过滤条件
	 * @param serviceTeam 活动报名管理信息查询的志愿者服务队过滤条件
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Activity> getApplyingActivityByEntity(Activity activity,String orderBy, int pageIndex,
			int pageSize);
	
	/**
	 * 获取当前用户所管理的服务队下的长期及已完成和已撤销的活动列表
	 * @param orderBy 排序字段
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Activity> getRecruitActivityList(Activity activity,String orderBy, int currPage, int pageSize);

	/**
	 * 更新指定活动的活动记录信息
	 * @param activityId
	 * @param imgUrls
	 * @return
	 */
	public int saveActivityRecords(Activity activity, String imgUrls);

	/**
	 * 根据指定的活动id批量撤销活动
	 * @param activityIds
	 * @return
	 * @throws TPSException 
	 */
	public boolean batchCancelActivitys(Long[] activityIds) throws TPSException;

	/**
	 * 根据指定的活动id批量删除活动
	 * @param activityIds
	 * @return
	 * @throws TPSException 
	 */
	public boolean batchDelActivitys(Long[] activityIds) throws TPSException;

	/**
	 * 根据指定的活动id批量发布活动
	 * @param activityIds
	 * @return
	 * @throws TPSException 
	 */
	public boolean batchPubActivitys(Long[] activityIds) throws TPSException;

	/**
	 * 根据指定的活动id批量提交活动
	 * @param activityIds
	 * @return
	 * @throws TPSException 
	 */
	public boolean batchCommitActivitys(Long[] activityIds) throws TPSException;

	
	/**
	 * 活动统计信息
	 * @return
	 */
	public List<Statistics> stsActivityShow();
	
	/**
	 * 返回市级的统计信息
	 * @return
	 */
	public List<Statistics> returnActivity();
	
	/**
	 * 得到服务队中的活动数量
	 * @return
	 */
	public List<Statistics> getSerAct();
	
	/**
	 * 得到业务机构中每年的活动数量
	 * 如："2017","4"
	 * @param statistics
	 * @return
	 */
	public PageInfo<Statistics> selActivityByYear(Statistics statistics, int pageIndex, int pageSize);
	
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
