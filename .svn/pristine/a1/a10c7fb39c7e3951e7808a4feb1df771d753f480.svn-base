package com.topstar.volunteer.schedule;

public enum ScheduleStatus {
	
	   /**
     * 正常
     */
	NORMAL(0,"等待执行"),
    /**
     * 暂停
     */
	PAUSE(1,"已暂停"),
	
	RUNNING(2,"运行中");

    private int value;
    private String name;


	private ScheduleStatus(int value,String name) {
        this.value = value;
        this.name=name;
    }
	
    public String getName() {
		return name;
	}
    
    public int getValue() {
        return value;
    }
    
    // 普通方法  
    public static String getName(int status) {  
        for (ScheduleStatus c : ScheduleStatus.values()) {  
            if (c.getValue() == status) {  
            	try{
            		return c.name;  
            	}catch (Exception e) {
					return "未知";
				}
            }  
        }  
        return "未知";  
    }
    


}
