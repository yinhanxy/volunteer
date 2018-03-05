package com.topstar.volunteer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.LoggerDao;
import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.model.LoggerView;
import com.topstar.volunteer.service.LoggerService;
@Service
public class LoggerServiceImpl extends BaseServiceImpl<Logger> implements LoggerService {
	
	@Autowired
	LoggerDao loggerDao;

	/**
	 * 根据编号查找日志信息
	 * @param id
	 * @return
	 */
	public Logger findById(long id) {
		return loggerDao.selectByKey(id);
	}
	
	public PageInfo<LoggerView> selectLogger(Logger logger,Date startTime,Date endTime,int pageIndex,int pageSize){
		PageInfo<Logger> pageInfo=loggerDao.selectLogger(logger,startTime,endTime,pageIndex,pageSize);
		List<LoggerView> loggerViews=new ArrayList<LoggerView>();
		List<Logger> list=pageInfo.getList();
		for (int i = 0; null!=list&& i < list.size(); i++) {
			loggerViews.add(new LoggerView(list.get(i)));
		}
		PageInfo<LoggerView> page=new PageInfo<>();
		page.setEndRow(pageInfo.getEndRow());
		page.setHasNextPage(pageInfo.isHasNextPage());
		page.setHasPreviousPage(pageInfo.isHasPreviousPage());
		page.setIsFirstPage(pageInfo.isIsFirstPage());
		page.setIsLastPage(pageInfo.isIsLastPage());
		page.setList(loggerViews);
		page.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
		page.setNavigateLastPage(pageInfo.getNavigateLastPage());
		page.setNavigatepageNums(pageInfo.getNavigatepageNums());
		page.setNavigatePages(pageInfo.getNavigatePages());
		page.setNextPage(pageInfo.getNextPage());
		page.setPageNum(pageInfo.getPageNum());
		page.setPages(pageInfo.getPages());
		page.setPageSize(pageInfo.getPageSize());
		page.setPrePage(pageInfo.getPrePage());
		page.setSize(page.getSize());
		page.setStartRow(pageInfo.getStartRow());
		page.setTotal(pageInfo.getTotal());
		return page;
	}

	@Override
	public LoggerView findLoggerViewById(long loggerId) {
		Logger logger=findById(loggerId);
		return new LoggerView(logger);
	}

	@Override
	public int delLoggersThree() {
		return loggerDao.delLoggersThree();
	}

}
