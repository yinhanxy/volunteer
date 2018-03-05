package com.topstar.volunteer.service;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.MenuView;


public interface UserService extends BaseService<User> {
	
	public List<User> selectByUser(User user, int page, int rows);
	
	public PageInfo<User> findByEntity(User user,String orderBy, int page, int rows);
	
	public User findByUserName(String userName);
	
	/**
	 * 根据用户keys批量删除用户
	 * @param userKeys 用户的主键值数组
	 * @return 
	 */
	public boolean deleteUsers(long[] userKeys);
	
	/**
	 * 根据指定条件检查指定的用户名称是否已存在
	 * @param userName 需要检查的用户名称
	 * @param excludeKey 需要排除在外的主键值对应的记录
	 * @return -1：不存在  1：存在 0：系统管理员
	 */
	public int existsWithUserName(String userName,String excludeKey);
	
	/**
	 * 添加用户及给予用户角色身份
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public Boolean addUser(User user,List<Long> roleIds);

	/**
	 * 根据用户keys批量改变用户的状态
	 * @param userKeys 用户的主键值
	 * @param status 用户的实现状态
	 * @return 操作影响的行数
	 */
	public int setUsersStatus(String userKeys,int status);
	
	/**
	 * 修改用户及用户角色身份
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public boolean updateUser(User user,List<Long> roleIds);
	
	/**
	 * 检查指定的用户是否被赋予指定的角色
	 * @param userId 用户ID 
	 * @param roleId 角色ID
	 * @return
	 */
	public boolean existsWithRoleUser(Long userId,Long roleId);
	
	/**
	 * 用户登录成功后，将用户添加到缓存中
	 * @param user 用户信息
	 * @return
	 */
	public boolean addUserToCache(User user);
	
	/**
	 * 用户退出时将用户从缓存中移除
	 * @param userId
	 * @return
	 */
	public boolean removeUserToCache(Long userId);
	
	/**
	 * 根据用户编号查询此用户拥有的角色名称
	 * @param id 用户编号
	 * @return
	 */
	public Set<String> findRoleNames(Long id);
	
	/**
	 * 根据用户编号查询此用户可访问的权限请求信息
	 * @param id 用户编号
	 * @return
	 */
	public Set<String> findPermissions(Long id);
	
	/**
	 * 根据用户编号查询此用户可访问的菜单类型的菜单信息
	 * @param id
	 * @return
	 */
	public List<MenuView> findMenusByUserId(Long id);

	/**
	 * 根据用户的主键值重置用户的密码为初始值密码
	 * @param ids 重置密码所有操作用户的主键值
	 * @return 是否重置密码操作成功，成功返回：0
	 */
	public int resetUsersPassword(long[] ids);
	
	/**
	 * 根据用户id修改用户的密码
	 * @param id
	 * @return 
	 */
	public int editUserPwd(Long id,String userPwd);
	
	/**
	 * 通过用户ID查询可访问的栏目ID
	 * @param userId
	 * @return
	 */
	public List<Long> getChannelIds(Long userId);
	
	/**
	 * 给指定的用户分配栏目可访问权限
	 * @param userId
	 * @param channelIds
	 * @return
	 */
	public boolean addUserChannels(Long userId,List<Long> channelIds);
	
	/**
	 * 获取已登录用户可访问的栏目集合
	 * @return
	 */
	public List<Channel> getAllChannelNotInRecycle();
	
	/**
	 * 获取已登录用户可访问的栏目集合
	 * @param siteId 站点ID
	 * @return
	 */
	public List<Channel> getAllChannelNotInRecycle(Long siteId);
	
	/**
	 * 获取已登录用户可访问的栏目集合
	 * @param siteId 站点ID
	 * @return
	 */
	public List<Long> getAllChannelIdsNotINRecycle(Long siteId);
}
