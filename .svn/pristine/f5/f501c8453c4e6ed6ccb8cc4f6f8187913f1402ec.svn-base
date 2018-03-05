package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.util.BaseMapper;

public interface TrRecordMapper extends BaseMapper<TrRecord> {
	
	List<TrRecord> findByEntity(@Param("trRecord")TrRecord trRecord);

	/**
	 * 根据志愿者编号,查询该志愿者参加过的培训记录
	 * @param volunteerId
	 * @return
	 */
	List<TrRecord> findTrRecordsByVolunteerId(@Param("volunteerId")Long volunteerId);
	
	/**
	 * 得到每年的培训数量
	 * 如："2017","4"
	 * @param statistics
	 * @return
	 */
	List<Statistics> selTrRecordByYear(@Param("statistics")Statistics statistics);
	
	/**
	 * 查询培训统计信息,如：(培训名称,参加培训人数,培训时间)
	 * @param trRecord
	 * @return
	 */
	List<TrRecord> getTrRecordStatis(@Param("trRecord")TrRecord trRecord);
}