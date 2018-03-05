package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.TurnTeamDao;
import com.topstar.volunteer.entity.TurnTeam;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.mapper.TurnTeamMapper;
import com.topstar.volunteer.model.TurnTeamView;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class TurnTeamDaoImpl extends BaseDaoImpl<TurnTeam> implements TurnTeamDao{

	@Autowired
	private TurnTeamMapper turnTeamMapper;
	
	
	@Override
	public PageInfo<TurnTeamView> findTurnTeamApplysByEntity(Volunteer volunteer,String orderBy, int page, int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<TurnTeamView> turnTeamViews = turnTeamMapper.findTurnTeamApplysByEntity(volunteer);
		PageInfo<TurnTeamView> pageTurnTeamViews = new PageInfo<TurnTeamView>(turnTeamViews);
		return pageTurnTeamViews;
	}

	@Override
	public TurnTeamView findTurnTeamApplyByTurnId(Long turnId) {
		TurnTeamView turnTeamView=turnTeamMapper.findTurnTeamApplyByTurnId(turnId);
		if(turnTeamView!=null){
			return turnTeamView;
		}
		return null;
	}

}
