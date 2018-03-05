package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.topstar.volunteer.validator.constraints.Phone;
import com.topstar.volunteer.validator.group.Groups;
/**
 * 培训记录表
 */
@Entity
@Table(name = "TR_RECORD")
public class TrRecord implements Serializable{

	private static final long serialVersionUID = 268701275671832447L;
	/**
	 * 编号
	 */
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_TR_RECORD.nextval from dual")
	@Null(message="{trRecord.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{trRecord.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{trRecord.id.minValue.error}",groups=Groups.Update.class)
	private Long id;
	
	/**
	 * 培训名称
	 */
    @Column(name = "TR_NAME")
    @NotBlank(message="{trRecord.trName.notNull.error}",groups={Groups.Add.class})
	@Pattern(regexp="^[\u4e00-\u9fa5]{2,50}$",message="{trRecord.trName.length.error}", groups={Groups.Add.class,Groups.Update.class})
    private String trName;
    
    /**
     * 培训内容
     */
    @Column(name = "TR_CONTENT")
    @NotBlank(message="{trRecord.trContent.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String trContent;
    
    /**
     * 主讲人
     */
    @Column(name = "PRESENTER")
    @NotNull(message="{trRecord.presenter.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String presenter;
    
    /**
     * 培训时间
     */
    @Column(name = "TR_TIME")
    @NotNull(message="{trRecord.trTime.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String trTime;
    
    /**
     * 培训地点
     */
    @Column(name = "TR_LOCATION")
    @NotNull(message="{trRecord.trLocation.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String trLocation;
    
    /**
     * 所属服务队编号
     */
    @Column(name = "TEAM_ID")
    @NotNull(message="{trRecord.teamId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Min(value=1,message="{trRecord.teamId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
    private Long teamId;
    
    /**
     * 负责人
     */
    @Column(name = "PRINCIPAL")
    @NotNull(message="{trRecord.principal.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String principal;
    
    /**
     * 负责人电话
     */
    @Column(name = "PRINCIPAL_TEL")
    @NotNull(message="{trRecord.principalTel.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
   	@Phone(message="{trRecord.principalTel.length.error}",groups={Groups.Add.class,Groups.Update.class})
    private String principalTel;
    
    /**
     * 联系人
     */
    @Column(name = "CONTACT")
    @NotNull(message="{trRecord.contact.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    private String contact;
    
    /**
     * 联系电话
     */
    @Column(name = "CONTACT_TEL")
    @NotNull(message="{trRecord.contactTel.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
   	@Phone(message="{trRecord.contactTel.length.error}",groups={Groups.Add.class,Groups.Update.class})
    private String contactTel;
    
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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date crTime;
    
    /**
     * 状态
     */
    @Column(name = "STATUS")
    @NotNull(message="{public.status.notNull.error}",groups={Groups.Add.class})
    private Integer status;
    
    /**
     * 不是表中的字段
     * 服务队名称
     */
    @Transient
    private String teamName;
    
    /**
     * 不是表中的字段
     * 组织机构Id
     */
    @Transient
    private Long orgId;
    
    /**
     * 不是表中的字段
     * 管理机构的Id
     */
    @Transient
    private Long curOrgId;
    
    /**
     * 不是表中的字段
     * 参加培训人数
     */
    @Transient
    private int trainVolNum;
    
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
     * @return TR_NAME
     */
    public String getTrName() {
        return trName;
    }

    /**
     * @param trName
     */
    public void setTrName(String trName) {
        this.trName = trName;
    }

    /**
     * @return TR_CONTENT
     */
    public String getTrContent() {
        return trContent;
    }

    /**
     * @param trContent
     */
    public void setTrContent(String trContent) {
        this.trContent = trContent;
    }

    /**
     * @return PRESENTER
     */
    public String getPresenter() {
        return presenter;
    }

    /**
     * @param presenter
     */
    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    /**
     * @return TR_TIME
     */
    public String getTrTime() {
        return trTime;
    }

    /**
     * @param trTime
     */
    public void setTrTime(String trTime) {
        this.trTime = trTime;
    }

    /**
     * @return TR_LOCATION
     */
    public String getTrLocation() {
        return trLocation;
    }

    /**
     * @param trLocation
     */
    public void setTrLocation(String trLocation) {
        this.trLocation = trLocation;
    }

    /**
     * @return TEAM_ID
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

	public int getTrainVolNum() {
		return trainVolNum;
	}

	public void setTrainVolNum(int trainVolNum) {
		this.trainVolNum = trainVolNum;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public Long getCurOrgId() {
		return curOrgId;
	}

	public void setCurOrgId(Long curOrgId) {
		this.curOrgId = curOrgId;
	}

	@Override
	public String toString() {
		return "TrRecord [id=" + id + ", trName=" + trName + ", trContent=" + trContent + ", presenter=" + presenter
				+ ", trTime=" + trTime + ", trLocation=" + trLocation + ", teamId=" + teamId + ", principal="
				+ principal + ", principalTel=" + principalTel + ", contact=" + contact + ", contactTel=" + contactTel
				+ ", crUser=" + crUser + ", crTime=" + crTime + ", status=" + status + ", teamName=" + teamName
				+ ", orgId=" + orgId + ", curOrgId=" + curOrgId + ", trainVolNum=" + trainVolNum + "]";
	}

}