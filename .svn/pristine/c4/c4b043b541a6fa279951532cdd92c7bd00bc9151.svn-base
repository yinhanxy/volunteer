package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.Statistics;

public interface TrRecordService extends BaseService<TrRecord>{
	
	/**
	 * 根据培训记录实体字段查找符合条件的培训记录列表
	 * @param trRecord 培训记录实体
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 培训记录的分页列表
	 */
	public PageInfo<TrRecord> findByEntity(TrRecord trRecord, int page, int rows);
	
	/**
	 * 添加培训记录
	 * @param trRecord
	 * @return
	 */
	public boolean addTrRecord(TrRecord trRecord);
	
	/**
	 * 根据指定条件检查指定的名称是否已存在
	 * @param name 需要检查的名称
	 * @param excludeKey 需要排除在外的主键值对应的记录
	 * @return 0：不存在  1：存在
	 */
	public int existsWithTrRecordName(String name ,Long teamId, String excludeKey);
	
	/**
	 * 根据培训记录keys批量删除
	 * @param trRecordKeys 培训记录的主键值数组
	 * @return 
	 */
	public boolean deleteTrRecordKeys(long[] trRecordKeys);
	
	/**
	 * 修改培训记录信息
	 * @param trRecord
	 * @return
	 */
	public boolean updateTrRecord(TrRecord trRecord) throws TPSException;
	
	/**
	 * 根据志愿者编号voId分页获取参加的培训记录
	 * @param volunteerId 志愿者编号
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 培训记录的分页列表
	 */
	public PageInfo<TrRecord> getTrRecordsByVolunteerId(Long volunteerId,String orderBy, int page, int rows);
	
	/**
	 * 得到每年的培训数量
	 * 如："2017","4"
	 * @param statistics
	 * @return
	 */
	public PageInfo<Statistics> selTrRecordByYear(String orderBy, int page, int rows);
	
	/**
	 * 查询培训统计信息,如：(培训名称,参加培训人数,培训时间)
	 * @param trRecord
	 * @return
	 */
	public PageInfo<TrRecord> getTrRecordStatis(String orderBy, int page, int rows);
}
