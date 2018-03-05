package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.CheckTime;

public interface CheckTimeDao extends BaseDao<CheckTime>{

	/**
	 * 分页查询指定条件下的证书年检时间配置信息列表
	 * @param checkTime 查询的过滤条件
	 * @param page 查询的页码
	 * @param rows 每页显示数目
	 * @return
	 */
	public PageInfo<CheckTime> findByEntity(CheckTime checkTime, int page, int rows);

	 /**
	  * 查询本年份是否允许证书年检操作
	  * @param certId 
	  * @return 如果允许则返回本年份,否则，返回null
	  */
	public List<String> allowCertYearCheck(String certId);
}
