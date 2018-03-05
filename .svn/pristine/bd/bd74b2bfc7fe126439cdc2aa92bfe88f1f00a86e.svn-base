package com.topstar.volunteer.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.entity.Config;

public class ConfigServiceTest extends BaseTest{

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private ConfigCache configCache;
	
	@Test
	public void getConfigByName(){
		Config config=configCache.getConfigValueByName("DEFAULT_PASSWORD");
		System.out.println(config);
	}
	
	@Test
	public void getConfigPage(){
		Config config =new Config();
		config.setName("PASSWORD");
		PageInfo<Config> configPage=configService.loadConfigPage(config, 0, 0);
		List<Config> configs=configPage.getList();
		for (Config config1 : configs) {
			System.out.println(config1);
		}
	}
	
}
