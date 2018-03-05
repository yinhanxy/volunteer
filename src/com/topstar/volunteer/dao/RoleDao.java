package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Role;

public interface RoleDao extends BaseDao<Role>{
	
	public PageInfo<Role> findByEntity(Role role,String orderBy, int page, int rows);
	
	/**
	 * 根据用户编号查询此用户拥有的角色信息
	 * @param userId 用户编号
	 * @return
	 */
	public List<Role> findRolesByUserId(Long userId);
	
	/**
	 * 通过角色名称查询对应的角色信息
	 * @param roleName 角色名称
	 * @return
	 */
	public Role findRoleByRoleName(String roleName);
	
	/**
	 * 查询对应状态的所有角色信息,包含角色对应的菜单信息
	 * @return
	 */
	public List<Role> selectAll();
	
}
