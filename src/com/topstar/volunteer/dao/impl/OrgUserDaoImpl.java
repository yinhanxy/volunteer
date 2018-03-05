package com.topstar.volunteer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.OrgUserDao;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.mapper.OrgUserMapper;

@Repository
public class OrgUserDaoImpl extends BaseDaoImpl<OrgUser> implements OrgUserDao {

	@Autowired
	private OrgUserMapper orgUserMapper;
	
	@Override
	public Long findOrgIdByUserId(Long id) {
		if(id == null || id < 1){
			return null;
		}
		Long orgId=orgUserMapper.findOrgIdByUserId(id);
		if(orgId!=null && orgId>0){
			return orgId;
		}
		return null;
	}

}
