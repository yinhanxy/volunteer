package com.topstar.volunteer.mapper;

import java.util.List;

import com.topstar.volunteer.entity.UserMenu;
import com.topstar.volunteer.util.BaseMapper;

public interface UserMenuMapper extends BaseMapper<UserMenu> {
	public List<Long> findMenuIdsByUserId(Long userId);
}