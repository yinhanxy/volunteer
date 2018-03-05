package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.RoleChannelDao;
import com.topstar.volunteer.entity.RoleChannel;
import com.topstar.volunteer.mapper.RoleChannelMapper;

@Repository
public class RoleChannelDaoImpl extends BaseDaoImpl<RoleChannel> implements RoleChannelDao {

	@Autowired
	private RoleChannelMapper roleChannelMapper;
	

	public List<Long> getChannelIds(Long roleId){
		if(roleId != null && roleId.longValue() > 0){
			return roleChannelMapper.getChannelIds(roleId);
		}
		return null;
	}
	
	/**
	 * 根据角色信息删除栏目关联信息
	 * @param roleId
	 * @return
	 */
	public int deleteByRoleId(Long roleId){
		if(roleId != null && roleId.longValue() > 0){
			return roleChannelMapper.deleteByRoleId(roleId);
		}
		return 0;
	}
	
	/**
	 * 根据栏目ID删除栏目关联信息
	 * @param channelId
	 * @return
	 */
	public int deleteByChannelId(Long channelId){
		if(channelId != null && channelId.longValue() > 0){
			return roleChannelMapper.deleteByChannelId(channelId);
		}
		return -1;
	}
	
}
