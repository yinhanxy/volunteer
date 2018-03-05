package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.exception.TPSException;


public interface RoleService extends BaseService<Role> {
	
	/**
	 * 根据角色实体字段查找符合条件的角色列表
	 * @param role 查询的角色实体条件
	 * @param orderBy
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 
	 */
	public PageInfo<Role> findByEntity(Role role,String orderBy, int page, int rows);
	
	/**
	 * 根据角色keys批量删除角色并删除系统中对应的缓存
	 * @param roleKeys 角色的主键值
	 * @return 
	 */
	public boolean deleteRoles(long[] roleKeys);
	
	
	/**
	 * 根据指定条件检查指定的角色名称是否已存在
	 * @param roleName 需要检查的角色名称
	 * @param excludeKey 需要排除在外的主键值对应的记录
	 * @return 0：不存在  1：存在
	 */
	public int existsWithRoleName(String roleName,String excludeKey);
	
	/**
	 * 通过角色名称查询对应的角色信息
	 * @param roleName 角色名称
	 * @return
	 */
	public Role findRoleByRoleName(String roleName);
	
	
	/**
	 * 获取所有的角色信息
	 * @return 角色实体列表
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 根据用户编号查询此用户拥有的角色信息
	 * @param userId 用户编号
	 * @return
	 */
	public List<Role> findRolesByUserId(Long userId);
	
	
	/**
	 * 添加角色并往系统中添加对应的缓存
	 * @param role
	 * @return
	 */
	public boolean addRole(Role role);
	
	
	/**
	 * 更新角色信息并更新系统中该角色的缓存信息
	 * @param role
	 * @return
	 */
	public boolean updateRole(Role role)throws TPSException;
	
	/**
	 * 根据角色ID和对查询结果的过滤条件查询该角色ID下符合指定过滤条件的用户列表
	 * @param roleId
	 * @param selectType 0：表示对结果没有过滤条件  1：对查询结果的用户名进行过滤  2：对查询结果的真实姓名进行过滤
	 * @param selectName 指定的过滤条件
	 * @param pageNum
	 * @param pageIndex
	 * @return
	 */
	public PageInfo<User> getUsersByGivenRoleId(String roleId,int selectType,String selectName,int pageNum, int pageIndex);
	
	/**
	 * 获取带有指定角色身份标识的所有用户信息列表
	 * @param orgId 指定的机构Id
	 * @param roleId 指定的角色身份roleId
	 * @param selectType 对返回结果的过滤条件类型
	 * @param selectName 对返回结果的过滤条件内容
	 * @param pageNum
	 * @param pageIndex 
	 * @return 若用户不具有指定角色，则返回的用户信息中roleIdList为空，反之，不为空
	 */
	public PageInfo<User> getAllUsersIncludeRoleIds(Long orgId,Long roleId,int selectType,String selectName,int pageNum, int pageIndex);
	
	/**
	 * 给指定的角色分配用户
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	public boolean addUsersWithRoleId(Long roleId,List<Long> userIds);
	
	/**
	 * 给指定的角色分配菜单操作权限
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	public boolean addRoleMenus(Long roleId,List<Long> menuIds);
	
	/**
	 * 根据角色Id获取具有的菜单角色ID
	 * @param roleId
	 * @return
	 */
	public List<Long> getMenuIdsByRoleId(Long roleId);
	
	/**
	 * 通过角色ID查询可访问的栏目ID
	 * @param roleId
	 * @return
	 */
	public List<Long> getChannelIds(Long roleId);
	
	/**
	 * 给指定的角色分配栏目可访问权限
	 * @param roleId
	 * @param channelIds
	 * @return
	 */
	public boolean addRoleChannels(Long roleId,List<Long> channelIds);
}
