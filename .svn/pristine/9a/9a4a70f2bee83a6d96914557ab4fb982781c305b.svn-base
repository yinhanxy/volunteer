package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_CHANNEL")
public class UserChannel implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2523345102206563153L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_USER_CHANNEL.nextval from dual")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CHANNEL_ID")
    private Long channelId;

    @Column(name = "CR_USER")
    private String crUser;

    @Column(name = "CR_TIME")
    private Timestamp crTime;

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
     * @return CHANNEL_ID
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
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
    public Timestamp getCrTime() {
        return crTime;
    }

    /**
     * @param crTime
     */
    public void setCrTime(Timestamp crTime) {
        this.crTime = crTime;
    }
}