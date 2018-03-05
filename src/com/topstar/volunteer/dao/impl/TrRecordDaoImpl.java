package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.TrRecordDao;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.mapper.TrRecordMapper;
import com.topstar.volunteer.model.Statistics;

@Repository
public class TrRecordDaoImpl extends BaseDaoImpl<TrRecord> implements TrRecordDao{

	@Autowired
	private TrRecordMapper trRecordMapper;
	
	@Override
	public PageInfo<TrRecord> findByEntity(TrRecord trRecord,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<TrRecord> trRecords= trRecordMapper.findByEntity(trRecord);
		PageInfo<TrRecord> page = new PageInfo<TrRecord>(trRecords);
		return page;
	}

	@Override
	public PageInfo<TrRecord> findTrRecordsByVolunteerId(Long volunteerId, String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<TrRecord> trRecords= trRecordMapper.findTrRecordsByVolunteerId(volunteerId);
		if(trRecords!=null && trRecords.size()>0){
			PageInfo<TrRecord> page = new PageInfo<TrRecord>(trRecords);
			return page;
		}
		return null;
	}

	@Override
	public PageInfo<Statistics> selTrRecordByYear(Statistics statistics, String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Statistics> trRecords= trRecordMapper.selTrRecordByYear(statistics);
		if(trRecords!=null && trRecords.size()>0){
			PageInfo<Statistics> page = new PageInfo<Statistics>(trRecords);
			return page;
		}
		return null;
	}

	@Override
	public PageInfo<TrRecord> getTrRecordStatis(TrRecord trRecord, String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<TrRecord> trRecords= trRecordMapper.getTrRecordStatis(trRecord);
		if(trRecords!=null && trRecords.size()>0){
			PageInfo<TrRecord> page = new PageInfo<TrRecord>(trRecords);
			return page;
		}
		return null;
	}

}

