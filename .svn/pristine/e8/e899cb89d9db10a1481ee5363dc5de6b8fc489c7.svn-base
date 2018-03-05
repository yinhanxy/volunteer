package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.model.LockUser;

public interface LockUserService {


	/**
	 * 分页显示被锁住的用户信息
	 * @param userName 供查询的用户名或昵称
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<LockUser> getList(String userName,int pageIndex,int pageSize);
	
	
	/**
	 * 解锁用户
	 * @param userNames
	 * @return
	 */
	public int unlockUser(List<String> userNames);
}
