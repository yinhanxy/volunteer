package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.CheckTime;

/**
 * 志愿者证书年检时间管理业务层接口
 * @author TRS
 *
 */
public interface CheckTimeService extends BaseService<CheckTime>{
	
	
	/**
	 * 分页查询指定条件下的证书年检时间配置信息列表
	 * @param checkTime 查询的过滤条件
	 * @param page 查询的页码
	 * @param pageSize 每页显示数目
	 * @return
	 */
	public PageInfo<CheckTime> loadCheckTimePage(CheckTime checkTime,int page,int pageSize);
	
	/**
	 * 查找指定的年检时间年份是否已存在
	 * @param checkYear 年检年份
	 * @param excludeKey
	 * @return
	 */
	public int existsWithCheckYear(String checkYear, String excludeKey);
	
	/**
	 * 批量删除年度年检时间信息
	 * @param configIds
	 * @return
	 */
	public boolean deleteCheckTime(Long[] checkTimeIds);
	
	/**
	  * 查询是否允许证书年检操作
	  * @param certId 
	  * @return 如果允许则返回本年份,否则，返回null
	  */
	public String allowCertYearCheck(String certId);
	
}
