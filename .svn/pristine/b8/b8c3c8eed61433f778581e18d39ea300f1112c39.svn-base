package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_CHANNEL")
public class RoleChannel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5405644744240053783L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_ROLE_CHANNEL.nextval from dual")
    private Long id;

    @Column(name = "ROLE_ID")
    private Long roleId;

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
     * @return ROLE_ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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