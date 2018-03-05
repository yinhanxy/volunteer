package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.model.OrgView;

public interface OrgService extends BaseService<Org>{
	
	/**
	 * 得到所有的无递归关系机构信息列表
	 * @return
	 */
	List<OrgView> getAllOrgList();
	
	/**
	 * 获取指定机构下的无递归关系的所有下级机构信息列表(包含当前机构信息)
	 * @param parentId 上级机构id
	 * @return
	 */
	public List<OrgView> getAllOrgsListByParentId(Long parentId);
	
	/**
	 * 获取指定机构的具有递归层级关系的所有下级机构信息列表
	 * @param parentId 上级机构id
	 * @return
	 */
	public List<OrgView> getAllOrgsByParentId(Long parentId);
	
	/**
	 * 获取指定机构的的下级机构信息列表(包含当前机构)
	 * @param parentId 上级机构id
	 * @return
	 */
	public List<OrgView> getOrgsByParentId(Long parentId);
	
	/**
	 * 根据指定区域获取该区域下的无递归关系的所有机构信息列表
	 * @param areaId 区域Id
	 * @return
	 */
	public List<OrgView> getOrgsListByAreaId(Long areaId);
	
	/**
	 * 获取用户归属的部门信息
	 * @param userId 用户Id
	 * @return
	 */
	public Org getOrgByUserId(Long userId);
	
	/**
	 * 添加机构
	 * @param area
	 * @return
	 */
	public boolean addOrg(Org org);
	
	/**
	 * 删除指定及其所有下级机构信息列表
	 * @param delOrgId 需要删除的机构信息的唯一标识
	 * @return
	 */
	public boolean delOrgAndSubOrg(Long delOrgId);
	
	/**
	 * 更新保存机构信息
	 * @param area
	 * @return
	 */
	public int saveOrg(Org org);
	
	/**
	 * 检查操作机构所在区域是否已存在管理机构
	 * @param operateOrg 操作的对象
	 * @param isInclude 是否包含自身
	 * @return 
	 */
	public boolean isOrgOperateValid(Org operateOrg,boolean isInclude);

	/**
	 * 根据机构ID和对查询结果的过滤条件查询该机构ID下符合指定过滤条件的用户列表
	 * @param orgId
	 * @param selectType 0：表示对结果没有过滤条件  1：对查询结果的用户名进行过滤  2：对查询结果的真实姓名进行过滤
	 * @param selectName 指定的过滤条件
	 * @param pageNum
	 * @param pageIndex
	 * @return
	 */
	public PageInfo<User> getUsersByGivenOrgId(Long orgId,int selectType,String selectName,int pageNum, int pageIndex);
	
	/**
	 * 获取带有机构信息的所有用户信息列表
	 * @param orgId 指定的机构身份orgId
	 * @param selectType 对返回结果的过滤条件类型
	 * @param selectName 对返回结果的过滤条件内容
	 * @param pageNum
	 * @param pageIndex 
	 * @return 若用户不在任何机构下，则返回的用户信息中orgId为空，反之，不为空
	 */
	public PageInfo<User> getAllUsersIncludeOrgId(Long orgId,int selectType,String selectName,int pageNum, int pageIndex);
	
	/**
	 * 根据区域和过滤条件得到指定的区域信息列表
	 * @param areaId
	 * @param selectType 过滤条件 1:对机构名称进行过滤  2:对机构备注进行过滤
	 * @param selectContent 过滤的内容
	 * @return
	 */
	public List<OrgView> getOrgsListByAreaIdAndFilter(Long areaId,int selectType,String selectContent);
	
	/**
	 * 根据机构名称和说明对机构进行模糊查询
	 * @param orgName 查询的机构名称
	 * @param summary 查询的机构说明
	 * @param orderBy 查询排序条件
	 * @param pageIndex 当前页码
	 * @param pageSize 每页显示记录数
	 * @return
	 */
	public PageInfo<OrgView> getOrgsListByFilter(String orgName,String summary,String orderBy, int pageIndex, int pageSize); 

	/**
	 * 根据组织机构orgId得到它的子孙orgIdList(List中包含它本身)
	 * @param orgId
	 * @return
	 */
	public List<Long> getOrgIdLists(Long orgId);
	
	/**
	 * 验证给定的组织名称是否已存在
	 * @param orgName
	 * @param excludeKey 是否包含自身
	 * @return
	 */
	public boolean existsWithOrgName(String orgName, Long excludeKey);
}
