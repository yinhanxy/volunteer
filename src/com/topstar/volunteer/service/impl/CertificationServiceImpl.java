package com.topstar.volunteer.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.dao.CertCheckDao;
import com.topstar.volunteer.dao.CertificationDao;
import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.entity.Certification;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.CertificationService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;

@Service
public class CertificationServiceImpl extends BaseServiceImpl<Certification> implements CertificationService {
	
	@Autowired
	private CertificationDao certificationDao;
	@Autowired
	private CertCheckDao certCheckDao;
	
	@Override
	public boolean batchDelVolunteerCert(long[] volunteerCertKeys) {
		if(volunteerCertKeys!=null && volunteerCertKeys.length>0){
			int size=volunteerCertKeys.length;
			int result=certificationDao.updateVolunteerCertByVolunteerIds(volunteerCertKeys);
			if(size==result){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 添加证书后直接审核通过
	 */
	public int checkNewCert(Certification certification){
		CertCheck certCheck = new CertCheck();
		int addCertCheck=0;
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		certCheck.setOpertUser(crtUser.getUserName());
		Date date = new Date();
		certCheck.setOpertTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		certCheck.setCheckYear(sdf.format(date)); 
		certCheck.setCheckState("01");
		certCheck.setCertId(certification.getId());
		try {
			int addCertification=certificationDao.insert(certification);
			certCheck.setCertId(certification.getId());
			List<ValidateMessage> error =ValidatorUtil.validate(certCheck, Groups.Add.class);
			if(null!=error&&error.size()>0){
				return addCertCheck;
			}
			addCertCheck=certCheckDao.insert(certCheck);
			return addCertCheck+addCertification;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addCertCheck;
	}
}
