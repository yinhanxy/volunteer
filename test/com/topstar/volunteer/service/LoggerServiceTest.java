package com.topstar.volunteer.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.model.LoggerView;

public class LoggerServiceTest extends BaseTest{
	
	@Autowired
	LoggerService loggerService;
	
	@Test
	public void selectLoggerTest(){
		Logger log=new Logger();
		log.setLoggerType(4);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		Date startTime=calendar.getTime();
		calendar.add(Calendar.HOUR, 2);
		Date endTime=calendar.getTime();
		PageInfo<LoggerView> page=loggerService.selectLogger(log,startTime,endTime, 1, 3);
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
		List<LoggerView> list= page.getList();
		for (int i = 0; i < list.size(); i++) {
			LoggerView logger=list.get(i);
			System.out.println(logger.toString());
		}
	}
}
