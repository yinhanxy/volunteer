package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Config;

public interface ConfigService extends BaseService<Config>{
	
	/**
	 * 通过配置名称得到对应的配置信息
	 * @param name 配置名称
	 * @return config配置对象
	 */
	public Config getConfigsByName(String name);
	
	/**
	 * 通过配置类型获取对应的配置信息列表信息
	 * @param type 配置类型
	 * @return 返回配置类型列表对象
	 */
	public List<Config> getConfigsByType(String type);
	
	/**
	 * 获取所有配置对象
	 * @return 返回配置类型列表对象
	 */
	public List<Config> getAllConfigs();
	
	/**
	 * 分页查询指定条件下的系统配置信息列表
	 * @param config 查询的过滤条件
	 * @param page 查询的页码
	 * @param pageSize 每页显示数目
	 * @return
	 */
	public PageInfo<Config> loadConfigPage(Config config,int page,int pageSize);
	
	/**
	 * 添加配置,并添加到配置缓存中
	 * @param config
	 * @return
	 */
	public boolean addConfig(Config config);
	
	/**
	 * 根据过滤条件查找指定的配置名称是否已存在
	 * @param configName
	 * @param excludeKey
	 * @return
	 */
	public int existsWithConfigName(String configName, String excludeKey);
	
	/**
	 * 批量删除配置信息，并移除系统中对应的配置缓存
	 * @param configIds
	 * @return
	 */
	public boolean deleteConfig(Long[] configIds);
	
	/**
	 * 更新配置信息并更新系统中该配置的缓存信息
	 * @param config
	 * @return
	 */
	public boolean updateConfig(Config config);
	
	
	/**
	 * 获取所有配置类型
	 * @return
	 */
	public List<String> getConfigType();
		
}
