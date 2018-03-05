package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.RoleMenuDao;
import com.topstar.volunteer.entity.RoleMenu;
import com.topstar.volunteer.mapper.RoleMenuMapper;

@Repository
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu> implements RoleMenuDao {

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public List<Long> findMenuIdsByRoleId(Long roleId) {
		List<Long> menuIds=roleMenuMapper.findMenuIdsByRoleId(roleId);
		if(menuIds!=null && menuIds.size()>0){
			return menuIds;
		}
		return null;
	}
	
}
