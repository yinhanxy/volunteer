package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.TrainVol;
import com.topstar.volunteer.util.BaseMapper;

public interface TrainVolMapper extends BaseMapper<TrainVol> {
	
	/**
	 * 根据培训记录Id查询
	 * @param trainId 培训记录Id
	 * @return 志愿者信息列表
	 */
	List<TrainVol> findByTrainId(@Param("trainId")Long trainId);
	
	/**
	 * 查询志愿者拥有的服务队编号
	 * @param trainId 培训记录编号
	 * @return  志愿者编号集合
	 */
	public List<Long> findVolIdsByTrainId(@Param("trainId")Long trainId);
}