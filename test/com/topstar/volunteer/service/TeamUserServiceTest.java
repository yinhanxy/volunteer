package com.topstar.volunteer.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.User;

public class TeamUserServiceTest  extends BaseTest{

	@Autowired
	private TeamUserService teamUserService;
	
	@Test
	public void test(){
		User userss = new User();
		long teamId = 3l;
		int page=1;
		int rows=10;
		PageInfo<User> res= teamUserService.findByUserTeamID(userss,teamId, page, rows);
		List<User> users=res.getList();
		for (User user : users) {
			System.out.println(user.getId()+","+user.getRegTime());
		}
		
	}
	
	@Test
	public void get(){
		User userss = new User();
		long teamId = 3l;
		int page=1;
		int rows=10;
		PageInfo<User> res=teamUserService.getUserTeams(userss, teamId, page, rows);
		List<User> users=res.getList();
		for (User user : users) {
			System.out.println(user.getTeamIdList()+",");
		}
	}
	
	@Test
	public void del(){
		long teamId = 4l;
		List<Long> userKeys=new ArrayList<Long>();
		userKeys.add(3l) ;
		userKeys.add(4l) ;
		boolean res=teamUserService.delTeamUser(userKeys, teamId);
		System.out.println(res);
	}
}
