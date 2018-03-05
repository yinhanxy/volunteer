package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.OrgDao;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.mapper.OrgMapper;
import com.topstar.volunteer.model.OrgView;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class OrgDaoImpl extends BaseDaoImpl<Org> implements OrgDao {
	
	@Autowired
	private OrgMapper orgMapper;

	@Override
	public List<Org> selectAllOrg() {
		List<Org> orgs=orgMapper.findAllOrg();
		if(orgs!=null && orgs.size()>0){
			return orgs;
		}
		return null;
	}

	@Override
	public List<Org> findOrgByParentId(Long parentId) {
		List<Org> orgs=orgMapper.findOrgByParentId(parentId);
		if(orgs!=null && orgs.size()>0){
			return orgs;
		}
		return null;
	}

	@Override
	public Long findOrgByAreaId(Long areaId) {
		Long id=orgMapper.findOrgByAreaId(areaId);
		if(id!=null){
			return id;
		}
		return null;
	}
	
	/**
	 * 根据用户Id获取归属的机构信息
	 * @param userId
	 * @return
	 */
	public Org findOrgByUserId(Long userId){
		Org org=orgMapper.findOrgByUserId(userId);
		if(org!=null){
			return org;
		}
		return null;
	}
	
	/**
	 * 通过机构名称和说明对机构进行模糊查询
	 * @param orgName
	 * @param summary
	 * @return
	 */
	@Override
	public PageInfo<OrgView> findEntity(String orgName, String summary,
			Long orgId, String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<OrgView> orgs= orgMapper.findEntityByFilter(orgName, summary,orgId);
		PageInfo<OrgView> page = new PageInfo<OrgView>(orgs);
		return page;
	}

	@Override
	public List<Long> getOrgIdLists(Long orgId) {
		List<Long> list=orgMapper.getOrgIdLists(orgId);
		if (list!=null) {
			return list;
		}
		return null;
	}

	@Override
	public List<Org> getAllOrgsByAreaId(Long areaId) {
		List<Org> list=orgMapper.getAllOrgsByAreaId(areaId);
		if (list!=null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public List<Org> getOrgsByAreaId(Long areaId,Long orgId) {
		List<Org> list=orgMapper.getOrgsByAreaId(areaId,orgId);
		if (list!=null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

}
