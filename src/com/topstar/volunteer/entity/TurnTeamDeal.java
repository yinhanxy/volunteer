package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

/**
 * 志愿者转队处理信息实体
 * @author TRS
 *
 */
@Entity
public class TurnTeamDeal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139620594391499170L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="select SEQ_TURN_RESULT.nextval from dual")
	private Long id;
	
	@Column(name="APPLY_ID")
	private Long applyId;

	@Column(name="SOURCE_USER")
	private String sourceUser;
	
	@Column(name="SOURCE_TEAM")
	private String sourceTeam;
	
	@Column(name="SOURCE_TIME")
	private Date sourceTime;
	
	@Column(name="SOURCE_RESULT")
	private String sourceResult;
	
	@Column(name="SOURCE_OPINION")
	private String sourceOpinion;
	
	@Column(name="TARGET_USER")
	private String targetUser;
	
	@Column(name="TARGET_TEAM")
	private String targetTeam;
	
	@Column(name="TARGET_TIME")
	private Date targetTime;
	
	@Column(name="TARGET_RESULT")
	private String targetResult;
	
	@Column(name="TARGET_OPINION")
	private String targetOpinion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getSourceUser() {
		return sourceUser;
	}

	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}

	public String getSourceTeam() {
		return sourceTeam;
	}

	public void setSourceTeam(String sourceTeam) {
		this.sourceTeam = sourceTeam;
	}

	public Date getSourceTime() {
		return sourceTime;
	}

	public void setSourceTime(Date sourceTime) {
		this.sourceTime = sourceTime;
	}

	public String getSourceResult() {
		return sourceResult;
	}

	public void setSourceResult(String sourceResult) {
		this.sourceResult = sourceResult;
	}

	public String getSourceOpinion() {
		return sourceOpinion;
	}

	public void setSourceOpinion(String sourceOpinion) {
		this.sourceOpinion = sourceOpinion;
	}

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	public String getTargetTeam() {
		return targetTeam;
	}

	public void setTargetTeam(String targetTeam) {
		this.targetTeam = targetTeam;
	}

	public Date getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(Date targetTime) {
		this.targetTime = targetTime;
	}

	public String getTargetResult() {
		return targetResult;
	}

	public void setTargetResult(String targetResult) {
		this.targetResult = targetResult;
	}

	public String getTargetOpinion() {
		return targetOpinion;
	}

	public void setTargetOpinion(String targetOpinion) {
		this.targetOpinion = targetOpinion;
	}

	@Override
	public String toString() {
		return "TurnTeamDeal [id=" + id + ", applyId=" + applyId + ", sourceUser=" + sourceUser + ", sourceTeam="
				+ sourceTeam + ", sourceTime=" + sourceTime + ", sourceResult=" + sourceResult + ", sourceOpinion="
				+ sourceOpinion + ", targetUser=" + targetUser + ", targetTeam=" + targetTeam + ", targetTime="
				+ targetTime + ", targetResult=" + targetResult + ", targetOpinion=" + targetOpinion + "]";
	}
	
}
