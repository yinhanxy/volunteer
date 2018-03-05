package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.model.Statistics;

public class ActivityMapperTest extends BaseTest{

	@Autowired
	private ActivityMapper activityMapper;
	
	@Test
	public void getActivityArea(){
		Statistics statistics =new Statistics();
		List<Statistics> lists=activityMapper.getActivityArea(statistics);
		for (Statistics statistics2 : lists) {
			System.out.println(statistics2.toString());
		}
	}
	
	@Test
	public void getActivitySer(){
		Statistics statistics =new Statistics();
		List<Statistics> lists=activityMapper.getActivitySer(statistics);
		for (Statistics statistics2 : lists) {
			System.out.println(statistics2.toString());
		}
	}
	
	@Test
	public void selActivityByYear(){
		Statistics statistics =new Statistics();
//		statistics.setOrgId(2l);
		List<Statistics> lists=activityMapper.selActivityByYear(statistics);
		for (Statistics statistics2 : lists) {
			System.out.println(statistics2.toString());
		}
	}
}
