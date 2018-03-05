package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;

public class TeamUserMapperTest  extends BaseTest{
	
	@Autowired
	private TeamUserMapper teamUserMapper;
	
	/**
	 * 根据teamId得到用户列表
	 */
	@Test
	public void findByTeamId(){
		TeamUser teamUser = new TeamUser();
		teamUser.setTeamId(4l);
		User user =new User();
		List<User> users =  teamUserMapper.findByTeamId(user, teamUser);
		for (User user2 : users) {
			System.out.println(user2.toString());
		}
	}
	
	/**
	 * 根据用户Id得到服务队Id列表
	 */
	@Test
	public void findTeamIdsByUserId(){
		Long userId = 5l;
		List<Long> teamIds= teamUserMapper.findTeamIdsByUserId(userId);
		for (Long long1 : teamIds) {
			System.out.println(long1.toString());
		}
	}
	
	/**
	 * 根据用户Id和服务队Id删除服务队中的用户
	 */
	@Test
	public void delTeamUser(){
		Long userId = 5l;
		Long teamId = 6l;
		int res=teamUserMapper.delTeamUser(userId, teamId);
		if (res>0) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
}
