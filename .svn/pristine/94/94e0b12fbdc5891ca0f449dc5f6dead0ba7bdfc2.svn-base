package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.UserMenuDao;
import com.topstar.volunteer.entity.UserMenu;
import com.topstar.volunteer.mapper.UserMenuMapper;

@Repository
public class UserMenuDaoImpl extends BaseDaoImpl<UserMenu> implements UserMenuDao {

	@Autowired
	private UserMenuMapper userMenuMapper;

	@Override
	public List<Long> findMenuIdsByUserId(Long userId) {
		List<Long> menuIds=userMenuMapper.findMenuIdsByUserId(userId);
		if(menuIds!=null && menuIds.size()>0){
			return menuIds;
		}
		return null;
	}
	
}
