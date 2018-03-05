package com.topstar.volunteer.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Config;

public interface ConfigDao extends BaseDao<Config>{

	public List<Config> loadConfigs(Map<String, String> params);
	
	/**
	 * 分页查询指定条件下的系统配置信息列表
	 * @param config 查询的过滤条件
	 * @param page 查询的页码
	 * @param rows 每页显示数目
	 * @return
	 */
	public PageInfo<Config> findByEntity(Config config, int page, int rows);
	
	/**
	 * 获取所有配置信息的类型集合
	 * @return
	 */
	public List<String> findAllConfigTypes();
}
