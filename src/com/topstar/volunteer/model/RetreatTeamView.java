package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 志愿者退队视图展示实体
 * @author TRS
 *
 */
public class RetreatTeamView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5380363136392002312L;

	private Long id;

    private String userName;
    
    private String realName;

    private String sex;

    private String idcard;

    private String serviceTeam;

    private String mobile;

    private String email;
    
    private Date birthday;

    private Date regTime;
    
    /**
     * 发证日期
     */
    private Date certTime;
    
    /**
     * 申请退队时间
     */
    private Date applyTime;
    
    /**
     * 申请退队原因
     */
    private String retreatReason;
    
    
	/**
	 * 退队处理人
	 */
	private String dealUser;
	
	/**
	 * 退队处理时间
	 */
	private String dealTime;
	
	/**
     * 退队处理结果
     */
    private String dealStatus;

	/**
     * 退队处理意见
     */
    private String dealOpinion;
    
    /**
     * 退队处理Id
     */
    private Long dealId;
    
    /**
     * 服务队名称
     */
    private String serTeamName;
    
    /**
     * 服务时长
     */
    private int serviceHour;
    
    /**
     * 退队申请ID
     */
    private Long rId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getServiceTeam() {
		return serviceTeam;
	}

	public void setServiceTeam(String serviceTeam) {
		this.serviceTeam = serviceTeam;
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

	public Date getCertTime() {
		return certTime;
	}

	public void setCertTime(Date certTime) {
		this.certTime = certTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getRetreatReason() {
		return retreatReason;
	}

	public void setRetreatReason(String retreatReason) {
		this.retreatReason = retreatReason;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getDealOpinion() {
		return dealOpinion;
	}

	public void setDealOpinion(String dealOpinion) {
		this.dealOpinion = dealOpinion;
	}

	public String getRetreatTeamResult() {
		if(!StringUtils.isBlank(dealStatus)){
			if("1".equals(dealStatus)){
				return "已同意";
			}
			if("0".equals(dealStatus)){
				return "不同意";
			}
		}
		return "待处理";
	}

	public String getSerTeamName() {
		return serTeamName;
	}

	public void setSerTeamName(String serTeamName) {
		this.serTeamName = serTeamName;
	}

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public int getServiceHour() {
		return serviceHour;
	}

	public void setServiceHour(int serviceHour) {
		this.serviceHour = serviceHour;
	}

	
	
	/**
	 * @return the rId
	 */
	public Long getrId() {
		return rId;
	}

	/**
	 * @param rId the rId to set
	 */
	public void setrId(Long rId) {
		this.rId = rId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RetreatTeamView [id=" + id + ", userName=" + userName
				+ ", realName=" + realName + ", sex=" + sex + ", idcard="
				+ idcard + ", serviceTeam=" + serviceTeam + ", mobile="
				+ mobile + ", email=" + email + ", birthday=" + birthday
				+ ", regTime=" + regTime + ", certTime=" + certTime
				+ ", applyTime=" + applyTime + ", retreatReason="
				+ retreatReason + ", dealUser=" + dealUser + ", dealTime="
				+ dealTime + ", dealStatus=" + dealStatus + ", dealOpinion="
				+ dealOpinion + ", dealId=" + dealId + ", serTeamName="
				+ serTeamName + ", serviceHour=" + serviceHour + ", rId=" + rId
				+ "]";
	}

	

}