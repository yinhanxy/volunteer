package com.topstar.volunteer.mapper;

import java.util.List;

import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.util.BaseMapper;

public interface AreaMapper extends BaseMapper<Area> {
	
	/**
	 * 获取所有的区域信息列表
	 * @return
	 */
	public List<Area> findAllArea();
	
	/**
	 * 查询指定上级区域id下的下辖区域信息列表
	 * @param parentId 上级区域id
	 * @return
	 */
	public List<Area> findAreaByParentId(Long parentId);
	
	public int addArea(Area area);
	
	/**
	 * 根据区域ID获取所有该区域下的区县类型的子级区域列表
	 * @param parentId
	 * @return
	 */
	public List<Area> findAreaChildersByParentId(Long parentId);
	
	public int updateArea(Area area);
	
	/**
	 * 查询所有的市级名称及其对应区域编码
	 * @return
	 */
	public List<Area> getAreas() ;
}