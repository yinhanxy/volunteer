package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.CertCheckDao;
import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.mapper.CertCheckMapper;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class CertCheckDaoImpl extends BaseDaoImpl<CertCheck> implements CertCheckDao{

	@Autowired
	private CertCheckMapper certCheckMapper;
	
	public PageInfo<CertCheck> findCertChecksByCertId(Long certId,String orderBy, int page, int rows){
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<CertCheck> certChecks = certCheckMapper.selectCertChecksByCertId(certId);
		PageInfo<CertCheck> pageCertCheck = new PageInfo<CertCheck>(certChecks);
		return pageCertCheck ;
	}
	
}
