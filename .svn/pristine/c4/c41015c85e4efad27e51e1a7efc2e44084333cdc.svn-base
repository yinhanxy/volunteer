package com.topstar.volunteer.cache.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.dao.ConfigDao;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.entity.Role;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class ConfigCacheImpl implements ConfigCache{

	private Logger logger=LoggerFactory.getLogger(ConfigCacheImpl.class);
	
	private CacheManager cacheManager;
	
	private ConfigDao configDao;
	
	private String cacheName="sysconfig";
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}

	@Override
	public synchronized boolean init() {
		logger.debug("开始缓存所有的系统配置信息");
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		List<Config> configs=configDao.loadConfigs(null);
		if(configs!=null && !configs.isEmpty()){
			for(Config config : configs) {
				Element ele=new Element(config.getName(), config);
				cache.put(ele);
			}
		}
		logger.debug("系统配置缓存结束,总共缓存了"+configs.size()+"条配置信息");
		return true;
	}

	@Override
	public Config getConfigValueByName(String name) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		List<Config> configs=null;
		Config config=null;
		Map<String,String> params=null;
		if(!StringUtils.isBlank(name)){
			Element ele=cache.get(name);
			if(ele!=null){
				config=(Config) ele.getObjectValue();
				if(config!=null){
					return config;
				}else{
					params=new HashMap<String,String>();
					params.put("name", name);
					configs=configDao.loadConfigs(params);
					if(configs!=null && configs.size()!=0){
						config=configs.get(0);
						Element e=new Element(config.getName(), config);
						cache.put(e);
						return config;
					}
				}
			}else{
				params=new HashMap<String,String>();
				params.put("name", name);
				configs=configDao.loadConfigs(params);
				if(configs!=null && configs.size()!=0){
					config=configs.get(0);
					Element e=new Element(config.getName(), config);
					cache.put(e);
					return config;
				}
			}
		}
		return config;
	}

	
	public List<Config> getConfigValuesByType(String type) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		List<Config> configs=null;
		Config config=null;
		Map<String,String> params=null;
		if(!StringUtils.isBlank(type)){
			List<String> list = cache.getKeys();
			if(list!=null && list.size()!=0){
				configs=new ArrayList<Config>();
				for(Object o : list){
					Element element =  cache.get(o);
					config=(Config) element.getObjectValue();
					if(type.equals(String.valueOf(config.getType()))){
						configs.add(config);
					}
				}
			}else{
				params=new HashMap<String,String>();
				params.put("type", type);
				configs=configDao.loadConfigs(params);
				if(configs!=null){
					for (Config c : configs) {
						Element ele=new Element(c.getName(), c);
						cache.put(ele);
					}
				}
			}
			if(configs!=null && !configs.isEmpty()){
				Collections.sort(configs);
			}
		}
		return configs;
	}
	
	
	@Override
	public List<Config> getAllConfigValues() {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		List<Config> configs=null;
		Config config=null;
		List list = cache.getKeys();
		if(list!=null && list.size()!=0){
			configs=new ArrayList<Config>();
			for(Object o : list){
				String key = (String ) o;
				Element element =  cache.get(key);
				config=(Config) element.getObjectValue();
				configs.add(config);
			}
		}else{
			configs=configDao.loadConfigs(null);
			if(configs!=null){
				for (Config c : configs) {
					Element ele=new Element(c.getName(), c);
					cache.put(ele);
					return configs;
				}
			}
		}
		return configs;
	}
	

	/**
	 * 更新系统配置的缓存
	 * @param config 更新的配置信息
	 * @return 
	 */
	@Override
	public Boolean updateConfigValue(Config config){
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		Config tempConfig=null;
		if(config!=null && !StringUtils.isBlank(config.getName())){
			String name =config.getName();
			try{
				logger.debug("开始更新缓存中的配置信息config:{}",config);
				cache.acquireWriteLockOnKey(name);
				Element el=cache.get(name);
				if(el!=null){
					tempConfig=(Config) el.getObjectValue();
					if(tempConfig!=null){
						tempConfig.setRemark(config.getRemark());
						tempConfig.setContent(config.getContent());
						tempConfig.setType(config.getType());
						tempConfig.setOrderNo(config.getOrderNo());
						logger.debug("完成更新缓存中的配置信息config:{}",config);
						return true;
					}
				}
			}catch (Exception e) {
				logger.error("将[config={}]配置信息更新到缓存时出错",config,e);
				return false;
			}finally {
				cache.releaseWriteLockOnKey(name);
			}
		}
		logger.debug("更新缓存中的配置信息config:{}失败",config);
		return false;
	}
	
	/**
	 * 添加系统配置的缓存
	 * @param config 配置信息
	 * @return 
	 */
	public Boolean addConfigValue(Config config){
		if(config!=null && !StringUtils.isBlank(config.getName())){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			String name=config.getName();
			try{
				cache.acquireWriteLockOnKey(name);
				Element el=new Element(name, config);
				cache.put(el);
				return true;
			}catch (Exception e) {
				logger.error("将[config={}]配置信息添加到缓存时出错",config,e);
				return false;
			}finally {
				cache.releaseWriteLockOnKey(name);
			}
		}
		return false;
	}
	
	/**
	 * 移除系统配置的缓存
	 * @param configName 配置信息
	 * @return 
	 */
	public Boolean removeConfigValue(String configName){
		if(StringUtils.isBlank(configName)){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try{
				cache.acquireWriteLockOnKey(configName);
				cache.remove(configName);
				return true;
			}catch (Exception e) {
				logger.error("将[configName={}]配置信息移除缓存时出错",configName,e);
				return false;
			}finally {
				cache.releaseWriteLockOnKey(configName);
			}
			
		}
		return false;
	}
	
	/**
	 * 通过配置的ID批量来移除系统配置的缓存
	 * @param config 配置信息
	 * @return 
	 */
	public Boolean removeConfigValue(Long[] configIds){
		if(configIds!=null && configIds.length>0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Config> configs=getAllConfigValues();
			for (int i = 0; i < configIds.length; i++) {
				for (Config conf : configs) {
					if(conf.getId().equals(configIds[i])){
						try{
							cache.acquireWriteLockOnKey(conf.getName());
							cache.remove(conf.getName());
							break;
						}catch (Exception e) {
							logger.error("将[config={}]配置信息移除缓存时出错",conf,e);
							return false;
						}finally {
							cache.releaseWriteLockOnKey(conf.getName());
						}
						
					}
				}
			}
			return true;
			
		}
		return false;
	}
}
