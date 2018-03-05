package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;

public class ConfigDaoTest extends BaseTest {

	@Autowired
	private ConfigDao configDao;

	@Test
	public void getConfigType(){
		List<String> types=configDao.findAllConfigTypes();
		System.out.println(types);
	}
}
