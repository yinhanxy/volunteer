package com.topstar.volunteer.service;

import java.util.List;

import com.topstar.volunteer.entity.RoleUser;

public interface RoleUserService extends BaseService<RoleUser> {
	
	/**
	 * 根据用户ID删除该用户所赋予的角色
	 * @param userId
	 * @return
	 */
	public int deleteRoleUserByUser(Long userId);
	
	/**
	 * 根据用户ID获取该用户所具有的角色
	 * @param userId
	 * @return
	 */
	public List<RoleUser> getRoleUserByUser(Long userId);
	
	/**
	 * 根据角色Id获取所有被给予该角色的用户信息列表
	 * @param roleId 角色ID
	 * @return
	 */
	public List<RoleUser> getRoleUserByRoleId(Long roleId);
	
	/**
	 * 删除具备指定角色身份的用户
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	public Boolean deleteRoleUsersUnderRole(Long roleId,List<Long> userIds);
	
}
