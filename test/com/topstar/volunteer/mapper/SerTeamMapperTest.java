package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.model.Statistics;

public class SerTeamMapperTest  extends BaseTest{

	@Autowired
	private SerTeamMapper serTeamMapper;
	
	/**
	 * 得到服务队列表信息
	 */
	@Test
	public void findByEntity(){
		SerTeam serteam =new SerTeam();
		serteam.setCurOrgId(2l);
		List<SerTeam> serteams = serTeamMapper.findByEntity(serteam);
		for (SerTeam ser : serteams) {
			System.out.println(ser.toString());
		}
		
	}
	
	@Test
	public void getSerTeamName(){
		Long orgId=1l;
		List<SerTeam> serteams = serTeamMapper.getSerTeamName(orgId);
		for (SerTeam ser : serteams) {
			System.out.println(ser.toString());
		}
		
	}
	
	@Test
	public void getSerByArea(){
		Statistics statistics =new Statistics();
		 List<Statistics> lists= serTeamMapper.getSerByArea(statistics);
		 for (Statistics statistics2 : lists) {
			System.out.println(statistics2.toString());
		}
	}
	
	@Test
	public void getNameAndHour(){
		Statistics statistics =new Statistics();
		List<Statistics> lists= serTeamMapper.getNameAndHour(statistics);
		 for (Statistics statistics2 : lists) {
				System.out.println(statistics2.toString());
			}
	}
	
	@Test
	public void judgeSerNum(){
		System.out.println("res:"+serTeamMapper.judgeSerByOrg(6L));
	}
}
