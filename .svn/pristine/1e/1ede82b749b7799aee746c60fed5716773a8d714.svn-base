package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.topstar.volunteer.validator.group.Groups;

/**
 * 活动实体信息类
 * @author TRS
 *
 */
public class Activity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -691961804931712941L;

	/**
	 * 唯一标识
	 */
	@Null(message="{activity.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{activity.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{activity.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_ACTIVITY.nextval from dual")
	private Long id;
	
	/**
	 * 组织活动的服务队编号
	 */
	@NotNull(message="{activity.serId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{activity.serId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Long serId;
	
	/**
	 * 活动名称
	 */
	@NotBlank(message="{activity.name.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Pattern(regexp="^[\u4e00-\u9fa5]{2,50}$",message="{activity.name.length.error}",groups={Groups.Add.class,Groups.Update.class})
	private String name;
	
	/**
	 * 活动类型 (1:短期；2:长期)
	 */
	@NotNull(message="{activity.type.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{activity.type.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Max(value=2,message="{activity.type.maxValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Integer type;
	
	/**
	 * 招募条件
	 */
	@NotNull(message="{activity.requirements.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private String requirements;
	
	/**
	 * 活动类别
	 */
	@NotNull(message="{activity.activityClass.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private Long activityClass;
	
	/**
	 * 招募人数
	 */
	@NotNull(message="{activity.recruitNum.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{activity.recruitNum.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Integer recruitNum;
	
	/**
	 * 联系人
	 */
	@NotNull(message="{activity.contactPerson.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private String contactPerson;
	
	/**
	 * 联系方式
	 */
	@NotNull(message="{activity.contact.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private String contact;
	
	/**
	 * 活动地址
	 */
	@NotNull(message="{activity.address.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private String address;
	
	/**
	 * 活动时长
	 */
	@NotNull(message="{activity.hours.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=0,message="{activity.hours.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Double hours;
	
	/**
	 * 活动招募开始时间
	 */
	@NotNull(message="{activity.recruitSt.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date recruitSt;
	
	/**
	 * 活动招募结束时间
	 */
	@NotNull(message="{activity.recruitEt.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date recruitEt;
	
	/**
	 * 活动开始时间
	 */
	@NotNull(message="{activity.activitySt.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date activitySt;
	
	/**
	 * 活动结束时间
	 */
	@NotNull(message="{activity.activityEt.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date activityEt;
	
	/**
	 * 活动简介
	 */
	@NotNull(message="{activity.summary.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private String summary;

	/**
	 * 发布人
	 */
	@NotNull(message="{activity.publisher.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	private String publisher;
	
	
	/**
	 * 活动报名人数
	 */
	@Transient
	private Integer applyNum;
	
	/**
	 * 活动招募范围(1:公开招募 2:只限本服务队)
	 */
	@NotNull(message="{activity.recruitRange.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=1,message="{activity.recruitRange.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Max(value=2,message="{activity.recruitRange.maxValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Integer recruitRange;
	
	/**
	 * 
	 * 活动状态
	 * 		"1":待提交
	 * 		"2":已提交
	 * 		"3":待招募
	 * 		"4":招募中
	 *  	"5":待进行
	 *   	"6":进行中
	 *   	"7":已完成
	 *   	"8":已撤销
	 */
	private Integer status;

	/**
     * 不是表中的字段
     * 充当查询条件的用户所属组织机构Id
     */
    @Transient
    private Long orgId;
    
    /**
     * 不是表中的字段
     * 充当查询条件的用户所属管理组织机构的Id
     */
    @Transient
    private Long parentOrgId;
	
    /**
     * 活动图片地址(以","分隔)
     */
    private String activityImg;
    
    /**
     * 活动图片地址
     */
    @Transient
    private List<String> activityImgList;
    
    @Transient
    public String activityClassDesc;
    
    public String getStatusDesc(){
    	if(status!=null){
    		switch (status) {
			case 1:
				return "待提交";
			case 2:
				return "已提交";
			case 3:
				return "待招募";
			case 4:
				return "招募中";
			case 5:
				return "待进行";
			case 6:
				return "进行中";
			case 7:
				return "已完成";
			case 8:
				return "已撤销";
			default:
				break;
			}
    	}
    	return "异常";
    }
    
    public String getRecruitRangeDesc(){
    	if(recruitRange!=null){
    		switch (recruitRange) {
			case 1:
				return "公开招募";
			case 2:
				return "只限本服务队";
    		}
    	}
    	return "异常";
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSerId() {
		return serId;
	}

	public void setSerId(Long serId) {
		this.serId = serId;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public String getRequirements() {
		return requirements;
	}



	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}



	public Long getActivityClass() {
		return activityClass;
	}



	public void setActivityClass(Long activityClass) {
		this.activityClass = activityClass;
	}



	public Integer getRecruitNum() {
		return recruitNum;
	}



	public void setRecruitNum(Integer recruitNum) {
		this.recruitNum = recruitNum;
	}



	public String getContactPerson() {
		return contactPerson;
	}



	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Double getHours() {
		return hours;
	}



	public void setHours(Double hours) {
		this.hours = hours;
	}



	public Date getRecruitSt() {
		return recruitSt;
	}



	public void setRecruitSt(Date recruitSt) {
		this.recruitSt = recruitSt;
	}



	public Date getRecruitEt() {
		return recruitEt;
	}



	public void setRecruitEt(Date recruitEt) {
		this.recruitEt = recruitEt;
	}



	public Date getActivitySt() {
		return activitySt;
	}



	public void setActivitySt(Date activitySt) {
		this.activitySt = activitySt;
	}



	public Date getActivityEt() {
		return activityEt;
	}



	public void setActivityEt(Date activityEt) {
		this.activityEt = activityEt;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}

	public Integer getRecruitRange() {
		return recruitRange;
	}

	public void setRecruitRange(Integer recruitRange) {
		this.recruitRange = recruitRange;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getActivityImg() {
		return activityImg;
	}

	public void setActivityImg(String activityImg) {
		this.activityImg = activityImg;
	}

	public List<String> getActivityImgList() {
		List<String> imgUrls=null;
		if(activityImg!=null){
			imgUrls=new ArrayList<String>();
			String[] imgs=activityImg.split(",");
			for (int i = 0; i < imgs.length; i++) {
				imgUrls.add(imgs[i]);
			}
		}
		return imgUrls;
	}

	public String getActivityClassDesc() {
		return activityClassDesc;
	}

	public void setActivityClassDesc(String activityClassDesc) {
		this.activityClassDesc = activityClassDesc;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", serId=" + serId + ", name=" + name + ", type=" + type + ", requirements="
				+ requirements + ", activityClass=" + activityClass + ", recruitNum=" + recruitNum + ", contactPerson="
				+ contactPerson + ", contact=" + contact + ", address=" + address + ", hours=" + hours + ", recruitSt="
				+ recruitSt + ", recruitEt=" + recruitEt + ", activitySt=" + activitySt + ", activityEt=" + activityEt
				+ ", summary=" + summary + ", publisher=" + publisher + ", applyNum=" + applyNum + ", recruitRange="
				+ recruitRange + ", status=" + status + ", orgId=" + orgId + ", parentOrgId=" + parentOrgId
				+ ", activityImg=" + activityImg + ", activityImgList=" + activityImgList + "]";
	}

}
