package com.topstar.volunteer.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.entity.Config;

public class UserServiceTest extends BaseTest{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConfigCache configCache;
	
	@Test
	public void getConfigByName(){
		Config config=configCache.getConfigValueByName("DEFAULT_PASSWORD");
		System.out.println(config);
	}
	
	@Test
	public void resetPassword(){
		long[] ids={18l};
		int rows=userService.resetUsersPassword(ids);
		System.out.println(rows);
	}
	
}
