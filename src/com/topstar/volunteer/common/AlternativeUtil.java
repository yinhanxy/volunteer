package com.topstar.volunteer.common;

/**
 * 
 * @author TRS
 *
 */
public class AlternativeUtil {
	
	/**
	 * 获取民族
	 * @param nationFlag
	 * @return
	 */
	public static String getNationName(String nationFlag) {
		String nationName="汉族";
		if (nationFlag!=null && nationFlag!="") {
         switch (nationFlag) {
             case "01":
            	 nationName = "汉族";
                 break;
             case "02":
            	 nationName = "蒙古族";
                 break;
             case "03":
            	 nationName = "满族";
                 break;
             case "04":
            	 nationName = "朝鲜族";
                 break;
             case "05":
            	 nationName = "赫哲族";
                 break;
             case "06":
            	 nationName = "达斡尔族";
                 break;
             case "07":
            	 nationName = "鄂温克族";
                 break;
             case "08":
            	 nationName = "鄂伦春族";
                 break;
             case "09":
            	 nationName = "回族";
                 break;
             case "10":
            	 nationName = "东乡族";
                 break;
             case "11":
            	 nationName = "土族";
                 break;
             case "12":
            	 nationName = "撒拉族";
                 break;
             case "13":
            	 nationName = "保安族";
                 break;
             case "14":
            	 nationName = "裕固族";
                 break;
             case "15":
            	 nationName = "维吾尔族";
                 break;
             case "16":
            	 nationName = "哈萨克族";
                 break;
             case "17":
            	 nationName = "柯尔克孜族";
                 break;
             case "18":
            	 nationName = "锡伯族";
                 break;
             case "19":
            	 nationName = "塔吉克族";
                 break;
             case "20":
            	 nationName = "乌孜别克族";
                 break;
             case "21":
            	 nationName = "俄罗斯族";
                 break;
             case "22":
            	 nationName = "塔塔尔族";
                 break;
             case "23":
            	 nationName = "藏族";
                 break;
             case "24":
            	 nationName = "门巴族";
                 break;
             case "25":
            	 nationName = "珞巴族";
                 break;
             case "26":
            	 nationName = "羌族";
                 break;
             case "27":
            	 nationName = "彝族";
                 break;
             case "28":
            	 nationName = "白族";
                 break;
             case "29":
            	 nationName = "哈尼族";
                 break;
             case "30":
            	 nationName = "傣族";
                 break;
             case "31":
            	 nationName = "傈僳族";
                 break;
             case "32":
            	 nationName = "佤族";
                 break;
             case "33":
            	 nationName = "拉祜族";
                 break;
             case "34":
            	 nationName = "纳西族";
                 break;
             case "35":
            	 nationName = "景颇族";
                 break;
             case "36":
            	 nationName = "布朗族";
                 break;
             case "37":
            	 nationName = "阿昌族";
                 break;
             case "38":
            	 nationName = "普米族";
                 break;
             case "39":
            	 nationName = "怒族";
                 break;
             case "40":
            	 nationName = "德昂族";
                 break;
             case "41":
            	 nationName = "独龙族";
                 break;
             case "42":
            	 nationName = "基诺族";
                 break;
             case "43":
            	 nationName = "苗族";
                 break;
             case "44":
            	 nationName = "布依族";
                 break;
             case "45":
            	 nationName = "侗族";
                 break;
             case "46":
            	 nationName = "水族";
                 break;
             case "47":
            	 nationName = "仡佬族";
                 break;
             case "48":
            	 nationName = "壮族";
                 break;
             case "49":
            	 nationName = "瑶族";
                 break;
             case "50":
            	 nationName = "仫佬族";
                 break;
             case "51":
            	 nationName = "毛南族";
                 break;
             case "52":
            	 nationName = "京族";
                 break;
             case "53":
            	 nationName = "土家族";
                 break;
             case "54":
            	 nationName = "黎族";
                 break;
             case "55":
            	 nationName = "畲族";
                 break;
             case "56":
            	 nationName = "高山族";
                 break;
             default:
            	 break;
         }
		}
         return nationName;
     }
	
	/**
	 * 获取政治面貌
	 * @param polityFlag
	 * @return
	 */
	public static String getPolityName(String polityFlag) {
		String polityName="";
		if (polityFlag!=null && polityFlag!="") {
         switch (polityFlag) {
             case "01":
            	 polityName = "中共党员";
                 break;
             case "02":
            	 polityName = "中共预备党员";
                 break;
             case "03":
            	 polityName = "共青团员";
                 break;
             case "04":
            	 polityName = "民主党派";
                 break;
             case "05":
            	 polityName = "无党派民主人士";
                 break;
             case "06":
            	 polityName = "群众";
                 break;
             default:
            	 break;
         }
		}
         return polityName;
     }
	
	/**
	 * 获取学历
	 * @param educationFlag
	 * @return
	 */
	public static String getEducation(String educationFlag) {
		String educationName="无";
		if(educationFlag!=null && educationFlag!=""){
         switch (educationFlag) {
             case "01":
            	 educationName = "博士";
                 break;
             case "02":
            	 educationName = "硕士";
                 break;
             case "03":
            	 educationName = "本科";
                 break;
             case "04":
            	 educationName = "大专";
                 break;
             case "05":
            	 educationName = "中专";
                 break;
             case "06":
            	 educationName = "职业高中";
                 break;
             case "07":
            	 educationName = "普通高中";
                 break;
             case "08":
            	 educationName = "初中";
                 break;
             case "09":
            	 educationName = "小学";
                 break;
             case "10":
            	 educationName = "其他";
                 break;
             default:
            	 break;
         }
		}
         return educationName;
     }
	
	/**
	 * 获取职称
	 * @param jobTitleFlag
	 * @return
	 */
	public static String getJobTitle(String jobTitleFlag) {
		String jobTitle="无";
		if (jobTitleFlag!=null && jobTitleFlag!="") {
         switch (jobTitleFlag) {
             case "01":
            	 jobTitle = "正高";
                 break;
             case "02":
            	 jobTitle = "副高";
                 break;
             case "03":
            	 jobTitle = "中级";
                 break;
             case "04":
            	 jobTitle = "初级";
                 break;
             default:
            	 break;
         }
		}
         return jobTitle;
     }
	
	/**
	 * 获取志愿者审核状态
	 * @param status
	 * @return
	 */
	public static String getVolunteerCheckStatus(String status) {
		String checkStatus="未审核";
		if(status!=null){
	         switch (status) {
	             case "2":
	            	 checkStatus = "已通过";
	                 break;
	             case "3":
	            	 checkStatus = "再审";
	                 break;
	             case "4":
	            	 checkStatus = "已否";
	                 break;
	             default:
	            	 break;
	         }
	         return checkStatus;
		}
		
		return checkStatus;
		
     }
	
	/**
	 * 获取志愿者状态
	 * @param status
	 * @return
	 */
	public static String getVolunteerStatus(Integer status) {
		String statusDesc="";
		if(status!=null){
	         switch (status) {
		         case 1:
		        	 statusDesc = "未审核";
	                 break;
	             case 2:
	            	 statusDesc = "正常";
	                 break;
	             case 3:
	            	 statusDesc = "再审";
	                 break;
	             case 4:
	            	 statusDesc = "已否";
	                 break;
	             case 5:
	            	 statusDesc = "已退队";
	                 break;
	             case 6:
	            	 statusDesc = "黑名单";
	                 break;
	             default:
	            	 break;
	         }
	         return statusDesc;
		}
		
		return "异常";
		
     }
	
	public static String getVolunteerCertificationStatus(String status) {
		String certificationStatus="待审核";
		if(status!=null){
	         switch (status) {
	             case "01":
	            	 certificationStatus = "已通过";
	                 break;
	             case "02":
	            	 certificationStatus = "已否";
	                 break;
	             default:
	            	 break;
	         }
	         return certificationStatus;
		}
		
		return certificationStatus;
		
     }
	
	public static String getVolunteerStarStatus(String star) {
		String starStatus="暂无";
		if(star!=null){
	         switch (star) {
		         case "0":
	            	 starStatus = "无星";
	                 break;
	             case "1":
	            	 starStatus = "一星";
	                 break;
	             case "2":
	            	 starStatus = "二星";
	                 break;
	             case "3":
	            	 starStatus = "三星";
	                 break;
	             case "4":
	            	 starStatus = "四星";
	                 break;
	             case "5":
	            	 starStatus = "五星";
	                 break;
	         }
	         return starStatus;
		}
		
		return starStatus;
		
     }
}
