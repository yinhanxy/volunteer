package com.topstar.volunteer.mapper;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Certification;
import com.topstar.volunteer.util.BaseMapper;

public interface CertificationMapper extends BaseMapper<Certification> {

	/**
	 * 删除指定志愿者的证书信息
	 * @param volunteerCertKeys
	 * @return
	 */
	int updateCertsByVolunteerIds(@Param("volunteerIds") long[] volunteerCertKeys);
	
}