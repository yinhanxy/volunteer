package com.topstar.volunteer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.CertificationDao;
import com.topstar.volunteer.entity.Certification;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.mapper.CertificationMapper;
import com.topstar.volunteer.mapper.VolunteerCertificationMapper;
import com.topstar.volunteer.mapper.VolunteerMapper;
import com.topstar.volunteer.model.VolunteerView;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class CertificationDaoImpl extends BaseDaoImpl<Certification> implements CertificationDao{

	@Autowired
	private CertificationMapper certificationMapper;

	@Override
	public int updateVolunteerCertByVolunteerIds(long[] volunteerCertKeys) {
		return certificationMapper.updateCertsByVolunteerIds(volunteerCertKeys);
	}
	
}
