package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.model.Statistics;

public class TrRecordMapperTest  extends BaseTest{

	@Autowired
	private TrRecordMapper trRecordMapper;
	
	/**
	 * 得到培训记录列表信息
	 */
	@Test
	public void findByEntity(){
		TrRecord trRecord =new TrRecord();
//		trRecord.setCurOrgId(2l);
		List<TrRecord> trRecords = trRecordMapper.findByEntity(trRecord);
		for (TrRecord trRecord2 : trRecords) {
			System.out.println(trRecord2.toString());
		}
		
	}
	
	@Test
	public void selTrRecordAndYear(){
		Statistics statistics=new Statistics();
		List<Statistics> list=trRecordMapper.selTrRecordByYear(statistics);
		for (Statistics statistics2 : list) {
			System.out.println(statistics2.getYear()+","+statistics2.getRecordNum());
		}
	}
	
	@Test
	public void getTrRecordStatis(){
		TrRecord trRecord=new TrRecord();
		List<TrRecord> trRecords = trRecordMapper.getTrRecordStatis(trRecord);
		for (TrRecord trRecord2 : trRecords) {
			System.out.println(trRecord2.getTrName()+","+trRecord2.getTrainVolNum()+","+trRecord2.getTrTime());
		}
	}
}
