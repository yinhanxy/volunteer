package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;

public class OrgMapperTest  extends BaseTest{

	@Autowired
	private OrgMapper orgMapper;
	
	
	@Test
	public void getOrgIdLists(){
		Long orgId=1l;
		List<Long> list = orgMapper.getOrgIdLists(orgId);
		System.out.println(list.toString());
		System.out.println(list.contains(2l));
		
	}
}
