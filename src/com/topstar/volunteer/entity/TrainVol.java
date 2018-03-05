package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
/**
 * 志愿者培训记录表
 */
@Entity
@Table(name = "TRAIN_VOL")
public class TrainVol implements Serializable{

	private static final long serialVersionUID = 6085784861175055333L;
	/**
	 * 编号
	 */
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_TRAIN_VOL.nextval from dual")
    private Long id;
	
	/**
	 * 培训记录编号
	 */
    @Column(name = "TRAIN_ID")
    private Long trainId;

    /**
     * 志愿者编号
     */
    @Column(name = "VOL_ID")
    private Long volId;
    
    /**
     * 创建用户
     */
    @Column(name = "CR_USER")
    private String crUser;

    /**
     * 创建时间
     */
    @Column(name = "CR_TIME")
    private Date crTime;
    
    @Transient
    private String userName;
    
    @Transient
    private String realName;
    
    @Transient
    private String sex;
    
    @Transient
    private String idcard;
    
    @Transient
    private String mobile;
    
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
     * @return TRAIN_ID
     */
    public Long getTrainId() {
        return trainId;
    }

    /**
     * @param trainId
     */
    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    /**
     * @return VOL_ID
     */
    public Long getVolId() {
        return volId;
    }

    /**
     * @param volId
     */
    public void setVolId(Long volId) {
        this.volId = volId;
    }

    /**
     * @return CR_USER
     */
    public String getCrUser() {
        return crUser;
    }

    /**
     * @param crUser
     */
    public void setCrUser(String crUser) {
        this.crUser = crUser;
    }

    /**
     * @return CR_TIME
     */
    public Date getCrTime() {
        return crTime;
    }

    /**
     * @param crTime
     */
    public void setCrTime(Date crTime) {
        this.crTime = crTime;
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

	@Override
	public String toString() {
		return "TrainVol [id=" + id + ", trainId=" + trainId + ", volId=" + volId + ", crUser=" + crUser + ", crTime="
				+ crTime + ", userName=" + userName + ", realName=" + realName + ", sex=" + sex + ", idcard=" + idcard
				+ ", mobile=" + mobile + "]";
	}

}