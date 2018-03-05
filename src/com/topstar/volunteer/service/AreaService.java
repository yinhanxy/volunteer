package com.topstar.volunteer.service;

import java.util.List;

import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.model.AreaView;

public interface AreaService extends BaseService<Area>{
	
	/**
	 * 得到所有的区域信息
	 * @return
	 */
	List<AreaView> getAllArea();
	
	/**
	 * 获取指定区域的下辖区域信息列表
	 * @param parentId 上级区域id
	 * @return
	 */
	public List<AreaView> getAreasByParentId(Long parentId);
	
	/**
	 * 添加区域
	 * @param area
	 * @return
	 */
	public boolean addArea(Area area);
	
	/**
	 * 删除指定及其下辖的区域信息列表
	 * @param delAreaId 需要删除的区域信息的唯一标识
	 * @return
	 */
	public int delAreaAndSubArea(Long delAreaId);
	
	/**
	 * 更新保存区域信息
	 * @param area
	 * @return
	 */
	public int saveArea(Area area);
	
	/**
	 * 检查区域操作是否合法
	 * @param operateArea 操作的对象
	 * @param parentId 操作对象的上级区域的唯一标识
	 * @return
	 */
	public boolean isAreaOperateValid(Area operateArea,Long parentId);

	/**
	 * 验证给定的区域名称是否已存在
	 * @param name 区域名称
	 * @param id 排除在外的区域主键
	 * @return
	 */
	boolean existsWithAreaName(String areaName, Long excludeKey);
	
}
