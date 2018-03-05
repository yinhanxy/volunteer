package com.topstar.volunteer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 短信验证码
 */
@Entity
@Table(name = "SMS")
public class SMS {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SMS.nextval from dual")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    /**
     * 1.注册时;2.忘记密码;
     */
    @Column(name = "TYPE")
    private Long type;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SEND_TIME")
    private Date sendTime;

    @Column(name = "EXPIRE_TIME")
    private Date expireTime;

    @Column(name = "EXPIRED")
    private Integer expired;

    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 不是表中的字段
     * 接受者名称
     */
    @Transient
    private String volName;
    
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
     * @return USER_ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return TYPE
     */
    public Long getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * @return CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return SEND_TIME
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return EXPIRE_TIME
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
    
    public String getExpireDesc(){
    	Long exTime = expireTime.getTime();
    	Date curTime = new Date();
		Long thisTime = curTime.getTime();
		if (exTime >= thisTime) {
			return "有效";
		}
    	return "无效";
    }
    
    /**
     * @return EXPIRED
     */
    public Integer getExpired() {
        return expired;
    }

    /**
     * @param expired
     */
    public void setExpired(Integer expired) {
        this.expired = expired;
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

	public String getVolName() {
		return volName;
	}

	public void setVolName(String volName) {
		this.volName = volName;
	}

	@Override
	public String toString() {
		return "SMS [id=" + id + ", userId=" + userId + ", type=" + type + ", content=" + content + ", sendTime="
				+ sendTime + ", expireTime=" + expireTime + ", expired=" + expired + ", mobile=" + mobile + ", volName="
				+ volName + "]";
	}
    
}