package com.topstar.volunteer.cache;

import java.util.List;

import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.entity.Role;

/**
 * 角色缓存接口，缓存系统内所有的角色信息，并缓存角色对应的菜单信息
 */
public interface RoleCache {
	
	public boolean init();
	
	/**
	 * 在缓存中查找对应的角色信息
	 * @param id 角色ID
	 * @return
	 */
	public Role findById(Long id);
	
	/**
	 * 新建角色时将角色添加到缓存中
	 * @param role 角色对象
	 * @return
	 */
	public boolean add(Role role);
	
	/**
	 * 删除角色时将角色从缓存中移除
	 * @param roleId
	 * @return
	 */
	public boolean delete(Long roleId);
	
	/**
	 * 修改角色基本信息和启动/禁用角色时，更新缓存中的角色
	 * @param role 修改后的角色信息
	 * @return
	 */
	public boolean update(Role role);

	/**
	 * 为角色添加菜单时将菜单信息添加到角色的缓存中<br/>
	 * 添加菜单时，必须为管理员角色增加对应的菜单信息
	 * @param roleId 角色编号
	 * @param menuId 菜单编号
	 * @return
	 */
	public boolean addRoleMenu(Long roleId,Long menuId);
	
	/**
	 * 修改菜单信息
	 * @param menu
	 * @return
	 */
	public boolean updateMenu(Menu menu);
	
	/**
	 * 为角色取消菜单权限时，将菜单信息从角色的缓存中移除
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public boolean removeRoleMenu(Long roleId,Long menuId);
	
	/**
	 * 删除菜单时，移除所有缓存角色的菜单权限
	 * @param menuId 菜单ID
	 * @return 
	 */
	public boolean removeMenu(Long menuId);
	
	/**
	 * 设置菜单是否可见时，同步设置所有角色缓存中对应的菜单是否可见
	 * @param menuId 菜单ID
	 * @param isShow 是否可见
	 * @return
	 */
	public boolean setShowMenu(Long menuId,Integer isShow);
	
	/**
	 * 批量添加角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean addChannelIds(Long roleId,List<Long> channelIds);
	
	/**
	 * 批量移除角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean removeChannelIds(Long roleId,List<Long> channelIds);
	
	/**
	 * 添加角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean addChannelId(Long roleId,Long channelId);
	
	/**
	 * 移除角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean removeChannelId(Long roleId,Long channelId);
	
	/**
	 * 获取可访问的栏目ID集合
	 * @param roleId
	 * @return
	 */
	public List<Long> getChannelIds(Long roleId);
	
}
