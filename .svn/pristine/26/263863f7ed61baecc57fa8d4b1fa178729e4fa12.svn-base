package com.topstar.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.ChannelCache;
import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.cache.RoleCache;
import com.topstar.volunteer.common.Constant;
import com.topstar.volunteer.dao.UserChannelDao;
import com.topstar.volunteer.dao.UserDao;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.RoleUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.entity.UserChannel;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.MenuView;
import com.topstar.volunteer.service.MenuService;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.service.RoleUserService;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.secrecy.MD5Util;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private RoleUserService roleUserService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleCache roleCache;

	@Autowired
	private LoginUserCache userCache;
	
	@Autowired
	private UserChannelDao userChannelDao;
	
	@Autowired
	private ChannelCache channelCache;
	
	
	@Override
	public List<User> selectByUser(User user, int page, int rows) {
		return userDao.selectByUser(user, page, rows);
	}

	@Override
	public PageInfo<User> findByEntity(User user,String orderBy, int pageIndex, int pageSize) {
		return userDao.findByEntity(user, orderBy, pageIndex, pageSize);
	}
	
	public User findByUserName(String userName){
		return userDao.findByUserName(userName);
	}

	@Override
	public boolean deleteUsers(long[] userKeys) {
		if(userKeys!=null && userKeys.length>0){
			User user=new User();
			int rows=userKeys.length;
			int flag=0;
			for (int i = 0; i < userKeys.length; i++) {
				user.setId(userKeys[i]);
				user.setStatus(04);
				flag=updateNotNull(user);
				if(flag>0){
					roleUserService.deleteRoleUserByUser(user.getId());
					boolean delCache=removeUserToCache(user.getId());
					ShiroSessionMgr.kickUserByUserId(user.getId(),true);
					if(!delCache){
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
	public int setUsersStatus(String userKeys,int status) {
		if(StringUtils.isBlank(userKeys) || status<1 || status>3){
			return 1;
		}
		String[] keys=userKeys.split(",");
		User user=new User();
		int rows=keys.length;
		int flag=0;
		for (int i = 0; i < keys.length; i++) {
			user.setId(Long.valueOf(keys[i]));
			user.setStatus(status);
			flag=updateNotNull(user);
			if(flag>0){
				rows--;
			}
		}
		return rows;
	}

	@Override
	public int existsWithUserName(String userName, String excludeKey) {
		if("admin".equalsIgnoreCase(userName)){
			return 0;
		}
		Example example=new Example(User.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userName", userName);
		if (StringUtils.isNotEmpty(excludeKey)) {
			criteria.andNotEqualTo("id", excludeKey);
		}
		List<User> users=selectByExample(example);
		if(users!=null && users.size()!=0){
			return 1;
		}
		return -1;
	}

	@Override
	public Boolean addUser(User user,List<Long> roleIds) {

		long userId;
		RoleUser roleUser;
		OrgUser orgUser;
		int rowUser= insertNotNull(user);
		if(rowUser>0){
			userId=user.getId();
			for (long roleId : roleIds) {
				roleUser=new RoleUser();
				roleUser.setCrTime(new Timestamp(new Date().getTime()));
				roleUser.setCrUser(user.getCrUser());
				roleUser.setUserId(userId);
				roleUser.setRoleId(roleId);
				roleUserService.insertNotNull(roleUser);
			}
			if(user.getOrgId()!=null && user.getOrgId()>0){
				orgUser=new OrgUser();
				orgUser.setUserId(userId);
				orgUser.setOrgId(user.getOrgId());
				orgUser.setCrTime(new Timestamp(new Date().getTime()));
				orgUser.setCrUser(user.getCrUser());
				orgUserService.insert(orgUser);
			}
			return true;
			
		}
		
		return false;
	}

	@Override
	public boolean existsWithRoleUser(Long userId, Long roleId) {
		
		Example example=new Example(RoleUser.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("roleId", roleId);
		List<RoleUser> roleUsers=roleUserService.selectByExample(example);
		if(roleUsers !=null && roleUsers.size()!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(User user, List<Long> roleIds) {
		int updateRow=updateNotNull(user);
		if(updateRow>0){
			userCache.update(user);
			Long userId=user.getId();
			List<RoleUser> roleUsers=roleUserService.getRoleUserByUser(userId);
			if(roleUsers!=null && roleUsers.size()>0){
				int cacheIds=roleUsers.size();
				Example example=new Example(RoleUser.class);
				Criteria criteria=example.createCriteria();
				criteria.andEqualTo("userId", userId);
				int delRoleUserRow=roleUserService.deleteByExample(example);
				if(delRoleUserRow!=cacheIds){
					return false;
				}
				for (RoleUser roleUser : roleUsers) {
					userCache.removeRole(userId, roleUser.getRoleId());
				}
			}
			if(roleIds!=null && roleIds.size()!=0){
				for (Long roleId : roleIds) {
					RoleUser roleUser=new RoleUser();
					roleUser.setRoleId(roleId);
					roleUser.setUserId(userId);
					roleUser.setCrUser(user.getCrUser());
					roleUser.setCrTime(new Timestamp(new Date().getTime()));
					int addRoleUserRow=roleUserService.insertNotNull(roleUser);
					if(addRoleUserRow==0){
						return false;
					}
					userCache.addRole(userId, roleId);
				}
				
			}
			Long orgId=user.getOrgId();
				OrgUser orgUser=orgUserService.getOrgUserByUserId(userId);
				if(orgUser!=null){
					if(orgId!=null && orgId>0){
						orgUser.setOrgId(orgId);
						orgUser.setCrTime(new Timestamp(new Date().getTime()));
						orgUser.setCrUser(user.getCrUser());
						int updateOrgUser =orgUserService.updateNotNull(orgUser);
						if(updateOrgUser ==0){
							return false;
						}
					}else{
						int delOrgUser=orgUserService.deleteByKey(orgUser.getId());
						if(delOrgUser==0){
							return false;
						}
					}
				}else{
					if(orgId!=null && orgId>0){
						orgUser=new OrgUser();
						orgUser.setUserId(userId);
						orgUser.setOrgId(orgId);
						orgUser.setCrTime(new Timestamp(new Date().getTime()));
						orgUser.setCrUser(user.getCrUser());
						int addOrgUser =orgUserService.insert(orgUser);
						if(addOrgUser ==0){
							return false;
						}
					}
				}
			return true;
		}
		return false;
	}
	
	/**
	 * 用户登录成功后，将用户添加到缓存中
	 * @param user 用户信息
	 * @return
	 */
	public boolean addUserToCache(User user){
		if(user == null || user.getId() == null){
			return false;
		}
		return userCache.add(user);
	}
	
	/**
	 * 用户退出时将用户从缓存中移除
	 * @param userId
	 * @return
	 */
	public boolean removeUserToCache(Long userId){
		if(userId == null || userId.longValue() < 1){
			return false;
		}
		return userCache.delete(userId);
	}
	
	/**
	 * 根据用户编号查询此用户拥有的角色名称
	 * @param id 用户编号
	 * @return
	 */
	@Override
	public Set<String> findRoleNames(Long id) {
		User user = userCache.findById(id);
		if(user == null){
			return null;
		}
		Set<String> roleNames = new HashSet<String>();
		List<Long> roleIdList = user.getRoleIdList();
		if(roleIdList != null && !roleIdList.isEmpty()){
			for(Long roleId:roleIdList){
				Role role = roleCache.findById(roleId);
				if(role != null && role.getStatus().intValue() == 1){
					roleNames.add(role.getRoleName());
				}
			}
			return roleNames;
		}
		return null;
	}
	
	
	
	/**
	 * 根据用户编号查询此用户可访问的权限请求信息
	 * @param id 用户编号
	 * @return
	 */
	@Override
	public Set<String> findPermissions(Long id) {
		User user = userCache.findById(id);
		if(user == null){
			return null;
		}
		boolean isAdmin = false;
		Map<Long, Menu> menuMap = user.getMenuMap();
		List<Long> roleIdList = user.getRoleIdList();
		Map<Long, Menu> tempMap = new HashMap<Long, Menu>();
		if(roleIdList != null && !roleIdList.isEmpty()){
			for(Long roleId:roleIdList){
				Role role = roleCache.findById(roleId);
				if(role != null && role.getStatus().intValue() == 1){
					if(Constant.ADMIN.equals(role.getRoleName())){
						isAdmin = true;
					}
					Map<Long, Menu> roleMenuMap = role.getMenuMap();
					if(roleMenuMap != null && !roleMenuMap.isEmpty()){
						if(isAdmin){
							tempMap.clear();
						}
						tempMap.putAll(roleMenuMap);
					}
					if(isAdmin){
						break;
					}
				}
			}
		}
		//用户本身拥有的权限
		if(!isAdmin){
			if(menuMap != null && !menuMap.isEmpty()){
				tempMap.putAll(menuMap);
			}
		}
		
		if(tempMap != null && !tempMap.isEmpty()){
			Iterator<Menu> iter = tempMap.values().iterator();
			Set<String> permissions = new HashSet<String>();
			while(iter.hasNext()){
				Menu menu = iter.next();
				if(menu.getIsShow() == 1){
					String menuUrl = menu.getUrl();
					if(StringUtils.isNotBlank(menuUrl)){
						String[] urls = menuUrl.split(",");
						if(urls != null && urls.length > 0){
							for(int i=0 ;i < urls.length;i++){
								String url = urls[i];
								if(StringUtils.isNotBlank(url)){
									permissions.add(url);
								}
							}
						}
					}
				}
			}
			return permissions;
		}
		return null;
	}

	/**
	 * 根据用户编号查询此用户可访问的菜单信息
	 * @param id
	 * @return
	 */
	public List<MenuView> findMenusByUserId(Long id){
		User user = userCache.findById(id);
		if(user == null){
			return null;
		}
		boolean isAdmin = false;
		List<Long> roleIdList = user.getRoleIdList();
		Map<Long, Menu> tempMap = new HashMap<Long, Menu>();
		if(roleIdList != null && !roleIdList.isEmpty()){
			for(Long roleId:roleIdList){
				Role role = roleCache.findById(roleId);
				if(role != null && role.getStatus().intValue() == 1){
					if(Constant.ADMIN.equals(role.getRoleName())){
						isAdmin = true;
					}
					Map<Long, Menu> roleMenuMap = role.getMenuMap();
					if(roleMenuMap != null && !roleMenuMap.isEmpty()){
						if(isAdmin){
							tempMap.clear();
						}
						tempMap.putAll(roleMenuMap);
					}
					if(isAdmin){
						break;
					}
				}
			}
		}
		if(!isAdmin){
			Map<Long, Menu> menuMap = user.getMenuMap();
			if(menuMap != null && !menuMap.isEmpty()){
				tempMap.putAll(menuMap);
			}
		}
		Map<Long, MenuView> menuHashMap = new HashMap<Long,MenuView>();
		//集合深度拷贝，修改菜单的URL，避免影响缓存数据
		List<Menu> tempList = new ArrayList<Menu>(tempMap.values());
		List<Menu> menuList = new ArrayList<Menu>();
		for(Menu m : tempList){
			menuList.add(m.clone());
		}
		if(menuList != null && !menuList.isEmpty()){
			for(Menu m : menuList){
				if(m.getMenuType().intValue() == 1){
					continue;
				}
				if(m.getIsShow().intValue() == 0){
					continue;
				}
				String url = m.getUrl();
				if(StringUtils.isNotBlank(url)){
					String[] urls = url.split(",");
					if(urls != null && urls.length > 1){
						for(int i = 0 ; i < urls.length;i++){
							if(urls[i].lastIndexOf(".html") > -1){
								url = urls[i];
								break;
							}
						}
						m.setUrl(url);
					}
				}
				long parentId = m.getParentId().longValue();
				if(parentId == 0){
					if(menuHashMap.get(m.getId()) != null){
						continue;
					}
					menuHashMap.put(m.getId(), new MenuView(m));
				}else{
					MenuView parentMenu = menuHashMap.get(m.getParentId());
					if(parentMenu == null){
						Menu menu = tempMap.get(m.getParentId());
						if(menu != null){
							parentMenu = new MenuView(menu);
							if(parentMenu.getParentId() == 0){
								menuHashMap.put(parentMenu.getId(), parentMenu);
							}
						}
					}
					List<MenuView> menuViews = parentMenu.getMenuViews();
					if(menuViews == null){
						menuViews = new ArrayList<MenuView>();
						parentMenu.setMenuViews(menuViews);
					}
					menuViews.add(new MenuView(m));
					if(menuViews.size() > 0){
						Collections.sort(menuViews);
					}
				}
			}
		}
		if(menuHashMap.size() > 0){
			MenuViewComparator comparator = new MenuViewComparator(menuHashMap);
			Map<Long, MenuView> menuTreeMap = new TreeMap<Long,MenuView>(comparator);
			menuTreeMap.putAll(menuHashMap);
			return new ArrayList<MenuView>(menuTreeMap.values());
		}
		return new ArrayList<MenuView>(menuHashMap.values());
	}
	
	
	/**
	 * 根据用户的主键值重置用户的密码为初始值密码
	 * @param ids 用户的主键值数组
	 * @return 是否重置密码操作成功，成功返回：0
	 * @throws TPSException 
	 */
	public int resetUsersPassword(long[] ids){
		int rows=1;
		if(ids!=null && ids.length>0){
			Config defPassConfig=ConfigUtils.getConfigsByName("DEFAULT_PASSWORD");
			if(defPassConfig!=null && StringUtils.isNoneBlank(defPassConfig.getContent())){
				String encodePass=MD5Util.encode(defPassConfig.getContent());
				User user=new User();
				rows=ids.length;
				int flag=0;
				try{
					for (int i = 0; i < ids.length; i++) {
						user.setId(ids[i]);
						user.setUserPwd(encodePass);
						flag=updateNotNull(user);
						if(flag>0){
							rows--;
						}
					}
				}catch (Exception e) {
					throw new RuntimeException("重置用户密码出现错误", e);
				}
				return rows;
			}else{
				throw new IllegalArgumentException("没有设置初始密码的系统配置");
			}

		}else{
			throw new IllegalArgumentException("设置用户初始密码参数不合法");
		}
	}

	@Override
	public int editUserPwd(Long id, String userPwd) {
		if(id == 0 || userPwd== null){
			return 0;
		}
		return userDao.editUserPwd(id, userPwd);
	}
	
	/**
	 * 通过用户ID查询可访问的栏目ID
	 * @param userId
	 * @return
	 */
	public List<Long> getChannelIds(Long userId){
		return userChannelDao.getChannelIds(userId);
	}
	
	/**
	 * 给指定的用户分配栏目可访问权限
	 * @param userId
	 * @param channelIds
	 * @return
	 */
	public boolean addUserChannels(Long userId,List<Long> channelIds){
		if(userId ==null || userId<0){
			return false;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser==null){
			return false;
		}
		List<Long> existedChannelIds=getChannelIds(userId);
		if(existedChannelIds!=null){
			Example example=new Example(UserChannel.class);
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("userId", userId);
			int delRoleMenuRow=userChannelDao.deleteByExample(example);
			if(delRoleMenuRow!=existedChannelIds.size()){
				return false;
			}
			userCache.removeChannelIds(userId, existedChannelIds);
		}
		if(channelIds!=null && channelIds.size()>0){
			int flag=channelIds.size();
			for (Long channelId : channelIds) {
				UserChannel userChannel=new UserChannel();
				userChannel.setUserId(userId);
				userChannel.setCrUser(crtUser.getUserName());
				userChannel.setCrTime(new Timestamp(new Date().getTime()));
				userChannel.setChannelId(channelId);
				int addRoleMenu=userChannelDao.insert(userChannel);
				if(addRoleMenu>0){
					flag--;
				}
			}
			userCache.addChannelIds(userId, channelIds);
			if(flag==0){
				return true;
			}
		}
		return true;
	}
	
	/**
	 * 获取已登录用户可访问的栏目集合
	 * @return
	 */
	public List<Channel> getAllChannelNotInRecycle(){
		BaseUser baseUser = ShiroSessionMgr.getLoginUser();
		if(baseUser != null){
			if(baseUser.isAdmin()){
				List<Channel> list = channelCache.getAllNotInRecycle();
				return list;
			}else{
				User user = userCache.findById(baseUser.getId());
				if(user != null){
					List<Long> channelIds = new ArrayList<Long>();
					
					///获取用户对应角色可见的栏目ID
					List<Long> roleIds = user.getRoleIdList();
					if(roleIds != null){
						for(Long roleId : roleIds){
							List<Long> tempChannelIds = roleCache.getChannelIds(roleId);
							if(tempChannelIds != null && !tempChannelIds.isEmpty()){
								channelIds.addAll(tempChannelIds);
							}
						}
					}
					 
					//获取用户本身可见的栏目ID
					 List<Long> userChannelIds = user.getChannelIds();
					 if(userChannelIds != null && !userChannelIds.isEmpty()){
						 channelIds.addAll(userChannelIds);
					 }
					 
					 //去除重复栏目ID
					 if(channelIds != null && !channelIds.isEmpty()){
						 for(int i=0 ;i<channelIds.size()-1;i++){
							 for(int j=channelIds.size()-1;j>i;j--){
								 if(channelIds.get(j).equals(channelIds.get(i))){
									 channelIds.remove(j);
						        }
						    }
						}
					 }
					 
					 if(channelIds != null && !channelIds.isEmpty()){
						 List<Channel> channels = new ArrayList<Channel>();
						 for(Long channelId : channelIds){
							 Channel channel = channelCache.findById(channelId);
							 if(channel != null && channel.getStatus().longValue() == 1){
								 channels.add(channel.clone());
							 }
						 }
						 
						 if( !channels.isEmpty()){
								Map<Long, Channel> map = new TreeMap<Long,Channel>();
								for(Channel channel :channels){
									if(channel != null){
										map.put(channel.getId(), channel);
									}
								}
								for(Channel channel : channels){
									Long parentId = channel.getParentId();
									Long tempParentId = parentId;
									
									while(tempParentId.longValue() > 0L && (map.get(tempParentId) == null || map.get(tempParentId).getStatus().intValue() != 1)){
										Channel parentChannel = channelCache.findById(tempParentId);
										tempParentId = parentChannel.getParentId();
									}
									
									if(tempParentId.longValue() > 0L){
										if(tempParentId.longValue() != parentId.longValue()){
											channel.setParentId(tempParentId);
										}
									}else if(tempParentId.longValue() == 0L){
										channel.setParentId(0L);
									}
								}
								Collections.sort(channels);
								List<Channel> channelList = new ArrayList<Channel>();
								sortList(channelList, channels, 0L);
								return channelList;
							}
						 
						 return channels;
					 }
					 
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取已登录用户可访问的栏目集合
	 * @param siteId 站点ID
	 * @return
	 */
	public List<Channel> getAllChannelNotInRecycle(Long siteId){
		BaseUser baseUser = ShiroSessionMgr.getLoginUser();
		List<Channel> channels = new ArrayList<Channel>();
		if(baseUser != null){
			if(baseUser.isAdmin()){
				channels = channelCache.getAllBySiteIdNotInRecycle(siteId);
			}else{
				User user = userCache.findById(baseUser.getId());
				if(user != null){
					List<Long> channelIds = new ArrayList<Long>();
					
					///获取用户对应角色可见的栏目ID
					List<Long> roleIds = user.getRoleIdList();
					if(roleIds != null){
						for(Long roleId : roleIds){
							List<Long> tempChannelIds = roleCache.getChannelIds(roleId);
							if(tempChannelIds != null && !tempChannelIds.isEmpty()){
								channelIds.addAll(tempChannelIds);
							}
						}
					}
					 
					//获取用户本身可见的栏目ID
					 List<Long> userChannelIds = user.getChannelIds();
					 if(userChannelIds != null && !userChannelIds.isEmpty()){
						 channelIds.addAll(userChannelIds);
					 }
					 
					 //去除重复栏目ID
					 if(channelIds != null && !channelIds.isEmpty()){
						 for(int i=0 ;i<channelIds.size()-1;i++){
							 for(int j=channelIds.size()-1;j>i;j--){
								 if(channelIds.get(j).equals(channelIds.get(i))){
									 channelIds.remove(j);
						        }
						    }
						}
					 }
					 
					 if(channelIds != null && !channelIds.isEmpty()){
						 for(Long channelId : channelIds){
							 Channel channel = channelCache.findById(channelId);
							 if(channel != null && channel.getSiteId().longValue() == siteId.longValue()
									 && channel.getStatus().longValue() == 1){
								 channels.add(channel.clone());
							 }
						 }
					 }
				}
			}
			
			if( !channels.isEmpty()){
				Map<Long, Channel> map = new TreeMap<Long,Channel>();
				for(Channel channel :channels){
					if(channel != null){
						map.put(channel.getId(), channel);
					}
				}
				for(Channel channel : channels){
					Long parentId = channel.getParentId();
					Long tempParentId = parentId;
					
					while(tempParentId.longValue() > 0L && (map.get(tempParentId) == null || map.get(tempParentId).getStatus().intValue() != 1)){
						Channel parentChannel = channelCache.findById(tempParentId);
						tempParentId = parentChannel.getParentId();
					}
					
					if(tempParentId.longValue() > 0L){
						if(tempParentId.longValue() != parentId.longValue()){
							channel.setParentId(tempParentId);
						}
					}else if(tempParentId.longValue() == 0L){
						channel.setParentId(0L-siteId);
					}
				}
				Collections.sort(channels);
				List<Channel> channelList = new ArrayList<Channel>();
				sortList(channelList, channels, 0L-siteId);
				return channelList;
			}
		}
		return null;
	 }
	
	
	/**
	 * 将栏目集合按父子栏目顺序排序
	 * @param resultList
	 * @param list
	 * @param id
	 */
	 private void sortList(List<Channel> resultList,List<Channel> list,long id) {  
        for (Channel channel :list) {
            if (channel.getParentId().longValue() == id) {
                resultList.add(channel);
                sortList(resultList,list,channel.getId());
            }
        }
    }
	
	/**
	 * 获取已登录用户可访问的栏目集合
	 * @param siteId 站点ID
	 * @return
	 */
	public List<Long> getAllChannelIdsNotINRecycle(Long siteId){
		BaseUser baseUser = ShiroSessionMgr.getLoginUser();
		List<Long> channels = new ArrayList<Long>();
		if(baseUser != null){
			if(baseUser.isAdmin()){
				channels = channelCache.getAllChannelIdsNotInRecycle(siteId);
				return channels;
			}
			
			User user = userCache.findById(baseUser.getId());
			if(user != null){
				List<Long> channelIds = new ArrayList<Long>();
				
				///获取用户对应角色可见的栏目ID
				List<Long> roleIds = user.getRoleIdList();
				if(roleIds != null){
					for(Long roleId : roleIds){
						List<Long> tempChannelIds = roleCache.getChannelIds(roleId);
						if(tempChannelIds != null && !tempChannelIds.isEmpty()){
							channelIds.addAll(tempChannelIds);
						}
					}
				}
				 
				//获取用户本身可见的栏目ID
				 List<Long> userChannelIds = user.getChannelIds();
				 if(userChannelIds != null && !userChannelIds.isEmpty()){
					 channelIds.addAll(userChannelIds);
				 }
				 
				 //去除重复栏目ID
				 if(channelIds != null && !channelIds.isEmpty()){
					 for(int i=0 ;i<channelIds.size()-1;i++){
						 for(int j=channelIds.size()-1;j>i;j--){
							 if(channelIds.get(j).equals(channelIds.get(i))){
								 channelIds.remove(j);
					        }
					    }
					}
				 }
				 
				 //过滤掉被存放在回收站的栏目
				 List<Long> ids = new ArrayList<Long>();
				 if(channelIds != null && !channelIds.isEmpty()){
					 for(Long channelId : channelIds){
						 Channel channel = channelCache.findById(channelId);
						 if(channel != null && channel.getSiteId().longValue() == siteId.longValue()
								 && channel.getStatus().longValue() == 1){
							 ids.add(channel.getId());
						 }
					 }
				 }
				 return ids;
			}
		}
		return null;
	}

	 
	/**
	 * 菜单排序比较类
	 */
	private class MenuViewComparator implements Comparator<Long> {  
		  
		Map<Long, MenuView> map;
		
	    protected MenuViewComparator(Map<Long, MenuView> map) {  
	        this.map = map;  
	    }  
	  
	    public int compare(Long a, Long b) {  
	        return map.get(a).compareTo(map.get(b));
	    }  
	}
}
