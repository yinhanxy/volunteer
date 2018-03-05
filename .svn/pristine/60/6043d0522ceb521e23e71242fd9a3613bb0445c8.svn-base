package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.RetreatTeamView;

public class RetreatTeamMapperTest extends BaseTest{

	@Autowired
	private RetreatTeamMapper retreatTeamMapper;
	
	@Test
	public void list(){
		Volunteer volunteer = new Volunteer();
		List<RetreatTeamView> list=retreatTeamMapper.findRetreatTeamApplysByEntity(volunteer);
		for (RetreatTeamView retreatTeamView : list) {
			System.out.println(retreatTeamView.toString());
		}
	}
	
	@Test
	public void getRetreatTeam(){
		RetreatTeamView entity= retreatTeamMapper.getRetreatTeamByVolunteerId(71L);
		System.out.println(entity.toString());
	}
}
