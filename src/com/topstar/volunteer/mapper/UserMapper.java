package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.util.BaseMapper;

public interface UserMapper extends BaseMapper<User>{
	
	List<User> findByEntity(@Param("user")User user);
	
	List<User> findByEntityWithoutOrg(@Param("user")User user);
	
	List<User> findByUserName(@Param("userName")String userName);
	
	List<User> findByInfo(@Param("userName")String userName,@Param("nickName")String nickName);

	/**
	 *  根据角色ID和对查询结果的过滤条件查询该角色ID下符合指定过滤条件的用户列表
	 * @param roleId  根据角色ID查询出用户列表
	 * @param userName 指定查询结果用户的用户名
	 * @param realName 指定查询结果用户的真实姓名
	 * @return
	 */
	List<User> findUsersByRoleId(@Param("roleId")String roleId,@Param("userName")String userName, @Param("realName")String realName);
	
	/**
	 *  根据机构ID和对查询结果的过滤条件查询该机构下符合指定过滤条件的用户列表
	 * @param orgId  根据机构ID查询出用户列表
	 * @param userName 指定查询结果用户的用户名
	 * @param realName 指定查询结果用户的真实姓名
	 * @return
	 */
	List<User> findUsersByOrgId(@Param("orgId")Long orgId,@Param("userName")String userName, @Param("realName")String realName);
	
	int editUserPwd(@Param("id")Long id,@Param("userPwd")String userPwd);
	
	/**
	 * 根据机构Id得到当前机构下且还没被添加到服务队的用户列表
	 * @param orgId
	 * @param user
	 * @param page
	 * @param rows
	 * @return
	 */
    List<User> getUsersByOrgId(@Param("orgId")Long orgId,@Param("user")User user);
    
    /**
	 * 得到未被分配到服务队的用户列表
	 * @param user
	 * @return
	 */
    List<User> getUsersBySer(@Param("user")User user);
}