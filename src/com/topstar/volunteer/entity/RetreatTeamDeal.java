package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 志愿者退队处理信息实体
 * @author TRS
 *
 */
@Table(name= "RETREAT_RESULT")
@Entity
public class RetreatTeamDeal implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4574065179194897039L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="select SEQ_RETREAT_RESULT.nextval from dual")
	private Long id;
	
	@Column(name="APPLY_ID")
	private Long applyId;

	/**
	 * 处理人
	 */
	@Column(name="DEAL_USER")
	private String dealUser;
	
	/**
	 * 处理时间
	 */
	@Column(name="DEAL_TIME")
	private Date dealTime;
	
	/**
	 * 处理结果
	 */
	@Column(name="DEAL_RESULT")
	private String dealResult;
	
	/**
	 * 处理意见
	 */
	@Column(name="DEAL_OPINION")
	private String dealOpinion;

	 /**
     * 不是表中的字段
     * 志愿者的Id
     */
    @Transient
    private Long volId;
	
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

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getDealOpinion() {
		return dealOpinion;
	}

	public void setDealOpinion(String dealOpinion) {
		this.dealOpinion = dealOpinion;
	}
	
	public Long getVolId() {
		return volId;
	}

	public void setVolId(Long volId) {
		this.volId = volId;
	}

	@Override
	public String toString() {
		return "RetreatTeamDeal [id=" + id + ", applyId=" + applyId + ", dealUser=" + dealUser + ", dealTime="
				+ dealTime + ", dealResult=" + dealResult + ", dealOpinion=" + dealOpinion + ", volId=" + volId + "]";
	}
}
