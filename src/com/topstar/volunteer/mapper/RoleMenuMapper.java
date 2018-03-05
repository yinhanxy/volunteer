package com.topstar.volunteer.mapper;

import java.util.List;

import com.topstar.volunteer.entity.RoleMenu;
import com.topstar.volunteer.util.BaseMapper;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
	public List<Long> findMenuIdsByRoleId(Long roleId);
}