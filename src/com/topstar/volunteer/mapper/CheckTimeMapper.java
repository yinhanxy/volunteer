package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.CheckTime;
import com.topstar.volunteer.util.BaseMapper;

public interface CheckTimeMapper extends BaseMapper<CheckTime> {
	
	/**
	 * 根据条件查询年检时间管理列表信息
	 * @param checkTime
	 * @return
	 */
	public List<CheckTime> findByEntity(@Param("checkTime")CheckTime checkTime);
	
	/**
	 * 查询本年份是否允许证书年检操作
	 * @param certId 
	 * @return
	 */
	List<String> allowCertYearCheck(@Param("certId")String certId);
	
}