package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;

public class RoleUserMapperTest extends BaseTest {

	@Autowired
	private RoleUserMapper roleUserMapper;
	
	@Test
	public void findRoleIdsByUserId(){
		Long userId = 2L;
		List<Long> roleIdList = roleUserMapper.findRoleIdsByUserId(userId);
		if(roleIdList != null){
			System.out.println(roleIdList.size());
		}
	}
}
