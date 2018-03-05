package com.topstar.volunteer.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.User;

public class RoleServiceTest extends BaseTest{
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void selectRole(){
		long id=55l;
		Role role=roleService.selectByKey(id);
		System.out.println(role);
	}
	
	@Test
	public void updateRole(){
		Role role=new Role();
		role.setId(55l);
		role.setStatus(0);
		int row=roleService.updateNotNull(role);
		System.out.println(row);
	}
	
	@Test
	public void deleteRole(){
		long[] roleKeys=new long[]{6l};
		boolean row=roleService.deleteRoles(roleKeys);
		System.out.println(row);
	}
	
	@Test
	public void selectByExample(){
		String excludeKey="2";
		int exists=roleService.existsWithRoleName("超级管理员", excludeKey);
		System.out.println(exists);
	}
	
	@Test
	public void findAllRole(){
		List<Role> roles=roleService.getAllRoles();
		for (Role role : roles) {
			System.out.println(role);
		}
	}
	
	@Test
	public void saveRole(){
		Role role=new Role();
		role.setRoleName("测试数据");
		int row=roleService.insertNotNull(role);
		System.out.println(role);
		System.out.println(row);
	}
	
	@Test
	public void findUsersByRoleId(){
		
		PageInfo<User> page=roleService.getUsersByGivenRoleId("1", 0, "",10,1);
		System.out.println(page);
	}
	
	@Test
	public void getUsers(){
		PageInfo<User> users=roleService.getAllUsersIncludeRoleIds(null,1l,0,null,10, 1);
		List<User> userList=users.getList();
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void addUsersWithRole(){
		Long roleId=1l;
		List<Long> userIds=new ArrayList<Long>();
		userIds.add(20l);
		boolean result=roleService.addUsersWithRoleId(roleId, userIds);
		System.out.println(result);
	}
}
