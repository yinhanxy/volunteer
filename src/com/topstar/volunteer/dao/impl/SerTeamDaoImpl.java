package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.SerTeamDao;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.mapper.SerTeamMapper;
import com.topstar.volunteer.model.Statistics;

@Repository
public class SerTeamDaoImpl extends BaseDaoImpl<SerTeam> implements SerTeamDao{

	@Autowired
	private SerTeamMapper serTeamMapper;
	
	@Override
	public PageInfo<SerTeam> findByEntity(SerTeam serTeam,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<SerTeam> serTeams= serTeamMapper.findByEntity(serTeam);
		PageInfo<SerTeam> page = new PageInfo<SerTeam>(serTeams);
		return page;
	}

	@Override
	public List<SerTeam> getSerTeamName(Long orgId) {
		return serTeamMapper.getSerTeamName(orgId);
	}


	@Override
	public List<Statistics> getSerByArea(Statistics statistics) {
		return serTeamMapper.getSerByArea(statistics);
	}

	@Override
	public List<Statistics> getNameAndHour(Statistics statistics) {
		return serTeamMapper.getNameAndHour(statistics);
	}

	@Override
	public int judgeSerByOrg(Long orgId) {
		return serTeamMapper.judgeSerByOrg(orgId);
	}

}

