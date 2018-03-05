package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.topstar.volunteer.validator.constraints.Phone;
import com.topstar.volunteer.validator.group.Groups;

@Entity
@Table(name="org")
public class Org implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2734476948365817289L;

	/**
	 * 唯一标识
	 */
	@Null(message="{org.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{org.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{org.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_ORG.nextval from dual")
	private Long id;
	
	/**
	 * 机构名称
	 */
	@NotBlank(message="{org.name.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Pattern(regexp="^[\u4e00-\u9fa5]{2,50}$",message="{org.name.length.error}",groups={Groups.Add.class,Groups.Update.class})
	private String name;
	
	/**
	 * 备注
	 */
	private String summary;
	
	/**
	 * 机构类型
	 */
	@NotNull(message="{org.type.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Range(min=1,max=2,message="{org.type.range.error}",groups={Groups.Add.class,Groups.Update.class})
	private Integer type;

	/**
	 * 上级机构的唯一标识
	 */
	@NotNull(message="{org.parentId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=0,message="{org.parentId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="PARENT_ID")
	private Long parentId;
	
	/**
	 * 机构编码
	 */
	@Pattern(regexp="^[0-9]{3,20}|[0-9]{0}$",message="{org.code.pattern.error}",groups={Groups.Add.class,Groups.Update.class})
	private String code;
	
	/**
	 * 机构地址
	 */
	private String address;
	
	/**
	 * 机构所在区域的唯一标识
	 */
	@NotNull(message="{org.areaId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=0,message="{org.areaId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="AREA_ID")
	private Long areaId;
	
	/**
	 * 机构所属单位系统
	 */
	//@NotNull(message="{org.systemCode.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{org.systemCode.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="SYSTEM_CODE")
	private Long systemCode;
	
	/**
	 * 机构联系人
	 */
	@NotBlank(message="{org.contact.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	//@Pattern(regexp="^[\u4e00-\u9fa5]{2,50}$",message="{org.contact.length.error}",groups={Groups.Add.class,Groups.Update.class})
	private String contact;

	/**
	 * 机构联系电话
	 */
	@NotBlank(message="{org.mobile.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	/*@Phone(message="{org.mobile.length.error}",groups={Groups.Add.class,Groups.Update.class})*/
    private String mobile;
	
	/**
	 *机构的邮箱地址
	 */
	//@NotBlank(message="{org.email.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Pattern(regexp="^$|^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,3})+$",message="{org.email.pattren.error}",groups={Groups.Add.class,Groups.Update.class})
    private String email;
	
	/**
	 * 机构级别
	 */
	@NotNull(message="{org.grade.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Range(min=1,max=5,message="{org.grade.range.error}",groups={Groups.Add.class,Groups.Update.class})
	private Integer grade;
	
	@Column(name="CRT_TIME")
	@NotNull(message="{public.crTime.notNull.error}",groups={Groups.Add.class})
	private Timestamp crtTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(Long systemCode) {
		this.systemCode = systemCode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Timestamp getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Timestamp crtTime) {
		this.crtTime = crtTime;
	}
	
	public String getTypeName() {
		if(1==this.type){
			return "管理机构";
		}
		return "业务机构";
	}
	
	@Override
	public String toString() {
		return "Org [id=" + id + ", name=" + name + ", summary=" + summary + ", type=" + type + ", parentId=" + parentId
				+ ", code=" + code + ", address=" + address + ", areaId=" + areaId + ", systemCode=" + systemCode
				+ ", contact=" + contact + ", mobile=" + mobile + ", email=" + email + ", grade=" + grade + ", crtTime="
				+ crtTime + "]";
	}

}
