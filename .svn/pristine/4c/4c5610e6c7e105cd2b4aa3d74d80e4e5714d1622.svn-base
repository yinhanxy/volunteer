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
@Table(name = "ORG_USER")
public class OrgUser implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 67207909659021621L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_ORG_USER.nextval from dual")
    private Long id;

    @Column(name = "ORG_ID")
    private Long orgId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CR_USER")
    private String crUser;

    @Column(name = "CR_TIME")
    private Timestamp crTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCrUser() {
		return crUser;
	}

	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	public Timestamp getCrTime() {
		return crTime;
	}

	public void setCrTime(Timestamp crTime) {
		this.crTime = crTime;
	}

	@Override
	public String toString() {
		return "OrgUser [id=" + id + ", orgId=" + orgId + ", userId=" + userId + ", crUser=" + crUser + ", crTime="
				+ crTime + "]";
	}

}