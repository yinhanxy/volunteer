package com.topstar.volunteer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.TurnTeam;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.TurnTeamView;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.BaseMapper;

public interface TurnTeamMapper extends BaseMapper<TurnTeam>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者转队申请信息列表
	 * @param volunteer
	 * @return
	 */
	List<TurnTeamView> findTurnTeamApplysByEntity(@Param("volunteer")Volunteer volunteer);

	/**
	 * 根据ID查询指定志愿者转队申请信息
	 * @param volunteer
	 * @return
	 */
	TurnTeamView findTurnTeamApplyByTurnId(@Param("turnId")Long turnId);

	
}