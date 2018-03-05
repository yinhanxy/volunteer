package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.model.Statistics;

public interface SerTeamDao extends BaseDao<SerTeam>{
	
	/**
	 * 根据服务队实体字段查找符合条件的服务队列表
	 * @param serteam 服务队实体
	 * @param orderBy 排序条件
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 服务队的分页列表
	 */
	public PageInfo<SerTeam> findByEntity(SerTeam serteam,String orderBy, int page, int rows);
	
	/**
	 * 根据当前用户的组织机构Id获取到服务队列表
	 * @param orgId
	 * @return
	 */
	public List<SerTeam> getSerTeamName(Long orgId);
	
	/**
	 * 得到各区域的服务队数量
	 * 如:[["洪山区","5"],["青山区","3"]]
	 * @param statistics
	 * @return
	 */
	public List<Statistics> getSerByArea(Statistics statistics);
	
	/**
	 * 得到服务队的名称及其对应时长
	 * @param statistics
	 * @return
	 */
	public List<Statistics> getNameAndHour(Statistics statistics);
	
	/**
	 * 根据机构Id查询该机构的服务队数量(一个机构只有一个服务队)
	 * @param orgId
	 * @return
	 */
	public int judgeSerByOrg(Long orgId);
}
