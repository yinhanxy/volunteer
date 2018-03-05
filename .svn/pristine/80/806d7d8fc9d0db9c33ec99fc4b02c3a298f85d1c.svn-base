package com.topstar.volunteer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.RetreatTeamView;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;

public class VolunteerServiceTest extends BaseTest{
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private RetreatTeamService retreatTeamService;
	

	
	@Test
	public void addVols(){
		List<Volunteer> list =new ArrayList<Volunteer>();
		Volunteer vol = new Volunteer();
//		vol.setPassword("]$YuCaaZN+8h3uFQg+EoSVFA==");
		vol.setUserName("王五");
		vol.setSex("男");
		vol.setMobile("13588888888");
		vol.setIdcard("123457851516151563");
		vol.setBirthday(new Date());
		vol.setPostcode("21234");
		list.add(vol);
		Long trainId = 3l;
		boolean res = volunteerService.addVols(list,trainId);
		System.out.println("addVolsResult:="+res);
	}
	
	@Test
	public void getRetreatTeam(){
		Volunteer volunteer = new Volunteer();
		String orderBy="";
		int pageIndex= 1;
		int pageSize =10;
		PageInfo<RetreatTeamView> resList=retreatTeamService.getRetreatTeamApplysByEntity(volunteer, orderBy, pageIndex, pageSize);
	    List<RetreatTeamView> list = resList.getList();
	    for (RetreatTeamView retreatTeamView : list) {
			System.out.println(retreatTeamView.toString());
		}
	}
	
	@Test
	public void updateVolunteer(){
		Volunteer entity = new Volunteer();
		entity.setId(1l);
//		entity.setRetreatTeamStatus(1l);
		volunteerService.updateNotNull(entity);
	}
	
	@Test
	public void statisticsShow(){
		 List<Statistics> list=volunteerService.statisticsShow();
		 System.out.println(list.toString());
		 for (Statistics statistics : list) {
				System.out.println(statistics.toString());
			}
	}
	
	@Test
	public void getVols(){
		PageInfo<Volunteer> vol=volunteerService.getVols(5l, "", 1, 10);
		List<Volunteer> volList= vol.getList();
		for (Volunteer volunteer : volList) {
			System.out.println(volunteer.toString());
		}
	}
	
	@Test
	public void getVol(){
		Volunteer entity = new Volunteer();
		entity.setStatus(1);
		PageInfo<VolunteerView> pageInfo = volunteerService.findVolunteerCheckByEntity(entity, "", 1, 10);
		System.out.println(pageInfo.toString());
		List<VolunteerView> volList= pageInfo.getList();
		for (VolunteerView volunteer : volList) {
			System.out.println(volunteer.toString());
		}
	}
	
	@Test
	public void getVolStatic(){
		PageInfo<Statistics> pageInfo = volunteerService.getVolStatis("", 1, 10);
		List<Statistics> list =pageInfo.getList();
		for (Statistics statistics : list) {
			System.out.println(statistics.toString());
		}
	}
}
