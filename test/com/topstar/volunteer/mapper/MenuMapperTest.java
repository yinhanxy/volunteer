package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Menu;

public class MenuMapperTest  extends BaseTest{

	@Autowired
	private MenuMapper menuMapper;
	
	@Test
	public void findMenuByRoleId(){
		Long roleId = 2L;
		List<Menu> menus = menuMapper.findMenuByRoleId(roleId);
		if(menus != null && !menus.isEmpty()){
			System.out.println(menus.size());
		}
		
	}
}
