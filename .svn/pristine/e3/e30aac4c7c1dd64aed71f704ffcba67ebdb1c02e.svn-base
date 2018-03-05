package com.topstar.volunteer.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtils {
    
    public static String toJSONString(Object ojbect){
        return JSON.toJSONString(ojbect,ResultData.features);
    }
    
    public static void sort(JSONArray array,final String field,boolean asc){
    	List<JSONObject> list=new ArrayList<JSONObject>();
    	for(int i=0;null!=array&&i<array.size();i++){
    		list.add(array.getJSONObject(i));
    	}
    	Collections.sort(list, new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject obj1, JSONObject obj2) {
				Object field1 = obj1.get(field);
				Object field2 = obj2.get(field);
				if(field1 instanceof Number && field2 instanceof Number){
					return ((Number)field1).intValue() - ((Number)field2).intValue();
				}else{
					return field1.toString().compareTo(field2.toString());
				}
			}
		});
    	if(!asc){
    		Collections.reverse(array);
		}
    }
}
