package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.RoleUser;
import com.topstar.volunteer.util.BaseMapper;

public interface RoleUserMapper extends BaseMapper<RoleUser> {
	
	/**
	 * 查询用户拥有的角色编号
	 * @param userId 用户编号
	 * @return  角色编号集合
	 */
	public List<Long> findRoleIdsByUserId(@Param("userId")Long userId);
}