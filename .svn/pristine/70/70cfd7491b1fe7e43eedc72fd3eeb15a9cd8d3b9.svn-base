package com.topstar.volunteer.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.TurnTeam;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.TurnTeamView;

public interface TurnTeamService extends BaseService<TurnTeam>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者转队申请信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<TurnTeamView> getTurnTeamApplysByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 根据ID查询指定志愿者转队申请信息
	 * @param turnId
	 * @return
	 */
	public TurnTeamView getTurnTeamApplyByTurnId(Long turnId);

}
