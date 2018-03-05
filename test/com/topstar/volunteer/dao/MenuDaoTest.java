package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.util.ResultData;

public class MenuDaoTest extends BaseTest {

	@Autowired
	private MenuDao menuDao;
	
	@Test
	public void findMenus(){
		List<Menu> list = menuDao.findMenus();
		ResultData data = new ResultData(true);
		data.put("data",list);
		System.out.println(data.toJSONString());
	}
	
	@Test
	public void findMenusByUserId(){
		long userId = 25L;
		List<Menu> list = menuDao.findMenusByUserId(userId);
		System.out.println(list);
	}
}
