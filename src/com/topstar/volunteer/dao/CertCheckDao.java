package com.topstar.volunteer.dao;


import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.CertCheck;

public interface CertCheckDao extends BaseDao<CertCheck>{
	
	/**
	 * 根据证书ID查询对应的所有年度审核记录信息列表
	 * @param certId 证书ID
	 * @param orderBy 排序规则
	 * @param page 页码
	 * @param rows 每页显示记录数
	 * @return
	 */
	public PageInfo<CertCheck> findCertChecksByCertId(Long certId,String orderBy, int page, int rows);
}
