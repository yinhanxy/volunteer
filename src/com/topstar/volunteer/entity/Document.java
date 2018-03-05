package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.topstar.volunteer.validator.constraints.OracleLength;
import com.topstar.volunteer.validator.group.Groups;

@Entity
@Table(name="document")
public class Document implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5063963087064105364L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_DOCUMENT.nextval from dual")
    private Long id;

    @Column(name="SITE_ID")
    private Long siteId;
    
    @Column(name = "CHANNEL_ID")
    private Long channelId;

    @Column(name = "ORG_ID")
    private Long orgId;

    @NotNull(message="{document.title.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @OracleLength(max=200,message="{document.title.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "TITLE")
    private String title;

    @OracleLength(max=1000,message="{document.summary.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "SUMMARY")
    private String summary;

    @OracleLength(max=200,message="{document.keyword.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "KEYWORD")
    private String keyword;

    @OracleLength(max=50,message="{document.source.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "SOURCE")
    private String source;

    @OracleLength(max=40,message="{document.author.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "DOC_ORDER")
    private Long docOrder;

    @Column(name = "REL_TIME")
    private Timestamp relTime;

    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "HTMLCON")
    private String htmlcon;

    @Column(name = "PUB_TIME")
    private Timestamp pubTime;

    @Column(name = "LAST_UPDATE_TIME")
    private Timestamp lastUpdateTime;

    @Column(name = "LAST_UPDATE_USER")
    private String lastUpdateUser;

    @Column(name = "CR_USER")
    private String crUser;

    @Column(name = "CR_TIME")
    private Timestamp crTime;

    @Column(name = "SUB_TIME")
    private Timestamp subTime;

    @Column(name = "SUB_USER")
    private String subUser;

    @Column(name = "DEL_USER")
    private String delUser;

    @Column(name = "DEL_TIME")
    private Timestamp delTime;
    
    @Column(name = "HITS_COUNT")
    private Long hitsCount;

    /**
     * 1：新稿 <br/> 2：已提交<br/> 10：已发 <br/> -1：已否 <br/> -10 删除
     */
    @Column(name = "STATUS")
    private Integer status;
    
    /**
     * 文档状态
     * 1：新稿 <br/> 2：已提交<br/> 10：已发 <br/> -1：已否 <br/> -10 删除
     */
    public enum StatusType{
    	/**
    	 * 1:新稿
    	 */
    	new_doc(1,"新稿"),
    	
    	/**
    	 * 2:已提交
    	 */
    	commit(2,"已提交"),

    	/**
    	 * 10:已发布
    	 */
    	pub(10,"已发布"),
    	
    	/**
    	 * -1:已否
    	 */
    	cancel(-1,"已否"),
    	
    	/**
    	 * -10: 已删除
    	 */
    	delete(-10,"已删除");
    	
    	private Integer value = 1;
    	
    	private String desc;
    	
    	StatusType(Integer value,String desc){
    		this.value = value;
    		this.desc = desc;
    	}
    	
    	public String toString(){
    		return value.toString();
    	}
    	
    	public String getDesc() {
			return desc;
		}

		public int getValue(){
    		return this.value.intValue();
    	}
    	
		public static String getDesc(int status){
			StatusType[] types = values();
			for(int i = 0 ; i< types.length ;i ++){
				if(types[i].getValue() == status){
					return types[i].getDesc();
				}
			}
			return "未知状态";
		}
    }
    
    /**
     * 文档状态描述字段
     * 不是表中的字段
     */
    @Transient
    private String statusDesc;
    
    /**
     * 文档所属部门名称
     * 不是表中的字段
     */
    @Transient
    private String orgName;
    
    /**
     * 文档所属栏目名称
     * 不是表中的字段
     */
    @Transient
    private String channelName;

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

    public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	/**
     * @return CHANNEL_ID
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
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
     * @return TITLE
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return KEYWORD
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return SOURCE
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return AUTHOR
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return DOC_ORDER
     */
    public Long getDocOrder() {
        return docOrder;
    }

    /**
     * @param docOrder
     */
    public void setDocOrder(Long docOrder) {
        this.docOrder = docOrder;
    }

    /**
     * @return REL_TIME
     */
    public Timestamp getRelTime() {
        return relTime;
    }

    /**
     * @param relTime
     */
    public void setRelTime(Timestamp relTime) {
        this.relTime = relTime;
    }

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHtmlcon() {
		return htmlcon;
	}

	public void setHtmlcon(String htmlcon) {
		this.htmlcon = htmlcon;
	}

	/**
     * @return PUB_TIME
     */
    public Timestamp getPubTime() {
        return pubTime;
    }

    /**
     * @param pubTime
     */
    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    /**
     * @return LAST_UPDATE_TIME
     */
    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @return LAST_UPDATE_USER
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * @param lastUpdateUser
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
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
    public Timestamp getCrTime() {
        return crTime;
    }

    /**
     * @param crTime
     */
    public void setCrTime(Timestamp crTime) {
        this.crTime = crTime;
    }

    /**
     * @return SUB_TIME
     */
    public Timestamp getSubTime() {
        return subTime;
    }

    /**
     * @param subTime
     */
    public void setSubTime(Timestamp subTime) {
        this.subTime = subTime;
    }

    /**
     * @return SUB_USER
     */
    public String getSubUser() {
        return subUser;
    }

    /**
     * @param subUser
     */
    public void setSubUser(String subUser) {
        this.subUser = subUser;
    }

    /**
     * @return DEL_USER
     */
    public String getDelUser() {
        return delUser;
    }

    /**
     * @param delUser
     */
    public void setDelUser(String delUser) {
        this.delUser = delUser;
    }

    /**
     * @return DEL_TIME
     */
    public Timestamp getDelTime() {
        return delTime;
    }

    /**
     * @param delTime
     */
    public void setDelTime(Timestamp delTime) {
        this.delTime = delTime;
    }

    public Long getHitsCount() {
		return hitsCount;
	}

	public void setHitsCount(Long hitsCount) {
		this.hitsCount = hitsCount;
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

	public String getStatusDesc() {
		this.statusDesc = StatusType.getDesc(this.status);
		return statusDesc;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", siteId=" + siteId + ", channelId=" + channelId + ", orgId=" + orgId
				+ ", title=" + title + ", summary=" + summary + ", keyword=" + keyword + ", source=" + source
				+ ", author=" + author + ", docOrder=" + docOrder + ", relTime=" + relTime + ", content=" + content
				+ ", htmlcon=" + htmlcon + ", pubTime=" + pubTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", lastUpdateUser=" + lastUpdateUser + ", crUser=" + crUser + ", crTime=" + crTime + ", subTime="
				+ subTime + ", subUser=" + subUser + ", delUser=" + delUser + ", delTime=" + delTime + ", hitsCount="
				+ hitsCount + ", status=" + status + ", statusDesc=" + statusDesc + ", orgName=" + orgName
				+ ", channelName=" + channelName + "]";
	}

}