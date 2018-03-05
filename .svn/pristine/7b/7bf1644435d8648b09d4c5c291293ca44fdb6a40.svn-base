package com.topstar.volunteer.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ScheduleJobLogDao;
import com.topstar.volunteer.entity.ScheduleJobLog;
import com.topstar.volunteer.service.ScheduleJobLogService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLog> implements ScheduleJobLogService  {

	@Autowired
	ScheduleJobLogDao scheduleJobLogDao;
	
	@Override
	public PageInfo<ScheduleJobLog> queryList(ScheduleJobLog jobLog, int pageSize, int pageIndex,Date beginTime,Date endTime) throws Exception {
		Example example=new Example(ScheduleJobLog.class);
		Example.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(jobLog.getJobName())){
	        criteria.andLike("jobName", "%"+jobLog.getJobName()+"%");
	    }
		if(jobLog!=null&&jobLog.getJobId()>0){
			criteria.andEqualTo("jobId", jobLog.getJobId());
		}
		if(StringUtils.isNotBlank(jobLog.getBeanName())){
			criteria.andLike("jobName", "%"+jobLog.getJobName()+"%");
		}
		if(beginTime==null){
			Calendar calendar=Calendar.getInstance();
			calendar.set(Calendar.YEAR, -10);
			beginTime=calendar.getTime();
		}
		if(endTime==null)
			endTime=new Date();
		criteria.andBetween("createTime", beginTime, endTime);
		if(jobLog.getStatus()!=null&&(jobLog.getStatus()==0||jobLog.getStatus()==1)){
			criteria.andEqualTo("status", jobLog.getStatus());
		}
		return scheduleJobLogDao.selectByExample(example,"CREATE_TIME DESC" ,pageIndex, pageSize);
	}

}
