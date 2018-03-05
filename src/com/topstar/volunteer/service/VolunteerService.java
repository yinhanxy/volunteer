package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;

public interface VolunteerService extends BaseService<Volunteer>{
	
	/**
	 * 根据过滤条件分页查询志愿者信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> findByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 根据志愿者查询条件分页获取志愿者审核信息列表
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> findVolunteerCheckByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize);
	
	public boolean resetVolunteerPassword(long[] ids) throws TPSException;
	
	/**
	 * 将志愿者列表添加到数据库
	 * @param volList
	 * @return
	 */
	public boolean addVols(List<Volunteer> volList,Long trainId); 
	
	/**
	 * 更新志愿者信息
	 * @param vol
	 * @return
	 */
	public int updateVolsInfo(Volunteer vol);
	
	/**
	 * 志愿者统计页面信息
	 * @return
	 */
	public List<Statistics> statisticsShow();
	
	/**
	 * 返回所有市级志愿者人数信息
	 * 如[["武汉市","121"],["十堰市","84"]]
	 * @return
	 */
	public List<Statistics> returnStcsVol();
	
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
	public Statistics getVolInfo();
	
	/**
	 * 得到志愿者比例信息按照服务队划分
	 * @param statistics
	 * @return
	 */
	public List<Statistics> getVolInfoList();
	
	/**
	 * 得到志愿者统计信息(名称,注册时间,服务时长,活动次数)
	 * @param volunteer
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<Statistics> getVolStatis(String orderBy, int page, int rows);
}
