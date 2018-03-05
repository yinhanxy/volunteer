package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.entity.TrainVol;
import com.topstar.volunteer.entity.Volunteer;

public interface TrainVolService extends BaseService<TrainVol>{
	
	/**
	 * 根据培训记录编号查找符合条件的志愿者列表
	 * @param trainId 培训记录编号
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 志愿者的分页列表
	 */
	public PageInfo<TrainVol> findByEntity(Long trainId, int page, int rows);
	
	/**
	 * 得到志愿者信息列表
	 * @param vol
	 * @param trainId
	 * @param page
	 * @param rows
	 * @return 返回所有志愿者信息,在服务队中已有的志愿者带有trainIdList
	 */
	public PageInfo<Volunteer> getTrainVols(Volunteer vol,Long trainId , int page, int rows);
	
	/**
	 * 将志愿者添加到培训记录中
	 * @param trainId 
	 * @param volIds
	 * @return
	 */
	public boolean addVolsWithTrainId(Long trainId,List<Long> volIds);
	
	/**
	 * 根据培训记录keys批量删除
	 * @param trRecordKeys 培训记录的主键值数组
	 * @return 
	 */
	public boolean deleteTrainVolKeys(long[] trainVolKeys);
	
}
