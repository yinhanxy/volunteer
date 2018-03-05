package com.topstar.volunteer.cache;

import java.util.List;

import com.topstar.volunteer.entity.Config;

public interface ConfigCache {
	
	/**
	 * 初始化缓存对象
	 * @return
	 */
	public boolean init();
	
	/**
	 * 根据参数名得到对应的配置参数值
	 * @param name 配置名
	 * @return 配置对象信息
	 */
	public Config getConfigValueByName(String name);
	
	/**
	 * 根据参数类型从缓存或者数据库中得到对应的配置参数值
	 * @param type 配置类型
	 * @return 配置对象信息
	 */
	public List<Config> getConfigValuesByType(String type);
	
	/**
	 * 更新系统配置的缓存
	 * @param config 更新的配置信息
	 * @return 
	 */
	public Boolean updateConfigValue(Config config);
	
	/**
	 * 添加系统配置的缓存
	 * @param config 配置信息
	 * @return 
	 */
	public Boolean addConfigValue(Config config);
	
	/**
	 * 移除系统配置的缓存
	 * @param config 配置信息
	 * @return 
	 */
	public Boolean removeConfigValue(String configName);
	
	/**
	 * 通过配置的ID批量来移除系统配置的缓存
	 * @param config 配置信息
	 * @return 
	 */
	public Boolean removeConfigValue(Long[] configIds);
	
	/**
	 * 查询所有配置参数
	 * @return
	 */
	public List<Config> getAllConfigValues();
}
