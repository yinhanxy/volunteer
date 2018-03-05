package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.RetreatTeamDao;
import com.topstar.volunteer.entity.RetreatTeamDeal;
import com.topstar.volunteer.entity.TurnTeamDeal;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.RetreatTeamView;
import com.topstar.volunteer.model.TurnTeamView;
import com.topstar.volunteer.mapper.RetreatTeamMapper;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class RetreatTeamDaoImpl extends BaseDaoImpl<RetreatTeamDeal> implements RetreatTeamDao{

	@Autowired
	private RetreatTeamMapper RetreatTeamMapper;
	
	
	@Override
	public PageInfo<RetreatTeamView> findRetreatTeamApplysByEntity(Volunteer volunteer,String orderBy, int page, int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<RetreatTeamView> turnTeamViews = RetreatTeamMapper.findRetreatTeamApplysByEntity(volunteer);
		PageInfo<RetreatTeamView> pageTurnTeamViews = new PageInfo<RetreatTeamView>(turnTeamViews);
		return pageTurnTeamViews;
	}

	@Override
	public TurnTeamView findRetreatTeamApplyByVolunteerId(Long volunteerId) {
//		TurnTeamView turnTeamView=RetreatTeamMapper.findTurnTeamApplyByVolunteerId(volunteerId);
//		if(turnTeamView!=null){
//			return turnTeamView;
//		}
		return null;
	}


	@Override
	public TurnTeamDeal findRetreatTeamResultByVolunteerId(Long volunteerId) {
//		TurnTeamDeal turnTeamDeal=RetreatTeamMapper.findTurnTeamResultByVolunteerId(volunteerId);
//		if(turnTeamDeal!=null){
//			return turnTeamDeal;
//		}
		return null;
	}

	@Override
	public RetreatTeamView getRetreatTeamByVolunteerId(Long volunteerId) {
		RetreatTeamView res=RetreatTeamMapper.getRetreatTeamByVolunteerId(volunteerId);
		if (res!=null) {
			return res;
		}
		return null;
	}
	
	
}
