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

import org.hibernate.validator.constraints.NotBlank;

import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.group.Groups.Add;

public class Site implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4546797602875371215L;

		/**
		 * 唯一标识
		 */
		@Null(message="{site.id.null.error}",groups=Groups.Add.class)
		@NotNull(message="{site.id.notNull.error}",groups=Groups.Update.class)
		@Min(value=1,message="{site.id.minValue.error}",groups=Groups.Update.class)
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SITE.nextval from dual")
		private Long id;
		
		/**
		 * 站点名称
		 */
		@NotBlank(message="{site.siteName.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
		@Pattern(regexp="^[\u4e00-\u9fa5]{2,50}$",message="{site.siteName.length.error}",groups={Groups.Add.class,Groups.Update.class})
		@Column(name="SITE_NAME")
		private String siteName;
		
		/**
		 * 站点描述
		 */
		@Column(name="SITE_DESC")
		private String siteDesc;
		
		/**
		 * 站点创建用户
		 */
		@NotNull(message="{public.crUser.notNull.error}", groups={Add.class})
		@Column(name="CR_USER")
		private String crUser;
		
		/**
		 * 站点创建时间
		 */
		@NotNull(message="{public.crTime.notNull.error}", groups={Add.class})
		@Column(name="CR_TIME")
		private Date crTime;
		
		/**
		 * 站点操作用户
		 */
		@NotNull(message="{site.operUser.notNull.error}", groups={Add.class})
		@Column(name="OPER_USER")
		private String operUser ;
		
		/**
		 * 站点操作时间
		 */
		@NotNull(message="{site.operTime.notNull.error}", groups={Add.class})
		@Column(name="OPER_TIME")
		private Date operTime;

		/**
		 * 站点状态
		 */
		@NotNull(message="{site.status.notNull.error}", groups={Add.class})
		private Integer status;
		
		/**
		 * 站点排序号
		 */
		@Column(name="ORDER_NO")
		private Long order;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSiteName() {
			return siteName;
		}

		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}

		public String getSiteDesc() {
			return siteDesc;
		}

		public void setSiteDesc(String siteDesc) {
			this.siteDesc = siteDesc;
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

		@Override
		public String toString() {
			return "Site [id=" + id + ", siteName=" + siteName + ", siteDesc=" + siteDesc + ", crUser=" + crUser
					+ ", crTime=" + crTime + ", operUser=" + operUser + ", operTime=" + operTime + ", status=" + status
					+ ", order=" + order + "]";
		}

}
