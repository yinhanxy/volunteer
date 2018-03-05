package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;

public interface VolunteerDao extends BaseDao<Volunteer>{
	
	/**
	 * 根据志愿者查询条件分页查询志愿者信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<VolunteerView> findByEntity(Volunteer volunteer,String orderBy, int page, int rows);
	
	/**
	 * 根据志愿者查询条件分页查询志愿者审核信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<VolunteerView> findVolunteerCheckByEntity(Volunteer volunteer,String orderBy, int page, int rows);

	public int setVolunteerPassword(long[] ids,String newEncodePwd);
	
	/**
	 * 通过idCard查询得到志愿者Id
	 * @param idCard
	 * @return
	 */
	public Long selectVolId(String idCard);
	
	/**
	 * 根据志愿者Id修改退队管理状态
	 * @param volId
	 * @param retreatTeamStatus
	 * @return
	 */
	public int editRetreatTeamStatus(Long volId, Long retreatTeamStatus);
	
	/**
	 * 志愿者统计页面信息
	 * @return
	 */
	public List<Statistics> statisticsShow(Statistics statistics);
	
	/**
	 * 根据机构trainId得到该机构下的志愿者信息列表
	 * @param trainId
	 * @param volunteer
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<Volunteer> getVolByOrgId(Long trainId, String orderBy, Volunteer vol, int page, int rows);
	
	/**
	 * 根据orgId得到志愿者列表
	 * @param orgId
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<Volunteer> getVols(Long orgId, String orderBy, int page, int rows);
	
	/**
	 * 得到志愿者比例信息(如,男女比例,政治面貌,学历信息)
	 * @param statistics
	 * @return
	 */
	public Statistics getVolInfo(Statistics statistics);
	
	/**
	 * 得到志愿者比例信息按照服务队划分
	 * @param statistics
	 * @return
	 */
	public List<Statistics> getVolInfoList(Statistics statistics);
	
	/**
	 * 得到志愿者统计信息(名称,注册时间,服务时长,活动次数)
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<Statistics> getVolStatis(Statistics statistics,String orderBy, int page, int rows);
}
