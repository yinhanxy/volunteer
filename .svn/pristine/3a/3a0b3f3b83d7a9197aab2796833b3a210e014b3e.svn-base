package com.topstar.volunteer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.UserMenu;

public interface UserMenuDao extends BaseDao<UserMenu> {
	
	/**
	 * 根据用户Id获取该用户具有的菜单操作对应的菜单主键
	 * @param roleId 角色Key
	 * @return
	 */
	public List<Long> findMenuIdsByUserId(@Param("userId")Long userId);
}
