package com.topstar.volunteer.dao;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.TurnTeam;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.TurnTeamView;

public interface TurnTeamDao extends BaseDao<TurnTeam>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者转队申请信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<TurnTeamView> findTurnTeamApplysByEntity(Volunteer volunteer,String orderBy, int page, int rows);
	
	/**
	 * 根据ID查询指定志愿者转队申请信息
	 * @param turnId
	 * @return
	 */
	TurnTeamView findTurnTeamApplyByTurnId(Long turnId);

}
