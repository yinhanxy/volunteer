package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.TeamUserDao;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.mapper.TeamUserMapper;

@Repository
public class TeamUserDaoImpl extends BaseDaoImpl<TeamUser> implements TeamUserDao{

	@Autowired
	private TeamUserMapper teamUserMapper;
	
	@Override
	public PageInfo<User> findByTeamUser(User user,TeamUser teamUser,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<User> users= teamUserMapper.findByTeamId(user,teamUser);
		PageInfo<User> page = new PageInfo<User>(users);
		return page;
	}

	@Override
	public PageInfo<User> findByUserTeamID(User uesr, Long teamId, String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<User> users= teamUserMapper.findByUserTeamId(uesr, teamId);
		PageInfo<User> page = new PageInfo<User>(users);
		return page;
	}

	@Override
	public boolean delTeamUser(Long userId, Long teamId) {
		if(userId == null || userId.longValue() < 1 ||teamId == null || teamId.longValue() < 1){
			return false;
		}
		int delRes=teamUserMapper.delTeamUser(userId, teamId);
		if (delRes>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Long> findTeamIdsByUserId(Long id) {
		if(id == null || id.longValue() < 1){
			return null;
		}
		List<Long> teamIds=teamUserMapper.findTeamIdsByUserId(id);
		if(teamIds!=null && teamIds.size()>0){
			return teamIds;
		}
		return null;
	}

	/**
	 * 通过用户ID查询用户所在的服务队信息
	 * @param userId
	 * @return
	 */
	public SerTeam findByUserId(Long userId){
		SerTeam serTeam=teamUserMapper.findTeamByUserId(userId);
		return serTeam;
	}

}

