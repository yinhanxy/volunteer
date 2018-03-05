package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.group.Groups.Add;

public class Channel implements Serializable,Comparable<Channel>,Cloneable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 3874655410108148453L;

		/**
		 * 唯一标识
		 */
		@Null(message="{channel.id.null.error}",groups=Groups.Add.class)
		@NotNull(message="{channel.id.notNull.error}",groups=Groups.Update.class)
		@Min(value=1,message="{channel.id.minValue.error}",groups=Groups.Update.class)
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_CHANNEL.nextval from dual")
		private Long id;
		
		/**
		 * 栏目所在的站点Id
		 */
		@NotNull(message="{channel.siteId.notNull.error}",groups={Groups.Add.class})
		@Min(value=0,message="{channel.siteId.minValue.error}",groups={Groups.Add.class})
		@Column(name="SITE_ID")
		private Long siteId;
		
		
		/**
		 * 上级栏目的唯一标识
		 */
		@NotNull(message="{channel.parentId.notNull.error}",groups={Groups.Add.class})
		@Min(value=0,message="{channel.parentId.minValue.error}",groups={Groups.Add.class})
		@Column(name="PARENT_ID")
		private Long parentId;
		
		/**
		 * 唯一标识
		 */
		@NotBlank(message="{channel.name.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
		@Pattern(regexp="^[a-zA-Z0-9_\u4e00-\u9fa5]{2,50}$",message="{channel.name.length.error}",groups={Groups.Add.class,Groups.Update.class})
		@Column(name="CHNL_NAME")
		private String chnlName;
		
		/**
		 * 显示名称
		 */
		@NotBlank(message="{channel.chnlDesc.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
		@Pattern(regexp="^[a-zA-Z0-9_\u4e00-\u9fa5]{2,50}$",message="{channel.chnlDesc.length.error}",groups={Groups.Add.class,Groups.Update.class})
		@Column(name="CHNL_DESC")
		private String chnlDesc;
		
		/**
		 * 创建用户
		 */
		@NotNull(message="{channel.crUser.notNull.error}", groups={Add.class})
		@Column(name="CR_USER")
		private String crUser;
		
		/**
		 * 创建时间
		 */
		@NotNull(message="{channel.crTime.notNull.error}", groups={Add.class})
		@Column(name="CR_TIME")
		private Date crTime;
		
		/**
		 * 操作用户
		 */
		@NotNull(message="{channel.operUser.notNull.error}", groups={Add.class})
		@Column(name="OPER_USER")
		private String operUser ;
		
		/**
		 * 操作时间
		 */
		@NotNull(message="{channel.operTime.notNull.error}", groups={Add.class})
		@Column(name="OPER_TIME")
		private Date operTime;

		/**
		 * 栏目状态
		 */
		@NotNull(message="{channel.status.notNull.error}", groups={Add.class})
		private Integer status;
		
		/**
		 * 栏目排序号
		 */
		@Column(name="ORDER_NO")
		private Long order;
		
		@Transient
		private String parentName;
		
		@Transient
		private List<Channel> childChnnels=new ArrayList<Channel>();
		
		public Channel() {
			super();
		}
		
		

		public Channel(Long id, Long siteId, Long parentId) {
			super();
			this.id = id;
			this.siteId = siteId;
			this.parentId = parentId;
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getSiteId() {
			return siteId;
		}

		public void setSiteId(Long siteId) {
			this.siteId = siteId;
		}

		public Long getParentId() {
			return parentId;
		}

		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}

		public String getChnlName() {
			return chnlName;
		}

		public void setChnlName(String chnlName) {
			this.chnlName = chnlName;
		}

		public String getChnlDesc() {
			return chnlDesc;
		}

		public void setChnlDesc(String chnlDesc) {
			this.chnlDesc = chnlDesc;
		}

		public String getCrUser() {
			return crUser;
		}

		public void setCrUser(String crUser) {
			this.crUser = crUser;
		}

		public Date getCrTime() {
			return crTime;
		}

		public void setCrTime(Date crTime) {
			this.crTime = crTime;
		}

		public String getOperUser() {
			return operUser;
		}

		public void setOperUser(String operUser) {
			this.operUser = operUser;
		}

		public Date getOperTime() {
			return operTime;
		}

		public void setOperTime(Date operTime) {
			this.operTime = operTime;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Long getOrder() {
			return order;
		}

		public void setOrder(Long order) {
			this.order = order;
		}

		public String getParentName() {
			return parentName;
		}

		public void setParentName(String parentName) {
			this.parentName = parentName;
		}

		
		public List<Channel> getChildChnnels() {
			return childChnnels;
		}

		public void setChildChnnels(Channel channel) {
			if(channel!=null){
				this.childChnnels.add(channel);
			}
		}

		@Override
		public int compareTo(Channel o) {
			if(o.getParentId().longValue() == this.getParentId().longValue()){
				int num = this.order.compareTo(o.getOrder());
				return num;
			}else{
				return this.getParentId().compareTo(o.getParentId());
			}
		}

		@Override
		public Channel clone(){
			try {
				return (Channel) super.clone();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}



		@Override
		public String toString() {
			return "Channel [id=" + id + ", siteId=" + siteId + ", parentId=" + parentId + ", chnlName=" + chnlName
					+ ", chnlDesc=" + chnlDesc + ", crUser=" + crUser + ", crTime=" + crTime + ", operUser=" + operUser
					+ ", operTime=" + operTime + ", status=" + status + ", order=" + order + ", parentName="
					+ parentName + ", childChnnels=" + childChnnels + "]";
		}
		
}
