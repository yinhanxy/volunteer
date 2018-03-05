package com.topstar.volunteer.cache.impl;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.cache.OrgCache;
import com.topstar.volunteer.dao.OrgDao;
import com.topstar.volunteer.entity.Org;

public class OrgCacheImpl implements OrgCache {


	private static Logger logger = LoggerFactory.getLogger(RoleCacheImpl.class);
	
	private CacheManager cacheManager;
	
	private OrgDao orgDao;
	
	private String cacheName="ORG_CACHE";
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public OrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	@Override
	public boolean init() {
		logger.info("开始缓存所有的组织信息");
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		//查询所有的组织信息
		List<Org> orgs = orgDao.selectAllOrg();
		if(orgs != null && !orgs.isEmpty()){
			for(Org org:orgs){
				Element e=new Element(org.getId(), org);
				cache.put(e);
			}
		}
		logger.info("缓存组织信息成功");
		return true;
	}

	@Override
	public Org findById(Long id) {
		if(id == null || id.longValue() < 1){
			return null;
		}
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		try {
			cache.acquireReadLockOnKey(id);
			Element element = cache.get(id);
			if(element != null){
				Org org = (Org) element.getObjectValue();
				return org;
			}else{
				Org org = orgDao.selectByKey(id);
				if(org != null){
					Element ele=new Element(id, org);
					cache.put(ele);
					return org;
				}
			}
		} catch (Exception e) {
			logger.error("从缓存中读取[id={}]组织机构信息出错",id,e);
		}finally{
			cache.releaseReadLockOnKey(id);
		}
		return null;
	}

	@Override
	public boolean add(Org org) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		if(org != null && org.getId() != null){
			Long orgId = org.getId();
			try {
				cache.acquireWriteLockOnKey(orgId);
				Element ele=new Element(orgId, org);
				cache.put(ele);
				return true;
			} catch (Exception e) {
				logger.error("将[orgId={}]组织机构添加到缓存时出错",orgId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(orgId);
			}
		}
		return false;
	}

	@Override
	public boolean delete(Long orgId) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		if( orgId != null && orgId.longValue() > 0){
			try {
				cache.acquireWriteLockOnKey(orgId);
				cache.remove(orgId);
				return true;
			} catch (Exception e) {
				logger.error("移除缓存中的[orgId={}]组织机构出错",orgId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(orgId);
			}
		}
		return false;
	}

	@Override
	public boolean update(Org org) {
		if(org != null && org.getId() != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Long orgId = org.getId();
			try {
				cache.acquireWriteLockOnKey(orgId);
				Element element = cache.get(orgId);
				Org cacheOrg = null;
				if(element != null){
					cacheOrg = (Org) element.getObjectValue();
				}
				if(cacheOrg == null){
					return false;
				}
				Element ele=new Element(orgId, org);
				cache.put(ele);
				return true;
			} catch (Exception e) {
				logger.error("将[orgId={}]组织机构更新到缓存时出错",orgId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(orgId);
			}
		}
		return false;
	}

}
