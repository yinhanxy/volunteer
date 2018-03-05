package com.topstar.volunteer.mapper;

import java.util.List;

import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.util.BaseMapper;

public interface LoggerMapper extends BaseMapper<Logger> {
	
	/**
	 * 查询3个月前的数据
	 * @return
	 */
	List<Logger> getLoggersThree();
	
	/**
	 * 删除3个月前的数据
	 * @return
	 */
	int delLoggersThree();
}