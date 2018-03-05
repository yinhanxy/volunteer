package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.Date;

public class RoleView implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -5267269548446651326L;

	private Long id;

    private String roleName;

    private String roleDesc;

    private Integer roleType;

    private Integer status;

    private String crUser;

    private Date crTime;

    private Integer isOwn;
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
    public Date getCrTime() {
        return crTime;
    }

    /**
     * @param crTime
     */
    public void setCrTime(Date crTime) {
        this.crTime = crTime;
    }

	public Integer getIsOwn() {
		return isOwn;
	}

	public void setIsOwn(Integer isOwn) {
		this.isOwn = isOwn;
	}

	@Override
	public String toString() {
		return "RoleView [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleType=" + roleType
				+ ", status=" + status + ", crUser=" + crUser + ", crTime=" + crTime + ", isOwn=" + isOwn + "]";
	}

    
    
}