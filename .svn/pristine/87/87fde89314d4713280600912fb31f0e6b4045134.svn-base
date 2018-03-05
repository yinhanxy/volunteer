package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

import com.topstar.volunteer.common.AlternativeUtil;
import com.topstar.volunteer.validator.group.Groups;

/**
 * 志愿者证书年度审核情况信息实体
 * @author TRS
 *
 */
@Table(name="cert_check")
public class CertCheck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 812404966715569177L;

	@Null(message="{certCheck.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{certCheck.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{certCheck.id.minValue.error}",groups=Groups.Update.class)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_CERT_CHECK.nextval from dual")
	private Long id;
	
	@NotNull(message="{certCheck.certId.notNull.error}",groups={Groups.Add.class})
	@Min(value=1,message="{certCheck.certId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="CERT_ID")
	private Long certId;
	
	@NotNull(message="{certCheck.opertUser.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="OPERT_USER")
	private String opertUser;
	
	@NotNull(message="{certCheck.opertTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="OPER_TIME")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date opertTime;
	
	@NotNull(message="{certCheck.checkYear.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="CHECK_YEAR")
	private String checkYear;
	
	@NotNull(message="{certCheck.checkState.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="CHECK_STATE")
	private String checkState;
	
	/**
	 * 判断是否能修改此年检信息
	 */
	@Transient
	private String editEnable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCertId() {
		return certId;
	}

	public void setCertId(Long certId) {
		this.certId = certId;
	}

	public String getOpertUser() {
		return opertUser;
	}

	public void setOpertUser(String opertUser) {
		this.opertUser = opertUser;
	}

	public Date getOpertTime() {
		return opertTime;
	}

	public void setOpertTime(Date opertTime) {
		this.opertTime = opertTime;
	}

	public String getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}

	public String getCheckState() {
		return checkState;
	}

	public String getCertCheckState(){
		return AlternativeUtil.getVolunteerCertificationStatus(checkState);
	}
	
	public void setCheckState(String checkState) {
		
		this.checkState = checkState;
	}

	public String getEditEnable() {
		return editEnable;
	}

	public void setEditEnable(String editEnable) {
		this.editEnable = editEnable;
	}

	@Override
	public String toString() {
		return "CertCheck [id=" + id + ", certId=" + certId + ", opertUser=" + opertUser + ", opertTime=" + opertTime
				+ ", checkYear=" + checkYear + ", checkState=" + checkState + "]";
	}
	
}
