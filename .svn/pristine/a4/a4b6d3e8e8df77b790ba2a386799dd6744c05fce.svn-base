package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class TurnTeamView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5380363136392002312L;

	private Long id;

	private Long volunteerId;
	
    private String userName;
    
    private String realName;

    private String sex;

    private String idcard;

    private String mobile;

    private String email;
    
    private Date birthday;

    private Date regTime;
    
    private Integer serviceHour;
    /**
     * 申请转队时间
     */
    private Date applyTime;
    
    /**
     * 申请转入的服务队标识
     */
    private Long targetId;
    
    /**
     * 申请转入的服务队
     */
    private String targetTeam;
    
    /**
     * 转入服务队属性
     */
    private String property;
    
	/**
	 * 转队原因
	 */
	private String applyReason;
	
	
	/**
	 * 转出处理人用户id
	 */
	private Long suserId;
	
	/**
	 * 转出处理人用户名
	 */
	private String sdealUser;
	
	/**
	 * 转出处理服务队
	 */
	private String sdealTeam;
	
	/**
	 * 原所属服务队id
	 */
	private Long sourceTeamId;
	
	/**
	 * 原所属服务队名称
	 */
	private String sourceTeam;
	
	
	/**
	 * 转出处理时间
	 */
	private Date sdealTime;
	
	/**
     * 转出处理结果
     */
    private Integer sourceResult;

	/**
     * 转出处理意见
     */
    private String sourceOpinion;
    
    /**
	 * 转入处理用户id
	 */
    private Long tuserId;
    
    /**
	 * 转入处理用户名
	 */
	private String tdealUser;
	
	/**
	 * 转入处理服务队名称
	 */
	private String tdealTeam;
	
	/**
	 * 转入处理时间
	 */
	private Date tdealTime;
	
	/**
     * 转入处理结果
     */
    private Integer targetResult;

	/**
     * 转入处理意见
     */
    private String targetOpinion;
    
    /**
     * 最终转队结果
     */
    private String turnTeamResult;
    
    /**
     * 转队操作的直接处理服务队编号
     */
    private Long directSerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(Long volunteerId) {
		this.volunteerId = volunteerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Integer getServiceHour() {
		return serviceHour;
	}

	public void setServiceHour(Integer serviceHour) {
		this.serviceHour = serviceHour;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getTargetTeam() {
		return targetTeam;
	}

	public void setTargetTeam(String targetTeam) {
		this.targetTeam = targetTeam;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Long getSuserId() {
		return suserId;
	}

	public void setSuserId(Long suserId) {
		this.suserId = suserId;
	}

	public String getSdealUser() {
		return sdealUser;
	}

	public void setSdealUser(String sdealUser) {
		this.sdealUser = sdealUser;
	}

	public String getSdealTeam() {
		return sdealTeam;
	}

	public void setSdealTeam(String sdealTeam) {
		this.sdealTeam = sdealTeam;
	}

	public Long getSourceTeamId() {
		return sourceTeamId;
	}

	public void setSourceTeamId(Long sourceTeamId) {
		this.sourceTeamId = sourceTeamId;
	}

	public String getSourceTeam() {
		return sourceTeam;
	}

	public void setSourceTeam(String sourceTeam) {
		this.sourceTeam = sourceTeam;
	}

	public Date getSdealTime() {
		return sdealTime;
	}

	public void setSdealTime(Date sdealTime) {
		this.sdealTime = sdealTime;
	}

	public Integer getSourceResult() {
		return sourceResult;
	}
	
	public String  getExitResult() {
		if(sourceResult!=null && sourceResult.equals(1)){
			return "已通过";
		}else if(sourceResult!=null && !sourceResult.equals(1)){
			return "已否";
		}
		return "异常";
	}

	public void setSourceResult(Integer sourceResult) {
		this.sourceResult = sourceResult;
	}

	public String getSourceOpinion() {
		return sourceOpinion;
	}

	public void setSourceOpinion(String sourceOpinion) {
		this.sourceOpinion = sourceOpinion;
	}

	public Long getTuserId() {
		return tuserId;
	}

	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
	}

	public String getTdealUser() {
		return tdealUser;
	}

	public void setTdealUser(String tdealUser) {
		this.tdealUser = tdealUser;
	}

	public String getTdealTeam() {
		return tdealTeam;
	}

	public void setTdealTeam(String tdealTeam) {
		this.tdealTeam = tdealTeam;
	}

	public Date getTdealTime() {
		return tdealTime;
	}

	public void setTdealTime(Date tdealTime) {
		this.tdealTime = tdealTime;
	}

	public Integer getTargetResult() {
		return targetResult;
	}
	
	public String  getAddResult() {
		if(targetResult!=null && targetResult.equals(1)){
			return "已通过";
		}else if(targetResult!=null && !targetResult.equals(1)){
			return "已否";
		}
		return "异常";
	}
	
	public void setTargetResult(Integer targetResult) {
		this.targetResult = targetResult;
	}

	public String getTargetOpinion() {
		return targetOpinion;
	}

	public void setTargetOpinion(String targetOpinion) {
		this.targetOpinion = targetOpinion;
	}

	public String getTurnTeamResult() {
		if(null==sourceResult){
			return "待处理";
		}else if(null!=sourceResult && null==targetResult){
			if (sourceResult==2) {
				return "已否";
			}else{
				return "处理中";
			}
		}else if(null!=sourceResult && null!=targetResult){
			if((sourceResult*targetResult)==1){
				return "已同意";
			}else{
				return "已否";
			}
		}
		return "异常";
	}

	public void setTurnTeamResult(String turnTeamResult) {
		this.turnTeamResult = turnTeamResult;
	}

	public Long getDirectSerId() {
		return directSerId;
	}

	public void setDirectSerId(Long directSerId) {
		this.directSerId = directSerId;
	}

	@Override
	public String toString() {
		return "TurnTeamView [id=" + id + ", volunteerId=" + volunteerId + ", userName=" + userName + ", sex=" + sex
				+ ", idcard=" + idcard + ", mobile=" + mobile + ", email=" + email + ", birthday=" + birthday
				+ ", regTime=" + regTime + ", serviceHour=" + serviceHour + ", applyTime=" + applyTime + ", targetId="
				+ targetId + ", targetTeam=" + targetTeam + ", property=" + property + ", applyReason=" + applyReason
				+ ", suserId=" + suserId + ", sdealUser=" + sdealUser + ", sdealTeam=" + sdealTeam + ", sourceTeamId="
				+ sourceTeamId + ", sourceTeam=" + sourceTeam + ", sdealTime=" + sdealTime + ", sourceResult="
				+ sourceResult + ", sourceOpinion=" + sourceOpinion + ", tuserId=" + tuserId + ", tdealUser="
				+ tdealUser + ", tdealTeam=" + tdealTeam + ", tdealTime=" + tdealTime + ", targetResult=" + targetResult
				+ ", targetOpinion=" + targetOpinion + ", turnTeamResult=" + turnTeamResult + ", directSerId="
				+ directSerId + "]";
	}

	
}