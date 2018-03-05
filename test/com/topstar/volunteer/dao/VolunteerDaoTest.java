package com.topstar.volunteer.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.RetreatTeamView;

public class VolunteerDaoTest extends BaseTest {

	@Autowired
	private VolunteerDao volunteerDao;
	
	@Autowired
	private RetreatTeamDao retreatTeamDao;
	
	@Test
	public void setPwd(){
		long[] ids={1l};
		int a=volunteerDao.setVolunteerPassword(ids, "111");
		System.out.println(a);
	}
	
	@Test
	public void findRetreatTeamApplysByEntity(){
		Volunteer volunteer = new Volunteer();
		String orderBy="";
		int page= 1;
		int rows =10;
		PageInfo<RetreatTeamView> resList=retreatTeamDao.findRetreatTeamApplysByEntity(volunteer, orderBy, page, rows);
		List<RetreatTeamView> list = resList.getList();
	    for (RetreatTeamView retreatTeamView : list) {
			System.out.println(retreatTeamView.toString());
		}
	}
}
