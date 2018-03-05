package com.topstar.volunteer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.BaseMapper;

public interface CertCheckMapper extends BaseMapper<CertCheck>{
	
	/**
	 * 根据证书ID查询对应的所有年度审核记录信息列表
	 * @param certId 证书ID
	 * @return
	 */
	public List<CertCheck> selectCertChecksByCertId(Long certId);
}