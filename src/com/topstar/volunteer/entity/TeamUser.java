package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
/**
 * 服务队用户表
 */
@Entity
@Table(name = "TEAM_USER")
public class TeamUser implements Serializable{

	private static final long serialVersionUID = 8679613535944564268L;

	/**
	 * 编号
	 */
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_TEAM_USER.nextval from dual")
    private Long id;
	
	/**
	 * 用户编号
	 */
    @Column(name = "USER_ID")
    private Long userId;
    
    /**
     * 服务队编号
     */
    @Column(name = "TEAM_ID")
    private Long teamId;
    
    @Column(name = "CR_USER")
    private String crUser;

    @Column(name = "CR_TIME")
    private Date crTime;

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
     * @return TEAM_ID
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

	@Override
	public String toString() {
		return "TeamUser [id=" + id + ", userId=" + userId + ", teamId=" + teamId + ", crUser=" + crUser + ", crTime="
				+ crTime + "]";
	}
    
    
}