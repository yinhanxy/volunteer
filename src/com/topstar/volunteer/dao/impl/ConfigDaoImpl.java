package com.topstar.volunteer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ConfigDao;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.mapper.ConfigMapper;

@Repository
public class ConfigDaoImpl extends BaseDaoImpl<Config> implements ConfigDao {

	@Autowired
	private ConfigMapper configMapper;
	
	public List<Config> loadConfigs(Map<String, String> params){
		return configMapper.loadConfigs(params);
	}

	/**
	 * 分页查询指定条件下的系统配置信息列表
	 * @param config 查询的过滤条件
	 * @param page 查询的页码
	 * @param rows 每页显示数目
	 * @return
	 */
	@Override
	public PageInfo<Config> findByEntity(Config config, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Config> configList=configMapper.findByEntity(config);
		PageInfo<Config> configs=new PageInfo<>(configList);
		return configs;
	}

	@Override
	public List<String> findAllConfigTypes() {
		return configMapper.findAllConfigTypes();
	}
}
