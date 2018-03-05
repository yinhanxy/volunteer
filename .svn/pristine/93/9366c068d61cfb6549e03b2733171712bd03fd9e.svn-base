package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.User;

public interface UserDao extends BaseDao<User>{
	
	public List<User> selectByUser(User user, int page, int rows);
	
	public PageInfo<User> findByEntity(User user,String orderBy, int page, int rows);
	
	public PageInfo<User> findByEntityWithoutOrg(User user,String orderBy, int page, int rows);
	
	/**
	 * 查询用户拥有的角色编号
	 * @param id 角色编号集合
	 * @return
	 */
	public List<Long> findRoleIdsByUserId(Long id);
	
	
	public User findByUserName(String userName);
	
	/**
	 * 根据角色ID获取对应角色的用户列表
	 * @param roleId 角色ID
	 * @return 包含用户列表的分页信息
	 */
	
	
	/**
	 * 根据角色ID和对查询结果的过滤条件查询该角色ID下符合指定过滤条件的用户列表
	 * @param roleId
	 * @param userName
	 * @param realName
	 * @param pageNum
	 * @param pageIndex
	 * @return
	 */
	public PageInfo<User> findUsersByRoleId(String roleId,String userName,String realName,int pageNum, int pageIndex);
	
	/**
	 * 根据机构ID和对查询结果的过滤条件查询该机构ID下符合指定过滤条件的用户列表
	 * @param orgId
	 * @param userName
	 * @param realName
	 * @param pageNum
	 * @param pageIndex
	 * @return
	 */
	public PageInfo<User> findUsersByOrgId(Long orgId,String userName,String realName,int pageNum, int pageIndex);
	
	/**
	 * 根据用户id修改用户的密码
	 * @param id
	 * @return 
	 */
	public int editUserPwd(Long id,String userPwd);
	
	/**
	 * 根据机构Id得到当前机构下且还没被添加到服务队的用户列表
	 * @param orgId
	 * @param user
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<User> getUsersByOrgId(Long orgId,User user, int page, int rows);
	
	/**
	 * 根据用户ID获取可访问的栏目ID集合
	 * @param userId
	 * @return
	 */
	public List<Long> getChannelIds(Long userId);
	
	
	/**
	 * 得到未被分配到服务队的用户列表
	 * @param user
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<User> getUsersBySer(User user,String orderBy, int page, int rows);
}
