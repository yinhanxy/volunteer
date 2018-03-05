package com.topstar.volunteer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ScheduleJobDao;
import com.topstar.volunteer.entity.ScheduleJob;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.schedule.ScheduleStatus;
import com.topstar.volunteer.schedule.ScheduleUtils;
import com.topstar.volunteer.service.ScheduleJobService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

import tk.mybatis.mapper.entity.Example;


@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJob> implements ScheduleJobService {
	
	
	@Autowired
    private Scheduler scheduler;

	@Autowired
	private ScheduleJobDao scheduleJobDao;
	
	/**
	 * 项目启动时，初始化定时器
	 * @throws Exception 
	 */
	@PostConstruct
	public void init() throws Exception{
		List<ScheduleJob> scheduleJobList = queryAll();
		for(ScheduleJob scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			List<Long> list=new ArrayList<Long>(); 
			if(scheduleJob.getStatus()==ScheduleStatus.RUNNING.getValue()){
				scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
				list.add(scheduleJob.getJobId());
			}
			if(list.size()>0){
				Object [] objs=list.toArray();
				Long [] jobIds =new Long[list.size()];
				System.arraycopy(objs, 0, jobIds, 0, list.size());
				updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
			}
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}
	
	@Override
	public  boolean exitJobName(String jobName) throws TPSException {
		return scheduleJobDao.exitJobName(jobName);
	}
	
	@Override
	public ScheduleJob findById(Long jobId) {
		return scheduleJobDao.selectByKey(jobId);
	}
	

	@Override
	public List<ScheduleJob> queryAll() {
		Example example=new Example(ScheduleJob.class);
		return scheduleJobDao.selectByExample(example);
	}

	@Override
	public void save(ScheduleJob scheduleJob) throws Exception {
		scheduleJob.setJobId(null);
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
		BaseUser user=ShiroSessionMgr.getLoginUser();
		scheduleJob.setCreateUser(user.getUserName());
		scheduleJob.setFireCount(0L);
        scheduleJobDao.insertNotNull(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	public void update(ScheduleJob scheduleJob) throws Exception {
        scheduleJobDao.updateNotNull(scheduleJob);
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
    }

	@Override 
    public void deleteBatch(Long[] jobIds) throws Exception {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	//删除数据
    	scheduleJobDao.deleteByKeys(jobIds);
	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", jobIds);
    	map.put("status", status);
    	if(jobIds.length>0){
    		return scheduleJobDao.updateBatch(map);
    	}
    	return 0;
    }
    
	@Override
    public void run(Long[] jobIds) throws Exception {
		List<Long> jobs=new ArrayList<Long>();
    	for(Long jobId : jobIds){
    		ScheduleJob scheduleJob=findById(jobId);
    		if(scheduleJob.getStatus().intValue()==ScheduleStatus.RUNNING.getValue()){
    			continue;
    		}
    		ScheduleUtils.run(scheduler,scheduleJob);
    		jobs.add(jobId);
    	}
    	Long [] ids=new Long[jobs.size()];
    	ids=jobs.toArray(new Long[jobs.size()]);
    	if(ids.length>0){
    		updateBatch(ids, ScheduleStatus.RUNNING.getValue());
    	}
    }

	@Override
    public void pause(Long[] jobIds) throws Exception {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
    	updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

	@Override
    public void resume(Long[] jobIds) throws Exception {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}
    	updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }

	@Override
	public PageInfo<ScheduleJob> queryList(ScheduleJob job, int pageSize, int pageIndex) throws Exception {
		Example example=new Example(ScheduleJob.class);
		Example.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(job.getJobName())){
	        	criteria.andLike("jobName", "%"+job.getJobName()+"%");
	    }
		return scheduleJobDao.selectByExample(example, pageIndex, pageSize);
	}    
}
