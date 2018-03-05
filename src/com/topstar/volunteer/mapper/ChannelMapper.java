package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.util.BaseMapper;

public interface ChannelMapper extends BaseMapper<Channel> {
	
	List<Channel> findByChannelName(@Param("channelName")String channelName);
	
	/**
	 * 获取所有的栏目信息列表
	 * @return
	 */
	public List<Channel> findAllChannel();
	
	/**
	 * 获取指定站点下的所有的栏目列表
	 * @param siteId
	 * @return
	 */
	public List<Channel> findAllChannelsBySite(@Param("siteId")Long siteId);
	
	/**
	 * 根据栏目ID获取该栏目的所有上级栏目信息
	 * @return
	 */
	public List<Channel> findParentChannelsByChannelId(@Param("channelId")Long channelId);
	
	/**
	 * 获取指定栏目ID下的所有子栏目(包含自身)
	 * @param channelId
	 * @return
	 */
	public List<Long> findChildrenChannelsByChannelId(@Param("channelId")Long channelId);
	
	/**
	 * 查询指定上级栏目id下的下辖栏目信息列表
	 * @param parentId 上级区域id
	 * @return
	 */
	public List<Channel> findChannelByParentId(@Param("parentId")Long parentId,@Param("channelCondition")String channelCondition);
	
	/**
	 * 查询指定已回收的上级栏目的未被回收的子级栏目信息 
	 * @param parentId 上级栏目id
	 * @return
	 */
	public List<Channel> findChannelByRecycleParentId(@Param("parentId")Long parentId);
	
	/**
	 * 查询指定上级栏目的所有状态的所有子级栏目信息 
	 * @param parentId 上级栏目id
	 * @return
	 */
	public List<Channel> findAllStateChannelByParentId(@Param("parentId")Long parentId);
	
	/**
	 * 查询指定站点下的顶级栏目信息
	 * @param siteId 站点ID
	 * @return
	 */
	public List<Channel> findTopChannelBySiteId(@Param("siteId")Long siteId,@Param("channelCondition")String channelCondition);
	
	public int addChannel(Channel channel);
	
	/*public int updateArea(Area area);*/
	
	public int setStatus(@Param("channelId")Long channelId,@Param("status")Integer status);
	
	/**
	 * 查询回收站中的栏目列表
	 * @param channelName
	 * @return
	 */
	List<Channel> findRecycleChannels(@Param("channelId")Long channelId,@Param("channelName")String channelName);
	
	/**
	 * @param parentId
	 * @return
	 */
	public Long getMaxOrderNoByParentId(@Param("parentId")Long parentId);
	
	public int moveForwardChannel(@Param("channelOrder") Long channelOrder,@Param("previousOrder") Long previousOrder,@Param("parent_id") Long parent_id);
	
	public int moveBackwardChannel(@Param("channelOrder") Long channelOrder,@Param("previousOrder") Long previousOrder,@Param("parent_id") Long parent_id);
	
	
	/**
	 * 通过栏目ID获取所有正常状态子栏目的栏目ID集合，包含自身
	 * @param channelId 
	 * @return
	 */
	public List<Long> getAllChildrenChannelIds(@Param("channelId")Long channelId);
}