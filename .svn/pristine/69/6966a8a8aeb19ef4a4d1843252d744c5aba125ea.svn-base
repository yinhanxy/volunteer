package com.topstar.volunteer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.util.BaseMapper;

public interface ConfigMapper extends BaseMapper<Config> {
	
	public List<Config> loadConfigs(Map<String, String> params);
	
	/**
	 * 根据config过滤条件查询配置列表信息
	 * @param config
	 * @return
	 */
	public List<Config> findByEntity(@Param("config")Config config);
	
	/**
	 * 获取所有配置信息的类型集合
	 * @return
	 */
	public List<String> findAllConfigTypes();

}