package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.SMS;
import com.topstar.volunteer.util.BaseMapper;

public interface SMSMapper extends BaseMapper<SMS> {
	
	/**
	 * 得到短信验证码列表信息
	 * @param sms
	 * @return
	 */
	List<SMS> getSMSLists(@Param("sms")SMS sms);
}