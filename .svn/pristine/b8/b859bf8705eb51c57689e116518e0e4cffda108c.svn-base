package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.model.OrgView;
import com.topstar.volunteer.util.BaseMapper;

public interface OrgMapper extends BaseMapper<Org> {
	
	/**
	 * 获取所有的机构信息列表
	 * @return
	 */
	public List<Org> findAllOrg();
	
	/**
	 * 查询指定上级机构id下的下级机构信息列表
	 * @param parentId 上级机构id
	 * @return
	 */
	public List<Org> findOrgByParentId(Long parentId);
	
	/**
	 * 根据指定的区域Id获取对应的机构信息
	 * @param areaId
	 * @return
	 */
	public Long findOrgByAreaId(Long areaId);
	
	/**
	 * 根据用户Id获取归属的机构信息
	 * @param userId
	 * @return
	 */
	public Org findOrgByUserId(Long userId);
	
	/**
	 * 通过机构名称和说明对机构进行模糊查询
	 * @param orgName
	 * @param summary
	 * @return
	 */
	public List<OrgView> findEntityByFilter(@Param("orgName")String orgName,@Param("summary")String summary,@Param("orgId")Long orgId);
	
	/**
	 * 根据组织机构orgId得到它的子孙orgIdList(List中包含它本身)
	 * @param orgId
	 * @return
	 */
	public List<Long> getOrgIdLists(Long orgId);
	
	/**根据区域Id获取该区域的所有机构
	 * @param areaId
	 * @return
	 */
	public List<Org> getAllOrgsByAreaId(@Param("areaId")Long areaId);
	
	/**
	 * 当区域id为2时获取区域内所有机构
	 * @param areaId
	 * @return
	 */
	public List<Org> getOrgsByAreaId(@Param("areaId")Long areaId,@Param("orgId")Long orgId);
}