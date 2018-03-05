package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.model.LoginUser;

public interface LoginUserService {

	/**
	 * 分页显示在线的用户信息
	 * @param userName 供查询的用户名或昵称
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<LoginUser> getList(String userName,int pageIndex,int pageSize);
	
	
	/**
	 * 强制下线用户
	 * @param userIds
	 * @return
	 */
	public int logoutUser(List<Long> userIds);
	
	
}
