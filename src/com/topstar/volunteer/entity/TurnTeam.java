package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.StringUtils;

import com.topstar.volunteer.validator.group.Groups;

/**
 * 志愿者转队处理信息实体
 * @author TRS
 *
 */
@Table(name="turn_serteam")
@Entity
public class TurnTeam implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6030197494360131619L;

	@Null(message="{turnTeam.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{turnTeam.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{turnTeam.id.minValue.error}",groups=Groups.Update.class)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_TURN_TEAM.nextval from dual")
	private Long id;

	@NotNull(message="{turnTeam.volunteerId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "VOLUNTEER_ID")
	private Long volunteerId;
	
    /**
     * 申请转队时间
     */
	@Column(name = "APPLY_TIME")
	@NotNull(message="{turnTeam.applyTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private Date applyTime;
    
    /**
     * 申请转入的服务队标识
     */
	@Column(name = "TARGET_ID")
	@NotNull(message="{turnTeam.targetId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private Long targetId;
    
    /**
     * 申请转入的服务队
     */
	@Column(name = "TARGET_TEAM")
	@NotNull(message="{turnTeam.targetTeam.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String targetTeam;
    
    /**
     * 转入服务队属性
     */
	@Column(name = "TURN_PROPERTY")
	@NotNull(message="{turnTeam.property.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String property;
    
	/**
	 * 转队原因
	 */
	@Column(name = "TURN_REASON")
	@NotNull(message="{turnTeam.applyReason.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private String applyReason;
	
	
	/**
	 * 转出处理人用户id
	 */
	@Column(name = "SUSER_ID")
	@NotNull(message="{turnTeam.suserId.notNull.error}",groups=Groups.Update.class)
	private Long suserId;
	
	/**
	 * 转出处理人用户名
	 */
	@Column(name = "SDEAL_USER")
	@NotNull(message="{turnTeam.sdealUser.notNull.error}",groups=Groups.Update.class)
	private String sdealUser;
	
	/**
	 * 转出处理服务队
	 */
	@Column(name = "SDEAL_TEAM")
	private String sdealTeam;
	
	/**
	 * 原所属服务队id
	 */
	@Column(name = "SOURCE_ID")
	@NotNull(message="{turnTeam.sourceTeamId.notNull.error}",groups=Groups.Add.class)
	private Long sourceTeamId;
	
	/**
	 * 原所属服务队名称
	 */
	@Column(name = "SOURCE_TEAM")
	@NotNull(message="{turnTeam.sourceTeam.notNull.error}",groups=Groups.Add.class)
	private String sourceTeam;
	
	
	/**
	 * 转出处理时间
	 */
	@Column(name = "SOURCE_TIME")
	@NotNull(message="{turnTeam.sdealTime.notNull.error}",groups=Groups.Update.class)
	private Date sdealTime;
	
	/**
     * 转出处理结果
     */
	@Column(name = "SOURCE_RESULT")
	@NotNull(message="{turnTeam.sourceResult.notNull.error}",groups=Groups.Update.class)
    private Integer sourceResult;

	/**
     * 转出处理意见
     */
	@Column(name = "SOURCE_OPINION")
	@NotNull(message="{turnTeam.sourceOpinion.notNull.error}",groups=Groups.Update.class)
    private String sourceOpinion;
    
    /**
	 * 转入处理用户id
	 */
	@Column(name = "TUSER_ID")
    private Long tuserId;
    
    /**
	 * 转入处理用户名
	 */
	@Column(name = "TDEAL_USER")
	private String tdealUser;
	
	/**
	 * 转入处理服务队名称
	 */
	@Column(name = "TDEAL_TEAM")
	private String tdealTeam;
	
	/**
	 * 转入处理时间
	 */
	@Column(name = "TARGET_TIME")
	private Date tdealTime;
	
	/**
     * 转入处理结果
     */
	@Column(name = "TARGET_RESULT")
    private Integer targetResult;

	/**
     * 转入处理意见
     */
	@Column(name = "TARGET_OPINION")
    private String targetOpinion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(Long volunteerId) {
		this.volunteerId = volunteerId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getTargetTeam() {
		return targetTeam;
	}

	public void setTargetTeam(String targetTeam) {
		this.targetTeam = targetTeam;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Long getSuserId() {
		return suserId;
	}

	public void setSuserId(Long suserId) {
		this.suserId = suserId;
	}

	public String getSdealUser() {
		return sdealUser;
	}

	public void setSdealUser(String sdealUser) {
		this.sdealUser = sdealUser;
	}

	public String getSdealTeam() {
		return sdealTeam;
	}

	public void setSdealTeam(String sdealTeam) {
		this.sdealTeam = sdealTeam;
	}

	public Long getSourceTeamId() {
		return sourceTeamId;
	}

	public void setSourceTeamId(Long sourceTeamId) {
		this.sourceTeamId = sourceTeamId;
	}

	public String getSourceTeam() {
		return sourceTeam;
	}

	public void setSourceTeam(String sourceTeam) {
		this.sourceTeam = sourceTeam;
	}

	public Date getSdealTime() {
		return sdealTime;
	}

	public void setSdealTime(Date sdealTime) {
		this.sdealTime = sdealTime;
	}

	public Integer getSourceResult() {
		return sourceResult;
	}

	public void setSourceResult(Integer sourceResult) {
		this.sourceResult = sourceResult;
	}

	public String getSourceOpinion() {
		return sourceOpinion;
	}

	public void setSourceOpinion(String sourceOpinion) {
		this.sourceOpinion = sourceOpinion;
	}

	public Long getTuserId() {
		return tuserId;
	}

	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
	}

	public String getTdealUser() {
		return tdealUser;
	}

	public void setTdealUser(String tdealUser) {
		this.tdealUser = tdealUser;
	}

	public String getTdealTeam() {
		return tdealTeam;
	}

	public void setTdealTeam(String tdealTeam) {
		this.tdealTeam = tdealTeam;
	}

	public Date getTdealTime() {
		return tdealTime;
	}

	public void setTdealTime(Date tdealTime) {
		this.tdealTime = tdealTime;
	}

	public Integer getTargetResult() {
		return targetResult;
	}

	public void setTargetResult(Integer targetResult) {
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
		return "TurnTeam [id=" + id + ", volunteerId=" + volunteerId + ", applyTime=" + applyTime + ", targetId="
				+ targetId + ", targetTeam=" + targetTeam + ", property=" + property + ", applyReason=" + applyReason
				+ ", suserId=" + suserId + ", sdealUser=" + sdealUser + ", sdealTeam=" + sdealTeam + ", sourceTeamId="
				+ sourceTeamId + ", sourceTeam=" + sourceTeam + ", sdealTime=" + sdealTime + ", sourceResult="
				+ sourceResult + ", sourceOpinion=" + sourceOpinion + ", tuserId=" + tuserId + ", tdealUser="
				+ tdealUser + ", tdealTeam=" + tdealTeam + ", tdealTime=" + tdealTime + ", targetResult=" + targetResult
				+ ", targetOpinion=" + targetOpinion + "]";
	}

}
