package com.topstar.volunteer.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.model.ChannelView;

public interface ChannelService extends BaseService<Channel>{
	
	/**
	 * 获取所有站点下的顶级栏目
	 * @return
	 */
	public List<ChannelView> findTopChannelBySiteId();
	
	/**
	 * 根据栏目ID获取该栏目的所有上级栏目信息
	 * @return
	 */
	public List<Channel> getParentChannelsByChannelId(Long channelId);
	
	/**
	 * 获取栏目移动操作的栏目结构视图信息
	 * @return
	 */
	public List<ChannelView> getChannelMoveTree(Long channelId); 
	
	/**
	 * 查询指定上级栏目id下的下辖栏目信息列表
	 * @param parentId 上级栏目id
	 * @return
	 */
	public List<ChannelView> findChannelByParentId(Long parentId);
	
	/**
	 * 根据栏目名称字段查找符合条件的栏目列表
	 * @param channelName 查询的栏目名称
	 * @param orderBy
	 * @param pageIndex 查询的页码
	 * @param pageSize 页码的显示条数
	 * @return 
	 */
	public PageInfo<Channel> findByChannelName(String channelName,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 查询当前栏目的兄弟栏目(当前栏目除外)
	 * @param channel 
	 * @return
	 */
	List<Channel> getBroChannelsByParentId(Channel channel);
	
	/**
	 * 根据栏目ID查找子栏目下的直接子栏目列表
	 * @param parentId 查询的父栏目ID
	 * @param orderBy
	 * @param pageIndex 查询的页码
	 * @param pageSize 页码的显示条数
	 * @return 
	 */
	public PageInfo<Channel> findSubChannelsByParentId(Long parentId, String channelCondition,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 获取指定栏目下所有子栏目(不包含当前栏目)
	 * @param parentId 上级栏目id
	 * @return
	 */
	public void getAllChannelsListByParentId(Long parentId,List<Channel> channelList,Long exculdeSpecialChannelId);
	
	/**
	 * 获取指定栏目的直接子栏目列表
	 * @param parentId 上级栏目id
	 * @return
	 */
	public List<ChannelView> getChannelViewsByParentId(Long parentId);
	
	/**
	 * 获取所有站点下的所有顶级栏目
	 * @return
	 */
	public List<ChannelView> getSitesBySiteIdIncTopChannels();
	
	/**
	 * 获取站点下的所有栏目
	 * @return
	 */
	public List<ChannelView> getAllChannelBySiteId();
	
	/**
	 * 从缓存中获取站点下的所有栏目
	 * @return
	 */
	public List<ChannelView> getAllChannelBySiteIdInCache();
	
	/**
	 * 添加栏目
	 * @param channel
	 * @return
	 */
	public Map<String,Object> addChannel(Channel channel,Long previousId);
	
	/**
	 * 彻底删除指定栏目及其子栏目
	 * @param delChnnelIds 需要删除的栏目信息的唯一标识数组
	 * @return
	 */
	public boolean delChannelAndSubChnnel(Long[] delChnnelIds);
	
	/**
	 * 还原指定栏目及其子栏目
	 * @param restoreChnnelIds 需要还原的栏目信息的唯一标识数组
	 * @return
	 */
	public boolean restoreChannelAndSubChnnel(Long[] restoreChnnelIds);
	
	/**
	 * 更新保存栏目信息
	 * @param channel 更新的栏目实体信息
	 * @return
	 */
	public Map<String,Object> saveChannel(Channel channel,Long previousId);
	
	/**
	 * 将指定的栏目移动到目标栏目下
	 * @param channelId
	 * @return
	 */
	public boolean moveChannelToChannel(Long srcChannelId,Long targetChannelId);
	
	/**
	 * 获取当前栏目下的所有上级栏目
	 * @param channel 当前栏目
	 * @return
	 */
	public void getParentChannelsWithCurrChannel(Channel channel,List<Channel> channels);
	
	public List<ChannelView> getAllChannels(Long channelId);

	/**
	 * 将指定的栏目放入回收站中
	 * @param channelId
	 * @return
	 */
	public boolean saveChannelRecycle(Long channelId);
	
	/**
	 * 根据栏目名称字段在栏目回收站中查找符合条件的直接子栏目信息
	 * @param channelId 查询指定栏目ID下已回收的直接子栏目信息
	 * @param channelName 查询的栏目名称
	 * @param orderBy
	 * @param pageIndex 查询的页码
	 * @param pageSize 页码的显示条数
	 * @return 
	 */
	public PageInfo<Channel> getRecycleChannels(Long channelId,String channelName,String orderBy, int pageIndex, int pageSize);

	/**
	 * 查询指定已回收的上级栏目的未被回收的所有子级栏目信息 (不包含当前栏目)
	 * @param parentId 上级栏目ID
	 * @param channelList 获取结果集对象
	 */
	public void getAllChannelsListByRecycleParentId(Long parentId, List<Channel> channelList);
	
	/**
	 * 查询指定上级栏目的所有状态的所有子级栏目信息 (不包含当前栏目)
	 * @param parentId  上级栏目ID
	 * @param channelList 获取结果集对象
	 */
	public void getAllStateChannelsListByParentId(Long parentId, List<Channel> channelList);

	/**
	 * 根据指定栏目的唯一标识是否已存在
	 * @param channelName 需要检查的栏目的唯一标识
	 * @param excludeKey 需要排除在外的主键值对应的记录
	 * @return false：不存在  true：存在
	 */
	public boolean existsWithChannelName(String channelName,String excludeKey);
	
	/**
	 * 通过栏目ID获取所有正常状态子栏目的栏目ID集合，包含自身
	 * @param channelId 
	 * @return
	 */
	public List<Long> getAllChildrenChannelIds(Long channelId);
	
	/**
	 * 获取指定栏目ID下的所有子栏目(不包含自身)
	 * @param channelId
	 * @return 栏目ID的List集合
	 */
	public List<Long> findChildrenChannelsByChannelId(List<Long> channelIds,Long channelId);
}
