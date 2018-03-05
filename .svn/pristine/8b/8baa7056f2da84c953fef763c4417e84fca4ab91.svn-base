package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.MenuDao;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.mapper.MenuMapper;

@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> findMenus() {
		List<Menu> list = menuMapper.findMenus();
		return list;
	}
	
	/**
	 * 通过用户编号查询此用户拥有的菜单信息
	 * @param userId 用户编号
	 * @return
	 */
	@Override
	public List<Menu> findMenusByUserId(Long userId) {
		if(userId == null || userId.longValue() < 1){
			return null;
		}
		List<Menu> list = menuMapper.findMenusByUserId(userId);
		return list;
	}

	
	/**
	 * 通过角色编号查询此角色的菜单信息
	 * @param roleId 角色编号
	 * @return
	 */
	public List<Menu> findMenuByRoleId(Long roleId){
		if(roleId == null || roleId.longValue() < 1){
			return null;
		}
		return menuMapper.findMenuByRoleId(roleId);
	}
	
	/**
	 * 通过父级菜单ID获取所有子菜单信息（一级）
	 * @param parentId 父级菜单Id
	 * @return
	 */
	public List<Menu> findMenuByParentId(Long parentId) {
		return  menuMapper.findMenuByParentId(parentId);
	}

}
