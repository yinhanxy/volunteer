package com.topstar.volunteer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.common.Constant;
import com.topstar.volunteer.dao.MenuDao;
import com.topstar.volunteer.dao.RoleChannelDao;
import com.topstar.volunteer.dao.RoleDao;
import com.topstar.volunteer.dao.RoleMenuDao;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.mapper.RoleMapper;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private RoleChannelDao roleChannelDao;

	@Override
	public PageInfo<Role> findByEntity(Role role,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Role> roles= roleMapper.findByEntity(role);
		PageInfo<Role> page = new PageInfo<Role>(roles);
		return page;
	}

	/**
	 * 根据用户编号查询此用户拥有的角色信息
	 * @param userId 用户编号
	 * @return
	 */
	@Override
	public List<Role> findRolesByUserId(Long userId) {
		if(userId == null || userId.longValue() < 1){
			return null;
		}
		List<Role> roles = roleMapper.findRolesByUserId(userId);
		return roles;
	}
	
	/**
	 * 通过角色名称查询对应的角色信息
	 * @param roleName 角色名称
	 * @return
	 */
	public Role findRoleByRoleName(String roleName){
		if(StringUtils.isNotBlank(roleName)){
			List<Role> roles = roleMapper.findRoleByRoleName(roleName);
			if(roles != null){
				return roles.get(0);
			}
		}
		return null;
	}

	/**
	 * 查询对应状态的所有角色信息,包含角色对应的菜单信息
	 * @return
	 */
	@Override
	public List<Role> selectAll() {
		List<Role> roles = roleMapper.selectAll();
		if(roles != null){
			for(Role role : roles){
				Long id = role.getId();
				List<Menu> menus = null;
				if(role.getRoleName().equals(Constant.ADMIN)){
					menus = menuDao.findMenus();
				}else{
					menus = menuDao.findMenuByRoleId(id);
				}
				if(menus != null && !menus.isEmpty()){
					Map<Long, Menu> menuMap = new HashMap<Long,Menu>();
					for(Menu menu:menus){
						menuMap.put(menu.getId(), menu);
					}
					role.setMenuMap(menuMap);
				}
				
				//如果不是超级管理员角色，查询角色可访问的栏目信息
				if(!role.getRoleName().equals(Constant.ADMIN)){
					List<Long> channelIds = roleChannelDao.getChannelIds(id);
					if(channelIds != null && channelIds.size() > 0){
						role.setChannelIds(channelIds);
					}
				}
			}
		}
		return roles;
	}

	public RoleMenuDao getRoleMenuDao() {
		return roleMenuDao;
	}

	public void setRoleMenuDao(RoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}

}

