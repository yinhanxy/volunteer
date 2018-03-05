package com.topstar.volunteer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.RoleMenu;

public interface RoleMenuDao extends BaseDao<RoleMenu> {
	
	/**
	 * 根据角色Id获取该角色具有的菜单操作对应的菜单主键
	 * @param roleId 角色Key
	 * @return
	 */
	public List<Long> findMenuIdsByRoleId(@Param("roleId")Long roleId);
}
