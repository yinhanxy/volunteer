package com.topstar.volunteer.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.dao.RoleUserDao;
import com.topstar.volunteer.entity.RoleUser;
import com.topstar.volunteer.service.RoleUserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUser> implements RoleUserService {
	
	@Autowired
	private RoleUserDao roleUserDao;

	@Autowired
	private LoginUserCache loginUserCache;
	/**
	 * 根据用户ID删除该用户所赋予的角色
	 * @param userId
	 * @return
	 */
	@Override
	public int deleteRoleUserByUser(Long userId) {
		Example example=new Example(RoleUser.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId", userId);
		return roleUserDao.deleteByExample(example);
	}
	
	/**
	 * 根据用户ID获取该用户所具有的角色
	 * @param userId
	 * @return
	 */
	@Override
	public List<RoleUser> getRoleUserByUser(Long userId) {
		Example example=new Example(RoleUser.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId", userId);
		List<RoleUser> roleUsers=roleUserDao.selectByExample(example);
		if(roleUsers!=null && roleUsers.size()!=0){
			return roleUsers;
		}
		return null;
	}
	
	/**
	 * 根据角色Id获取所有被给予该角色的用户信息列表
	 * @param roleId 角色ID
	 * @return
	 */
	public List<RoleUser> getRoleUserByRoleId(Long roleId){
		Example example=new Example(RoleUser.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("roleId", roleId);
		List<RoleUser> roleUsers=roleUserDao.selectByExample(example);
		if(roleUsers!=null && roleUsers.size()!=0){
			return roleUsers;
		}
		return null;
	}

	@Override
	public Boolean deleteRoleUsersUnderRole(Long roleId, List<Long> userIds) {
		if(roleId!=null && userIds!=null && userIds.size()>0){
			Example example=new Example(RoleUser.class);
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("roleId", roleId);
			criteria.andIn("userId", userIds);
			int delRoleUser=deleteByExample(example);
			if(userIds.size()==delRoleUser){
				for (Long userId : userIds) {
					loginUserCache.removeRole(userId, roleId);
				}
				return true;
			}
		}
		return false;
	}

}
