package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.ScheduleJob;

/**
 * 计划调度任务服务层
 * 
 */
public interface ScheduleJobService {

	/**
	 * 根据ID，查询定时任务
	 * @param jobId 定时任务ID
	 * @return 返回定时任务对象
	 * @throws Exception
	 */
	public ScheduleJob findById(Long jobId) throws Exception;
	
	
	/**
	 * 查询定时任务名称是否存在
	 * @param jobName 定时任务名称
	 * @return 存在返回true,否则返回false
	 * @throws Exception
	 */
	public boolean exitJobName(String jobName) throws Exception;
	
	/**
	 * 查询定时任务
	 * @param job 定时任务
	 * @param pageSize 每页数据条数
	 * @param pageNum 当前页码
	 * @return
	 * @throws Exception
	 */
	public PageInfo<ScheduleJob> queryList(ScheduleJob job,int pageSize,int pageIndex) throws Exception;
	
	/**
	 * 获取所有任务列表
	 */
	public List<ScheduleJob> queryAll() throws Exception;

	
	/**
	 * 保存定时任务
	 */
	public void save(ScheduleJob scheduleJob) throws Exception;
	
	/**
	 * 更新定时任务
	 */
	public void update(ScheduleJob scheduleJob) throws Exception;
	
	/**
	 * 批量删除定时任务
	 */
	public void deleteBatch(Long[] jobIds) throws Exception;
	
	/**
	 * 批量更新定时任务状态
	 */
	public int updateBatch(Long[] jobIds, int status) throws Exception;
	
	/**
	 * 立即执行
	 */
	public void run(Long[] jobIds) throws Exception;
	
	/**
	 * 暂停运行
	 */
	public void pause(Long[] jobIds) throws Exception;
	
	/**
	 * 恢复运行
	 */
	public void resume(Long[] jobIds) throws Exception;
	
}
