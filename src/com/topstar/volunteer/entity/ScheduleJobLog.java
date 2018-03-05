package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 定时调度任务执行日志
 *
 */
public class ScheduleJobLog implements Serializable {
	
	private static final long serialVersionUID = 7450610234052498712L;

	/**
	 * 日志id
	 */
	@Id
	@Column(name = "LOG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SCHEDULE_JOB_LOG.nextval from dual")
	private Long logId;
	
	/**
	 * 任务id
	 */
	@Column(name = "JOB_ID")
	private Long jobId;
	
	/**
	 * 任务名称
	 */
	@Column(name = "JOB_NAME")
	private String jobName;
	
	/**
	 * 方法名称
	 */
	@Column(name = "METHOD_NAME")
	private String methodName;
	
	/**
	 * bean名称
	 */
	@Column(name = "BEAN_NAME")
	private String beanName;
	
	/**
	 * 参数
	 */
	@Column(name = "PARAMS")
	private String params;
	
	/**
	 * 任务状态    0：成功    1：失败
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	/**
	 * 失败信息
	 */
	@Column(name = "MESSAGE")
	private String message;
	
	/**
	 * 耗时(单位：毫秒)
	 */
	@Column(name = "USETIME")
	private Integer useTime;
	
	/**
	 * 任务开始时间
	 */
	@Column(name = "BEGIN_TIME")
	private Date beginTime;
	
	/**
	 * 任务结束时间
	 */
	@Column(name = "END_TIME")
	private Date endTime;
	
	
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getUseTime() {
		return useTime;
	}

	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getStatusName(){
		return status==0?"成功":"失败";
	}
	@Override
	public String toString() {
		return "ScheduleJobLog [logId=" + logId + ", jobId=" + jobId + ", jobName=" + jobName + ", beanName=" + beanName
				+ ", params=" + params + ", status=" + status + ", message=" + message + ", useTime=" + useTime
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}
	
}
