package com.topstar.volunteer.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;

public interface TeamUserDao extends BaseDao<TeamUser>{
	
	/**
	 * 根据服务队ID得到用户信息列表
	 * @param user 用户对象
	 * @param teamUser 服务队对象
	 * @param orderBy 排序条件
	 * @param page 页数
	 * @param rows 
	 * @return 用户列表
	 */
	public PageInfo<User> findByTeamUser(User uesr,TeamUser teamUser,String orderBy, int page, int rows);
	
	/**
	 * 根据服务队ID得到可以被添加到服务队的用户信息列表
	 * @param uesr
	 * @param teamId
	 * @param orderBy
	 * @param page
	 * @param rows
	 * @return 可以被添加到服务队的用户集合
	 */
	public PageInfo<User> findByUserTeamID(User uesr,Long teamId,String orderBy, int page, int rows);
	
	/**
	 * 根据用户Id和服务队Id删除TeamUser中的服务队用户信息
	 * @param userId
	 * @param teamId
	 * @return
	 */
	public boolean delTeamUser(Long userId,Long teamId);
	
	/**
	 * 通过用户Id得到服务队的标号集合
	 * @param id
	 * @return
	 */
	public List<Long> findTeamIdsByUserId(Long id);

	/**
	 * 通过用户ID查询用户所在的服务队信息
	 * @param userId
	 * @return
	 */
	public SerTeam findByUserId(Long userId);
	
}
