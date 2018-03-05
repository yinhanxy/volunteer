package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.List;

import com.topstar.volunteer.entity.Org;


public class OrgView implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1592169816044975384L;

	private Long id;
	
	private String name;
	
	private String parentName;
	
	private String summary;
	
	private Integer type;
	
	private Long pId;
	
	private String code;
	
	private String address;
	
	private Long areaId;
	
	private Long systemCode;
	
	private String areaName;
	
	private String contact;
	
	private String mobile;
	
	private String email;
	
	private Integer grade;
	
	private boolean isParent;
	
	private boolean isOpen;
	
	private List<OrgView> orgList;

	public OrgView(Org org) {
		if(org!=null){
			this.id=org.getId();
			this.name=org.getName();
			this.summary=org.getSummary();
			this.type=org.getType();
			this.pId=org.getParentId();
			this.code=org.getCode();
			this.address=org.getAddress();
			this.areaId=org.getAreaId();
			this.systemCode=org.getSystemCode();
			this.contact=org.getContact();
			this.mobile=org.getMobile();
			this.email=org.getEmail();
			this.grade=org.getGrade();
		}
	}

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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public String getTypeName() {
		if(1==this.type){
			return "管理机构";
		}
		return "业务机构";
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public String getGradeName() {
		switch (grade) {
		case 1:
			return "一级";
		case 2:
			return "二级";
		case 3:
			return "三级";
		case 4:
			return "四级";
		case 5:
			return "五级";
		default:
			return "";
		}
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public List<OrgView> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<OrgView> orgList) {
		this.orgList = orgList;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrgView other = (OrgView) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrgView [id=" + id + ", name=" + name + ", parentName=" + parentName + ", summary=" + summary
				+ ", type=" + type + ", pId=" + pId + ", code=" + code + ", address=" + address + ", areaId=" + areaId
				+ ", systemCode=" + systemCode + ", areaName=" + areaName + ", contact=" + contact + ", mobile="
				+ mobile + ", email=" + email + ", grade=" + grade + ", isParent=" + isParent + ", isOpen=" + isOpen
				+ ", orgList=" + orgList + "]";
	}

}
