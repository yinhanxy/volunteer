package com.topstar.volunteer.shiro.cache.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.shiro.cache.ShiroSessionCache;
import com.topstar.volunteer.shiro.session.ShiroSession;

public class ShiroSessionCacheImpl implements ShiroSessionCache {
	
	private static Logger logger = LoggerFactory.getLogger(ShiroSessionCacheImpl.class);

	private CacheManager cacheManager;
	
	private String cacheName = "shiroSessionCache";

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public ShiroSession get(Serializable sessionId) {
		if(sessionId == null ){
			return null;
		}
		logger.debug("开始查询缓存中存在的[sessionId={}]会话信息",sessionId);
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		Element element = null;
		try {
			cache.acquireReadLockOnKey(sessionId);
			element = cache.get(sessionId);
		} catch (Exception e) {
			logger.error("从缓存中读取[sessionId={}]用户出错",sessionId,e);
		}finally{
			cache.releaseReadLockOnKey(sessionId);
		}
		if(element != null){
			ShiroSession session = (ShiroSession) element.getObjectValue();
			logger.debug("缓存到对应的[sessionId={}]用户信息",sessionId);
			return session;
		}
		return null;
	}

	@Override
	public boolean add(Serializable sessionId, ShiroSession msession) {
		if(msession != null && msession.getUser() != null){
			BaseUser user = msession.getUser();
			logger.debug("开始将[sessionId={}]会话的[userId={},userName={}]登录用户添加到缓存中",sessionId,user.getId(),user.getUserName());
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(sessionId);
				Element element=new Element(sessionId, msession);
				cache.put(element);
				logger.debug("完成[sessionId={}]会话的添加",sessionId);
			} catch (Exception e) {
				logger.error("添加[sessionId={}]会话到缓存出错",sessionId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(sessionId);
			}
		}else{
			return false;
		}
		return false;
	}

	@Override
	public boolean remove(Serializable sessionId) {
		if(sessionId != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(sessionId);
				cache.remove(sessionId);
				logger.debug("已将[sessionId={}]会话从缓存中移除",sessionId);
				return true;
			} catch (Exception e) {
				logger.error("将[sessionId={}]会话从缓存中移除出错",sessionId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(sessionId);
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		List list = cache.getKeys();
		return (list == null || list.isEmpty());
	}

	@Override
	public Map<Serializable, ShiroSession> getAll() {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		List list = cache.getKeys();
		if(list != null && !list.isEmpty()){
			Map<Serializable, ShiroSession> map = new TreeMap<Serializable, ShiroSession>();
			for(int i = 0 ; i < list.size();i++){
				Serializable id = (Serializable) list.get(i);
				Element element = cache.get(id);
				if(element != null){
					ShiroSession session = (ShiroSession) element.getObjectValue();
					if( session != null){
						map.put(id, session);
					}
				}
				
			}
			return map;
		}
		return null;
	}
	
	

}
