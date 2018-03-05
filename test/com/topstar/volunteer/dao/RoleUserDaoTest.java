package com.topstar.volunteer.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.RoleUser;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class RoleUserDaoTest extends BaseTest{

	@Autowired
	private RoleUserDao roleUserDao;

	@Test
	public void delRoleUser(){
		Example example=new Example(RoleUser.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId", 29);
		int delRow=roleUserDao.deleteByExample(example);
		System.out.println(delRow);
	}
}
