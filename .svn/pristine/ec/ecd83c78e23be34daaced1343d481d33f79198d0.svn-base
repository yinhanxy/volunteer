package com.topstar.volunteer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Menu;

public class MenuServiceTest extends BaseTest{

	@Autowired	
	MenuService menuService;
	
	
	
	@Test
	public void updateMenuTest(){
		long menuid=11;
		Menu menu=menuService.selectByKey(menuid);
		menu.setParentId(1L);
		try {
			menuService.updateMenu(menu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
