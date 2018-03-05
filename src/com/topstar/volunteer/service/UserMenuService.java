package com.topstar.volunteer.service;

import java.util.List;

import com.topstar.volunteer.entity.UserMenu;
import com.topstar.volunteer.model.MenuView;

public interface UserMenuService extends BaseService<UserMenu> {
	
	/**
	 * 根据用户Id获取用户本身具有操作权限的菜单ID(仅包含用户本身的权限菜单信息)
	 * @param roleId
	 * @return
	 */
	public List<Long> getMenuIdsByUserId(Long userId);
	
	/**
	 * 根据用户Id获取用户角色的操作权限的菜单ID(仅包含用户角色的权限菜单信息)
	 * @param roleId
	 * @return
	 */
	public List<Long> getUserRoleMenuIdsByUserId(Long userId);

	/**
	 * 给指定的用户分配菜单操作权限
	 * @param userId
	 * @param menuIds
	 * @return
	 */
	public Boolean addUserMenus(Long userId, List<Long> menuKeys);

}
