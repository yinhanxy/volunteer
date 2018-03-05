package com.topstar.volunteer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Liqy
 *
 */
public class HttpClientUtil {
	
	public static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static final String CHARSET = "UTF-8";
	
	/**
     * HTTP Get 获取内容，默认UTF-8编码
     * @param url  请求的url地址,?之前的地址
     * @param paramsMap	请求的参数
     * @return	页面内容
     */
	public static String doGet(String url,Map<String,String> paramsMap) throws Exception{
		return doGet(url, paramsMap, CHARSET);
	}
	
	/**
	 * HTTP POST，默认UTF-8编码
	 * @param url 请求的url地址
	 * @param paramsMap 请求的参数
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url,Map<String,String> paramsMap) throws Exception{
		return doPost(url, paramsMap, CHARSET);
	}
	
	/**
	 * HTTP POST
	 * @param url 请求的url地址
	 * @param paramsMap 请求的参数
	 * @param charset 编码格式
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url,Map<String,String> paramsMap,String charset) throws Exception{
		if(StringUtils.isBlank(url)){
    		return null;
    	}
		
		String result = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		
		List<NameValuePair> formparams = getParamsList(paramsMap);
		try {
			if(formparams!=null){
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, charset);  
	            httpPost.setEntity(uefEntity); 
	        }
			httpPost.setConfig(buildConfig());
	        response = httpClient.execute(httpPost);
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode != 200) {
    			httpPost.abort();
    			logger.error("HttpClient post methd  error status code:{},url:{}",statusCode,url);
    			throw new RuntimeException("HttpClient post method,error status code :" + statusCode);
    		}
	        HttpEntity entity = response.getEntity();
	        if(entity!=null){
	            result = EntityUtils.toString(entity,"UTF-8");
	        }
	        EntityUtils.consume(entity);
		}finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				logger.warn("关闭response出错",e);
			}
		}
		return result;
	}
	
	/**
     * HTTP Get 获取内容
     * @param url  请求的url地址,?之前的地址
     * @param paramsMap	请求的参数
     * @param charset	编码格式
     * @return	页面内容
     */
    public static String doGet(String url,Map<String,String> paramsMap,String charset) throws Exception{
    	if(StringUtils.isBlank(url)){
    		return null;
    	}
    	CloseableHttpClient client = HttpClients.createDefault();
    	CloseableHttpResponse response = null;
    	String result = null;
    	try {
    		if(paramsMap != null && !paramsMap.isEmpty()){
    			List<NameValuePair> formparams = getParamsList(paramsMap);
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, charset);
				url += "?" + EntityUtils.toString(uefEntity);
	        }
    		
    		HttpGet httpGet = new HttpGet(url);
    		httpGet.setConfig(buildConfig());
    		response = client.execute(httpGet);
    		int statusCode = response.getStatusLine().getStatusCode();
    		if (statusCode != 200) {
    			httpGet.abort();
    			logger.error("HttpClient get methd error status code:{} ,url:{}",statusCode,url);
    			throw new RuntimeException("HttpClient get methd,error status code :" + statusCode);
    		}
    		HttpEntity entity = response.getEntity();
    		if (entity != null){
    			result = EntityUtils.toString(entity, "UTF-8");
    		}
    		EntityUtils.consume(entity);
    	}finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				logger.warn("关闭response出错",e);
			}
		}
    	return result;
    }
    	
    private static List<NameValuePair> getParamsList(Map<String,String> paramsMap){  
        if(paramsMap==null||paramsMap.size()==0){  
            return null;  
        }  
        List<NameValuePair> list = new ArrayList<NameValuePair>();  
        Set<Map.Entry<String,String>> set = paramsMap.entrySet();  
        for(Map.Entry<String,String> entry : set){
            list.add(new  BasicNameValuePair(entry.getKey(),entry.getValue()));  
        }
        return list;
    }  
    
    
    private static RequestConfig buildConfig(){
    	RequestConfig config = RequestConfig.custom()
        	    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
        	    .setSocketTimeout(10000).build();
    	return config;
    }
    
    
    public static void main(String[] args) {
		String s = "http://10.72.0.245:8081/ws/projectService/listAllDeptToAndroid";
		String result;
		try {
			result = HttpClientUtil.doGet(s, null);
			System.out.println(result);
			
			JSONObject object = JSON.parseObject(result);
			boolean success = object.getBoolean("success");
			JSONArray array = object.getJSONArray("data");
			
			if(array != null){
				for(int i = 0 ;i < array.size();i++){
					JSONObject o = array.getJSONObject(i);
					System.out.println(o.getString("is_bus"));
				}
			}
			
			System.out.println(success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
