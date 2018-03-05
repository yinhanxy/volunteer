package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.Date;

import com.topstar.volunteer.common.AlternativeUtil;

public class VolunteerView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1511908290337562467L;

	private Long id;

    private String userName;

    private String realName;

    private String password;

    private String sex;

    private String idcard;

    private Date birthday;
    
    private String qq;
    
    private String nation;

    private String polstatus;

    private String postcode;

    private String address;

    private String naplace;

    private String education;
    
    private Long serviceId;
    
    private String serviceTeam;

    private String jobTitle;

    private String duties;

    private String grasch;

    private String profession;

    private String work;

    private String jobAddress;

    private String mobile;

    private String email;

    private String specility;

    private String serviceExperiment;

    private String serviceTime;

    private String remark;
    
    private String serviceClass;

    private Date regTime;
    
    /**
     * 头像地址
     */
    private String imgUrl;
    
    /**
     * 服务时长
     */
    private Integer serviceHour;
    
    /**
     * 志愿者的审核状态
     */
    private String status;
    
    /**
     * 志愿者状态
     */
    private Integer volunteerStatus;
    
    /**
     * 志愿者证书信息记录标识
     */
    private String certId;
    
    /**
     * 志愿者证书的颁发时间
     */
    private Date certDate;
    
	/**
	 * 证书审核人
	 */
	private String opertUser;
	
	/**
	 * 证书审核时间
	 */
	private Date opertTime;
	
	/**
	 * 证书审核年份
	 */
	private String checkYear;
	
	/**
	 * 证书审核状态
	 */
	private String checkState;
	
	/**
     * 志愿者星级评定信息的标识ID
     */
    private String volunteerStarId;
	

	/**
     * 志愿者星级评定等级
     */
    private String star;
    

	/**
     * 志愿者星级评定时间
     */
    private Date evaluateTime;
    

	/**
     * 志愿者星级评定用户
     */
    private String evaluateUser;
    

	/**
     * 志愿者星级评定内容
     */
    private String evaluateContent;
    
    /**
     * 志愿者星级评价是否为手动评价
     * 0:否   1:是
     */
    private String isManual;
    
    private String blackId;
    
    /**
     * 志愿者黑名单操作服务队
     */
    private String operateUserSerName;
    
    /**
     * 志愿者黑名单操作人
     */
    private String blackOperateUser;
    
    /**
     * 志愿者黑名单操作时间
     */
    private Date blackOperateDate;
    
    /**
     * 志愿者黑名单拉黑原因
     */
    private String blackOperateReason;
    
    /**
     * 志愿者活动标识Id
     */
    private Long activityVolunteerId;
    
    /**
     * 参加活动的activityId
     */
    private Long activityId;
    
    /**
     * 参加活动的状态
     * 	活动状态
	 * 		"01":待提交
	 * 		"02":已提交
	 * 		"03":待招募
	 * 		"04":招募中
	 *  	"05":待进行
	 *   	"06":进行中
	 *   	"07":已完成
	 *   	"08":已撤销
     */
    private Integer activityStatus;
    
    /**
     * 活动所属服务队
     */
    private String activityServiceTeam;
    
    /**
     * 活动开始时间
     */
    private Date activitySt;
    
    /**
     * 活动结束时间
     */
    private Date activityEt;
    
    /**
     * 报名该活动的时间
     */
    private Date applyTime;
    
    /**
     * 志愿者报名活动结果状态
     */
    private Integer applyActivityStatus;
    
    /**
     * 参加活动的名称
     */
    private String activityName;
    
    /**
     * 志愿者参加活动的签到状态 (1:签到 其他:未签到)
     */
    private Integer checkStatus;

    /**
	 * 志愿者参加活动的服务星级
	 */
	private Integer activityServiceStar;
    
	/**
	 * 志愿者参加活动的服务时长
	 */
	private Double activityServiceHours;
	
	 /**
	  * 志愿者参加活动的状态描述
	 * @return
	 */
	public String getActivityStatusDesc(){
	    	if(activityStatus!=null){
	    		switch (activityStatus) {
	    		case 1:
					return "待提交";
				case 2:
					return "已提交";
				case 3:
					return "待招募";
				case 4:
					return "招募中";
				case 5:
					return "待进行";
				case 6:
					return "进行中";
				case 7:
					return "已完成";
				case 8:
					return "已撤销";
				default:
					break;
				}
	    	}
	    	return "异常";
    }
	
    /**
     * 获取志愿者报名活动的最终结果
     * @return
     */
    public String getApplyResult(){
    	if(applyActivityStatus!=null){
    		switch (applyActivityStatus) {
			case 1:
				return "已接受";
			case 2:
				return "已拒绝";	
			default:
				return null;
			}
    	}
    	return null;
    }
    
    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return REAL_NAME
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return SEX
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return IDCARD
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * @param idcard
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * @return BIRTHDAY
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public String getNationName() {
		return AlternativeUtil.getNationName(nation);
	}
    
    /**
     * @return POLSTATUS
     */
    public String getPolstatus() {
        return polstatus;
    }

    public String getPolstatusName() {
		return AlternativeUtil.getPolityName(polstatus);
	}
    
    /**
     * @param polstatus
     */
    public void setPolstatus(String polstatus) {
        this.polstatus = polstatus;
    }

    /**
     * @return POSTCODE
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return NAPLACE
     */
    public String getNaplace() {
        return naplace;
    }

    /**
     * @param naplace
     */
    public void setNaplace(String naplace) {
        this.naplace = naplace;
    }

    /**
     * @return EDUCATION
     */
    public String getEducation() {
        return education;
    }

    public String getEducationName() {
		return AlternativeUtil.getEducation(education);
	}
    
    /**
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }
    
    public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	/**
     * @return SERVICE_TEAM
     */
    public String getServiceTeam() {
        return serviceTeam;
    }

    /**
     * @param serviceTeam
     */
    public void setServiceTeam(String serviceTeam) {
        this.serviceTeam = serviceTeam;
    }

    /**
     * @return JOB_TITLE
     */
    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobTitleName() {
		return AlternativeUtil.getJobTitle(jobTitle);
	}
    
    /**
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return DUTIES
     */
    public String getDuties() {
        return duties;
    }

    /**
     * @param duties
     */
    public void setDuties(String duties) {
        this.duties = duties;
    }

    /**
     * @return GRASCH
     */
    public String getGrasch() {
        return grasch;
    }

    /**
     * @param grasch
     */
    public void setGrasch(String grasch) {
        this.grasch = grasch;
    }

    /**
     * @return PROFESSION
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return WORK
     */
    public String getWork() {
        return work;
    }

    /**
     * @param work
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     * @return JOB_ADDRESS
     */
    public String getJobAddress() {
        return jobAddress;
    }

    /**
     * @param jobAddress
     */
    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    /**
     * @return MOBILE
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return SPECILITY
     */
    public String getSpecility() {
        return specility;
    }

    /**
     * @param specility
     */
    public void setSpecility(String specility) {
        this.specility = specility;
    }

    /**
     * @return SERVICE_EXPERIMENT
     */
    public String getServiceExperiment() {
        return serviceExperiment;
    }

    /**
     * @param serviceExperiment
     */
    public void setServiceExperiment(String serviceExperiment) {
        this.serviceExperiment = serviceExperiment;
    }

    /**
     * @return SERVICE_TIME
     */
    public String getServiceTime() {
        return serviceTime;
    }

    /**
     * @param serviceTime
     */
    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
     * @return REG_TIME
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * @param regTime
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getServiceHour() {
		return serviceHour;
	}

	public void setServiceHour(Integer serviceHour) {
		this.serviceHour = serviceHour;
	}

	public String getStatus() {
		return AlternativeUtil.getVolunteerCheckStatus(status);
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVolunteerStatus() {
		return AlternativeUtil.getVolunteerStatus(volunteerStatus);
	}

	public void setVolunteerStatus(Integer volunteerStatus) {
		this.volunteerStatus = volunteerStatus;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public Date getCertDate() {
		return certDate;
	}

	public void setCertDate(Date certDate) {
		this.certDate = certDate;
	}

	public String getOpertUser() {
		return opertUser;
	}

	public void setOpertUser(String opertUser) {
		this.opertUser = opertUser;
	}

	public Date getOpertTime() {
		return opertTime;
	}

	public void setOpertTime(Date opertTime) {
		this.opertTime = opertTime;
	}

	public String getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}

	public String getCheckState() {
		return AlternativeUtil.getVolunteerCertificationStatus(checkState);
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getVolunteerStarId() {
		return volunteerStarId;
	}

	public void setVolunteerStarId(String volunteerStarId) {
		this.volunteerStarId = volunteerStarId;
	}

	public String getStar() {
		return star;
	}
	
	public String getEvaluateStar() {
		return AlternativeUtil.getVolunteerStarStatus(star);
	}

	public void setStar(String star) {
		this.star = star;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getEvaluateUser() {
		return evaluateUser;
	}

	public void setEvaluateUser(String evaluateUser) {
		this.evaluateUser = evaluateUser;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}
	
	
	public String getIsManual() {
		return isManual;
	}

	public void setIsManual(String isManual) {
		this.isManual = isManual;
	}

	public String getBlackId() {
		return blackId;
	}

	public void setBlackId(String blackId) {
		this.blackId = blackId;
	}

	public String getOperateUserSerName() {
		return operateUserSerName;
	}

	public void setOperateUserSerName(String operateUserSerName) {
		this.operateUserSerName = operateUserSerName;
	}

	public String getBlackOperateUser() {
		return blackOperateUser;
	}

	public void setBlackOperateUser(String blackOperateUser) {
		this.blackOperateUser = blackOperateUser;
	}

	public Date getBlackOperateDate() {
		return blackOperateDate;
	}

	public void setBlackOperateDate(Date blackOperateDate) {
		this.blackOperateDate = blackOperateDate;
	}

	public String getBlackOperateReason() {
		return blackOperateReason;
	}

	public void setBlackOperateReason(String blackOperateReason) {
		this.blackOperateReason = blackOperateReason;
	}

	public Long getActivityVolunteerId() {
		return activityVolunteerId;
	}

	public void setActivityVolunteerId(Long activityVolunteerId) {
		this.activityVolunteerId = activityVolunteerId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	
	public String getActivityServiceTeam() {
		return activityServiceTeam;
	}

	public void setActivityServiceTeam(String activityServiceTeam) {
		this.activityServiceTeam = activityServiceTeam;
	}

	public Date getActivitySt() {
		return activitySt;
	}

	public void setActivitySt(Date activitySt) {
		this.activitySt = activitySt;
	}

	public Date getActivityEt() {
		return activityEt;
	}

	public void setActivityEt(Date activityEt) {
		this.activityEt = activityEt;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getApplyActivityStatus() {
		return applyActivityStatus;
	}

	public void setApplyActivityStatus(Integer applyActivityStatus) {
		this.applyActivityStatus = applyActivityStatus;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getActivityServiceStar() {
		return activityServiceStar;
	}

	public void setActivityServiceStar(Integer activityServiceStar) {
		this.activityServiceStar = activityServiceStar;
	}

	public Double getActivityServiceHours() {
		return activityServiceHours;
	}

	public void setActivityServiceHours(Double activityServiceHours) {
		this.activityServiceHours = activityServiceHours;
	}

	@Override
	public String toString() {
		return "VolunteerView [id=" + id + ", userName=" + userName + ", realName=" + realName + ", password="
				+ password + ", sex=" + sex + ", idcard=" + idcard + ", birthday=" + birthday + ", qq=" + qq
				+ ", nation=" + nation + ", polstatus=" + polstatus + ", postcode=" + postcode + ", address=" + address
				+ ", naplace=" + naplace + ", education=" + education + ", serviceId=" + serviceId + ", serviceTeam="
				+ serviceTeam + ", jobTitle=" + jobTitle + ", duties=" + duties + ", grasch=" + grasch + ", profession="
				+ profession + ", work=" + work + ", jobAddress=" + jobAddress + ", mobile=" + mobile + ", email="
				+ email + ", specility=" + specility + ", serviceExperiment=" + serviceExperiment + ", serviceTime="
				+ serviceTime + ", remark=" + remark + ", regTime=" + regTime + ", imgUrl=" + imgUrl + ", serviceHour="
				+ serviceHour + ", status=" + status + ", certId=" + certId + ", certDate=" + certDate + ", opertUser="
				+ opertUser + ", opertTime=" + opertTime + ", checkYear=" + checkYear + ", checkState=" + checkState
				+ ", volunteerStarId=" + volunteerStarId + ", star=" + star + ", evaluateTime=" + evaluateTime
				+ ", evaluateUser=" + evaluateUser + ", evaluateContent=" + evaluateContent + ", blackId=" + blackId
				+ ", operateUserSerName=" + operateUserSerName + ", blackOperateUser=" + blackOperateUser
				+ ", blackOperateDate=" + blackOperateDate + ", blackOperateReason=" + blackOperateReason
				+ ", activityId=" + activityId + ", activityStatus=" + activityStatus + ", activityServiceTeam="
				+ activityServiceTeam + ", activitySt=" + activitySt + ", activityEt=" + activityEt
				+ ", applyTime=" + applyTime + ", applyActivityStatus=" + applyActivityStatus + ", activityName="
				+ activityName + ", checkStatus=" + checkStatus + ", activityServiceStar=" + activityServiceStar
				+ ", activityServiceHours=" + activityServiceHours + "]";
	}

}