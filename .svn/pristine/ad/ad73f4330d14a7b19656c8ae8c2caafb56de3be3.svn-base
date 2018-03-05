package com.topstar.volunteer.cache;

import java.util.List;

import com.topstar.volunteer.entity.Channel;

public interface ChannelCache {
	
	public boolean init();
	
	/**
	 * 在缓存中查找对应的栏目信息
	 * @param id 栏目ID
	 * @return
	 */
	public Channel findById(Long id);
	
	/**
	 * 新建栏目时将栏目添加到缓存中
	 * @param channel 栏目对象
	 * @return
	 */
	public boolean add(Channel channel);
	
	/**
	 * 删除栏目时将栏目从缓存中移除
	 * @param channelId
	 * @return
	 */
	public boolean delete(Long channelId);
	
	/**
	 * 修改栏目基本信息时，更新缓存中的栏目
	 * @param channel 修改后的栏目信息
	 * @return
	 */
	public boolean update(Channel channel);
	
	/**
	 * 从缓存中获取所有不在回收站的栏目信息
	 * @return
	 */
	public List<Channel> getAllNotInRecycle();
	
	/**
	 * 从缓存中获取所有不在回收站的栏目信息
	 * @return
	 */
	public List<Channel> getAllBySiteIdNotInRecycle(Long siteId);
	
	/**
	 * 从缓存中获取所有不在回收站的栏目ID
	 * @return
	 */
	public List<Long> getAllChannelIdsNotInRecycle(Long siteId);
	
	
	/**
	 * 从缓存中获取栏目信息
	 * @param status : 栏目状态
	 * @return
	 */
	public List<Channel> getAll(Integer status);
}
