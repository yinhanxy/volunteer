package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.util.BaseMapper;

public interface TeamUserMapper extends BaseMapper<TeamUser> {
	
	/**
	 * 根据服务队ID得到用户列表
	 * @param teamId
	 * @return 用户集合
	 */
	List<User> findByTeamId(@Param("user")User user,@Param("teamUser")TeamUser teamUser);
	
	/**
	 * 根据服务队ID得到可以被添加到服务队的用户列表
	 * @param user
	 * @param teamId
	 * @return 用户集合
	 */
	List<User> findByUserTeamId(@Param("user")User user,@Param("teamId")Long teamId);
	
	/**
	 * 根据用户Id和服务队Id删除TeamUser中的服务队用户信息
	 * @param userId
	 * @param teamId
	 * @return
	 */
	int delTeamUser(@Param("userId")Long userId,@Param("teamId")Long teamId);
	
	/**
	 * 得到所有的用户信息
	 * @param user
	 * @return
	 */
	List<User> getAllUsers(@Param("user")User user);
	
	/**
	 * 查询用户拥有的服务队编号
	 * @param userId 用户编号
	 * @return  服务队编号集合
	 */
	public List<Long> findTeamIdsByUserId(@Param("userId")Long userId);

	/**
	 * 查询用户所在服务队
	 * @param userId
	 * @return
	 */
	public SerTeam findTeamByUserId(@Param("userId")Long userId);
}