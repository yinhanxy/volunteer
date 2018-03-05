package com.topstar.volunteer.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.User;

public class UserMapperTest extends BaseTest{

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void existsWithPrimaryKey(){
		boolean exists = userMapper.existsWithPrimaryKey(1);
		System.out.println(exists);
	}
	
	@Test
	public void findById(){
		User user = new User();
		user.setId(1L);
		User u = userMapper.selectByPrimaryKey(1);
		System.out.println(u);
	}
	

	@Test
	public void add(){
		User user = new User();
		user.setMobile("13982718292");
		user.setRealName("测试");
		user.setRegTime(new Timestamp(new Date().getTime()));
		user.setRemark("备注测试");
		user.setStatus(1);
		user.setUserName("测试姓名");
		user.setNickName("昵称");
		user.setUserPwd("aadddd");
		user.setUseTime(new Timestamp(new Date().getTime()));
		user.setEmail("3333333@qq.com");
		int a = userMapper.insert(user);
		long b = user.getId();
		System.out.println(a);
		System.out.println(b);
	}
	
	@Test
	public void findByUserName(){
		List<User> users = userMapper.findByUserName("admin");
		System.out.println(users.get(0));
	}
	
	@Test
	public void findByInfo(){
		String userName = "admin";
		String nickName = "超级管理员";
		List<User> users = userMapper.findByInfo(userName, nickName);
		System.out.println(users.get(0));
	}
}
