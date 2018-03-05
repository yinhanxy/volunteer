package com.topstar.volunteer.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Area;

public class AreaServiceTest extends BaseTest{

	@Autowired
	private AreaService areaService;
	
	@Test
	public void getAreaTreeMap(){
		areaService.getAllArea();
	}
	
	@Test
	public void getAreas(){
		Area area=areaService.selectByKey(1);
		System.out.println(area);
	}
}
