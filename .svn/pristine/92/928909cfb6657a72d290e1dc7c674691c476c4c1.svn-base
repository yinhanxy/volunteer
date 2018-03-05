package com.topstar.volunteer.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.dao.CheckTimeDao;
import com.topstar.volunteer.dao.ConfigDao;
import com.topstar.volunteer.entity.CheckTime;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.service.CheckTimeService;
import com.topstar.volunteer.service.ConfigService;

@Service("checkTimeService")
public class CheckTimeServiceImpl  extends BaseServiceImpl<CheckTime> implements CheckTimeService{

	@Autowired
	private CheckTimeDao checkTimeDao;

	@Override
	public PageInfo<CheckTime> loadCheckTimePage(CheckTime checkTime, int page, int pageSize) {
		return checkTimeDao.findByEntity(checkTime, page, pageSize);
	}

	
	
	@Override
	public int existsWithCheckYear(String checkYear, String excludeKey) {
		if(checkYear!=null){
			Example example=new Example(CheckTime.class); 
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("year", checkYear);
			if (StringUtils.isNotEmpty(excludeKey)) {
				criteria.andNotEqualTo("id", excludeKey);
			}
			List<CheckTime> checkTime=selectByExample(example);
			if(checkTime!=null && checkTime.size()!=0){
				return 1;
			}
		}
		return 0;
	}
	
	public  boolean deleteCheckTime(Long[] checkTimeIds){
		if(checkTimeIds!=null && checkTimeIds.length>0){
			int rows=checkTimeIds.length;
			int delCheckTimeRow=checkTimeDao.deleteByKeys(checkTimeIds);
			if(delCheckTimeRow==rows){
				return true;
			}
		}
		return false;
	}



	@Override
	public String allowCertYearCheck(String certId) {
		List<String> checkYears=checkTimeDao.allowCertYearCheck(certId);
		if(checkYears!=null){
			return checkYears.get(0);
		}
		return null;
	}
	
}
