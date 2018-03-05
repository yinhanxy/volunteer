package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.group.Groups.Add;
import com.topstar.volunteer.validator.group.Groups.Update;

@Entity
@Table(name = "SYS_ROLE")
public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1877649856216993559L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SYS_ROLE.nextval from dual")
	@Null(message="{role.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{role.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{config.configId.minValue.error}",groups=Groups.Update.class)
	private Long id;

	@NotBlank(message="{role.roleName.notNull.error}",groups={Groups.Add.class})
	@Pattern(regexp="^[a-zA-Z\u4e00-\u9fa5]{2,10}$",message="{role.roleName.length.error}", groups={Groups.Add.class,Groups.Update.class})
	@Column(name = "ROLE_NAME")
    private String roleName;

	@Length(min=0,max=100,message="{role.roleDesc.length.error}", groups={Add.class,Update.class} )
    @Column(name = "ROLE_DESC")
    private String roleDesc;
	
	@NotNull(message="{role.roleType.notNull.error}", groups={Add.class,Update.class} )
	@Range(min=0,max=1,message="{role.roleType.range.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "ROLE_TYPE")
    private Integer roleType;

	@NotNull(message="{public.status.notNull.error}", groups={Add.class})
    @Column(name = "STATUS")
    private Integer status;

	@NotNull(message="{public.crUser.notNull.error}", groups={Add.class})
    @Column(name = "CR_USER")
    private String crUser;

	@NotNull(message="{public.crTime.notNull.error}", groups={Add.class})
    @Column(name = "CR_TIME")
    private Timestamp crTime;
    
    /**
     * 不是表中的字段
     */
    @Transient
    private Map<Long, Menu> menuMap;
    
    @Transient
    private List<Long> channelIds;
    
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
     * @return ROLE_NAME
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return ROLE_DESC
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * @return ROLE_TYPE
     */
    public Integer getRoleType() {
        return roleType;
    }
    
    public String getRoleTypeName() {
    	if(roleType==0){
    		return "普通角色";
    	}else if(roleType==1){
    		return "系统角色";
    	}
        return "";
    }

    /**
     * @param roleType
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
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

	public Map<Long, Menu> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<Long, Menu> menuMap) {
		this.menuMap = menuMap;
	}

	public List<Long> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<Long> channelIds) {
		this.channelIds = channelIds;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleType=" + roleType
				+ ", status=" + status + ", crUser=" + crUser + ", crTime=" + crTime + "]";
	}
    
}