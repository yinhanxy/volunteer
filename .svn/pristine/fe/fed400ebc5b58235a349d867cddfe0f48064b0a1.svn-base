package com.topstar.volunteer.service;

import com.topstar.volunteer.entity.Certification;

public interface CertificationService extends BaseService<Certification>{
	/**
	 * 批量删除志愿者证书
	 * @param volunteerCertKeys
	 * @return
	 */
	boolean batchDelVolunteerCert(long[] volunteerCertKeys);
	
	/**
	 * 审核通过新增的证书
	 */
	public int checkNewCert(Certification certification);
}
