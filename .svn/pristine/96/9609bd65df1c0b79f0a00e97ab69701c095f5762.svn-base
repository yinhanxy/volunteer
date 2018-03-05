package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.UserChannel;
import com.topstar.volunteer.util.BaseMapper;

public interface UserChannelMapper extends BaseMapper<UserChannel> {
	
	
	/**
	 * 通过用户ID查询可访问的栏目ID
	 * @param userId
	 * @return
	 */
	public List<Long> getChannelIds(@Param("userId")Long userId);
	
	
	/**
	 * 根据栏目ID删除可访问的栏目信息
	 * @param channelId
	 * @return
	 */
	public int deleteByChannelId(@Param("channelId")Long channelId);
}