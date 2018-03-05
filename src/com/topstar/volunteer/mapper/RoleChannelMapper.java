package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.RoleChannel;
import com.topstar.volunteer.util.BaseMapper;

public interface RoleChannelMapper extends BaseMapper<RoleChannel> {
	
	/**
	 * 通过角色ID查询可访问的栏目ID
	 * @param roleId
	 * @return
	 */
	public List<Long> getChannelIds(@Param("roleId")Long roleId);
	
	
	/**
	 * 根据角色信息删除栏目关联信息
	 * @param roleId
	 * @return
	 */
	public int deleteByRoleId(@Param("roleId")Long roleId);
	
	/**
	 * 根据栏目ID删除栏目关联信息
	 * @param channelId
	 * @return
	 */
	public int deleteByChannelId(@Param("channelId")Long channelId);
	
}