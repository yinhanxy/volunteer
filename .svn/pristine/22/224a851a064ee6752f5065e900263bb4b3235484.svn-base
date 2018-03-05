package com.topstar.volunteer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.entity.Area;

public interface AreaDao extends BaseDao<Area>{
	
	/**
	 * 查询所有的区域信息对象
	 * @return
	 */
	List<Area> selectAllArea();	
	
	/**
	 * 查询指定上级区域id下的下辖区域信息列表
	 * @param parentId 上级区域id
	 * @return
	 */
	public List<Area> findAreaByParentId(Long parentId);
	
	/**
	 * 添加区域信息
	 * @param area
	 * @return
	 */
	public int addArea(Area area);
	
	/**
	 * 更新区域信息
	 * @param area
	 * @return
	 */
	public int updateArea(Area area);
	
	/**
	 * 查询所有的市级名称及其对应区域编码
	 * @return
	 */
	public List<Area> getAreas();
	
	/**
	 * 根据区域ID获取所有该区域下的区县类型的子级区域列表
	 * @param parentId
	 * @return
	 */
	public List<Area> findAreaChildersByParentId(Long parentId);
}
