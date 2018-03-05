package com.topstar.volunteer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.SMSDao;
import com.topstar.volunteer.entity.SMS;
import com.topstar.volunteer.service.SMSService;

@Service
public class SMSServiceImpl extends BaseServiceImpl<SMS> implements SMSService{

	@Autowired
	private SMSDao smsDao;
	
	@Override
	public PageInfo<SMS> getSMSLists(SMS sms, int page, int rows) {
		String orderBy="SEND_TIME desc";
		return smsDao.getSMSLists(sms, orderBy, page, rows);
	}

}
