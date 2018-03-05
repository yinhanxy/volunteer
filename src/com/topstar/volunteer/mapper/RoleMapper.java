package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.util.BaseMapper;

public interface RoleMapper extends BaseMapper<Role> {
	
	List<Role> findByEntity(@Param("role")Role role);
	
	/**
	 * 根据用户编号查询此用户拥有的角色信息
	 * @param userId 用户编号
	 * @return
	 */
	List<Role> findRolesByUserId(@Param("userId")Long userId);
	
	/**
	 * 查询对应状态的所有角色信息
	 * @return
	 */
	public List<Role> selectAllByStatus(@Param("status")Integer status);
	
	
	/**
	 * 通过角色名称查询对应的角色信息
	 * @param roleName 角色名称
	 * @return
	 */
	public List<Role> findRoleByRoleName(@Param("roleName")String roleName);
	
}