package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.AreaDao;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.mapper.AreaMapper;

@Repository
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao {
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public List<Area> selectAllArea() {
		List<Area> areas=areaMapper.findAllArea();
		if(areas!=null && areas.size()>0){
			return areas;
		}
		return null;
	}

	@Override
	public List<Area> findAreaByParentId(Long parentId) {
		return areaMapper.findAreaByParentId(parentId);
	}

	@Override
	public int addArea(Area area) {
		return areaMapper.addArea(area);
	}

	@Override
	public int updateArea(Area area) {
		return areaMapper.updateArea(area);
	}

	@Override
	public List<Area> getAreas() {
		return areaMapper.getAreas();
	}

	@Override
	public List<Area> findAreaChildersByParentId(Long parentId) {
		List<Area> areas=areaMapper.findAreaChildersByParentId(parentId);
		if(areas!=null && !areas.isEmpty()){
			return areas;
		}
		return null;
	}
	
}
