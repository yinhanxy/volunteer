package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.topstar.volunteer.validator.constraints.Phone;
import com.topstar.volunteer.validator.group.Groups;
/**
 * 服务队
 */
@Entity
@Table(name = "SERTEAM")
public class SerTeam implements Serializable{

	private static final long serialVersionUID = 7397340435940252578L;

	/**
	 * 服务队编号
	 */
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SERTEAM.nextval from dual")
    @Null(message="{serTeam.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{serTeam.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{serTeam.id.minValue.error}",groups=Groups.Update.class)
	private Long id;
	
	/**
	 * 服务队名称
	 */
    @Column(name = "NAME")
    @NotBlank(message="{serTeam.name.notNull.error}",groups={Groups.Add.class})
	@Pattern(regexp="^[\u4e00-\u9fa5]{2,50}$",message="{serTeam.name.length.error}", groups={Groups.Add.class,Groups.Update.class})
    private String name;
    
    /**
     * 组织机构编号
     */
    @Column(name = "ORG_ID")
    @NotNull(message="{serTeam.orgId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Min(value=1,message="{serTeam.orgId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
    private Long orgId;
    
    /**
     * 简介
     */
    @Column(name = "SUMMARY")
    @NotBlank(message="{serTeam.summary.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    //@Pattern(regexp="^[\u4e00-\u9fa5]{2,100}$",message="{serTeam.summary.length.error}", groups={Groups.Add.class,Groups.Update.class})
    private String summary;
    
    /**
     * 联系人
     */
    @Column(name = "CONTACT")
    @NotNull(message="{serTeam.contact.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String contact;
    
    /**
     * 联系电话
     */
    @Column(name = "CONTACT_TEL")
    @NotBlank(message="{serTeam.contactTel.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Phone(message="{serTeam.contactTel.length.error}",groups={Groups.Add.class,Groups.Update.class})
    private String contactTel;
    
    /**
     * 传真
     */
    @Column(name = "FAX")
   	@Pattern(regexp="^$|^(\\d{3,4}-)?\\d{7,8}$",message="传真格式不正确",groups={Groups.Add.class,Groups.Update.class})
    private String fax;
    
    /**
     * 电子邮箱
     */
    @Column(name = "EMAIL")
  //  @NotBlank(message="{serTeam.email.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Pattern(regexp="^$|^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,3})+$",message="{serTeam.email.pattren.error}",groups={Groups.Add.class,Groups.Update.class})
    private String email;
    
    /**
     * 负责人
     */
    @Column(name = "PRINCIPAL")
    @NotBlank(message="{serTeam.principal.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String principal;
    
    /**
     * 负责人电话
     */
    @Column(name = "PRINCIPAL_TEL")
    @NotBlank(message="{serTeam.principalTel.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
   	@Phone(message="{serTeam.principalTel.length.error}",groups={Groups.Add.class,Groups.Update.class})
    private String principalTel;
    
    /**
     * 服务队地址
     */
    @Column(name = "ADDRESS")
    @NotBlank(message="{serTeam.address.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String address;
    
    /**
     * 区域编号
     */
    @Column(name = "AREA_ID")
    @NotNull(message="{serTeam.areaId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private Long areaId;
    
    /**
     * 创建用户
     */
    @Column(name = "CR_USER")
    @NotNull(message="{public.crUser.notNull.error}",groups={Groups.Add.class})
    private String crUser;
    
    /**
     * 创建时间
     */
    @Column(name = "CR_TIME")
    @NotNull(message="{public.crTime.notNull.error}",groups={Groups.Add.class})
    private Date crTime;
    
    /**
     * 状态
     */
    @Column(name = "STATUS")
    @NotNull(message="{public.status.notNull.error}",groups={Groups.Add.class})
    private Integer status;
    
    /**
     * 头像地址
     */
    @Column(name = "AVATAR_URL")
    private String avatarUrl;
    
    /**
     * 不是表中的字段
     */
    @Transient
    private String orgName;
    
    /**
     * 不是表中的字段
     * 管理机构的Id
     */
    @Transient
    private Long curOrgId;
    
    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ORG_ID
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * @return SUMMARY
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return CONTACT
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return CONTACT_TEL
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * @param contactTel
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    /**
     * @return FAX
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return PRINCIPAL
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * @param principal
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * @return PRINCIPAL_TEL
     */
    public String getPrincipalTel() {
        return principalTel;
    }

    /**
     * @param principalTel
     */
    public void setPrincipalTel(String principalTel) {
        this.principalTel = principalTel;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return AREA_ID
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * @return CR_USER
     */
    public String getCrUser() {
        return crUser;
    }

    /**
     * @param crUser
     */
    public void setCrUser(String crUser) {
        this.crUser = crUser;
    }

    /**
     * @return CR_TIME
     */
    public Date getCrTime() {
        return crTime;
    }

    /**
     * @param crTime
     */
    public void setCrTime(Date crTime) {
        this.crTime = crTime;
    }

    /**
     * @return STATUS
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getCurOrgId() {
		return curOrgId;
	}

	public void setCurOrgId(Long curOrgId) {
		this.curOrgId = curOrgId;
	}

	@Override
	public String toString() {
		return "SerTeam [id=" + id + ", name=" + name + ", orgId=" + orgId + ", summary=" + summary + ", contact="
				+ contact + ", contactTel=" + contactTel + ", fax=" + fax + ", email=" + email + ", principal="
				+ principal + ", principalTel=" + principalTel + ", address=" + address + ", areaId=" + areaId
				+ ", crUser=" + crUser + ", crTime=" + crTime + ", status=" + status + ", avatarUrl=" + avatarUrl
				+ ", orgName=" + orgName + ", curOrgId=" + curOrgId + "]";
	}

}