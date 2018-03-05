package com.topstar.volunteer.log;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作类型
 * @author admin
 *
 */
public enum OperateType {
	
	//前台-志愿者操作类
	VOLUNTEERLOGIN("志愿者登录", 101,ObjectType.VOLUNTEER), VOLUNTEERLOGOUT("志愿者退出登录", 102,ObjectType.VOLUNTEER),
	CREATEVOLUNTEER("新建志愿者", 103,ObjectType.VOLUNTEER),  EDITVOLUNTEER("修改志愿者", 104,ObjectType.VOLUNTEER), 
	DELVOLUNTEER("删除志愿者", 105,ObjectType.VOLUNTEER),DROPVOLUNTEER("彻底删除志愿者", 106,ObjectType.VOLUNTEER),
	SHOWVOLUNTEER("查看志愿者", 107,ObjectType.VOLUNTEER),SHOWREVIEWVOLUNTEER("查看志愿者审核信息", 108,ObjectType.VOLUNTEER),
	
	REVIEWVOLUNTEER("志愿者信息审核", 109,ObjectType.VOLUNTEER), SHOWVOLUNTEERLIST("查询志愿者信息列表", 110,ObjectType.VOLUNTEER),
	REVIEWVOLUNTEERLIST("查询志愿者审核信息列表", 111,ObjectType.VOLUNTEER), RESETVOLUNTEERPWD("志愿者重置密码", 112,ObjectType.VOLUNTEER),
	CHECKVOLUNTEER("志愿者信息批量审核", 113,ObjectType.VOLUNTEER),
	
	SELECTVOLUNTEER("查询尚未授予证书的志愿者信息列表", 114,ObjectType.VOLUNTEER),CREATECERTIFICATION("授予志愿者证书", 115,ObjectType.VOLUNTEER),
	SHOWCERTCHECK("查询志愿者证书年度审核信息", 116,ObjectType.VOLUNTEER),SHOWVOLUNTEERCERT("查询志愿者证书信息", 117,ObjectType.VOLUNTEER),
	CREATECERTCHECK("新增志愿者证书年度考核登记信息", 118,ObjectType.VOLUNTEER),EDITCERTCHECK("编辑志愿者证书年度考核登记信息", 119,ObjectType.VOLUNTEER),
	DELCERTCHECK("删除志愿者证书年度考核登记信息", 120,ObjectType.VOLUNTEER),
	
	VOLUNTEERCERTLIST("查询志愿者证书审核名单信息列表", 121,ObjectType.VOLUNTEER),DELVOLUNTEERCERTS("批量删除志愿者证书信息", 122,ObjectType.VOLUNTEER),
	SHOWVOLUNTEERSTAR("查看志愿者星级评定信息", 123,ObjectType.VOLUNTEER),STAREVALUATE("志愿者星级评定", 124,ObjectType.VOLUNTEER),
	SHOWSTARLIST("查询志愿者服务星级信息列表", 125,ObjectType.VOLUNTEER),EDITBLACK("编辑志愿者黑名单信息", 126,ObjectType.VOLUNTEER),
	CREATEBLACK("新增志愿者黑名单信息", 127,ObjectType.VOLUNTEER),
	
	SHOWBLACK("查询志愿者黑名单信息", 128,ObjectType.VOLUNTEER),SHOWBLACKLIST("查询志愿者黑名单信息列表", 129,ObjectType.VOLUNTEER),
	DELBLACKLIST("批量删除志愿者黑名单信息", 130,ObjectType.VOLUNTEER),VOLUNTEERSWITHOUTBLACK("查询未有黑名单记录的志愿者信息列表", 131,ObjectType.VOLUNTEER), 
	DEALTURNTEAM("志愿者转队申请处理", 132,ObjectType.VOLUNTEER),SHOWDEALTURNTEAM("查看志愿者转队申请处理信息", 133,ObjectType.VOLUNTEER),
	SHOWTURNTEAMLIST("查看志愿者转队申请信息列表", 134,ObjectType.VOLUNTEER),
	
	SHOWRETREATTEAM("查询志愿者退队信息",135,ObjectType.VOLUNTEER),SHOWRETREATTEAMLIST("查询志愿者退队信息列表",136,ObjectType.VOLUNTEER),
	EDITRETREATTEAM("处理修改退队信息结果",137,ObjectType.VOLUNTEER),SHOWVOLTRRECORD("查看志愿者培训信息", 138,ObjectType.VOLUNTEER),
	
	SHOWSTATISTICSVOLLIST("查询志愿者统计信息列表",139,ObjectType.VOLUNTEER),
	SHOWJOINEDACTIVITY("查询志愿者参加的活动服务情况列表",140,ObjectType.VOLUNTEER),
	SHOWSTAREVALUATELIST("查询志愿者星级评定记录列表",141,ObjectType.VOLUNTEER),
	
	//后台-用户操作类
	USERLOGIN("用户登录", 10101,ObjectType.USER), USERLOGOUT("用户退出登录", 10102,ObjectType.USER),CREATEUSER("新建用户", 10103,ObjectType.USER),  
	EDITUSER("修改用户", 10104,ObjectType.USER), DELUSER("删除用户", 10105,ObjectType.USER),DROPUSER("彻底删除用户", 10106,ObjectType.USER),
	SHOWUSER("查看用户", 10107,ObjectType.USER),SHOWUSERLIST("查看用户列表", 10108,ObjectType.USER),CHANGEUSERSTATUS("更改用户状态", 10109,ObjectType.USER),
	SHOWUSERROLES("显示用户角色信息列表", 10110,ObjectType.USER),
	
	RESETPASSWORD("重置用户密码", 10111,ObjectType.USER),SHOWACCESSMENU("显示用户权限菜单", 10112,ObjectType.USER),
	ADDUSERMENU("添加用户菜单权限", 10113,ObjectType.USER),SHOWAllACCESSMENU("查看用户所有权限信息", 10114,ObjectType.USER),
	ADDUSERCHANNEL("添加用户可访问栏目", 10115,ObjectType.USER),
	
	//后台-组织机构操作类
	CREATEGROUP("新建组织机构", 10201,ObjectType.GROUP), EDITGROUP("修改组织机构", 10102,ObjectType.GROUP), 
	DELGROUP("删除组织机构", 10203,ObjectType.GROUP),DROPGROUP("彻底删除组织机构", 10204,ObjectType.GROUP), SHOWGROUP("查看组织机构", 10205,ObjectType.GROUP),
	SHOWGROUPLIST("查看组织机构列表", 10206,ObjectType.GROUP),SHOWSUBGROUPLIST("查看指定机构的下级机构列表信息列表", 10207,ObjectType.GROUP),
	SHOWORGUSERS("查看指定机构下的用户信息列表", 10208,ObjectType.GROUP),SHOWUSERSWITHOUTORG("查询将添加机构的用户信息列表", 10209,ObjectType.GROUP),
	DELGROUPUSER("移除指定机构下用户列表", 10210,ObjectType.GROUP),CREATEGROUPUSER("给指定机构添加用户", 10211,ObjectType.GROUP),
	
	//后台-角色操作类
	CREATEROLE("新建角色", 10301,ObjectType.ROLE),  EDITROLE("修改角色", 10302,ObjectType.ROLE), DELROLE("删除角色", 10303,ObjectType.ROLE),
	DROPROLE("彻底删除角色", 10304,ObjectType.ROLE), SHOWROLE("查看角色", 10305,ObjectType.ROLE),SHOWROLELIST("查看角色列表", 10306,ObjectType.ROLE),
	SHOWROLEUSER("查看角色下的用户列表", 10307,ObjectType.ROLE),DELROLEUSER("移除角色下的用户信息", 10308,ObjectType.ROLE),
	SHOWADDROLEUSER("查询添加角色页面下的用户信息", 10309,ObjectType.ROLE),
	ADDROLEUSERS("分配用户角色",10310,ObjectType.ROLE),SHOWROLEMENUS("获取角色菜单列表",10311,ObjectType.ROLE),
	ADDROLEMENUS("添加角色菜单",10312,ObjectType.ROLE),ADDROLECHANNELS("添加角色可访问栏目",10313,ObjectType.ROLE),
	
	//后台-菜单操作类
	CREATEMENU("新建菜单", 10401,ObjectType.MENU),  EDITMENU("修改菜单", 10402,ObjectType.MENU), DELMENU("删除菜单", 10403,ObjectType.MENU),
	DROPMENU("彻底删除菜单", 10404,ObjectType.MENU), SHOWMENU("查看菜单", 10405,ObjectType.MENU),SHOWMENULIST("查看菜单列表", 10406,ObjectType.MENU),
	
	//后台-网站操作类
	CREATEWEBSITE("新建网站", 10501,ObjectType.WEBSITE),  EDITWEBSITE("修改网站", 10502,ObjectType.WEBSITE), 
	DELWEBSITE("删除网站", 10503,ObjectType.WEBSITE),DROPWEBSITE("彻底删除网站", 10504,ObjectType.WEBSITE), 
	SHOWWEBSITE("查看网站", 10505,ObjectType.WEBSITE),SHOWWEBSITELIST("查看网站列表", 10506,ObjectType.WEBSITE),	
	CHANNELSELECT("获取指定栏目下的直接子栏目列表", 10507,ObjectType.WEBSITE),
	
	//后台-栏目操作类
	CREATECHANNEL("新建栏目", 10601,ObjectType.CHANNEL),  EDITCHANNEL("修改栏目", 10602,ObjectType.CHANNEL),
	DELCHANNEL("删除栏目", 10603,ObjectType.CHANNEL),DROPCHANNEL("彻底删除栏目", 10604,ObjectType.CHANNEL), 
	SHOWCHANNEL("查看栏目", 10605,ObjectType.CHANNEL),SHOWCHANNELLIST("查看栏目列表", 10606,ObjectType.CHANNEL),
	LOADSITESANDTOPCHANNEL("查看站点及顶级栏目列表", 10607,ObjectType.CHANNEL),SHOWMOVECHANNEL("查看栏目移动的栏目结构", 10608,ObjectType.CHANNEL),
	SHOWRECYCLECHANNEL("查询回收站中栏目信息", 10609,ObjectType.CHANNEL),
	RESTORECHANNEL("还原回收站栏目", 10609,ObjectType.CHANNEL),MOVECHANNEL("移动栏目", 10610,ObjectType.CHANNEL),
	
	//后台-文档操作类
	CREATEDOCUMENT("新建文档", 10701,ObjectType.DOCUMENT),  EDITDOCUMENT("修改文档", 10702,ObjectType.DOCUMENT), 
	DELDOCUMENT("删除文档", 10703,ObjectType.DOCUMENT),DROPDOCUMENT("彻底删除文档", 10704,ObjectType.DOCUMENT), 
	SHOWDOCUMENT("查看文档", 10705,ObjectType.DOCUMENT),SHOWDOCUMENTLIST("查看文档列表",10706,ObjectType.DOCUMENT),
	COMMITDOCUMENT("提交文档",10707,ObjectType.DOCUMENT),PUBDOCUMENT("发布文档",10708,ObjectType.DOCUMENT),
	CANCELDOCUMENT("撤回文档",10709,ObjectType.DOCUMENT),RESTOREDOCUMENT("恢复文档",10710,ObjectType.DOCUMENT),
	
	//后台-区域操作类
	CREATEAREA("新建区域", 10801,ObjectType.AREA),  EDITAREA("修改区域", 10802,ObjectType.AREA), DELAREA("删除区域", 10803,ObjectType.AREA),
	DROPAREA("彻底删除区域", 10804,ObjectType.AREA), SHOWAREA("查看区域", 10805,ObjectType.AREA),
	SHOWAREALIST("查看区域列表", 10806,ObjectType.AREA),SHOWSUBAREALIST("显示指定区域的所有子区域信息", 10807,ObjectType.AREA),
	
	//后台-日志操作类
	SHOWLOGGER("查看日志", 10901,ObjectType.LOGGER),SHOWLOGGERLIST("查看日志列表", 10902,ObjectType.LOGGER),
	
	//后台-服务队操作类
	CREATESERTEAM("新建服务队",11001,ObjectType.SERTEAM), EDITSERTEAM("修改服务队",11002,ObjectType.SERTEAM),
	DELSERTEAM("删除服务队",11003,ObjectType.SERTEAM),DROPSERTEAM("彻底删除服务队",11004,ObjectType.SERTEAM),
	SHOWSERTEAM("查看服务队",11005,ObjectType.SERTEAM),SHOWSERTEAMLIST("查看服务队列表",11006,ObjectType.SERTEAM),
	SHOWSTATISTICSSERVICELIST("查询服务队统计信息列表",11007,ObjectType.SERTEAM),
	
	//后台-服务队用户操作类
	CREATETEAMUSER("添加用户到服务队",11101,ObjectType.TEAMUSER),DROPTEAMUSER("彻底删除服务队用户",11102,ObjectType.TEAMUSER),
	SHOWTEAMUSERLIST("查看服务队中用户列表",11103,ObjectType.TEAMUSER),
	
	//后台-培训记录操纵类
	CREATETRRECORD("新建培训记录",11201,ObjectType.TRRECORD),EDITTRRECORD("修改培训记录",11202,ObjectType.TRRECORD),
	DELTRRECORD("删除培训记录",11203,ObjectType.TRRECORD),DROPTRRECORD("彻底修改培训记录",11204,ObjectType.TRRECORD),
	SHOWTRRECORD("查看培训记录",11205,ObjectType.TRRECORD),SHOWTRRECORDLIST("查看培训记录列表",11206,ObjectType.TRRECORD),
	
	//后台-培训记录志愿者操作类
	CREATETRAINVOL("添加志愿者到培训记录",11301,ObjectType.TRAINVOl),DROPTRAINVOL("彻底删除培训记录志愿者",11302,ObjectType.TRAINVOl),
	SHOWTRAINVOLLIST("查看培训记录志愿者列表",11303,ObjectType.TRAINVOl),
	
	//后台-定时任务操作类
	SHOWSCHEDULEJOB("查看定时任务", 11401,ObjectType.SCHEDULEJOB),SHOWSCHEDULEJOBLIST("查看定时任务列表", 11402,ObjectType.SCHEDULEJOB),
	CREATESCHEDULEJOB("创建定时任务", 11403,ObjectType.SCHEDULEJOB),EDITSCHEDULEJOB("编辑定时任务", 11404,ObjectType.SCHEDULEJOB),
	DELETECHEDULEJOB("删除定时任务", 11405,ObjectType.SCHEDULEJOB),STARTCHEDULEJOB("人工启动定时任务", 11406,ObjectType.SCHEDULEJOB),
	PAUSECHEDULEJOB("暂停定时任务", 11407,ObjectType.SCHEDULEJOB),RESUMECHEDULEJOB("恢复定时任务", 11408,ObjectType.SCHEDULEJOB),
	SHOWCHEDULEJOBLOGLIST("查看任务日志列表", 11409,ObjectType.SCHEDULEJOB),SHOWCHEDULEJOBLOGDETAIL("查看任务日志详情", 11410,ObjectType.SCHEDULEJOB),
	
	//后台-活动操作类
	SHOWACTIVITY("查看活动信息", 11501,ObjectType.ACTIVE),EDITACTIVITY("编辑活动信息", 11502,ObjectType.ACTIVE), 
	CREATEACTIVITY("添加活动信息", 11503,ObjectType.ACTIVE), SHOWACTIVITYLIST("查询活动登记信息列表", 11504,ObjectType.ACTIVE),
	SHOWACTIVITYAPPLYLIST("查询活动报名信息列表", 11505,ObjectType.ACTIVE),SHOWACTIVITYRECORDLIST("查询活动记录信息列表", 11506,ObjectType.ACTIVE),
	SHOWACTIVITYAPPLY("展示活动报名信息", 11507,ObjectType.ACTIVE),REVIEWACTIVITYAPPLY("审核志愿者报名活动申请", 11508,ObjectType.ACTIVE),
	SHOWACTIVITYRECORD("查看活动记录信息", 11509,ObjectType.ACTIVE), SHOWACTIVITYHOURS("查看活动时长管理信息", 11510,ObjectType.ACTIVE), 
	SHOWRECRUITACTIVITYLIST("查看长期类型且处于已完成和已撤销状态的活动", 11511,ObjectType.ACTIVE), SAVEACTIVITYRECORD("保存活动记录操作", 11512,ObjectType.ACTIVE),
	BATCHCANCELACTIVITYS("批量撤销活动", 11513,ObjectType.ACTIVE), BATCHDELACTIVITYS("批量删除活动", 11514,ObjectType.ACTIVE), 
	BATCHPUBACTIVITYS("批量发布活动", 11515,ObjectType.ACTIVE), BATCHCOMMITACTIVITYS("批量提交活动", 11516,ObjectType.ACTIVE),
	CREATERECRUIT("添加活动招募", 11517,ObjectType.ACTIVE), SAVEACTIVITYHOURS("保存指定活动中志愿者服务情况信息", 11518,ObjectType.ACTIVE),
	SHOWSTATISTICSACTIVITYLIST("查询活动统计信息列表",11519,ObjectType.ACTIVE),UPDATEACTIVITYSTATUS("更新活动状态",11520,ObjectType.ACTIVE),
	
	//后台-系统配置操作类
	SHOWCONFIGLIST("查看系统配置列表信息", 11601,ObjectType.CONFIG),CREATECONFIG("添加系统配置", 11602,ObjectType.CONFIG),
	SHOWCONFIG("查看系统配置", 11603,ObjectType.CONFIG), EDITCONFIG("编辑系统配置", 11604,ObjectType.CONFIG), 
	DELCONFIG("删除系统配置", 11605,ObjectType.CONFIG), SHOWCONFIGTYPE("获取系统配置类型", 11606,ObjectType.CONFIG),
	
	//后台-证书年检时间管理操作类
	SHOWCHECKTIMELIST("获取证书年检时间列表", 11701,ObjectType.CHECKTIME), CREATECHECKTIME("创建证书年检时间信息", 11702,ObjectType.CHECKTIME), 
	EDITCHECKTIME("编辑证书年检时间信息", 11703,ObjectType.CHECKTIME), DELCHECKTIME("批量删除证书年检时间信息", 11704,ObjectType.CHECKTIME),
	
	//后台 - 在线用户管理操作类
	SHOWLOGINUSERLIST("查看在线用户列表",11501,ObjectType.LOGINUSER) , LOGOUTUSER("强制下线用户",11502,ObjectType.LOGINUSER) , 
	
	//后台-锁定用户管理操作类
	SHOWLOCKUSERLIST("查看锁定用户列表",11601,ObjectType.LOCKUSER), UNLOCKUSER("解锁用户",11602,ObjectType.LOCKUSER),
	
	//后台-短信验证码管理操作类
	SHOWSMSLIST("查看短信验证码列表",11701,ObjectType.SMS);
	
	// 成员变量  
   private String name;  
   private int value;
   
   private ObjectType objType;
   
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
   
   public ObjectType getObjType() {  
       return objType;  
   }
   
   public void setgetObjType(ObjectType objType) {  
       this.objType = objType;  
   } 
	
	// 构造方法  
   private OperateType(String name, int value,ObjectType objType) {  
       this.name = name;  
       this.value = value;  
       this.objType=objType;
   }

   // 普通方法  
   public static String getName(int value) {  
       for (OperateType c : OperateType.values()) {  
           if (c.getValue() == value) {  
               return c.name;  
           }  
       }  
       return "未知";  
   }
   
   // 普通方法  
   public static OperateType getByValue(int value) {  
       for (OperateType c : OperateType.values()) {  
           if (c.getValue() == value) {  
               return c;  
           }  
       }  
       return null;  
   }
   
   public static List<OperateType> getOperateTypeByObjectType(ObjectType objType){
	   List<OperateType> list=new ArrayList<OperateType>();
	   for (OperateType c : OperateType.values()) {  
           if (c.getObjType().getValue() == objType.getValue()) {  
              list.add(c);
           }  
       }  
      return list;
   }

}
