package com.topstar.volunteer.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.log.LogType;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;

public class LoggerView implements Serializable{
	
	private static final long serialVersionUID = -3608955339194551213L;

	private Long id;

    private String loggerType;

    private String objectType;

    private String operateType;
    
    private String message;

    private String operateUser;

    private String operateTime;

    private String ip;

    private String success;
    
    private String objectName;
    
    private Long objectId;
    
    public LoggerView (Logger logger){
    	if(logger!=null){
    		this.setId(logger.getId());
    		this.setIp(logger.getIp());
    		this.setLoggerType(LogType.getName(logger.getLoggerType()));
    		this.setMessage(logger.getMessage());
    		this.setObjectType(ObjectType.getName(logger.getObjectType()));
    		this.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(logger.getOperateTime()));
    		this.setOperateType(OperateType.getName(logger.getOperateType()));
    		this.setOperateUser(logger.getOperateUser());
    		this.setSuccess(logger.getSuccess()==1?"成功":"失败");
    		this.setObjectId(logger.getObjectId());
    		this.setObjectName(logger.getObjectName());
    	}
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoggerType() {
		return loggerType;
	}

	public void setLoggerType(String loggerType) {
		this.loggerType = loggerType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "LoggerView [id=" + id + ", loggerType=" + loggerType + ", objectType=" + objectType + ", operateType="
				+ operateType + ", message=" + message + ", operateUser=" + operateUser + ", operateTime=" + operateTime
				+ ", ip=" + ip + ", success=" + success + "]";
	}

}
