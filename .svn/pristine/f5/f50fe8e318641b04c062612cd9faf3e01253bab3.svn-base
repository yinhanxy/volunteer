package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.UserChannelDao;
import com.topstar.volunteer.dao.UserDao;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.mapper.RoleUserMapper;
import com.topstar.volunteer.mapper.UserMapper;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleUserMapper roleUserMapper;
	
	@Autowired
	private UserChannelDao userChannelDao;

	@Override
	public List<User> selectByUser(User user, int page, int rows) {
		Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        if (StringUtils.isNotEmpty(user.getRealName())) {
            criteria.andLike("realName", "%" + user.getRealName() + "%");
        }
        Criteria c = example.createCriteria();
        if(StringUtils.isNotBlank(user.getNickName())){
        	c.andLike("nickName", "%" + user.getNickName() + "%");
        }
        example.or(c);
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
	}
	
	@Override
	public PageInfo<User> findByEntity(User user,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<User> users = userMapper.findByEntity(user);
		PageInfo<User> page = new PageInfo<User>(users);
		return page;
	}
	
	@Override
	public PageInfo<User> findByEntityWithoutOrg(User user,String orderBy, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<User> users = userMapper.findByEntityWithoutOrg(user);
		PageInfo<User> page = new PageInfo<User>(users);
		return page;
	}
	
	@Override
	public User findByUserName(String userName){
		List<User> users = userMapper.findByUserName(userName);
		if(users != null && users.size() == 1){
			User user = users.get(0);
			return user;
		}
		return null;
	}

	@Override
	public List<Long> findRoleIdsByUserId(Long id) {
		if(id == null || id.longValue() < 1){
			return null;
		}
		List<Long> roleIds=roleUserMapper.findRoleIdsByUserId(id);
		if(roleIds!=null && roleIds.size()>0){
			return roleIds;
		}
		return null;
	}

	@Override
	public PageInfo<User> findUsersByRoleId(String roleId, String userName, String realName, int pageNum, int pageIndex) {
		PageHelper.startPage(pageIndex,pageNum);
		OrderByHelper.orderBy("crTime DESC,userName");
		List<User> users=userMapper.findUsersByRoleId(roleId,userName,realName);
		PageInfo<User> userPage = new PageInfo<User>(users);
		return userPage;
	}

	@Override
	public PageInfo<User> findUsersByOrgId(Long orgId, String userName, String realName, int pageNum, int pageIndex) {
		PageHelper.startPage(pageIndex,pageNum);
		OrderByHelper.orderBy("crTime DESC,userName");
		List<User> users=userMapper.findUsersByOrgId(orgId,userName,realName);
		PageInfo<User> userPage = new PageInfo<User>(users);
		return userPage;
	}
	
	@Override
	public int editUserPwd(Long id,String userPwd) {
		// TODO Auto-generated method stub
		return userMapper.editUserPwd(id, userPwd);
	}

	@Override
	public PageInfo<User> getUsersByOrgId(Long orgId, User user, int pageIndex, int pageNum) {
		PageHelper.startPage(pageIndex,pageNum);
		OrderByHelper.orderBy("crTime DESC");
		List<User> users=userMapper.getUsersByOrgId(orgId, user);
		PageInfo<User> userPage = new PageInfo<User>(users);
		return userPage;
	}
	
	/**
	 * 根据用户ID获取可访问的栏目ID集合
	 * @param userId
	 * @return
	 */
	public List<Long> getChannelIds(Long userId){
		return userChannelDao.getChannelIds(userId);
	}
	

	@Override
	public PageInfo<User> getUsersBySer(User user, String orderBy, int pageIndex, int pageNum) {
		PageHelper.startPage(pageIndex,pageNum);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<User> users=userMapper.getUsersBySer(user);
		PageInfo<User> userPage = new PageInfo<User>(users);
		return userPage;
	}
}
