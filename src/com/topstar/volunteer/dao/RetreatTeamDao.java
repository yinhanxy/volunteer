package com.topstar.volunteer.dao;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.RetreatTeamDeal;
import com.topstar.volunteer.entity.TurnTeamDeal;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.RetreatTeamView;
import com.topstar.volunteer.model.TurnTeamView;

public interface RetreatTeamDao extends BaseDao<RetreatTeamDeal>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者退队申请信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<RetreatTeamView> findRetreatTeamApplysByEntity(Volunteer volunteer,String orderBy, int page, int rows);
	
	/**
	 * 根据志愿者ID查询相关退队信息
	 * @param volunteerId
	 * @return
	 */
	public RetreatTeamView getRetreatTeamByVolunteerId(Long volunteerId);
	
	/**
	 * 根据志愿者ID查询指定志愿者退队申请信息
	 * @param volunteer
	 * @return
	 */
	TurnTeamView findRetreatTeamApplyByVolunteerId(Long volunteerId);
	
	/**
	 * 根据志愿者ID查询指定志愿者退队申请处理信息
	 * @param params
	 * @return
	 */
	TurnTeamDeal findRetreatTeamResultByVolunteerId(Long volunteerId);
}
