package com.topstar.volunteer.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Certification;

public class CertificationDaoTest extends BaseTest {

	@Autowired
	private CertificationDao certificationDao;
	
	@Test
	public void selectCertification(){
		Certification certification=certificationDao.selectByKey(1l);
		System.out.println(certification);
	}
}
