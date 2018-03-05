package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.entity.TrainVol;

public class TrainVolDaoTest extends BaseTest {

	@Autowired
	private TrainVolDao trainVolDao;
	
	@Test
	public void getAll(){
		String orderBy="tv.CR_TIME";
		Long trainId= 1l;
		int page=1;
		int rows=10;
		PageInfo<TrainVol> trRecordss= trainVolDao.findByEntity(trainId, orderBy, page, rows);
		List<TrainVol> trRecords=trRecordss.getList();
		for (TrainVol tr : trRecords) {
			System.out.println(tr.toString());
		}
	}
	
	@Test
	public void findVolIdsByTrainId(){
		Long id =1l;
		List<Long> list =trainVolDao.findVolIdsByTrainId(id);
		for (Long long1 : list) {
			System.out.println(long1);
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
