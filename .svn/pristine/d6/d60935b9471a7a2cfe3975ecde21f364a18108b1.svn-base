package com.topstar.volunteer.dao;

import java.util.List;

import com.topstar.volunteer.entity.Menu;

public interface MenuDao extends BaseDao<Menu> {
	
	/**
	 * 查询所有的菜单信息
	 * @return
	 */
	List<Menu> findMenus();
	
	/**
	 * 通过用户编号查询此用户拥有的菜单信息
	 * @param userId 用户编号
	 * @return
	 */
	List<Menu> findMenusByUserId(Long userId);
	
	/**
	 * 通过角色编号查询此角色的菜单信息
	 * @param roleId 角色编号
	 * @return
	 */
	List<Menu> findMenuByRoleId(Long roleId); 
	
	/**
	 * 通过父级菜单ID获取所有子菜单信息（一级）
	 * @param parentId 父级菜单Id
	 * @return
	 */
	List<Menu> findMenuByParentId(Long parentId); 

}
