package com.topstar.volunteer.util;

import java.util.List;

import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.service.ConfigService;
import com.topstar.volunteer.web.context.SpringContextHolder;

public class ConfigUtils {

	private static ConfigService configService = (ConfigService) SpringContextHolder.getBean("configService");;

	/**
	 * 根据配置参数名查找对应配置参数信息
	 * @param name 配置参数名
	 * @return 配置对象信息
	 */
	public static Config getConfigsByName(String name){
		Config config=configService.getConfigsByName(name);
		if(config!=null){
			return config;
		}
		return null;
	}	
	
	/**
	 * 根据配置参数名查找对应配置参数的值
	 * @param name 配置参数名
	 * @return
	 */
	public static String getConfigContentByName(String name){
		Config config=configService.getConfigsByName(name);
		if(config!=null){
			return config.getContent();
		}
		return null;
	}
	
	/**
	 * 根据配置参数类型查找对应配置参数信息
	 * @param type 配置参数类型
	 * @return 配置对象信息
	 */
	public static List<Config> getConfigsByType(String type){
		List<Config> configs=configService.getConfigsByType(type);
		if(configs!=null && configs.size()!=0){
			return configs;
		}
		return null;
	}
	
	/**
	 * 根据配置参数名和参数类型从数据库中查找配置参数信息并更新缓存信息
	 * @param name 配置参数名
	 * @param type 配置参数类型
	 * @return 配置对象信息
	 */
	public static List<Config> getUpdateConfigs(String name,String type){
		/*List<Config> configs=configUtils.configService.
		if(configs!=null && configs.size()!=0){
			return configs;
		}*/
		return null;
	}
	
	
	/**
	 * 查找所有配置参数信息
	 * @return
	 */
	public static List<Config> getAllConfigs(){
		List<Config> configs=configService.getAllConfigs();
		if(configs!=null && configs.size()!=0){
			return configs;
		}
		return null;
	}
}
