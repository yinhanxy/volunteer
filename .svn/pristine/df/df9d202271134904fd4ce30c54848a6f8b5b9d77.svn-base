package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;

public class TeamUserDaoTest extends BaseTest{
	
	@Autowired
	private TeamUserDao teamUserDao;
	
	@Test
	public void findByTeamUser(){
		TeamUser teamUser=new TeamUser();
		teamUser.setTeamId(4l);
		String orderBy="";
		int page =1;
		int rows =10;
		User user=new User();
		PageInfo<User> res=teamUserDao.findByTeamUser(user, teamUser, orderBy, page, rows);
		List<User> users=res.getList();
		for (User u : users) {
			System.out.println(u.toString());
		}
	}
	
}
