package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Area;

public class AreaDaoTest extends BaseTest {

	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void getAll(){
		List<Area> areas=areaDao.selectAllArea();
		for (Area area : areas) {
			System.out.println(area);
		}
	}
	
	@Test
	public void addArea(){
		Area area=new Area();
		area.setName("黄冈市");
		area.setCode("123456");
		area.setParentId(1l);
		area.setType(1);
		area.setRemark("123");
		int result=areaDao.addArea(area);
		System.out.println(result);
	}
}
