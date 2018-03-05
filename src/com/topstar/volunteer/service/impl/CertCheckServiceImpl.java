package com.topstar.volunteer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.CertCheckDao;
import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.service.CertCheckService;
@Service
public class CertCheckServiceImpl extends BaseServiceImpl<CertCheck> implements CertCheckService {
	
	@Autowired
	private CertCheckDao certCheckDao;
	
	/**
	 * 根据证书ID分页查询对应的所有年度审核记录信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<CertCheck> getCertChecksByCertId(Long certId,String orderBy, int pageIndex, int pageSize){
		return certCheckDao.findCertChecksByCertId(certId, orderBy, pageIndex, pageSize);
	}
	
}
