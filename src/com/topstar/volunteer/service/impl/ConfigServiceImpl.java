package com.topstar.volunteer.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.dao.ConfigDao;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.service.ConfigService;

@Service("configService")
public class ConfigServiceImpl  extends BaseServiceImpl<Config> implements ConfigService{

	@Autowired
	private ConfigCache configCache;
	
	@Autowired
	private ConfigDao configDao;

	@Override
	public Config getConfigsByName(String name) {
		if(!StringUtils.isBlank(name)){
			return configCache.getConfigValueByName(name);
		}
		return null;
	}

	@Override
	public List<Config> getConfigsByType(String type) {
		if(!StringUtils.isBlank(type)){
			return configCache.getConfigValuesByType(type);
		}
		return null;
	}

	@Override
	public List<Config> getAllConfigs() {
		return configCache.getAllConfigValues();
	}

	@Override
	public PageInfo<Config> loadConfigPage(Config config, int page, int pageSize) {
		return configDao.findByEntity(config, page, pageSize);
	}

	
	@Override
	public boolean addConfig(Config config) {
		if(config!=null){
			int addConfig=configDao.insertNotNull(config);
			if(addConfig>0){
				boolean addConfigCache=configCache.addConfigValue(config);
				if(addConfigCache){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int existsWithConfigName(String configName, String excludeKey) {
		if(!StringUtils.isBlank(configName)){
			Example example=new Example(Config.class); 
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("name", configName);
			if (StringUtils.isNotEmpty(excludeKey)) {
				criteria.andNotEqualTo("id", excludeKey);
			}
			List<Config> config=selectByExample(example);
			if(config!=null && config.size()!=0){
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * 批量删除配置信息，并移除系统中对应的配置缓存
	 * @param configIds
	 * @return
	 */
	public boolean deleteConfig(Long[] configIds){
		if(configIds!=null && configIds.length>0){
			int rows=configIds.length;
			int delConfigRow=configDao.deleteByKeys(configIds);
			if(delConfigRow==rows){
				configCache.removeConfigValue(configIds);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 更新配置信息并更新系统中该配置的缓存信息
	 * @param config
	 * @return
	 */
	public boolean updateConfig(Config config){
		if(config!=null && config.getId()!=null){
			Config tempConfig = configDao.selectByKey(config.getId());
			if(tempConfig != null){
				config.setName(tempConfig.getName());
				int updateRow=updateNotNull(config);
				if(updateRow>0){
					boolean updateCache=configCache.updateConfigValue(config);
					if(updateCache){
						return true;
					}
				}
			}
			
		}
		return false;
	}

	@Override
	public List<String> getConfigType() {
		return configDao.findAllConfigTypes();
	}
	
}
