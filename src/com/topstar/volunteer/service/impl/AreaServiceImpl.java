package com.topstar.volunteer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.dao.AreaDao;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.model.AreaView;
import com.topstar.volunteer.service.AreaService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class AreaServiceImpl  extends BaseServiceImpl<Area> implements AreaService{

	@Autowired
	private AreaDao areaDao;

	@Override
	public List<AreaView> getAllArea() {
		List<Area> areas=areaDao.selectAllArea();
		List<AreaView> areaViewList=new ArrayList<AreaView>();
		final Map<Long,Area> tempMap=new HashMap<Long,Area>();
		TreeMap<Long, AreaView> treeMap=new TreeMap<Long, AreaView>();
		for (Area area : areas) {
			tempMap.put(area.getId(), area);
		}
		for (Area area : areas) {
			Long id=area.getId();
			AreaView areaView=new AreaView(area);
			if(area.getParentId()==0 || area.getParentId()==1){
				AreaView treeMapAreaView=treeMap.get(id);
				if(treeMapAreaView!=null){
					continue;
				}
				treeMap.put(id,areaView);
			}else{
				Long parentId=area.getParentId();
				AreaView areaParent=treeMap.get(parentId);
				List<AreaView> areaChildren;
				if(areaParent!=null){
					areaParent.setParent(true);
					areaChildren=areaParent.getAreaList();
					if(areaChildren!=null){
						areaChildren.add(areaView);
					}else{
						List<AreaView> list=new ArrayList<AreaView>();
						list.add(areaView);
						areaParent.setAreaList(list);
					}
				}else{
					areaParent=new AreaView(tempMap.get(parentId));
					areaParent.setParent(true);
					treeMap.put(areaParent.getId(),areaParent);
					areaChildren=areaParent.getAreaList();
					if(areaChildren!=null){
						areaChildren.add(areaView);
					}else{
						List<AreaView> list=new ArrayList<AreaView>();
						list.add(areaView);
						areaParent.setAreaList(list);
					}
				}
				Collections.sort(areaParent.getAreaList());
			}
		}
		for (Long id : treeMap.keySet()) {
			AreaView view =treeMap.get(id);
			areaViewList.add(view);
			if(id==0){
				continue;
			}
			List<AreaView> areaViewChildren=view.getAreaList();
			if(areaViewChildren!=null){
				areaViewList.addAll(areaViewChildren);
			}
			view.setAreaList(null);
		}
		return areaViewList;
	}

	@Override
	public List<AreaView> getAreasByParentId(Long parentId) {
		List<AreaView> areaViewList=new ArrayList<AreaView>();
		if(parentId>0){
			Area currentArea=selectByKey(parentId);
			List<Area> areas=areaDao.findAreaByParentId(parentId);
			AreaView areaView=null;
			if(parentId==1){
				areaView=new AreaView(currentArea);
				areaView.setOpen(true);
				areaView.setParent(true);
				areaViewList.add(areaView);
				Collections.sort(areas);
				for (Area area : areas) {
					areaView=new AreaView(area);
					areaView.setParent(true);
					areaViewList.add(areaView);
				}
			}else{
				areaView=new AreaView(currentArea);
				areaViewList.add(areaView);
				Collections.sort(areas);
				for (Area area : areas) {
					areaView=new AreaView(area);
					areaViewList.add(areaView);
				}
			}
		}
		return areaViewList;
	}

	@Override
	public boolean addArea(Area area) {
		if(area!=null){
			int addArea=areaDao.addArea(area);
			if(addArea>0){
				return true;
			}
		}
		return false;
	}

	@Override
	public int delAreaAndSubArea(Long delAreaId) {
        if(delAreaId>1){
        	int delArea=deleteByKey(delAreaId);
        	if(delArea>0){
        		List<Area> subAreas=areaDao.findAreaByParentId(delAreaId);
        		if(subAreas!=null && subAreas.size()>0){
        			for (Area area : subAreas) {
        				int delSubArea=deleteByKey(area.getId());
        				if(delSubArea==0){
        					return 0;
        				}
					}
        		}
        		return 1;
        	}
        }else if(delAreaId==1){
        	return -1;
        }
        return 0;
	}

	@Override
	public int saveArea(Area area) {
		if(area!=null && area.getId()!=null){
			int saveArea=areaDao.updateArea(area);
			if(saveArea>0){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public boolean isAreaOperateValid(Area operateArea, Long parentId) {
		int areaType=-1;
		Area parentArea=selectByKey(parentId);
		if(parentArea!=null){
			areaType=parentArea.getType();
			if(areaType==2){
				return false;
			}
		}
		if(operateArea!=null){
			if(0==operateArea.getType()){
				if(operateArea.getParentId()!=0){
					return false;
				}
			}else if(1==operateArea.getType()){
				if(1!=parentId){
					return false;
				}
			}else{
				if(1!=areaType){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean existsWithAreaName(String areaName, Long excludeKey) {
		Example example=new Example(Area.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("name", areaName);
		if (excludeKey!=null) {
			criteria.andNotEqualTo("id", excludeKey);
		}
		List<Area> areas=selectByExample(example);
		if(areas!=null && areas.size()!=0){
			return true;
		}
		return false;
	}
	
}
