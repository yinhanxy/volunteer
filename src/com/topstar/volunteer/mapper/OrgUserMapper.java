package com.topstar.volunteer.mapper;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.util.BaseMapper;

public interface OrgUserMapper extends BaseMapper<OrgUser> {
	
	/**
	 * 查询用户所属的机构编号
	 * @param userId 用户编号
	 * @return  机构编号Id
	 */
	public Long findOrgIdByUserId(@Param("userId")Long userId);
}