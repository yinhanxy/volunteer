package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerUtils;
import org.quartz.spi.OperableTrigger;

import com.topstar.volunteer.schedule.ScheduleStatus;

/**
 * 定时调度任务
 *
 */
public class ScheduleJob implements Serializable {

	private static final long serialVersionUID = -1570750573284149047L;
	public static final String JOB_PARAM_KEY="JOB_PARAM_KEY";


	/**
	 * 任务id
	 */
    @Id
	@Column(name = "JOB_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SCHEDULE_JOB.nextval from dual")
	private Long jobId;
	
	/**
	 * 任务名称
	 */
	@Column(name = "JOB_NAME")
	private String jobName;
	
	

	/**
	 * bean名称
	 */
	@Column(name = "BEAN_NAME")
	private String beanName;
	
	/**
	 * 方法名称
	 */
	@Column(name = "METHOD_NAME")
	private String methodName;
	
	
	/**
	 * 参数
	 */
	@Column(name = "PARAMS")
	private String params;
	
	/**
	 * cron表达式
	 */
	@Column(name = "CRON_EXPRESSION")
	private String cronExpression;

	/**
	 * 任务状态
	 */
	@Column(name = "STATUS")
	private Integer status;

	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_USER")
	private String createUser;
	
	/**
	 * 下次执行时间
	 */
	@Column(name = "NEXT_FIRE_TIME")
	private Date nextFireTime;
	
	
	/**
	 * 上次执行时间
	 */
	@Column(name="PRE_FIRE_TIME")
	private Date preFireTime;
	
	/**
	 * 已执行次数
	 */
	@Column(name = "FIRE_COUNT")
	private Long fireCount;
	

	/**
	 * 设置：任务id
	 * @param jobId 任务id
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	public Date getNextFireTime(){
		CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule(this.cronExpression);
		Trigger trigger=TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
		List<Date> date=TriggerUtils.computeFireTimes((OperableTrigger)trigger, null, 1);
		if(null!=date&&date.size()>0){
			return date.get(0);
		}
		return nextFireTime;
	}
	
	public void setNextFireTime(Date nextFireTime){
		this.nextFireTime=nextFireTime;
	}
	
	public Date getPreFireTime(){
		return preFireTime;
	}
	
	public void setPreFireTime(Date preFireTime){
		this.preFireTime=preFireTime;
	}
	
	public Long getFireCount(){
		return  fireCount;
	}
	
	public void setFireCount(Long fireCount){
		this.fireCount=fireCount;
	}
	
	public String getStatusName(){
		return ScheduleStatus.getName(status);
	}

	/**
	 * 获取：任务id
	 * @return Long
	 */
	public Long getJobId() {
		return jobId;
	}
	
	/**
	 * 设置：任务名称
	 * @param jobName 任务名称
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 获取：任务名称
	 * @return String
	 */
	public String getJobName() {
		return jobName;
	}
	
	/**
	 * 设置：任务方法名称
	 * @param methodName 任务方法名称
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 获取：任务名称
	 * @return String
	 */
	public String getMethodName() {
		return methodName;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 设置：任务状态
	 * @param status 任务状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：任务状态
	 * @return String
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 设置：cron表达式
	 * @param cronExpression cron表达式
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 获取：cron表达式
	 * @return String
	 */
	public String getCronExpression() {
		return cronExpression;
	}
	
	/**
	 * 设置：创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 * @return Date
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置：创建用户
	 * @param createUser 创建用户
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取：创建用户
	 * @return String
	 */
	public String getCreateUser() {
		return createUser;
	}

	@Override
	public String toString() {
		return "ScheduleJob [jobId=" + jobId + ", jobName=" + jobName + ", beanName=" + beanName + ", methodName="
				+ methodName + ", params=" + params + ", cronExpression=" + cronExpression + ", status=" + status
				+ ", remark=" + remark + ", createTime=" + createTime + ", createUser=" + createUser + ", nextFireTime="
				+ nextFireTime + ", preFireTime=" + preFireTime + ", fireCount=" + fireCount + "]";
	}


	

	
	
}
