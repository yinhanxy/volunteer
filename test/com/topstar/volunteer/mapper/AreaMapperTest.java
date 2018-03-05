package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Area;

public class AreaMapperTest extends BaseTest{

	@Autowired
	private AreaMapper areaMapper;
	
	@Test
	public void getAreas(){
		List<Area> list=areaMapper.getAreas();
		for (Area area : list) {
			System.out.println("城市名称："+area.getName()+",城市编码："+area.getCode().substring(0,4));
			System.out.println(area.toString());
		}
	}
}
