package com.topstar.volunteer.dao;

import java.util.List;

import com.topstar.volunteer.entity.UserChannel;

public interface UserChannelDao extends BaseDao<UserChannel> {

	/**
	 * 通过用户ID查询可访问的栏目ID
	 * @param userId
	 * @return
	 */
	public List<Long> getChannelIds(Long userId);
	
	/**
	 * 根据栏目ID删除可访问的栏目信息
	 * @param channelId
	 * @return
	 */
	public int deleteByChannelId(Long channelId);
}
