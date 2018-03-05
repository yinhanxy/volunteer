package com.topstar.volunteer.log;

import java.util.List;

/**
 * 对象类型
 * @author admin
 *
 */
public enum ObjectType {
	
	//前台类
    VOLUNTEER("志愿者",1),ACTIVE("活动",2),TRAIN("培训",3),CERTIFICATE("证书",4),
    
    SERTEAM("服务队",5), TEAMUSER("服务队用户",6),TRRECORD("培训记录",7),TRAINVOl("培训记录志愿者",8),
    
    //后台类
    USER("用户", 101), GROUP("组织机构", 102), ROLE("角色", 103),  MENU("菜单", 104),
    
    WEBSITE("网站",105),CHANNEL("栏目",106),DOCUMENT("文档",107),
    
    AREA("区域", 108),LOGGER("日志",109), CONFIG("系统配置",110),SCHEDULEJOB("定时任务",113), CHECKTIME("年检时间",114),
	
    LOGINUSER("在线用户",115),LOCKUSER("锁定用户",116),SMS("短信验证码",117);
    
    // 成员变量  
    private String name;  
    private int value;
   
   private List<OperateType> operateType;
   
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
   private ObjectType(String name, int value) {  
       this.name = name;  
       this.value = value;  
   }

   // 普通方法  
   public static String getName(int value) {  
       for (ObjectType c : ObjectType.values()) {  
           if (c.getValue() == value) {  
               return c.name;  
           }  
       }  
       return "未知";  
   }
   
   public  List<OperateType> getOperateType(){
      return OperateType.getOperateTypeByObjectType(this);
   }

}
