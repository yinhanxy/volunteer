package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;

public interface TeamUserService extends BaseService<TeamUser>{
	
	/**
	 * 根据服务队ID得到用户信息列表
	 * @param user 用户对象
	 * @param teamUser 服务队对象
	 * @param orderBy 排序条件
	 * @param page 查询的页码
	 * @param rows 页码的显示条数
	 * @return 用户列表
	 */
	public PageInfo<User> findByTeamUser(User user,TeamUser teamUser,String orderBy, int page, int rows);
	
	/**
	 * 根据服务队ID得到可以被添加到服务队的用户信息列表
	 * @param uesr
	 * @param teamId
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return 可以被添加到服务队的用户集合
	 */
	public PageInfo<User> findByUserTeamID(User uesr,Long teamId, int page, int rows);
	
	/**
	 * 得到用户信息列表
	 * @param uesr
	 * @param teamId
	 * @param page
	 * @param rows
	 * @return 返回所有用户信息,在服务队中已有的用户带有teamIdList
	 */
	public PageInfo<User> getUserTeams(User uesr,Long teamId , int page, int rows);
	
	/**
	 * 将用户添加到服务队中
	 * @param teamId
	 * @param userIds
	 * @return
	 */
	public boolean addUsersWithTeamId(Long teamId,List<Long> userIds);
	
	/**
	 * 根据用户Id和服务队Id删除TeamUser中的服务队用户信息
	 * @param userId
	 * @param teamId
	 * @return
	 */
	public boolean delTeamUser(List<Long> userIds,Long teamId);
	
	/**
	 * 根据用户ID获取服务队信息
	 * @param userId
	 * @return
	 */
	public SerTeam getSerTeamByUserId(Long userId);
}
