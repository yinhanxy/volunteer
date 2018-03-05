package com.topstar.volunteer.dao;

import java.util.List;

import com.topstar.volunteer.entity.RoleChannel;

public interface RoleChannelDao extends BaseDao<RoleChannel>{

	public List<Long> getChannelIds(Long roleId);
	
	/**
	 * 根据角色信息删除栏目关联信息
	 * @param roleId
	 * @return
	 */
	public int deleteByRoleId(Long roleId);
	
	/**
	 * 根据栏目ID删除栏目关联信息
	 * @param channelId
	 * @return
	 */
	public int deleteByChannelId(Long channelId);
	
}
