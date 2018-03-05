package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.CheckTimeDao;
import com.topstar.volunteer.entity.CheckTime;
import com.topstar.volunteer.mapper.CheckTimeMapper;

@Repository
public class CheckTimeDaoImpl extends BaseDaoImpl<CheckTime> implements CheckTimeDao {

	@Autowired
	private CheckTimeMapper checkTimeMapper;
	
	@Override
	public PageInfo<CheckTime> findByEntity(CheckTime checkTime, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<CheckTime> checkTimeList=checkTimeMapper.findByEntity(checkTime);
		PageInfo<CheckTime> checkTimes=new PageInfo<CheckTime>(checkTimeList);
		return checkTimes;
	}

	@Override
	public List<String> allowCertYearCheck(String certId) {
		List<String> checkYears=checkTimeMapper.allowCertYearCheck(certId);
		if(checkYears!=null && !checkYears.isEmpty()){
			return checkYears;
		}
		return null;
	}

}
