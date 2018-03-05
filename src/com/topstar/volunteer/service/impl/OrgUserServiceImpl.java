package com.topstar.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.dao.OrgUserDao;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.RoleUser;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OrgUserServiceImpl extends BaseServiceImpl<OrgUser> implements OrgUserService {
	
	@Autowired
	private OrgUserDao orgUserDao;

	@Override
	public OrgUser getOrgUserByUserId(Long userId) {
		Example example=new Example(OrgUser.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId", userId);
		List<OrgUser> orgUsers=orgUserDao.selectByExample(example);
		if(orgUsers!=null && orgUsers.size()!=0){
			return orgUsers.get(0);
		}
		return null;
	}

	@Override
	public Boolean deleteOrgUsersUnderOrg(Long orgId, List<Long> userIds) {
		if(orgId!=null && userIds!=null && userIds.size()>0){
			Example example=new Example(OrgUser.class);
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("orgId", orgId);
			criteria.andIn("userId", userIds);
			int delOrgUser=deleteByExample(example);
			if(userIds.size()==delOrgUser){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addUsersWithOrgId(Long orgId, List<Long> userIds) {
		try{
			if(orgId ==null || orgId<0){
				return false;
			}
			BaseUser crtUser=ShiroSessionMgr.getLoginUser();
			if(crtUser==null){
				return false;
			}
			if(userIds!=null && userIds.size()>0){
				int flag=userIds.size();
				for (Long userId : userIds) {
					OrgUser orgUser=new OrgUser();
					orgUser.setOrgId(orgId);
					orgUser.setCrUser(crtUser.getUserName());
					orgUser.setCrTime(new Timestamp(new Date().getTime()));
					orgUser.setUserId(userId);
					int addOrgUser=orgUserDao.insert(orgUser);
					if(addOrgUser>0){
						flag--;
					}
				}
				if(flag==0){
					return true;
				}
			}
		}catch (Exception e) {
			throw new RuntimeException("用户分配组织出错",e);
		}
		return false;
	}
	
	@Override
	public int existsWithUserOrg(Long userId){
		Example example=new Example(OrgUser.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId", userId);
		List<OrgUser> orgUsers=selectByExample(example);
		if(orgUsers!=null && orgUsers.size()!=0){
			return 1;
		}
		return 0;
	}
	
}
