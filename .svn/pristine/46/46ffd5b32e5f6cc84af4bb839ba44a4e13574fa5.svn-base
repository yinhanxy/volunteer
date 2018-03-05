package com.topstar.volunteer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.dao.SiteDao;
import com.topstar.volunteer.entity.Site;
import com.topstar.volunteer.service.SiteService;

@Service
public class SiteServiceImpl  extends BaseServiceImpl<Site> implements SiteService{

	@Autowired
	private SiteDao siteDao;
	
	@Override
	public List<Site> getAllSite() {
		return siteDao.selectAllSite();
	}
	
}
