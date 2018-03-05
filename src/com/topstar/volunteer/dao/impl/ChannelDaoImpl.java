package com.topstar.volunteer.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ChannelDao;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.mapper.ChannelMapper;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

@Repository
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements ChannelDao {
	
	@Autowired
	private ChannelMapper channelMapper;
	
	@Override
	public PageInfo<Channel> findByChannelName(String channelName,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Channel> channels= channelMapper.findByChannelName(channelName);
		PageInfo<Channel> page = new PageInfo<Channel>(channels);
		return page;
	}
	
	@Override
	public PageInfo<Channel> findSubChannelsByParentId(Long parentId, String channelCondition,String orderBy, int pageIndex, int pageSize) {
		PageInfo<Channel> page=null;
		
		if(parentId!=null && parentId<0){
			PageHelper.startPage(pageIndex, pageSize);
			if(StringUtils.isNotBlank(orderBy)){
				OrderByHelper.orderBy(orderBy);
			}
			List<Channel> channels=channelMapper.findTopChannelBySiteId(-parentId, channelCondition);
			if(channels!=null && channels.size()>0){
				Collections.sort(channels);
				page = new PageInfo<Channel>(channels);
			}
		}
		if(page==null){
			PageHelper.startPage(pageIndex, pageSize);
			if(StringUtils.isNotBlank(orderBy)){
				OrderByHelper.orderBy(orderBy);
			}
			List<Channel> channels=channelMapper.findChannelByParentId(parentId,channelCondition);
			page = new PageInfo<Channel>(channels);
		}
		return page;
	}

	/**
	 * 查询指定已回收的上级栏目的未被回收的直接子级栏目信息 
	 * @param parentId 上级区域id
	 * @return
	 */
	public List<Channel> findChannelByRecycleParentId(Long parentId){
		List<Channel> channels=channelMapper.findChannelByRecycleParentId(parentId);
		if(channels!=null && channels.size()>0)
			return channels;
		return null;
	}
	
	@Override
	public List<Channel> findAllStateChannelByParentId(Long parentId) {
		List<Channel> channels=channelMapper.findAllStateChannelByParentId(parentId);
		if(channels!=null && channels.size()>0)
			return channels;
		return null;
	}

	@Override
	public List<Channel> selectAllArea() {
		List<Channel> channels=channelMapper.findAllChannel();
		if(channels!=null && channels.size()>0){
			return channels;
		}
		return null;
	}

	
	
	@Override
	public List<Channel> findAllChannelsBySite(Long siteId) {
		List<Channel> channels=channelMapper.findAllChannelsBySite(siteId);
		if(channels!=null && channels.size()>0){
			return channels;
		}
		return null;
	}
	
	@Override
	public List<Channel> findParentChannelsByChannelId(Long channelId) {
		List<Channel> channels=channelMapper.findParentChannelsByChannelId(channelId);
		if(channels!=null && channels.size()>0){
			return channels;
		}
		return null;
	}

	@Override
	public List<Channel> findChannelByParentId(Long parentId) {
		List<Channel> channels=channelMapper.findChannelByParentId(parentId,null);
		if(channels!=null && channels.size()>0){
			return channels;
		}
		return null;
	}

	@Override
	public List<Channel> findTopChannelBySiteId(Long siteId) {
		List<Channel> channels=channelMapper.findTopChannelBySiteId(siteId,null);
		if(channels!=null && channels.size()>0){
			/*for (Channel channel : channels) {
				channel.setParentId(siteId);
			}*/
			return channels;
		}
		return null;
	}
	
	@Override
	public int addChannel(Channel channel) {
		return channelMapper.addChannel(channel);
	}

	/*@Override
	public int updateArea(Channel area) {
		return areaMapper.updateArea(area);
	}*/
	
	public int setStatus(Long channelId,Integer status){
		return channelMapper.setStatus(channelId, status);
	}
	
	@Override
	public PageInfo<Channel> getRecycleChannels(Long channelId,String channelName,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Channel> channels= channelMapper.findRecycleChannels(channelId,channelName);
		PageInfo<Channel> page = new PageInfo<Channel>(channels);
		return page;
	}

	@Override
	public Long getMaxOrderNoByParentId(Long parentId) {
		return channelMapper.getMaxOrderNoByParentId(parentId);
	}

	@Override
	public int moveForwardChannel(Long channelOrder, Long previousOrder, Long parent_id) {
		return channelMapper.moveForwardChannel(channelOrder, previousOrder, parent_id);
	}

	@Override
	public int moveBackwardChannel(Long channelOrder, Long previousOrder, Long parent_id) {
		return channelMapper.moveBackwardChannel(channelOrder, previousOrder, parent_id);
	}
	
	/**
	 * 通过栏目ID获取所有正常状态子栏目的栏目ID集合，包含自身
	 * @param channelId 
	 * @return
	 */
	public List<Long> getAllChildrenChannelIds(Long channelId){
		if(channelId != null && channelId.longValue() > 0){
			return channelMapper.findChildrenChannelsByChannelId(channelId);
		}
		return null;
	}
	
}
