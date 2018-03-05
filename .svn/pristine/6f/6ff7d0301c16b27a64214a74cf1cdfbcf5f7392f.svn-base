package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.topstar.volunteer.common.AlternativeUtil;
import com.topstar.volunteer.validator.constraints.Phone;
import com.topstar.volunteer.validator.group.Groups;

public class Volunteer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7755252994416428247L;

	@Null(message="{volunteer.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{volunteer.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{volunteer.id.minValue.error}",groups=Groups.Update.class)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_VOLUNTEER.nextval from dual")
    private Long id;

	@NotBlank(message="{volunteer.userName.notNull.error}",groups=Groups.Add.class)
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "REAL_NAME")
    private String realName;

    @NotBlank(message="{volunteer.password.notNull.error}",groups=Groups.Add.class)
	@Pattern(regexp="^[0-9a-zA-Z_]{5,12}$",message="{volunteer.password.length.error}",groups=Groups.Add.class)
    @Column(name = "PASSWORD")
    private String password;

    @NotBlank(message="{volunteer.sex.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "SEX")
    private String sex;

    @Column(name = "IDCARD")
    private String idcard;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "BIRTHDAY")
    private Date birthday;
    
    @Column(name = "QQ")
    private String qq;

    @NotBlank(message="{volunteer.nation.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "NATION")
    private String nation;
    
    @NotBlank(message="{volunteer.polstatus.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "POLSTATUS")
    private String polstatus;

    @Column(name = "POSTCODE")
    private String postcode;

    @NotBlank(message="{volunteer.address.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "ADDRESS")
    private String address;

    @NotBlank(message="{volunteer.naplace.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "NAPLACE")
    private String naplace;

    @NotBlank(message="{volunteer.education.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "EDUCATION")
    private String education;

    @Column(name = "SERVICE_TEAM")
    private Long serviceTeam;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "DUTIES")
    private String duties;

    @Column(name = "GRASCH")
    private String grasch;

    @Column(name = "PROFESSION")
    private String profession;

    @Column(name = "WORK")
    private String work;

    @Column(name = "JOB_ADDRESS")
    private String jobAddress;

    @NotBlank(message="{volunteer.mobile.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Phone(message="{volunteer.mobile.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "MOBILE")
    private String mobile;

   // @NotBlank(message="{volunteer.email.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Pattern(regexp="^$|^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,3})+$",message="{volunteer.email.pattren.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "EMAIL")
    private String email;

    @NotBlank(message="{volunteer.specility.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "SPECILITY")
    private String specility;

    @Column(name = "SERVICE_EXPERIMENT")
    private String serviceExperiment;

    @Column(name = "SERVICE_TIME")
    private String serviceTime;

    @Column(name = "REMARK")
    private String remark;
    
    @Column(name = "SERVICE_CLASS")
    private String serviceClass;

    @Column(name = "REG_TIME")
    private Date regTime;

    @Column(name = "STATUS")
    private Integer status;

    /**
     * 1(未审核) 2(正常) 3(再审) 4(已否) 5(退队) 6(黑名单)
     * @author TRS
     *
     */
    public enum StatusType{
    	/**
    	 * 未审核
    	 */
    	wait_review(1),
    	
    	/**
    	 * 通过(正常)
    	 */
    	pass(2),
    	
    	/**
    	 *再审 
    	 */
    	retrial(3),
    	
    	/**
    	 * 已否 
    	 */
    	deny(4),
    	
    	/**
    	 * 退队 
    	 */
    	retreat(5),
    	
    	/**
    	 * 黑名单 
    	 */
    	black(6);
    	
    	private Integer value=1;

		StatusType(Integer value) {
			this.value = value;
		}

		public int getValue() {
			return value.intValue();
		}

		public void setValue(Integer value) {
			this.value = value;
		}
    	
		public String toString(){
    		return value.toString();
    	}
    }
    /**
     * 头像地址
     */
    @Column(name = "IMG_URL")
    private String imgUrl;
    
    //是否经过监护人或单位领导人同意 ,1:同意,0:不同意,2:大于18岁
    @Column(name = "CHILD")
    private Integer child;
    
    /**
     * 志愿者总服务时长
     */
    @Column(name = "SERVICE_HOUR")
    private Double serviceHour;
    
    /**
	 * 不在数据库中的字段
	 */
	@Transient
    private List<Long> trainIdList;
	
	/**
     * 不是表中的字段
     * 充当查询条件的志愿者所属组织机构Id
     */
    @Transient
    private Long orgId;
    
    /**
     * 不是表中的字段
     * 充当查询条件的志愿者所属管理组织机构的Id
     */
    @Transient
    private Long parentOrgId;
    	
	@Transient
	private String teamName;
    
	@Transient
	private String dealReasult;
	
	 /**
     * 不是表中的字段
     * 管理机构的Id
     */
    @Transient
    private Long curOrgId;
    
    /**
     * 不是表中的字段
     * 充当查询条件的志愿者服务星级
     */
    @Transient
    private String star;
    
    /**
     * 不是表中的字段
     * 转队列表显示条件目标服务队id
     */
    @Transient
    private Long targetId;
    
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

    /**
     * @return SERVICE_TEAM
     */
    public Long getServiceTeam() {
        return serviceTeam;
    }

    /**
     * @param serviceTeam
     */
    public void setServiceTeam(Long serviceTeam) {
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

    /**
     * @return STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Double getServiceHour() {
		return serviceHour;
	}

	public void setServiceHour(Double serviceHour) {
		this.serviceHour = serviceHour;
	}

	public List<Long> getTrainIdList() {
		return trainIdList;
	}

	public void setTrainIdList(List<Long> trainIdList) {
		this.trainIdList = trainIdList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public Long getCurOrgId() {
		return curOrgId;
	}

	public void setCurOrgId(Long curOrgId) {
		this.curOrgId = curOrgId;
	}

	public String getDealReasult() {
		return dealReasult;
	}

	public void setDealReasult(String dealReasult) {
		this.dealReasult = dealReasult;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	
	
	/**
	 * @return the targetId
	 */
	public Long getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "Volunteer [id=" + id + ", userName=" + userName + ", realName=" + realName + ", password=" + password
				+ ", sex=" + sex + ", idcard=" + idcard + ", birthday=" + birthday + ", qq=" + qq + ", nation=" + nation
				+ ", polstatus=" + polstatus + ", postcode=" + postcode + ", address=" + address + ", naplace="
				+ naplace + ", education=" + education + ", serviceTeam=" + serviceTeam + ", jobTitle=" + jobTitle
				+ ", duties=" + duties + ", grasch=" + grasch + ", profession=" + profession + ", work=" + work
				+ ", jobAddress=" + jobAddress + ", mobile=" + mobile + ", email=" + email + ", specility=" + specility
				+ ", serviceExperiment=" + serviceExperiment + ", serviceTime=" + serviceTime + ", remark=" + remark
				+ ", serviceClass=" + serviceClass + ", regTime=" + regTime + ", status=" + status + ", imgUrl="
				+ imgUrl + ", child=" + child + ", serviceHour=" + serviceHour + ", trainIdList=" + trainIdList
				+ ", orgId=" + orgId + ", parentOrgId=" + parentOrgId + ", teamName=" + teamName + ", dealReasult="
				+ dealReasult + ", curOrgId=" + curOrgId + ", star=" + star + ", targetId=" + targetId + "]";
	}

}