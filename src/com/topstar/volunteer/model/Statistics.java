package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 志愿者统计
 */
public class Statistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 471840690642284508L;
	
	/**
	 * 区域名称
	 */
	private String areaName;
	
	/**
	 * 志愿者人数
	 */
	private int volNum;
	
	/**
	 * 志愿者男生人数
	 */
	private int volBoysNum;
	
	/**
	 * 志愿者女生人数
	 */
	private int volGirlsNum;
	
	/**
	 * 志愿者博士人数
	 */
	private int volGraduateNum;
	
	/**
	 * 志愿者硕士人数
	 */
	private int volPostGraduateNum;
	
	/**
	 * 志愿者本科生人数
	 */
	private int volUndergraduateNum;
	
	/**
	 * 志愿者大专人数
	 */
	private int volCollegeNum;
	
	/**
	 * 志愿者中专人数
	 */
	private int volSecondaryNum;
	
	/**
	 * 职业高中
	 */
	private int volVocationalNum;
	
	/**
	 * 普高
	 */
	private int volMiddleNum;
	
	/**
	 * 初中
	 */
	private int volJuniorNum;
	
	/**
	 * 小学
	 */
	private int volPrimaryNum;
	
	/**
	 * 志愿者其他学历
	 */
	private int volOtherEducation;
	
	/**
	 * 志愿者党员人数
	 */
	private int volCpcNum;
	
	/**
	 * 志愿者预备党员
	 */
	private int volPrepareNum;
	
	/**
	 * 志愿者共青团员
	 */
	private int volCYLNum;
	
	/**
	 * 民主党派
	 */
	private int volDemocraticNum; 
	
	/**
	 * 无党派民主人士
	 */
	private int volNonpartisanNum;
	
	/**
	 * 群众
	 */
	private int volCitizen;
	
	/**
	 * 20岁以下
	 */
	private int volTeen;
	
	/**
	 * 21-30
	 */
	private int volTwenty;
	
	/**
	 * 31-40
	 */
	private int volThirty;
	
	/**
	 * 41-50
	 */
	private int volForty;
	
	/**
	 * 51-60
	 */
	private int volFiFty;
	
	/**
	 * 60以上
	 */
	private int volSixty;
	
	/**
	 * 区域类型
	 */
	private int areaType;
	
	/**
	 * 区域编码
	 */
	private String areaCode;
	
	/**
	 * 当前用户所在的机构Id
	 */
	private Long curOrgId;
	
	/**
	 * 服务队名称
	 */
	private String serTeamName;
	
	/**
	 * 服务队的志愿者人数
	 */
	private int serVolNum;
	
	/**
	 * 是否是业务机构
	 */
	private boolean istreatAgency;
	
	/**
	 * 志愿者星级
	 */
	private String star;
	
	/**
	 * 服务队数量
	 */
	private int serTeamNum;
	
	/**
	 * 服务队时长
	 */
	private int serTeamHour;
	
	/**
	 * 活动数量
	 */
	private int activityNum;
	
	/**
	 * 培训次数
	 */
	private int recordNum;
	
	/**
	 * 志愿者名称
	 */
	private String volName;
	
	/**
	 * 注册时间
	 */
	private Date regTime;
	
	/**
	 * 年份
	 */
	private String year;
	
	private Long orgId;
	
	/**
	 * 服务队的服务时长
	 */
	private Double serHours;
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getVolNum() {
		return volNum;
	}

	public void setVolNum(int volNum) {
		this.volNum = volNum;
	}
	
	public int getAreaType() {
		return areaType;
	}

	public void setAreaType(int areaType) {
		this.areaType = areaType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Long getCurOrgId() {
		return curOrgId;
	}

	public void setCurOrgId(Long curOrgId) {
		this.curOrgId = curOrgId;
	}

	public String getSerTeamName() {
		return serTeamName;
	}

	public void setSerTeamName(String serTeamName) {
		this.serTeamName = serTeamName;
	}

	public int getSerVolNum() {
		return serVolNum;
	}

	public void setSerVolNum(int serVolNum) {
		this.serVolNum = serVolNum;
	}

	public boolean isIstreatAgency() {
		return istreatAgency;
	}

	public void setIstreatAgency(boolean istreatAgency) {
		this.istreatAgency = istreatAgency;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getSerTeamNum() {
		return serTeamNum;
	}

	public void setSerTeamNum(int serTeamNum) {
		this.serTeamNum = serTeamNum;
	}

	public int getSerTeamHour() {
		return serTeamHour;
	}

	public void setSerTeamHour(int serTeamHour) {
		this.serTeamHour = serTeamHour;
	}

	public int getActivityNum() {
		return activityNum;
	}

	public void setActivityNum(int activityNum) {
		this.activityNum = activityNum;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getVolBoysNum() {
		return volBoysNum;
	}

	public void setVolBoysNum(int volBoysNum) {
		this.volBoysNum = volBoysNum;
	}

	public int getVolGirlsNum() {
		return volGirlsNum;
	}

	public void setVolGirlsNum(int volGirlsNum) {
		this.volGirlsNum = volGirlsNum;
	}

	public int getVolGraduateNum() {
		return volGraduateNum;
	}

	public void setVolGraduateNum(int volGraduateNum) {
		this.volGraduateNum = volGraduateNum;
	}

	public int getVolUndergraduateNum() {
		return volUndergraduateNum;
	}

	public void setVolUndergraduateNum(int volUndergraduateNum) {
		this.volUndergraduateNum = volUndergraduateNum;
	}

	public int getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}

	public int getVolCollegeNum() {
		return volCollegeNum;
	}

	public void setVolCollegeNum(int volCollegeNum) {
		this.volCollegeNum = volCollegeNum;
	}

	public int getVolOtherEducation() {
		return volOtherEducation;
	}

	public void setVolOtherEducation(int volOtherEducation) {
		this.volOtherEducation = volOtherEducation;
	}

	public int getVolCpcNum() {
		return volCpcNum;
	}

	public void setVolCpcNum(int volCpcNum) {
		this.volCpcNum = volCpcNum;
	}

	public int getVolPrepareNum() {
		return volPrepareNum;
	}

	public void setVolPrepareNum(int volPrepareNum) {
		this.volPrepareNum = volPrepareNum;
	}

	public int getVolCYLNum() {
		return volCYLNum;
	}

	public void setVolCYLNum(int volCYLNum) {
		this.volCYLNum = volCYLNum;
	}

	public int getVolCitizen() {
		return volCitizen;
	}

	public void setVolCitizen(int volCitizen) {
		this.volCitizen = volCitizen;
	}
	
	public int getVolDemocraticNum() {
		return volDemocraticNum;
	}

	public void setVolDemocraticNum(int volDemocraticNum) {
		this.volDemocraticNum = volDemocraticNum;
	}

	public int getVolNonpartisanNum() {
		return volNonpartisanNum;
	}

	public void setVolNonpartisanNum(int volNonpartisanNum) {
		this.volNonpartisanNum = volNonpartisanNum;
	}

	public int getVolPostGraduateNum() {
		return volPostGraduateNum;
	}

	public void setVolPostGraduateNum(int volPostGraduateNum) {
		this.volPostGraduateNum = volPostGraduateNum;
	}

	public int getVolSecondaryNum() {
		return volSecondaryNum;
	}

	public void setVolSecondaryNum(int volSecondaryNum) {
		this.volSecondaryNum = volSecondaryNum;
	}

	public int getVolVocationalNum() {
		return volVocationalNum;
	}

	public void setVolVocationalNum(int volVocationalNum) {
		this.volVocationalNum = volVocationalNum;
	}

	public int getVolMiddleNum() {
		return volMiddleNum;
	}

	public void setVolMiddleNum(int volMiddleNum) {
		this.volMiddleNum = volMiddleNum;
	}

	public int getVolJuniorNum() {
		return volJuniorNum;
	}

	public void setVolJuniorNum(int volJuniorNum) {
		this.volJuniorNum = volJuniorNum;
	}

	public int getVolPrimaryNum() {
		return volPrimaryNum;
	}

	public void setVolPrimaryNum(int volPrimaryNum) {
		this.volPrimaryNum = volPrimaryNum;
	}

	public int getVolTeen() {
		return volTeen;
	}

	public void setVolTeen(int volTeen) {
		this.volTeen = volTeen;
	}

	public int getVolTwenty() {
		return volTwenty;
	}

	public void setVolTwenty(int volTwenty) {
		this.volTwenty = volTwenty;
	}

	public int getVolThirty() {
		return volThirty;
	}

	public void setVolThirty(int volThirty) {
		this.volThirty = volThirty;
	}

	public int getVolForty() {
		return volForty;
	}

	public void setVolForty(int volForty) {
		this.volForty = volForty;
	}

	public int getVolFiFty() {
		return volFiFty;
	}

	public void setVolFiFty(int volFiFty) {
		this.volFiFty = volFiFty;
	}

	public int getVolSixty() {
		return volSixty;
	}

	public void setVolSixty(int volSixty) {
		this.volSixty = volSixty;
	}

	public String getVolName() {
		return volName;
	}

	public void setVolName(String volName) {
		this.volName = volName;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Double getSerHours() {
		return serHours;
	}

	public void setSerHours(Double serHours) {
		this.serHours = serHours;
	}

	@Override
	public String toString() {
		return "Statistics [areaName=" + areaName + ", volNum=" + volNum + ", volBoysNum=" + volBoysNum
				+ ", volGirlsNum=" + volGirlsNum + ", volGraduateNum=" + volGraduateNum + ", volPostGraduateNum="
				+ volPostGraduateNum + ", volUndergraduateNum=" + volUndergraduateNum + ", volCollegeNum="
				+ volCollegeNum + ", volSecondaryNum=" + volSecondaryNum + ", volVocationalNum=" + volVocationalNum
				+ ", volMiddleNum=" + volMiddleNum + ", volJuniorNum=" + volJuniorNum + ", volPrimaryNum="
				+ volPrimaryNum + ", volOtherEducation=" + volOtherEducation + ", volCpcNum=" + volCpcNum
				+ ", volPrepareNum=" + volPrepareNum + ", volCYLNum=" + volCYLNum + ", volDemocraticNum="
				+ volDemocraticNum + ", volNonpartisanNum=" + volNonpartisanNum + ", volCitizen=" + volCitizen
				+ ", volTeen=" + volTeen + ", volTwenty=" + volTwenty + ", volThirty=" + volThirty + ", volForty="
				+ volForty + ", volFiFty=" + volFiFty + ", volSixty=" + volSixty + ", areaType=" + areaType
				+ ", areaCode=" + areaCode + ", curOrgId=" + curOrgId + ", serTeamName=" + serTeamName + ", serVolNum="
				+ serVolNum + ", istreatAgency=" + istreatAgency + ", star=" + star + ", serTeamNum=" + serTeamNum
				+ ", serTeamHour=" + serTeamHour + ", activityNum=" + activityNum + ", recordNum=" + recordNum
				+ ", volName=" + volName + ", regTime=" + regTime + ", year=" + year + ", orgId=" + orgId
				+ ", serHours=" + serHours + "]";
	}

}
