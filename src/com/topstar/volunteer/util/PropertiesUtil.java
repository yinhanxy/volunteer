package com.topstar.volunteer.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private Properties properties = null;

    private String propertyName = "/config.properties";
    
    private static PropertiesUtil instance = new PropertiesUtil();

    public static PropertiesUtil getInstance() {
        return instance;
    }
    
    private PropertiesUtil() {
        init();
    }

    private void init() {
        properties = new Properties();
        try {
            properties.load(PropertiesUtil.class.getResourceAsStream(propertyName));
        } catch (IOException e) {
            logger.error("加载配置文件[{}]出错，",propertyName,e);
        }
    }

    public String getProperty(String key) {
    	String value=properties.getProperty(key);
    	if(StringUtils.isNotBlank(value)){
    		try {
				return  new String(value.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}
        return null;
    }
    
    public String getPropertyValueAsString(String key, String defaultValue) {
    	String value=properties.getProperty(key,defaultValue);
    	if(StringUtils.isNotBlank(value)){
    		try {
				return new String(value.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}
    	return defaultValue;
    }
    
    public int getPropertyInt(String key,int defaultValue){
        String value = properties.getProperty(key);
        if (value != null && !value.trim().equals("")) {
            return Integer.parseInt(value);
        }
        return defaultValue;
    }
    
    public long getPropertyLong(String key,long defaultValue){
        String value = properties.getProperty(key);
        if (value != null && !value.trim().equals("")) {
        	try {
        		return Long.parseLong(value);
			} catch (Exception e) {
				return defaultValue;
			}
        }
        return defaultValue;
    }
    
    public int getPropertyValueAsInt(String key, String defaultValue) {
        return Integer.parseInt(properties.getProperty(key, defaultValue));
    }
}
