package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.SMSDao;
import com.topstar.volunteer.entity.SMS;
import com.topstar.volunteer.mapper.SMSMapper;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class SMSDaoImpl extends BaseDaoImpl<SMS> implements SMSDao{

	@Autowired
	private SMSMapper mapper;
	
	@Override
	public PageInfo<SMS> getSMSLists(SMS sms, String orderBy, int page, int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<SMS> list=mapper.getSMSLists(sms);
		PageInfo<SMS> pageInfo = new PageInfo<SMS>(list);
		return pageInfo;
	}
	
}
