package com.topstar.volunteer.service;

import java.util.List;

import com.topstar.volunteer.entity.OrgUser;

public interface OrgUserService extends BaseService<OrgUser> {
	
	/**
	 * 根据用户ID获取该用户所在的组织机构
	 * @param userId
	 * @return
	 */
	public OrgUser getOrgUserByUserId(Long userId);
	
	
	/**
	 * 移除机构下指定的用户
	 * @param orgId
	 * @param userIds
	 * @return
	 */
	public Boolean deleteOrgUsersUnderOrg(Long orgId,List<Long> userIds);
	
	/**
	 * 给指定的机构添加用户
	 * @param orgId
	 * @param userIds
	 * @return
	 */
	public boolean addUsersWithOrgId(Long orgId,List<Long> userIds);
	
	/**
	 * 检查该用户是否已经属于某种组织
	 * @param roleName 需要检查的角色名称
	 * @param excludeKey 需要排除在外的主键值对应的记录
	 * @return 0：不存在  1：存在
	 */
	public int existsWithUserOrg(Long userId);
}
