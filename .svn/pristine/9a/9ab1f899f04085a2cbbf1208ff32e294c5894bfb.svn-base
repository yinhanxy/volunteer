package com.topstar.volunteer.schedule;

import java.util.Date;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.entity.ScheduleJob;
import com.topstar.volunteer.entity.ScheduleJobLog;
import com.topstar.volunteer.service.ScheduleJobLogService;
import com.topstar.volunteer.service.ScheduleJobService;
import com.topstar.volunteer.web.context.SpringContextHolder;


/**
 * 定时任务
 * 
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public abstract class QuartzJob implements Job{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected abstract void runJob() throws Exception;
	
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	 //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextHolder.getBean(ScheduleJobLogService.class);
        ScheduleJobService scheduleJobService = (ScheduleJobService) SpringContextHolder.getBean(ScheduleJobService.class);
    	ScheduleJobLog log = new ScheduleJobLog();
    	log.setBeginTime(new Date());
    	 m_oDataMap=context.getTrigger().getJobDataMap();
    	long jobId=m_oDataMap.getLongValueFromString(ScheduleJob.JOB_PARAM_KEY);
    	ScheduleJob scheduleJob = null;
    	try{
    		scheduleJob = scheduleJobService.findById(jobId);
    	}catch (Exception e) {
			logger.error("没有找到对应的任务信息",e);
			return;
		}
    	if(scheduleJob==null){
    		logger.error("没有找到对应的任务信息");
			return;
    	}
    	
       
        String param=scheduleJob.getParams();
        if(StringUtils.isNotBlank(param)){

            StringTokenizer stAttributes = null;
            stAttributes = new StringTokenizer(param, "&");
            while (stAttributes.hasMoreElements())
            {
              String sTemp = stAttributes.nextToken();
              int nPose = sTemp.indexOf("=");
              if (nPose > 0)
              {
                String sName = sTemp.substring(0, nPose);
                String sValue = sTemp.substring(nPose + 1);
                m_oDataMap.put(sName, sValue);
              }
            }
        	
        }

        scheduleJob.setStatus(ScheduleStatus.RUNNING.getValue());
        scheduleJob.setPreFireTime(new Date());
        scheduleJob.setFireCount(scheduleJob.getFireCount()+1);
        try{
        	scheduleJobService.update(scheduleJob);
        }catch (Exception e) {
			logger.error("修改任务状态失败",e);
			throw new RuntimeException(e);
		}
        //数据库保存执行记录
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setJobName(scheduleJob.getJobName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        Class<?> bean=null;
		try {
			bean = Class.forName(scheduleJob.getBeanName());
		} catch (ClassNotFoundException e) {
			
		}
		
        try {
        	if(null==bean){
    			throw new RuntimeException("找不到对应的类文件【"+scheduleJob.getBeanName()+"】");
    		}
            //执行任务
        	logger.debug("正在执行任务【jobId={},jobName={}】" , scheduleJob.getJobId(),scheduleJob.getJobName());
        	runJob();
        	long times = System.currentTimeMillis() - startTime;
			log.setUseTime((int)times);
        	//任务状态    0：成功    1：失败
			log.setStatus(0);
			logger.debug("任务执行成功【jobId={},jobName={}】  总共耗时：{}毫秒", scheduleJob.getJobId(),scheduleJob.getJobName(),times);
		} catch (Exception e) {
			long times = System.currentTimeMillis() - startTime;
			log.setUseTime((int)times);
			logger.error("任务执行失败【jobId={},jobName={}】  总共耗时：{}毫秒", scheduleJob.getJobId(),scheduleJob.getJobName(),times, e);
			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setMessage(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			//任务执行总时长
			log.setEndTime(new Date());
			scheduleJobLogService.insertNotNull(log);
			scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
			scheduleJob.setFireCount(scheduleJob.getFireCount()+1);
	        try{
	        	scheduleJobService.update(scheduleJob);
	        }catch (Exception e) {
				logger.error("修改任务状态失败",e);
			}
		}
    }
    
    
    
    private JobDataMap m_oDataMap = null;
    
    protected int getArgAsInt(String _sArgName)
    {
      return this.m_oDataMap.getInt(_sArgName);
    }
    
    protected String getArgAsString(String _sArgName)
    {
      return this.m_oDataMap.getString(_sArgName);
    }
    
    protected Object getArgAsObject(String _sArgName)
    {
      return this.m_oDataMap.get(_sArgName);
    }
    
    protected boolean containsArg(String _sArgName)
    {
      return this.m_oDataMap.containsKey(_sArgName);
    }
    
    protected void putArg(String _sArg, int _nValue)
    {
      this.m_oDataMap.put(_sArg, _nValue);
    }
    
    protected void putArg(String _sArg, String _sValue)
    {
      if (logger.isDebugEnabled())
      {
    	  logger.debug("_sArg:" + _sArg);
    	  logger.debug("_sValue:" + _sValue);
      }
      this.m_oDataMap.put(_sArg, _sValue);
    }
    
    protected void putArg(String _sArg, Object _oValue)
    {
      this.m_oDataMap.put(_sArg, _oValue);
    }
}
