package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logger")
public class Logger implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5498932654224780977L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_LOGGER.nextval from dual")
    private Long id;

    @Column(name = "LOGGERTYPE")
    private Integer loggerType;

    @Column(name = "OBJECTTYPE")
    private Integer objectType;

    @Column(name = "OPERATETYPE")
    private Integer operateType;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "OPERATEUSER")
    private String operateUser;

    @Column(name = "OPERATETIME")
    private Date operateTime;

    @Column(name = "IP")
    private String ip;

    @Column(name = "SUCCESS")
    private Integer success;
    
    @Column(name="OBJECTNAME")
    private String objectName;
    
    @Column(name="OBJECTID")
    private Long objectId;

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
     * @return LOGGERTYPE
     */
    public Integer getLoggerType() {
        return loggerType;
    }

    /**
     * @param loggertype
     */
    public void setLoggerType(Integer loggerType) {
        this.loggerType = loggerType;
    }

    /**
     * @return ObjectType
     */
    public Integer getObjectType() {
        return objectType;
    }

    /**
     * @param objectType
     */
    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    /**
     * @return OperateType
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * @param operateType
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * @return MESSAGE
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return OperateUser
     */
    public String getOperateUser() {
        return operateUser;
    }

    /**
     * @param operateUser
     */
    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    /**
     * @return OperateTime
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * @param operateTime
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * @return IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return SUCCESS
     */
    public Integer getSuccess() {
        return success;
    }

    /**
     * @param success
     */
    public void setSuccess(Integer success) {
        this.success = success;
    }

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Override
	public String toString() {
		return "Logger [id=" + id + ", loggerType=" + loggerType + ", objectType=" + objectType + ", operateType="
				+ operateType + ", message=" + message + ", operateUser=" + operateUser + ", operateTime=" + operateTime
				+ ", ip=" + ip + ", success=" + success + ", objectName=" + objectName + ", objectId=" + objectId + "]";
	}
    
	
}