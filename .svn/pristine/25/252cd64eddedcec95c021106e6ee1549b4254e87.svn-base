package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.UserChannelDao;
import com.topstar.volunteer.entity.UserChannel;
import com.topstar.volunteer.mapper.UserChannelMapper;

@Repository
public class UserChannelDaoImpl extends BaseDaoImpl<UserChannel> implements UserChannelDao {

	@Autowired
	private UserChannelMapper userChannelMapper;

	/**
	 * 通过用户ID查询可访问的栏目ID
	 * @param userId
	 * @return
	 */
	public List<Long> getChannelIds(Long userId){
		if(userId !=null && userId.longValue() > 0){
			return userChannelMapper.getChannelIds(userId);
		}
		return null;
	}
	
	/**
	 * 根据栏目ID删除可访问的栏目信息
	 * @param channelId
	 * @return
	 */
	public int deleteByChannelId(Long channelId){
		if(channelId != null && channelId.longValue() > 0){
			return userChannelMapper.deleteByChannelId(channelId);
		}
		return -1;
	}
}
