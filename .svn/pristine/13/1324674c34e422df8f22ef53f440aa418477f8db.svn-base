package com.topstar.volunteer.service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.CertCheck;

public interface CertCheckService extends BaseService<CertCheck>{
	
	/**
	 * 根据证书ID分页查询对应的所有年度审核记录信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<CertCheck> getCertChecksByCertId(Long certId,String orderBy, int pageIndex, int pageSize);
}
