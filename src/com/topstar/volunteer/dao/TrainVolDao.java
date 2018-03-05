package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.TrainVol;

public interface TrainVolDao extends BaseDao<TrainVol>{
	
	/**
	 * 根据培训记录查找符合条件的志愿者列表
	 * @param trainId 
	 * @param orderBy 排序条件
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 志愿者的分页列表
	 */
	public PageInfo<TrainVol> findByEntity(Long trainId,String orderBy, int page, int rows);
	
	/**
	 * 通过培训记录Id得到志愿的编号集合
	 * @param id
	 * @return
	 */
	public List<Long> findVolIdsByTrainId(Long id);

}
