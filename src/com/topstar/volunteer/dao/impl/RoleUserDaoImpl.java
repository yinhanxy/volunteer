package com.topstar.volunteer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.RoleUserDao;
import com.topstar.volunteer.entity.RoleUser;
import com.topstar.volunteer.mapper.RoleUserMapper;

@Repository
public class RoleUserDaoImpl extends BaseDaoImpl<RoleUser> implements RoleUserDao {

	@Autowired
	private RoleUserMapper roleUserMapper;
	
}
