package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.Statistics;

public interface SerTeamService extends BaseService<SerTeam>{
	
	/**
	 * 根据服务队实体字段查找符合条件的服务队列表
	 * @param serTeam 查询的角色实体条件
	 * @param orderBy
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 
	 */
	public PageInfo<SerTeam> findByEntity(SerTeam serTeam,String orderBy, int page, int rows);
	
	/**
	 * 添加服务队
	 * @param serTeam
	 * @return
	 */
	public boolean addSerTeam(SerTeam serTeam);
	
	/**
	 * 根据指定条件检查指定的服务队名称是否已存在
	 * @param name 需要检查的服务队名称
	 * @param excludeKey 需要排除在外的主键值对应的记录
	 * @return 0：不存在  1：存在
	 */
	public int existsWithSerTeamName(String name ,String excludeKey);
	
	/**
	 * 根据服务队keys批量删除服务队
	 * @param serTeamKeys 服务队的主键值数组
	 * @return 
	 */
	public boolean deleteSerTeamKeys(long[] serTeamKeys);
	
	/**
	 * 修改服务队信息
	 * @param serTeam
	 * @return
	 */
	public boolean updateSerTeam(SerTeam serTeam) throws TPSException;
	
	/**
	 * 判断当前用户是否有操作服务队的权限
	 * @param serTeam
	 * @return
	 */
	public boolean judgeAuthority(SerTeam serTeam)throws TPSException;
	
	/**
	 * 根据当前用户的组织机构Id获取到服务队列表
	 * @param orgId
	 * @return
	 */
	public List<SerTeam> getSerTeamName(Long orgId);
	
	/**
	 * 服务队统计信息
	 * @return
	 */
	public List<Statistics> stsServiceShow();
	
	/**
	 * 返回所有市级服务队数量信息
	 * 如:[["武汉市","8"],["襄阳市","9"]]
	 * @return
	 */
	public List<Statistics> returnSerTeamNum();
	
	/**
	 * 得到服务队的名称及其对应时长
	 * @param statistics
	 * @return
	 */
	public List<Statistics> getNameAndHour();
	
	/**
	 * 根据机构Id查询该机构下是否有服务队(一个机构只有一个服务队)
	 * @param orgId
	 * @return
	 */
	public boolean judgeSerByOrg(Long orgId);
}
