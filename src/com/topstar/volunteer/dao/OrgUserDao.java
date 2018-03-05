package com.topstar.volunteer.dao;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.OrgUser;

public interface OrgUserDao extends BaseDao<OrgUser>{
	
	/**
	 * 查询用户所属的机构编号
	 * @param userId 用户编号
	 * @return  机构编号Id
	 */
	public Long findOrgIdByUserId(@Param("userId")Long userId);
	
}
