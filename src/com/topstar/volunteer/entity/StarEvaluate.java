package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.topstar.volunteer.validator.group.Groups;

/**
 * 志愿者服务星级评定信息实体
 * @author TRS
 *
 */
@Table(name="STAR_EVALUATE")
public class StarEvaluate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4504316027865681990L;

	@Null(message="{starEvaluate.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{starEvaluate.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{starEvaluate.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="select SEQ_STAR_EVALUATE.nextval from dual")
	private Long id;
	
	@NotNull(message="{starEvaluate.volunteetId.notNull.error}",groups={Groups.Add.class})
	@Min(value=1,message="{starEvaluate.volunteetId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="VOLUNTEER_ID")
	private Long volunteerId;
	
	@NotNull(message="{starEvaluate.star.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=0,message="{starEvaluate.star.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Max(value=5,message="{starEvaluate.star.maxValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private String star;
	
	@NotNull(message="{starEvaluate.evaluateTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="EVALUATE_TIME")
	private Date evaluateTime;
	
	@NotNull(message="{starEvaluate.evaluateUser.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="EVALUATE_USER")
	private String evaluateUser;
	
	@Column(name="EVALUATE_CONTENT")
	private String evaluateContent;
	
	/**
	 * 0:否    1:是 
	 */
	@Column(name="IS_MANUAL")
	private String isManual;

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

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getEvaluateUser() {
		return evaluateUser;
	}

	public void setEvaluateUser(String evaluateUser) {
		this.evaluateUser = evaluateUser;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public String getIsManual() {
		return isManual;
	}

	public void setIsManual(String isManual) {
		this.isManual = isManual;
	}

	@Override
	public String toString() {
		return "StarEvaluate [id=" + id + ", volunteerId=" + volunteerId + ", star=" + star + ", evaluateTime="
				+ evaluateTime + ", evaluateUser=" + evaluateUser + ", evaluateContent=" + evaluateContent + "]";
	}

}
