package com.topstar.volunteer.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.entity.ScheduleJob;


/**
 * 定时任务工具类
 * 
 */
public class ScheduleUtils {
    private final static String JOB_NAME = "TASK_";
    private final static String JOB_GROUP="TPS";
    
    private static Logger logger=LoggerFactory.getLogger(ScheduleUtils.class);
    
    
    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId,JOB_GROUP);
    }
    
    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId,JOB_GROUP);
    }
    

    /**
     * 获取表达式触发器
     * @throws Exception 
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) throws Exception {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
        	throw new RuntimeException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建定时任务
     * @throws Exception 
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
        try {
        	//构建job信息
            JobDetail jobDetail =JobBuilder.newJob((Class<? extends Job>) Class.forName(scheduleJob.getBeanName())).withIdentity(getJobKey(scheduleJob.getJobId())).build();
            
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
            		.withMisfireHandlingInstructionDoNothing();
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId())).withSchedule(scheduleBuilder).build();
            //放入参数，运行时的方法可以获取
            trigger.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, String.valueOf(scheduleJob.getJobId()));
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }
    
    /**
     * 更新定时任务
     * @throws Exception 
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());
            CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getJobId());
            if(!trigger.getCronExpression().equalsIgnoreCase(scheduleJob.getCronExpression())){
            	logger.info("修改任务CronExpression【原表达式：{},新表达式：{}】",trigger.getCronExpression(),scheduleJob.getCronExpression());
	            //表达式调度构建器
	            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
	            		.withMisfireHandlingInstructionDoNothing();
	            //按新的cronExpression表达式重新构建trigger
	            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
	            scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException("更新定时任务失败", e);
        }
    }

    /**
     * 立即执行任务
     * @throws Exception 
     */
    public static void run(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
        try {
        	JobDataMap dataMap = new JobDataMap();
        	dataMap.put(ScheduleJob.JOB_PARAM_KEY, String.valueOf(scheduleJob.getJobId()));
            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
        } catch (SchedulerException e) {
            throw new RuntimeException("立即执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     * @throws Exception 
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) throws Exception {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     * @throws Exception 
     */
    public static void resumeJob(Scheduler scheduler, Long jobId) throws Exception {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     * @throws Exception 
     */
    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) throws Exception {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }
   
}
