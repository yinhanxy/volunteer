package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.topstar.volunteer.validator.group.Groups;

/**
 * 志愿者证书的年检时间配置实体
 * @author TRS
 *
 */
@Table(name="time_Limit")
public class CheckTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2234408768781488977L;

	@Null(message="{checkTime.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{checkTime.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{checkTime.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_CHECKTIME.nextval from dual")
	private Long id;
	
	/**
	 * 年检年份
	 */
	@NotNull(message="{checkTime.year.notNull.error}",groups={Groups.Add.class})
	@Pattern(regexp="^[2-9]{1}[0-9]{3,}$",message="{checkTime.year.length.error}",groups=Groups.Add.class)
	private String year;
	
	/**
	 * 开始时间
	 */
	@Column(name="START_TIME")
	@NotNull(message="{checkTime.startTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@Column(name="END_TIME")
	@NotNull(message="{checkTime.endTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;
	
	/**
	 * 创建用户
	 */
	@Column(name="CREATE_USER")
	private String crtUser;
	
	/**
	 * 添加时间
	 */
	@Column(name="CREATE_TIME")
	private Date crtTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	@Override
	public String toString() {
		return "CheckTime [id=" + id + ", year=" + year + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", crtUser=" + crtUser + ", crtTime=" + crtTime + "]";
	}
	
}
