package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.topstar.volunteer.validator.group.Groups;

/**
 * 志愿者黑名单信息实体
 * @author TRS
 *
 */
public class VolunteerBlack implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1606323916683041121L;

	@Null(message="{volunteerBlack.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{volunteerBlack.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{volunteerBlack.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="select SEQ_VOLUNTEER_BLACK.nextval from dual")
	private Long id;
	
	@NotNull(message="{volunteerBlack.volunteetId.notNull.error}",groups={Groups.Add.class})
	@Min(value=1,message="{volunteerBlack.volunteetId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="VOLUNTEER_ID")
	private Long volunteerId;
	
	@NotNull(message="{volunteerBlack.opertTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="OPERT_TIME")
	private Date opertTime;
	
	@NotNull(message="{volunteerBlack.userId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{volunteerBlack.userId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="USER_ID")
	private Long userId;
	
	@NotNull(message="{volunteerBlack.opertUser.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="OPERT_USER")
	private String opertUser;
	
	private String reason;

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

	public Date getOpertTime() {
		return opertTime;
	}

	public void setOpertTime(Date opertTime) {
		this.opertTime = opertTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpertUser() {
		return opertUser;
	}

	public void setOpertUser(String opertUser) {
		this.opertUser = opertUser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "VolunteerBlack [id=" + id + ", volunteerId=" + volunteerId + ", opertTime=" + opertTime + ", userId="
				+ userId + ", opertUser=" + opertUser + ", reason=" + reason + "]";
	}

}
