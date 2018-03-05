package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.model.Statistics;

public class StarEvaluateMapperTest extends BaseTest{
	
	@Autowired
	private StarEvaluateMapper starEvaluateMapper;
	
	@Test
	public void getStarByorgId(){
		Statistics statistics =new Statistics();
		statistics.setOrgId(2l);
//		statistics.setCurOrgId(2l);
		List<Statistics> lists= starEvaluateMapper.getStarByorgId(statistics);
		for (Statistics statistics2 : lists) {
			System.out.println(statistics2.toString());
		}
	}
}
