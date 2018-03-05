package com.topstar.volunteer.cache;

import java.util.List;

import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.User;

/**
 * 登录用户缓存接口，缓存本系统登录的所有用户，并缓存用户拥有的角色信息和用户本身拥有的菜单信息
 */
public interface LoginUserCache {
	
	public boolean init();

	/**
	 * 添加用户<br/>
	 * 用户登录后添加到缓存中
	 * @param user
	 * @return
	 */
	public boolean add(User user);

	/**
	 * 修改登录用户基本信息时，更新缓存中的用户信息
	 * @param user 修改后的用户信息
	 * @return
	 */
	public boolean update(User user);
	
	/**
	 * 删除用户<br/>
	 * 用户退出后从缓存中移除
	 * @param userId
	 * @return
	 */
	public boolean delete(Long userId);
	
	/**
	 * 添加菜单<br/>
	 * 为用户单独设置菜单权限时，将此权限添加到缓存的对应的用户中
	 * @param userId 用户编号
	 * @param menuId 菜单编号
	 * @return
	 */
	public boolean addMenu(Long userId,Long menuId);
	
	/**
	 * 删除菜单<br/>
	 * 删除用户的菜单权限时，将此权限信息从缓存中移除
	 * @param userId 用户编号
	 * @param menuId 菜单编号
	 * @return
	 */
	public boolean removeMenu(Long userId,Long menuId);
	
	/**
	 * 设置菜单是否可见时，同步设置所有用户缓存中对应的菜单是否可见
	 * @param menuId 菜单编号
	 * @param isShow 是否可见
	 * @return
	 */
	public boolean setShowMenu(Long menuId,Integer isShow);
	
	/**
	 * 当修改菜单信息时，更新缓存中的菜单信息
	 * @param menu 菜单信息
	 * @return
	 */
	public boolean updateMenu(Menu menu);
	

	/**
	 * 系统删除菜单时，删除缓存中对应的菜单信息
	 * @param menuId
	 * @return
	 */
	public boolean removeMenu(Long menuId);
	
	/**
	 * 添加角色<br/>
	 * 当为用户添加角色时，如果用户已登录，将角色信息更新到缓存中
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return
	 */
	public boolean addRole(Long userId,Long roleId);
	
	/**
	 * 添加角色<br/>
	 * 当为用户添加角色时，如果用户已登录，将角色信息更新到缓存中
	 * @param userId 用户编号
	 * @param roleId 角色编号数组
	 * @return
	 */
	public boolean addRoles(Long userId,Long[] roleIds);
	
	
	/**
	 * 删除角色时，为所有缓存的用户移除对应的角色<br/>
	 * 设置角色是否启用时不需要
	 * @param roleId
	 * @return
	 */
	public boolean removeRole(Long roleId);
	
	
	/**
	 * 移除角色<br/>
	 * 当用户删除角色时，如果用户已登录，将角色信息从缓存中移除
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return
	 */
	public boolean removeRole(Long userId,Long roleId);
	
	/**
	 * 移除角色<br/>
	 * 当用户删除角色时，如果用户已登录，将角色信息从缓存中移除
	 * @param userId 用户编号
	 * @param roleId 角色编号数组
	 * @return
	 */
	public boolean removeRoles(Long userId,Long[] roleIds);
	
	/**
	 * 更新角色信息<br/>
	 * 当为用户更新角色时，如果用户已登录，将角色信息在缓存中更新<br/>
	 * 清空用户对应的角色编号数组信息，并将roleIds写入到缓存中<br/>
	 * 如果角色数组为空，清除此用户的所有角色信息
	 * @param userId 用户编号
	 * @param roleIds 需要更新的角色编号数组,数组为空，清除此用户的所有角色信息
	 * @return
	 */
	public boolean updateRoles(Long userId,Long[] roleIds);
	
	/**
	 * 从缓存中读取用户信息和对应的所属角色信息和菜单信息
	 * @param userId 用户编号
	 * @return
	 */
	public User findById(Long userId);
	
	/**
	 * 批量添加用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean addChannelIds(Long userId,List<Long> channelIds);
	
	/**
	 * 批量移除用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean removeChannelIds(Long userId,List<Long> channelIds);
	
	/**
	 * 添加用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean addChannelId(Long userId,Long channelId);
	
	/**
	 * 移除用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean removeChannelId(Long userId,Long channelId);
	
}
