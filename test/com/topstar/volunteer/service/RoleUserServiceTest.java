package com.topstar.volunteer.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.RoleUser;

public class RoleUserServiceTest extends BaseTest{
	
	@Autowired
	private RoleUserService roleUserService;

	@Test
	public void getRoleUsers(){
		Long roleId=2l;
		List<RoleUser> roleUsers=roleUserService.getRoleUserByRoleId(roleId);
		System.out.println(roleUsers);
	}
	
	@Test
	public void delRoleUser(){
		Long roleId=2l;
		List<Long> userIds=new ArrayList<Long>();
		userIds.add(5l);
		userIds.add(6l);
		userIds.add(7l);
		boolean del=roleUserService.deleteRoleUsersUnderRole(roleId, userIds);
		System.out.println(del);
	}
}
