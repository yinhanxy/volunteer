package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.TrRecord;

public class TrRecordDaoTest extends BaseTest {

	@Autowired
	private TrRecordDao trRecordDao;
	
	@Test
	public void getAll(){
		TrRecord trRecord= new TrRecord();
//		trRecord.setTrName("书架整理培训");
//		trRecord.setPrincipal("李四");
//		trRecord.setTeamId(3l);
//		trRecord.setStatus(2);
		String orderBy="tr.CR_TIME";
		int page=1;
		int rows=10;
		PageInfo<TrRecord> trRecordss= trRecordDao.findByEntity(trRecord, orderBy, page, rows);
		List<TrRecord> trRecords=trRecordss.getList();
		for (TrRecord tr : trRecords) {
			System.out.println(tr.toString());
		}
	}
	
//	@Test
//	public void addArea(){
//		Area area=new Area();
//		area.setName("黄冈市");
//		area.setCode("123456");
//		area.setParentId(1l);
//		area.setType(1);
//		area.setRemark("123");
//		int result=trRecord.addArea(area);
//		System.out.println(result);
//	}
}
