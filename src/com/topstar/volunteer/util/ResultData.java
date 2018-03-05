package com.topstar.volunteer.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 定义返回成功与否的标志
 */
public class ResultData {
	
	public static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空值字段
        SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
        SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
        SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
        SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };
	
	private boolean success;
	
	private Map<String, Object> data;

	private int operateCode; // 操作码
	
	public ResultData() {
		this.data = new HashMap<String, Object>();
	}
	
	public ResultData(boolean success) {
		this.success = success;
		this.data = new HashMap<String, Object>();
		data.put("success", success);
	}
	
	public ResultData(boolean success, Object object) {
		this.data = new HashMap<String, Object>();
		this.success = success;
		data.put("success", success);
		data.put("data", object);
	}
	
	public static ResultData ok() {
		ResultData result = new ResultData();
		result.put("success", true);
		result.success = true;
		return result;
	}

	public static ResultData ok(String message) {
		ResultData result = new ResultData();
		result.success = true;
		result.put("success", true).put("message", message);
		return result;
	}

	public static ResultData ok(Object data) {
		ResultData result = new ResultData();
		result.success = true;
		result.put("success", true).put("data", data);
		return result;
	}
	
	public static ResultData fail(Object data) {
		ResultData result = new ResultData();
		result.success = false;
		result.put("success", false).put("message", data);
		return result;
	}

	public static ResultData fail() {
		ResultData result = new ResultData();
		result.success = false;
		result.put("success", false);
		return result;
	}

	public static ResultData fail(String message) {
		ResultData result = new ResultData();
		result.success = false;
		result.put("success", false).put("message", message);
		return result;
	}

	public ResultData put(String key,Object value){
		this.data.put(key, value);
		return this;
	}
	
	public ResultData setMessage(String message){
		this.put("message", message);
		return this;
	}
	
	public int getOperateCode() {
		return operateCode;
	}

	public ResultData setOperateCode(int operateCode) {
		this.operateCode = operateCode;
		return this;
	}
	
	public boolean getSuccess(){
		return success;
	}
	
	public ResultData setSuccess(boolean success){
		this.put("success", success);
		this.success = success;
		return this;
	}
	
	/**
	 * 验证表单不通过时设置token
	 * @param request
	 * @param methodName
	 * @return
	 */
	public ResultData setToken(HttpServletRequest request,String methodName){
		String token=TokenUtil.setToken(request, methodName);
		this.data.put("token", token);
		return this;
	}
	
    public String toJSONString(){
        return JSON.toJSONString(this.data,features);
    }
    
    public String toJSONString(SerializerFeature[] features){
        return JSON.toJSONString(this.data,features);
    }
    
    public String toJSONStringWithDateFormat(String pattern){
    	return JSON.toJSONStringWithDateFormat(this.data, pattern, features);
    }
}