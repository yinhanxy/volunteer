package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.TrainVol;

public class TrainVolMapperTest  extends BaseTest{

	@Autowired
	private TrainVolMapper trainVolMapper;
	
	/**
	 * 根据培训记录Id得到志愿者信息列表
	 */
	@Test
	public void findByTrainId(){
		Long trainId=1l;
		List<TrainVol> trainVols = trainVolMapper.findByTrainId(trainId);
		for (TrainVol trainVol : trainVols) {
			System.out.println(trainVol.toString());
		}
		
	}
	
	/**
	 * 根据培训记录Id得到志愿者Id列表
	 */
	@Test
	public void findVolIdsByTrainId(){
		Long trainId=1l;
		List<Long> volIds = trainVolMapper.findVolIdsByTrainId(trainId);
		for (Long volId : volIds) {
			System.out.println(volId);
		}
	}
}
