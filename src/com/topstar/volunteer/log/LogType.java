package com.topstar.volunteer.log;

/**
 * 日志类型
 * @author admin
 *
 */
public  enum LogType {
	
		WARN("警告", 1), DEBUG("调试", 2), INFO("信息", 3),  ERROR("错误", 4);  
		 // 成员变量  
	    private String name;  
	    private int value;
	    
		 // get set 方法  
	    public String getName() {  
	        return name;  
	    }
	    
	    public void setName(String name) {  
	        this.name = name;  
	    }
	    
	    public int getValue() {  
	        return value;  
	    }
	    
	    public void setValue(int value) {  
	        this.value = value;  
	    } 
		
		// 构造方法  
	    private LogType(String name, int value) {  
	        this.name = name;  
	        this.value = value;  
	    }

	    // 普通方法  
	    public static String getName(int value) {  
	        for (LogType c : LogType.values()) {  
	            if (c.getValue() == value) {  
	                return c.name;  
	            }  
	        }  
	        return "未知";  
	    }
}
