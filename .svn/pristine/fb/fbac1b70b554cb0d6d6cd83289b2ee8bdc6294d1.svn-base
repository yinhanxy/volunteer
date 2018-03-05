package com.topstar.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.cache.RoleCache;
import com.topstar.volunteer.dao.RoleChannelDao;
import com.topstar.volunteer.dao.RoleDao;
import com.topstar.volunteer.dao.RoleMenuDao;
import com.topstar.volunteer.dao.RoleUserDao;
import com.topstar.volunteer.dao.UserDao;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.RoleChannel;
import com.topstar.volunteer.entity.RoleMenu;
import com.topstar.volunteer.entity.RoleUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.RoleMenuService;
import com.topstar.volunteer.service.RoleService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleCache roleCache;
	
	@Autowired
	private LoginUserCache userCache;
	
	@Autowired
	private RoleUserDao roleUserDao;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Autowired
	private RoleChannelDao roleChannelDao;

	@Override
	public PageInfo<Role> findByEntity(Role role, String orderBy, int page, int rows) {
		return roleDao.findByEntity(role, orderBy, page, rows);
	}

	@Override
	public boolean deleteRoles(long[] keys) {
		if(keys!=null && keys.length>0){
			int rows=keys.length;
			int flag=0;
			Example example=new Example(RoleUser.class);
			for (int i = 0; i < keys.length; i++) {
				flag=roleDao.deleteByKey(keys[i]);
				if(flag>0){
					example.createCriteria().andEqualTo("roleId", String.valueOf(keys[i]));
					roleUserDao.deleteByExample(example);
					logger.debug("开始删除角色缓存中roleId为{}对应的缓存角色信息",keys[i]);
					Boolean delResult=roleCache.delete(keys[i]);
					if(delResult){
						logger.debug("成功删除角色缓存中roleId为{}对应的缓存角色信息",keys[i]);
					}else{
						logger.debug("删除角色缓存中roleId为{}对应的缓存角色信息出现错误",keys[i]);
					}
					logger.debug("开始删除用户缓存中roleId为{}对应的角色信息",keys[i]);
					Boolean removeResult=userCache.removeRole(keys[i]);
					if(removeResult){
						logger.debug("成功删除用户缓存中roleId为{}对应的角色信息",keys[i]);
					}else{
						logger.debug("删除用户缓存中roleId为{}对应的角色信息出现错误",keys[i]);
					}
					int row = roleChannelDao.deleteByRoleId(keys[i]);
					logger.debug("成功删除角色可访问的栏目信息{}条",row);
					if(!delResult || !removeResult){
						return false;
					}
					rows--;
				}
			}
			if(rows==0){
				return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public int existsWithRoleName(String roleName, String excludeKey) {
		
		Example example=new Example(Role.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("roleName", roleName);
		if (StringUtils.isNotEmpty(excludeKey)) {
			criteria.andNotEqualTo("id", excludeKey);
		}
		criteria.andEqualTo("status", 1);
		List<Role> roles=selectByExample(example);
		if(roles!=null && roles.size()!=0){
			return 1;
		}
		return 0;
	}
	
	@Override
	public List<Role> findRolesByUserId(Long userId) {
		return roleDao.findRolesByUserId(userId);
	}


	@Override
	public List<Role> getAllRoles() {
		
		Example example=new Example(Role.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("status", 1);
		List<Role> roles=selectByExample(example);
		if(roles!=null && roles.size()!=0){
			return roles;
		}else{
			return null;
		}
	}
	
	/**
	 * 通过角色名称查询对应的角色信息
	 * @param roleName 角色名称
	 * @return
	 */
	public Role findRoleByRoleName(String roleName){
		return roleDao.findRoleByRoleName(roleName);
	}

	/**
	 * 添加用户角色
	 * @param role
	 * @return
	 */
	@Override
	public boolean addRole(Role role) {
		int addRow=insert(role);
		Long id = role.getId();
		role.setId(id);
		if(addRow>0){
			boolean addCache=roleCache.add(role);
			if(addCache){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 更新角色信息并更新系统中该角色的缓存信息
	 * @param role
	 * @return
	 * @throws TPSException 
	 */
	public boolean updateRole(Role role) throws TPSException{
		if(role!=null && role.getId()!=null){
			int updateRow=updateNotNull(role);
			if(updateRow>0){
				boolean updateCache=roleCache.update(role);
				if(updateCache){
					return true;
				}
			}
		}else{
			throw new TPSException("更新的角色信息参数不合法");
		}
		return false;
	}

	@Override
	public PageInfo<User> getUsersByGivenRoleId(String roleId,int selectType,String selectName, int pageNum,int pageIndex) {
		if(StringUtils.isBlank(roleId)){
			return null;
		}
		if(selectType<0 || selectType >2 ){
			selectType=0;
		}
		String userName=null;
		String realName=null;
		
		if(selectType==1){
			userName=selectName;
		}else if(selectType==2){
			realName=selectName;
		}
		return userDao.findUsersByRoleId(roleId, userName, realName, pageNum, pageIndex);
	}

	@Override
	public PageInfo<User> getAllUsersIncludeRoleIds(Long orgId,Long roleId,int selectType,String selectName,int pageNum, int pageIndex) {
		/*User user=new User();
		if(selectType==1){
			user.setUserName(selectName);
		}else if(selectType==2){
			user.setRealName(selectName);
		}
		String orderBy = "CR_TIME DESC,USER_NAME";
		PageInfo<User> users=userDao.findByEntity(user, orderBy, pageIndex, pageNum);*/
		PageInfo<User> users=orgService.getUsersByGivenOrgId(orgId, selectType, selectName, pageNum, pageIndex);
		List<User> userList=users.getList();
		if(userList!=null && userList.size()>0){
			for (User u : userList) {
				List<Long> roleIds=userDao.findRoleIdsByUserId(u.getId());
				if(roleIds!=null && roleIds.size()>0 &&  roleId!=null && roleIds.contains(roleId)){
					u.setRoleIdList(roleIds);
				}
			}
			
		}
		return users;
	}

	@Override
	public boolean addUsersWithRoleId(Long roleId, List<Long> userIds) {
		if(roleId ==null || roleId<0){
			return false;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser==null){
			return false;
		}
		if(userIds!=null && userIds.size()>0){
			int flag=userIds.size();
			for (Long userId : userIds) {
				RoleUser roleUser=new RoleUser();
				roleUser.setRoleId(roleId);
				roleUser.setCrUser(crtUser.getUserName());
				roleUser.setCrTime(new Timestamp(new Date().getTime()));
				roleUser.setUserId(userId);
				int addRoleUser=roleUserDao.insert(roleUser);
				if(addRoleUser>0){
					flag--;
					userCache.addRole(userId, roleId);
				}
			}
			if(flag==0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 给指定的角色分配菜单操作权限
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	public boolean addRoleMenus(Long roleId,List<Long> menuIds){
		if(roleId ==null || roleId<0){
			return false;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser==null){
			return false;
		}
		List<Long> existedMenuIds=getMenuIdsByRoleId(roleId);
		if(existedMenuIds!=null){
			Example example=new Example(RoleMenu.class);
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("roleId", roleId);
			int delRoleMenuRow=roleMenuService.deleteByExample(example);
			if(delRoleMenuRow!=existedMenuIds.size()){
				return false;
			}
			for (Long existedMenuId : existedMenuIds) {
				roleCache.removeRoleMenu(roleId, existedMenuId);
			}
		}
		if(menuIds!=null && menuIds.size()>0){
			int flag=menuIds.size();
			for (Long menuId : menuIds) {
				RoleMenu roleMenu=new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setCrUser(crtUser.getUserName());
				roleMenu.setCrTime(new Timestamp(new Date().getTime()));
				roleMenu.setMenuId(menuId);
				int addRoleMenu=roleMenuDao.insert(roleMenu);
				if(addRoleMenu>0){
					flag--;
					roleCache.addRoleMenu(null, menuId);
					roleCache.addRoleMenu(roleId, menuId);
				}
			}
			if(flag==0){
				return true;
			}
		}
		return true;
	}

	@Override
	public List<Long> getMenuIdsByRoleId(Long roleId) {
		List<Long> menuIds=null;
		if(roleId!=null && roleId>0){
			menuIds=roleMenuDao.findMenuIdsByRoleId(roleId);
		}
		return menuIds;
	}
	
	
	/**
	 * 通过角色ID查询可访问的栏目ID
	 * @param roleId
	 * @return
	 */
	public List<Long> getChannelIds(Long roleId){
		return roleChannelDao.getChannelIds(roleId);
	}
	
	/**
	 * 给指定的角色分配栏目可访问权限
	 * @param roleId
	 * @param channelIds
	 * @return
	 */
	public boolean addRoleChannels(Long roleId,List<Long> channelIds){
		if(roleId ==null || roleId<0){
			return false;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser==null){
			return false;
		}
		List<Long> existedChannelIds=getChannelIds(roleId);
		if(existedChannelIds!=null){
			Example example=new Example(RoleChannel.class);
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("roleId", roleId);
			int delRoleMenuRow=roleChannelDao.deleteByExample(example);
			if(delRoleMenuRow!=existedChannelIds.size()){
				return false;
			}
			roleCache.removeChannelIds(roleId, existedChannelIds);
		}
		if(channelIds!=null && channelIds.size()>0){
			int flag=channelIds.size();
			for (Long channelId : channelIds) {
				RoleChannel roleChannel=new RoleChannel();
				roleChannel.setRoleId(roleId);
				roleChannel.setCrUser(crtUser.getUserName());
				roleChannel.setCrTime(new Timestamp(new Date().getTime()));
				roleChannel.setChannelId(channelId);
				int addRoleMenu=roleChannelDao.insert(roleChannel);
				if(addRoleMenu>0){
					flag--;
				}
				roleCache.addChannelIds(roleId, channelIds);
			}
			if(flag==0){
				return true;
			}
		}
		return true;
	}
}
