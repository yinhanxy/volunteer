package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.SiteDao;
import com.topstar.volunteer.entity.Site;
import com.topstar.volunteer.mapper.SiteMapper;

@Repository
public class SiteDaoImpl extends BaseDaoImpl<Site> implements SiteDao {
	
	@Autowired
	private SiteMapper siteMapper;
	
	@Override
	public List<Site> selectAllSite() {
		List<Site> sites=siteMapper.findAllSite();
		if(sites!=null && sites.size()>0){
			return sites;
		}
		return null;
	}

}
