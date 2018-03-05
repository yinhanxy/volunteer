package com.topstar.volunteer.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;

/**
 * 志愿者星级评定业务层
 * @author TRS
 *
 */
public interface StarEvaluateService extends BaseService<StarEvaluate>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者服务星级评定的信息列表 
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> getVolunteerStarByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 根据志愿者ID查询指定志愿者服务星级评定信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public VolunteerView getLatestVolunteerStarByVolunteerId(Long volunteerId);
	
	/**
	 * 根据志愿者星级评定的ID查询志愿者某一条星级评定的具体信息
	 * @param starEvaluateId
	 * @return
	 */
	VolunteerView getStarEvaluateByStarEvaluateId(Long starEvaluateId);

	/**
	 * 分页查询志愿者星级评定记录列表信息
	 * @param volunteerId
	 * @param orderBy
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> getAllVolunteerStarByVolunteerId(Long volunteerId, String orderBy, int currPage,int pageSize);
	
	/**
	 * 得出指定机构Id中的各星级志愿者人数分布情况
	 * 如：[["1星","5"],["2星","8"]]
	 * @param orgId
	 * @return
	 */
	public List<Statistics> getStarByorgId(Statistics statistics);
	
	/**
	 * 星级信息统计展示
	 * @return
	 */
	public List<Statistics> starStatisticsShow();
	
	/**
	 * 查询志愿者服务时长与星级的信息列表 
	 * @return
	 */
	List<VolunteerView> getVolunteerServiceHoursAndStar();
}
