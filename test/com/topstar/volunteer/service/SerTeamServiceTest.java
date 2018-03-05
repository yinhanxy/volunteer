package com.topstar.volunteer.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.model.Statistics;

public class SerTeamServiceTest  extends BaseTest{

	@Autowired
	private SerTeamService serTeamService;
	
	@Test
	public void addSerTeam(){
		SerTeam serTeam=new SerTeam();
//		Long id=(long)1222212222;
//		serTeam.setId(id);
		serTeam.setName("湖北省群艺总队");
		serTeam.setOrgId((long)2);
		serTeam.setSummary("呵呵呵呵呵呵呵呵呵呵呵呵");
		serTeam.setContact("李四");
		serTeam.setContactTel("13888888888");
		serTeam.setFax("12456487");
		serTeam.setEmail("2254@136.com");
		serTeam.setPrincipal("小李");
		serTeam.setPrincipalTel("13888888888");
		serTeam.setAddress("湖北武汉");
		serTeam.setAreaId((long)1);
		serTeam.setCrUser("yinh");
		serTeam.setCrTime(new Date());
		serTeam.setStatus(1);
		boolean res= serTeamService.addSerTeam(serTeam);
		System.out.println(res);
	}
	
	@Test
	public void exist(){
		int res=serTeamService.existsWithSerTeamName("北省群艺总队", null);
		System.out.println(res);
	}
	
	@Test
	public void getSerByArea(){
//		List<Statistics> serTeamList=serTeamService.stsServiceShow();
		List<Statistics> serTeamListss=serTeamService.returnSerTeamNum();
		for (Statistics statistics : serTeamListss) {
			System.out.println(statistics.toString());
		}
//		for (Statistics statistics : serTeamList) {
//			System.out.println(statistics.toString());
//		}
	}
	
	@Test
	public void returnSerTeamNum(){
		List<Statistics> serTeamListss=serTeamService.returnSerTeamNum();
		for (Statistics statistics : serTeamListss) {
			System.out.println(statistics.toString());
		}
	}
	
	@Test
	public void getSerName(){
		 List<SerTeam> list=serTeamService.getSerTeamName(1L);
		 for (SerTeam serTeam : list) {
			System.out.println(serTeam.toString());
		}
	}
}
