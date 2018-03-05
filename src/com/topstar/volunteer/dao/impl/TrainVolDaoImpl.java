package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.TrainVolDao;
import com.topstar.volunteer.entity.TrainVol;
import com.topstar.volunteer.mapper.TrainVolMapper;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class TrainVolDaoImpl extends BaseDaoImpl<TrainVol> implements TrainVolDao{

	@Autowired
	private TrainVolMapper trainVolMapper;
	
	@Override
	public PageInfo<TrainVol> findByEntity(Long trainId, String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<TrainVol> TrainVols= trainVolMapper.findByTrainId(trainId);
		PageInfo<TrainVol> page = new PageInfo<TrainVol>(TrainVols);
		return page;
	}

	@Override
	public List<Long> findVolIdsByTrainId(Long id) {
		if(id == null || id.longValue() < 1){
			return null;
		}
		List<Long> vols=trainVolMapper.findVolIdsByTrainId(id);
		if(vols!=null && vols.size()>0){
			return vols;
		}
		return null;
	}

}

