package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.topstar.volunteer.validator.group.Groups;

/**
 * 志愿者报名活动的相关信息实体
 * @author TRS
 *
 */
public class ActivityVolunteer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1075962593096093635L;

	/**
	 * 唯一标识
	 */
	@Null(message="{activityVolunteer.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{activityVolunteer.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{activityVolunteer.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_ACTIVITY_VOLUNTEER.nextval from dual")
	private Long id;
	
	/**
	 * 活动的唯一标识
	 */
	@NotNull(message="{activityVolunteer.activityId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{activityVolunteer.activityId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Long activityId;
	
	/**
	 * 报名活动的志愿者Id
	 */
	@NotNull(message="{activityVolunteer.volunteerId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{activityVolunteer.volunteerId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Long volunteerId;
	
	/**
	 * 志愿者报名活动的结果(1:已接受 2:已拒绝  其他:未审核)
	 */
	private Integer applyResult;
	
	/**
	 *志愿者报名活动时间 
	 */
	@NotNull(message="{activityVolunteer.applyTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private Date applyTime;
	
	/**
	 * 签到状态 (1:签到 其他:未签到)
	 */
	private Integer checkStatus;
	
	/**
	 * 志愿者服务活动的评价星级
	 */
	private Integer serviceStar;
	
	/**
	 * 志愿者参加活动的服务时长
	 */
	private Double serHours;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(Long volunteerId) {
		this.volunteerId = volunteerId;
	}

	public Integer getApplyResult() {
		return applyResult;
	}

	public void setApplyResult(Integer applyResult) {
		this.applyResult = applyResult;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getServiceStar() {
		return serviceStar;
	}

	public void setServiceStar(Integer serviceStar) {
		this.serviceStar = serviceStar;
	}

	public Double getSerHours() {
		return serHours;
	}

	public void setSerHours(Double serHours) {
		this.serHours = serHours;
	}

	@Override
	public String toString() {
		return "ActivityVoulunteer [id=" + id + ", activityId=" + activityId + ", volunteerId=" + volunteerId
				+ ", applyResult=" + applyResult + ", applyTime=" + applyTime + ", checkStatus=" + checkStatus
				+ ", serviceStar=" + serviceStar + ", serHours=" + serHours + "]";
	}
	
}
