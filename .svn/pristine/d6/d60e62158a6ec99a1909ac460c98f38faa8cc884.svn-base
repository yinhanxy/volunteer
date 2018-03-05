package com.topstar.volunteer.cache.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.cache.ChannelCache;
import com.topstar.volunteer.dao.ChannelDao;
import com.topstar.volunteer.dao.SiteDao;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Site;

public class ChannelCacheImpl implements ChannelCache {
	
	private static Logger logger = LoggerFactory.getLogger(ChannelCacheImpl.class);
	
	private CacheManager cacheManager;
	
	private SiteDao siteDao;
	
	private ChannelDao channelDao;
	
	private String cacheName="CHANNEL_CACHE";
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public SiteDao getSiteDao() {
		return siteDao;
	}

	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}

	public ChannelDao getChannelDao() {
		return channelDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	@Override
	public boolean init() {
		logger.info("开始缓存所有的栏目信息");
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		//查询所有的站点信息
		List<Site> sites=siteDao.selectAllSite();
		if(sites != null && sites.size() > 0){
			for (Site site : sites) {
				List<Channel> channels=channelDao.findAllChannelsBySite(site.getId());
				if(channels != null && channels.size() > 0){
					for(Channel channel : channels){
						Element e=new Element(channel.getId(), channel);
						cache.put(e);
					}
				}
			}
		}
		logger.info("缓存栏目信息成功");
		return false;
	}

	@Override
	public Channel findById(Long id) {
		if(id == null || id.longValue() < 1){
			return null;
		}
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		boolean isRead = true;
		try {
			cache.acquireReadLockOnKey(id);
			Element element = cache.get(id);
			if(element != null){
				Channel channel = (Channel) element.getObjectValue();
				return channel;
			}else{
				Channel channel = channelDao.selectByKey(id);
				if(channel != null){
					cache.releaseReadLockOnKey(id);
					cache.acquireWriteLockOnKey(id);
					isRead = false;
					Element ele=new Element(id, channel);
					cache.put(ele);
					return channel;
				}
			}
		} catch (Exception e) {
			logger.error("从缓存中读取[id={}]栏目信息出错",id,e);
		}finally{
			if(isRead){
				cache.releaseReadLockOnKey(id);
			}else{
				cache.releaseWriteLockOnKey(id);
			}
		}
		return null;
	}

	@Override
	public boolean add(Channel channel) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		if(channel != null && channel.getId() != null){
			Long channelId = channel.getId();
			try {
				cache.acquireWriteLockOnKey(channelId);
				Element ele=new Element(channelId, channel);
				cache.put(ele);
				return true;
			} catch (Exception e) {
				logger.error("将[channelId={}]栏目添加到缓存时出错",channelId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(channelId);
			}
		}
		return false;
	}

	@Override
	public boolean delete(Long channelId) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		if( channelId != null && channelId.longValue() > 0){
			try {
				cache.acquireWriteLockOnKey(channelId);
				cache.remove(channelId);
				return true;
			} catch (Exception e) {
				logger.error("移除缓存中的[channelId={}]组织机构出错",channelId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(channelId);
			}
		}
		return false;
	}

	@Override
	public boolean update(Channel channel) {
		if(channel != null && channel.getId() != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Long channelId = channel.getId();
			try {
				cache.acquireWriteLockOnKey(channelId);
				Element element = cache.get(channelId);
				Channel cacheChannel = null;
				if(element != null){
					cacheChannel = (Channel) element.getObjectValue();
				}
				if(cacheChannel == null){
					return false;
				}
				Element ele=new Element(channelId, channel);
				cache.put(ele);
				return true;
			} catch (Exception e) {
				logger.error("将[channelId={}]栏目更新到缓存时出错",channelId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(channelId);
			}
		}
		return false;
	}

	/**
	 * 从缓存中获取所有不在回收站的栏目信息
	 * @return
	 */
	public List<Channel> getAllNotInRecycle(){
		Cache cache=cacheManager.getCache(cacheName);
		if(cache != null){
		 	List<Long> channelIds = cache.getKeys();
		 	if(channelIds != null){
		 		List<Channel> channels = new ArrayList<Channel>();
		 		for(Long channelId: channelIds){
		 			Element element = cache.get(channelId);
		 			if(element != null){
		 				Channel channel = (Channel) element.getObjectValue();
		 				if(channel != null && channel.getStatus().intValue() == 1){
		 					channels.add(channel.clone());
		 				}
		 			}
		 		}
		 		return channels;
		 	}
		}
		return null;
	}
	
	/**
	 * 从缓存中获取所有不在回收站的栏目信息
	 * @return
	 */
	public List<Channel> getAllBySiteIdNotInRecycle(Long siteId){
		Cache cache=cacheManager.getCache(cacheName);
		if(cache != null){
		 	List<Long> channelIds = cache.getKeys();
		 	if(channelIds != null){
		 		List<Channel> channels = new ArrayList<Channel>();
		 		for(Long channelId: channelIds){
		 			Element element = cache.get(channelId);
		 			if(element != null){
		 				Channel channel = (Channel) element.getObjectValue();
		 				if(channel != null){
		 					if(channel.getSiteId().longValue() == siteId.longValue() && channel.getStatus().intValue() == 1){
		 						channels.add(channel.clone());
		 					}
		 				}
		 			}
		 		}
		 		return channels;
		 	}
		}
		return null;
	}
	
	/**
	 * 从缓存中获取所有不在回收站的栏目ID
	 * @return
	 */
	public List<Long> getAllChannelIdsNotInRecycle(Long siteId){
		Cache cache=cacheManager.getCache(cacheName);
		if(cache != null){
		 	List<Long> keys = cache.getKeys();
		 	if(keys != null){
		 		List<Long> channelIds = new ArrayList<Long>();
		 		for(Long channelId: keys){
		 			Element element = cache.get(channelId);
		 			if(element != null){
		 				Channel channel = (Channel) element.getObjectValue();
		 				if(channel != null){
		 					if(channel.getSiteId().longValue() == siteId.longValue() && channel.getStatus().intValue() == 1){
		 						channelIds.add(channel.getId());
		 					}
		 				}
		 			}
		 		}
		 		return channelIds;
		 	}
		}
		return null;
	}
	
	/**
	 * 从缓存中获取栏目信息
	 * @param status : 栏目状态
	 * @return
	 */
	public List<Channel> getAll(Integer status){
		Cache cache=cacheManager.getCache(cacheName);
		if(cache != null){
		 	List<Long> channelIds = cache.getKeys();
		 	if(channelIds != null){
		 		List<Channel> channels = new ArrayList<Channel>();
		 		for(Long channelId: channelIds){
		 			Element element = cache.get(channelId);
		 			if(element != null){
		 				Channel channel = (Channel) element.getObjectValue();
		 				if(channel != null && channel.getStatus().intValue() == status.intValue()){
		 					channels.add(channel.clone());
		 				}
		 			}
		 		}
		 		return channels;
		 	}
		}
		return null;
	}
}
