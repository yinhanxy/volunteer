package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.BaseMapper;

public interface StarEvaluateMapper extends BaseMapper<StarEvaluate>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者服务星级评定的信息列表 
	 * @param volunteer
	 * @return
	 */
	List<VolunteerView> findVolunteerStarByEntity(@Param("volunteer")Volunteer volunteer);
	
	/**
	 * 根据志愿者ID查询指定志愿者服务星级评定信息 
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	List<VolunteerView> findLatestVolunteerStarByVolunteerId(@Param("volunteerId")Long volunteerId);
	
	/**
	 * 根据志愿者ID查询服务星级评定信息 
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	List<VolunteerView> findAllVolunteerStarByVolunteerId(@Param("volunteerId")Long volunteerId);
	
	/**
	 * 根据志愿者ID获取该志愿者星级评价信息
	 * @param volunteerId
	 * @return
	 */
	StarEvaluate findStarEvaluateByVolunteerId(@Param("volunteerId") Long volunteerId);
	
	/**
	 * 得出指定机构Id中的各星级志愿者人数分布情况
	 * 如：[["1星","5"],["2星","8"]]
	 * @param orgId
	 * @return
	 */
	List<Statistics> getStarByorgId(@Param("statistics")Statistics statistics);
	
	/**
	 * 根据志愿者星级评定的ID查询志愿者某一条星级评定的具体信息
	 * @param starEvaluateId 星级评定的ID
	 * @return
	 */
	VolunteerView findStarEvaluateByStarEvaluateId(@Param("starEvaluateId") Long starEvaluateId);
	
	/**
	 * 查询志愿者服务时长与星级的信息列表 
	 * @return
	 */
	List<VolunteerView> selectVolunteerServiceHoursAndStar();
}