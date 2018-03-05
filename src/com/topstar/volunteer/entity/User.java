package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.topstar.volunteer.util.DateUtil;
import com.topstar.volunteer.validator.constraints.NotEmptyAndOracleLength;
import com.topstar.volunteer.validator.constraints.OracleLength;
import com.topstar.volunteer.validator.constraints.Phone;
import com.topstar.volunteer.validator.group.Groups;

@Entity
@Table(name="sys_user")
public class User implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1746388830896564939L;
	
	@Null(message="{user.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{user.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{user.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SYS_USER.nextval from dual")
	private Long id;
    
	@NotBlank(message="{user.userName.notNull.error}",groups=Groups.Add.class)
	@Pattern(regexp="^[0-9a-zA-Z\u4e00-\u9fa5]{3,20}$",message="{user.userName.pattern.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "USER_NAME")
    private String userName;
    
	@NotBlank(message="{user.password.notNull.error}",groups=Groups.Add.class)
	@Pattern(regexp="^[0-9a-zA-Z_]{5,12}$",message="{user.password.length.error}",groups=Groups.Add.class)
	@Column(name = "USER_PWD")
    private String userPwd;
	
	@NotBlank(message="{user.nickName.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@OracleLength(min=2,max=30,message="{user.nickName.length.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "NICK_NAME")
    private String nickName;
    
	@NotEmptyAndOracleLength(min=1,max=30,message="{user.realName.length.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "REAL_NAME")
    private String realName;
	
	@NotBlank(message="{user.email.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Pattern(regexp="^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,3})+$",message="{user.email.pattren.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "EMAIL")
    private String email;
    
	@NotBlank(message="{user.mobile.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Phone(message="{user.mobile.length.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "MOBILE")
    private String mobile;
    
	@NotNull(message="{user.regTime.notNull.error}",groups=Groups.Add.class)
	@Column(name = "REG_TIME")
    private Timestamp regTime;
    
	@NotNull(message="{user.useTime.notNull.error}",groups=Groups.Add.class)
	@Column(name = "USE_TIME")
    private Timestamp useTime;
    
	@NotNull(message="{public.crUser.notNull.error}",groups=Groups.Add.class)
	@Column(name = "CR_USER")
    private String crUser;

	@NotNull(message="{public.crTime.notNull.error}",groups=Groups.Add.class)
	@Column(name = "CR_TIME")
    private Timestamp crTime;
    
	@NotNull(message="{public.status.notNull.error}",groups=Groups.Add.class)
	@Min(value=1,message="{user.status.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Max(value=4,message="{user.status.maxValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "STATUS")
    private Integer status;
    
	@Column(name = "REMARK")
    private String remark;
    
	/**
	 * 不在数据库中的字段
	 */
	@Transient
    private Map<Long, Menu> menuMap;
    
	/**
	 * 不在数据库中的字段
	 */
	@Transient
    private List<Long> roleIdList;
	
	/**
	 * 不在数据库中的字段
	 * 用户表关联的USER_CHANNEL表的数据
	 */
	@Transient
	private List<Long> channelIds;
	
	/**
	 * 不在数据库中的字段
	 */
	@Transient
    private Long orgId;
	
	/**
	 * 不在数据库中的字段
	 */
	@Transient
    private List<Long> teamIdList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public Timestamp getUseTime() {
		return useTime;
	}

	public void setUseTime(Timestamp useTime) {
		this.useTime = useTime;
	}
	
	public String getCrUser() {
		return crUser;
	}

	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	public Timestamp getCrTime() {
		return crTime;
	}

	public void setCrTime(Timestamp crTime) {
		this.crTime = crTime;
	}

	public Integer getStatus() {
		return status;
	}

	public String getUserStatus() {
		if(status!=null){
	    	if(status.intValue()==1){
	    		return "已开通";
			}else if(status.intValue()==2){
				return "未开通";
			}else if(status.intValue()==3){
				return "已停用";
			}
		}
		return "已删除";
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Map<Long, Menu> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<Long, Menu> menuMap) {
		this.menuMap = menuMap;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public List<Long> getTeamIdList() {
		return teamIdList;
	}

	public void setTeamIdList(List<Long> teamIdList) {
		this.teamIdList = teamIdList;
	}
	
	public List<Long> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<Long> channelIds) {
		this.channelIds = channelIds;
	}

	@Override
	public String toString() {
		String regTime = "";
		if(this.regTime != null ){
			regTime = DateUtil.format(this.regTime, DateUtil.YYYY_MM_DD_HHMMSS);
		}
		return "User [id=" + id + ", userName=" + userName + ", userPwd="
				+ userPwd + ", nickName=" + nickName + ", realName=" + realName
				+ ", email=" + email + ", mobile=" + mobile + ", regTime="
				+ regTime + ", useTime=" + useTime + ", crUser=" + crUser
				+ ", crTime=" + crTime + ", status=" + status + ", remark="
				+ remark +",menuMap="+menuMap+",roleIdList="+roleIdList+",orgId="+orgId+"]";
	}
    
    
}