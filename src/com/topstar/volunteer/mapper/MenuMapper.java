package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.util.BaseMapper;

public interface MenuMapper extends BaseMapper<Menu> {
	
	/**
	 * 显示菜单类型的菜单
	 * @return
	 */
	List<Menu> findMenus();
	
	/**
	 * 通过用户编号查询此用户拥有的菜单信息
	 * @param userId 用户编号
	 * @return
	 */
	List<Menu> findMenusByUserId(@Param("userId")Long userId);
	
	/**
	 * 通过角色编号查询此角色的菜单信息
	 * @param menuId 角色编号
	 * @return
	 */
	List<Menu> findMenuByRoleId(@Param("roleId")Long roleId);
	
	/**
	 * 通过父级菜单ID获取所有子菜单信息（一级）
	 * @param parentId 父级菜单Id
	 * @return
	 */
	List<Menu> findMenuByParentId(@Param("parentId")Long parentId);
	
}