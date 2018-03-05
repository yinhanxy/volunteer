package com.topstar.volunteer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Channel;

public interface ChannelDao extends BaseDao<Channel>{
	
	public PageInfo<Channel> findByChannelName(String channelName,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 查询所有的栏目信息对象
	 * @return
	 */
	List<Channel> selectAllArea();	
	
	/**
	 * 获取指定站点下的所有的栏目列表
	 * @param siteId
	 * @return
	 */
	public List<Channel> findAllChannelsBySite(Long siteId);
	
	/**
	 * 根据栏目ID获取该栏目的所有上级栏目信息
	 * @return
	 */
	public List<Channel> findParentChannelsByChannelId(Long channelId);
	
	
	/**
	 * 查询指定上级栏目id下的下辖栏目信息列表
	 * @param parentId 上级栏目id
	 * @return
	 */
	public List<Channel> findChannelByParentId(Long parentId);
	
	/**
	 * 查询指定站点下的顶级栏目信息
	 * @param siteId 站点ID
	 * @return
	 */
	public List<Channel> findTopChannelBySiteId(Long siteId);
	
	/**
	 * 添加栏目信息
	 * @param channel
	 * @return
	 */
	public int addChannel(Channel channel);
	
	/**
	 * 更新区域信息
	 * @param area
	 * @return
	 *//*
	public int updateArea(Area area);*/
	
	/**
	 * 改变栏目状态
	 * @param channelId
	 * @return
	 */
	public int setStatus(Long channelId,Integer status);

	/**
	 * 根据指定过滤条件分页查询指定站点或栏目的直接子栏目列表
	 * @param parentId 需要查询的直接子栏目的站点id或栏目id
	 * @param channelCondition
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Channel> findSubChannelsByParentId(Long parentId, String channelCondition,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 查询指定已回收的上级栏目的未被回收的直接子级栏目信息 
	 * @param parentId 上级区域id
	 * @return
	 */
	public List<Channel> findChannelByRecycleParentId(Long parentId);
	
	/**
	 * 查询指定上级栏目的所有状态的所有子级栏目信息 
	 * @param parentId 上级栏目id
	 * @return
	 */
	public List<Channel> findAllStateChannelByParentId(Long parentId);
	
	/**
	 * 查询指定栏目下已被删除的直接子栏目列表
	 */
	public PageInfo<Channel> getRecycleChannels(Long channelId,String channelName,String orderBy, int pageIndex, int pageSize);

	/**
	 * 查询指定栏目下直接子栏目中最大的排序号
	 * @param parentId
	 * @return
	 */
	public Long getMaxOrderNoByParentId(Long parentId);
	
	/**
	 *  向前移动栏目
	 * @param channelOrder 操作的栏目排序号
	 * @param previousOrder 
	 * @param parent_id 父栏目ID
	 * @return
	 */
	public int moveForwardChannel( Long channelOrder, Long previousOrder,Long parent_id);
	
	/**
	 *  向后移动栏目
	 * @param channelOrder 操作的栏目排序号
	 * @param previousOrder 
	 * @param parent_id 父栏目ID
	 * @return
	 */
	public int moveBackwardChannel( Long channelOrder, Long previousOrder,Long parent_id);
	
	/**
	 * 通过栏目ID获取所有正常状态子栏目的栏目ID集合，包含自身
	 * @param channelId 
	 * @return
	 */
	public List<Long> getAllChildrenChannelIds(Long channelId);
}
