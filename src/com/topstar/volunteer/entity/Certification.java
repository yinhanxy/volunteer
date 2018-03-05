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
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.topstar.volunteer.validator.group.Groups;

public class Certification implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9179106754239744548L;
	
	@Null(message="{certification.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{certification.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{certification.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_CERTIFICATION.nextval from dual")
	private Long id;
	
	@NotNull(message="{certification.volunteerId.notNull.error}",groups={Groups.Add.class})
	@Min(value=1,message="{certification.volunteerId.minValue.error}",groups={Groups.Add.class})
	@Column(name="VOLUNTEER_ID")
	private Long volunteerId;
	
	@NotNull(message="{certification.userName.notNull.error}",groups={Groups.Add.class})
	@Column(name="USER_NAME")
	private String userName;
	
	@NotNull(message="{certification.idcard.notNull.error}",groups={Groups.Add.class})
	@Column(name="idcard")
	private String idcard;
	
	@NotNull(message="{certification.serviceTeam.notNull.error}",groups={Groups.Add.class})
	@Column(name="SERVICE_TEAM")
	private String serviceTeam;
	
	@Column(name="CERT_DATE")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date certDate;
	
	
	/**
	 * 证书状态: 0 可用  ; 1 不可用
	 */
	@NotNull(message="{certification.isUse.notNull.error}",groups={Groups.Add.class})
	@Column(name="IS_USE")
	private String isUse;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getServiceTeam() {
		return serviceTeam;
	}
	public void setServiceTeam(String serviceTeam) {
		this.serviceTeam = serviceTeam;
	}
	public Date getCertDate() {
		return certDate;
	}
	public void setCertDate(Date certDate) {
		this.certDate = certDate;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	@Override
	public String toString() {
		return "Certification [id=" + id + ", volunteerId=" + volunteerId + ", userName=" + userName + ", idcard="
				+ idcard + ", serviceTeam=" + serviceTeam + ", certDate=" + certDate + ", isUse=" + isUse + "]";
	}

}
