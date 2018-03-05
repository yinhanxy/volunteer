package com.topstar.volunteer.dao;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;

public interface StarEvaluateDao extends BaseDao<StarEvaluate>{

	/**
	 * 根据志愿者实体字段的值过滤查询志愿者服务星级评定的信息列表 
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<VolunteerView> findVolunteerStarByEntity(Volunteer volunteer,String orderBy, int page, int rows);
	

	/**
	 * 根据志愿者ID查询最新的服务星级评定信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public List<VolunteerView> findLatestVolunteerStarByVolunteerId(Long volunteerId);
	
	/**
	 * 根据志愿者ID查询所有的服务星级评定信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public List<VolunteerView> findAllVolunteerStarByVolunteerId(Long volunteerId);
	
	/**
	 * 根据志愿者星级评定的ID查询志愿者某一条星级评定的具体信息
	 * @param volunteerId
	 * @return
	 */
	VolunteerView findStarEvaluateByStarEvaluateId(Long starEvaluateId);
	
	/**
	 * 得出指定机构Id中的各星级志愿者人数分布情况
	 * 如：[["1星","5"],["2星","8"]]
	 * @param orgId
	 * @return
	 */
	public List<Statistics> getStarByorgId(Statistics statistics);
	
	/**
	 * 查询志愿者服务时长与星级的信息列表 
	 * @return
	 */
	List<VolunteerView> findVolunteerServiceHoursAndStar();
}
