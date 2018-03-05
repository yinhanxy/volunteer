package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.RetreatTeamDeal;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.RetreatTeamView;
import com.topstar.volunteer.util.BaseMapper;

public interface RetreatTeamMapper extends BaseMapper<RetreatTeamDeal>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者转队申请信息列表
	 * @param volunteer
	 * @return
	 */
	List<RetreatTeamView> findRetreatTeamApplysByEntity(@Param("volunteer")Volunteer volunteer);
	
	/**
	 * 根据志愿者ID查询相关退队信息
	 * @param volunteerId
	 * @return
	 */
	RetreatTeamView getRetreatTeamByVolunteerId(@Param("volunteerId")Long volunteerId);
//	/**
//	 * 根据志愿者ID查询指定志愿者转队申请信息
//	 * @param volunteer
//	 * @return
//	 */
//	TurnTeamView findTurnTeamApplyByVolunteerId(@Param("volunteerId")Long volunteerId);
//	
//	/**
//	 * 根据志愿者ID查询指定志愿者转队申请处理信息
//	 * @param params
//	 * @return
//	 */
//	TurnTeamDeal findTurnTeamResultByVolunteerId(@Param("volunteerId")Long volunteerId);
	
}