package com.topstar.volunteer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.model.Statistics;

public interface TrRecordDao extends BaseDao<TrRecord>{
	
	/**
	 * 根据培训记录实体字段查找符合条件的培训记录列表
	 * @param trRecord 培训记录实体
	 * @param orderBy 排序条件
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 培训记录的分页列表
	 */
	public PageInfo<TrRecord> findByEntity(TrRecord trRecord,String orderBy, int page, int rows);
	
	/**
	 * 根据志愿者编号分页查询该志愿者参加的培训记录
	 * @param volunteerId
	 * @return
	 */
	public PageInfo<TrRecord> findTrRecordsByVolunteerId(Long volunteerId,String orderBy,  int pageIndex, int pageSize);
	
	/**
	 * 得到每年的培训数量
	 * 如："2017","4"
	 * @param statistics
	 * @return
	 */
	public PageInfo<Statistics> selTrRecordByYear(Statistics statistics,String orderBy,  int pageIndex, int pageSize);
	
	/**
	 * 查询培训统计信息,如：(培训名称,参加培训人数,培训时间)
	 * @param trRecord
	 * @return
	 */
	public PageInfo<TrRecord> getTrRecordStatis(TrRecord trRecord,String orderBy,  int pageIndex, int pageSize);
}
