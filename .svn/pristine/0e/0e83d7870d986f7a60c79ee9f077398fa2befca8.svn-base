package com.topstar.volunteer.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.model.Statistics;

public class ActivityServiceTest extends BaseTest{
	
	@Autowired
	private ActivityService activityService;
	
	@Test
	public void getActivityArea(){
		List<Statistics> lists=activityService.returnActivity();
		for (Statistics statistics2 : lists) {
			System.out.println(statistics2.toString());
		}
	}
}
