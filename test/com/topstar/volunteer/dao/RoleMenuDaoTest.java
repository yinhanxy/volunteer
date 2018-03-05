package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.util.ResultData;

public class RoleMenuDaoTest extends BaseTest {

	@Autowired
	private RoleMenuDao rolemenuDao;
	
	@Test
	public void findMenuIds(){
		Long roleId=6l;
		List<Long> menuIds=rolemenuDao.findMenuIdsByRoleId(roleId);
		System.out.println(menuIds);
	}
	
}
