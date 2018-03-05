package com.topstar.volunteer.cache.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.dao.MenuDao;
import com.topstar.volunteer.dao.UserDao;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.entity.User;

public class LoginUserCacheImpl implements LoginUserCache {

	private static Logger logger = LoggerFactory.getLogger(LoginUserCacheImpl.class);
	
	private CacheManager cacheManager;
	
	private UserDao userDao;
	
	private MenuDao menuDao;
	
	private String cacheName="LOGIN_USER";

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public synchronized boolean init(){
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		return true;
	}
	
	/**
	 * 添加用户<br/>
	 * 用户登录后添加到缓存中
	 * @param user
	 * @return
	 */
	@Override
	public boolean add(User user) {
		if(user != null && user.getId() != null){
			logger.debug("开始将[userId={}]登录用户添加到缓存中",user.getId());
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Long userId = user.getId();
			List<Menu> menus = menuDao.findMenusByUserId(userId);
			if(menus != null){
				logger.debug("查询到对应的菜单权限{}条",menus.size());
				logger.debug("查询到对应的菜单权限具体信息:{}",menus);
				Map<Long, Menu> menuMap = menuListToMap(menus);
				user.setMenuMap(menuMap);
			}
			List<Long> roleIdList = userDao.findRoleIdsByUserId(userId);
			if(roleIdList != null){
				logger.debug("查询到对应的角色权限{}条",roleIdList.size());
				user.setRoleIdList(roleIdList);
			}
			
			List<Long> channelIds = userDao.getChannelIds(userId);
			if(channelIds != null){
				logger.debug("查询到对应的栏目信息{}条",channelIds.size());
				user.setChannelIds(channelIds);
			}
			
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element=new Element(userId, user);
				cache.put(element);
				logger.debug("完成[userId={}]用户的添加",user.getId());
			} catch (Exception e) {
				logger.error("添加[userId={}]用户到缓存出错",userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * 修改登录用户基本信息时，更新缓存中的用户信息
	 * @param user 修改后的用户信息
	 * @return
	 */
	@Override
	public boolean update(User user) {
		if(user != null && user.getId() != null){
			Long userId = user.getId();
			if(userId == null){
				return false;
			}
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				User cacheUser = null;
				if(element != null){
					cacheUser = (User) element.getObjectValue();
				}
				if(cacheUser == null){
					return false;
				}
				Map<Long, Menu> cacheMenuMap = cacheUser.getMenuMap();
				List<Long> roleIdList = cacheUser.getRoleIdList();
				if(cacheMenuMap != null && !cacheMenuMap.isEmpty()){
					user.setMenuMap(cacheMenuMap);
				}
				if(roleIdList != null && !roleIdList.isEmpty()){
					user.setRoleIdList(roleIdList);
				}
				List<Long> channelIdList = cacheUser.getChannelIds();
				if(channelIdList != null && channelIdList.size() > 0){
					user.setChannelIds(channelIdList);
				}
				Element ele=new Element(userId, user);
				cache.put(ele);
				return true;
			} catch (Exception e) {
				logger.error("将[userId={}]用户更新到缓存时出错",userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return false;
	}


	/**
	 * 删除用户<br/>
	 * 用户退出后从缓存中移除
	 * @param userId
	 * @return
	 */
	@Override
	public boolean delete(Long userId) {
		if(userId != null && userId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				cache.remove(userId);
				logger.debug("已将[userId={}]用户从缓存中移除",userId);
				return true;
			} catch (Exception e) {
				logger.error("将[userId={}]用户从缓存中移除出错",userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return false;
	}

	/**
	 * 添加菜单<br/>
	 * 为用户单独设置菜单权限时，将此权限添加到缓存的对应的用户中
	 * @param userId 用户编号
	 * @param menuId 菜单编号
	 * @return
	 */
	@Override
	public boolean addMenu(Long userId, Long menuId) {
		if(userId != null && menuId != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Menu menu = menuDao.selectByKey(menuId);
			if(menu == null || menu.getIsShow().intValue() == 0){
				return false;
			}
			try{
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				if(element == null){
					return false;
				}
				
				User user = (User) element.getObjectValue();
				if(user != null){
					Map<Long, Menu> menuMap = user.getMenuMap();
					if(menuMap == null){
						menuMap = new HashMap<Long,Menu>();
					}
					menuMap.put(menuId, menu);
					user.setMenuMap(menuMap);
					cache.remove(userId);
					element = new Element(userId, user);
					cache.put(element);
				}
			}catch(Exception e){
				logger.error("将[menuId={}]菜单添加到[userId={}]用户的缓存中出错",menuId,userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return true;
	}

	/**
	 * 删除菜单<br/>
	 * 删除用户的菜单权限时，将此权限信息从缓存中移除
	 * @param userId 用户编号
	 * @param menuId 菜单编号
	 * @return
	 */
	@Override
	public boolean removeMenu(Long userId, Long menuId) {
		if(userId != null && menuId != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				if(element == null){
					return false;
				}
				User user = (User) element.getObjectValue();
				if(user != null){
					Map<Long, Menu> menuMap = user.getMenuMap();
					if(menuMap != null && !menuMap.isEmpty()){
						menuMap.remove(menuId);
					}
					user.setMenuMap(menuMap);
					cache.remove(userId);
					element = new Element(userId, user);
					cache.put(element);
				}
			} catch (Exception e) {
				logger.error("将[menuId={}]菜单从[userId={}]用户的缓存中移除出错",menuId,userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return true;
	}

	/**
	 * 添加角色<br/>
	 * 当为用户添加角色时，如果用户已登录，将角色信息更新到缓存中
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return
	 */
	@Override
	public boolean addRole(Long userId, Long roleId) {
		if(userId != null && roleId != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				if(element == null){
					return false;
				}
				User user = (User) element.getObjectValue();
				if(user != null){
					logger.debug("从缓存中开始添加[userId={}]用户",userId);
					List<Long> roleIdList = user.getRoleIdList();
					if(roleIdList == null){
						roleIdList = new ArrayList<Long>();
					}
					roleIdList.add(roleId);
					user.setRoleIdList(roleIdList);
					cache.remove(userId);
					element = new Element(userId, user);
					cache.put(element);
				}
			} catch (Exception e) {
				logger.error("将[roleId={}]角色添加到[userId={}]用户的缓存中出错",roleId,userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return true;
	}

	/**
	 * 添加角色<br/>
	 * 当为用户添加角色时，如果用户已登录，将角色信息更新到缓存中
	 * @param userId 用户编号
	 * @param roleId 角色编号数组
	 * @return
	 */
	@Override
	public boolean addRoles(Long userId, Long[] roleIds) {
		if(userId != null && roleIds != null && roleIds.length > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				if(element == null){
					return false;
				}
				User user = (User) element.getObjectValue();
				if(user != null){
					List<Long> roleIdList = user.getRoleIdList();
					if(roleIdList == null){
						roleIdList = new ArrayList<Long>();
					}
					for(Long roleId:roleIds){
						roleIdList.add(roleId);
						logger.debug("完成将[roleId={}]角色添加[userId={}]用户",roleId,userId);
					}
					user.setRoleIdList(roleIdList);
					cache.remove(userId);
					element = new Element(userId, user);
					cache.put(element);
				}
			} catch (Exception e) {
				logger.error("将[roleIds={}]角色添加到[userId={}]用户的缓存中出错",Arrays.toString(roleIds),userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return true;
	}
	
	/**
	 * 移除角色<br/>
	 * 当用户删除角色时，如果用户已登录，将角色信息从缓存中移除
	 * @param userId 用户编号
	 * @param roleId 角色编号数组
	 * @return
	 */
	@Override
	public boolean removeRole(Long userId, Long roleId) {
		if(userId != null && roleId != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				if(element == null){
					return false;
				}
				User user = (User) element.getObjectValue();
				if(user != null){
					List<Long> roleIdList = user.getRoleIdList();
					if(roleIdList != null && !roleIdList.isEmpty()){
						roleIdList.remove(roleId);
					}
					user.setRoleIdList(roleIdList);
					cache.remove(userId);
					element = new Element(userId, user);
					cache.put(element);
				}
			} catch (Exception e) {
				logger.error("将[roleId={}]角色从[userId={}]用户的缓存中移除出错",roleId,userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return true;
	}

	/**
	 * 移除角色<br/>
	 * 当用户删除角色时，如果用户已登录，将角色信息从缓存中移除
	 * @param userId 用户编号
	 * @param roleId 角色编号数组
	 * @return
	 */
	@Override
	public boolean removeRoles(Long userId, Long[] roleIds) {
		if(userId != null && roleIds != null && roleIds.length > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				if(element == null){
					return false;
				}
				User user = (User) element.getObjectValue();
				if(user != null){
					List<Long> roleIdList = user.getRoleIdList();
					if(roleIdList != null && !roleIdList.isEmpty()){
						for(Long roleId : roleIds){
							roleIdList.remove(roleId);
						}
					}
					user.setRoleIdList(roleIdList);
					cache.remove(userId);
					element = new Element(userId, user);
					cache.put(element);
				}
			} catch (Exception e) {
				logger.error("将[roleIds={}]角色从[userId={}]用户的缓存中移除出错",Arrays.toString(roleIds),userId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return true;
	}
	
	/**
	 * 更新角色信息<br/>
	 * 当为用户更新角色时，如果用户已登录，将角色信息在缓存中更新<br/>
	 * 清空用户对应的角色编号数组信息，并将roleIds写入到缓存中<br/>
	 * 如果角色数组为空，清除此用户的所有角色信息
	 * @param userId 用户编号
	 * @param roleIds 需要更新的角色编号数组,数组为空，清除此用户的所有角色信息
	 * @return
	 */
	public boolean updateRoles(Long userId,Long[] roleIds){
		if(userId != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			try {
				cache.acquireWriteLockOnKey(userId);
				Element element = cache.get(userId);
				if(element == null){
					return false;
				}
				User user = (User) element.getObjectValue();
				if(user != null){
					List<Long> roleIdList = user.getRoleIdList();
					if(roleIds == null || roleIds.length == 0){
						roleIdList = new ArrayList<Long>();
					}else{
						if(roleIdList != null && !roleIdList.isEmpty()){
							List<Long> tempList = new ArrayList<Long>();
							for(int i = 0 ; i < roleIds.length;i++){
								tempList.add(roleIds[i]);
							}
							if(!tempList.isEmpty()){
								roleIdList = tempList;
							}
						}
					}
					user.setRoleIdList(roleIdList);
					cache.remove(userId);
					element = new Element(userId, user);
					cache.put(element);
				}
			} catch (Exception e) {
				logger.error("更新[userId={}]用户缓存中的[roleIds={}]角色编号出错",userId,Arrays.toString(roleIds),e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(userId);
			}
		}
		return true;
	}
	
	
	@Override
	public User findById(Long userId) {
		if(userId == null || userId.longValue() < 1){
			return null;
		}
		logger.debug("开始查询缓存中存在的[userId={}]用户信息",userId);
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		Element element = null;
		try {
			cache.acquireReadLockOnKey(userId);
			element = cache.get(userId);
		} catch (Exception e) {
			logger.error("从缓存中读取[userId={}]用户出错",userId,e);
		}finally{
			cache.releaseReadLockOnKey(userId);
		}
		if(element != null){
			User user = (User) element.getObjectValue();
			logger.debug("查询到对应的[userId={}]用户信息,具体信息为:{}",userId,user);
			return user;
		}
		return null;
	}

	
	private Map<Long, Menu> menuListToMap(List<Menu> menus){
		if(menus != null && !menus.isEmpty()){
			Map<Long, Menu> menuMap = new HashMap<Long,Menu>();
			for(Menu menu:menus){
				menuMap.put(menu.getId(), menu);
			}
			return menuMap;
		}
		return null;
	}

	/**
	 * 设置菜单是否可见时，同步设置所有用户缓存中对应的菜单是否可见
	 * @param menuId 菜单编号
	 * @param isShow 是否可见
	 * @return
	 */
	@Override
	public boolean setShowMenu(Long menuId, Integer isShow) {
		if(menuId != null && menuId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Long> userIdList = cache.getKeys();
			if(userIdList != null && !userIdList.isEmpty()){
				for(Long userId : userIdList){
					try {
						cache.acquireWriteLockOnKey(userId);
						Element element = cache.get(userId);
						if(element != null){
							User user = (User) element.getObjectValue();
							if(user != null){
								Map<Long, Menu> menuMap = user.getMenuMap();
								if(menuMap != null && !menuMap.isEmpty()){
									Menu menu = menuMap.get(menuId);
									if(menu != null){
										menu.setIsShow(isShow);
										menuMap.put(menuId, menu);
										user.setMenuMap(menuMap);
									}
								}
							}
						}
					} catch (Exception e) {
						logger.warn("为缓存的[useId={}]用户设置[menuId={}]菜单是否可见[isShow={}]时出错",userId,menuId,isShow,e);
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 系统删除菜单时，删除缓存中对应的菜单信息
	 * @param menuId
	 * @return
	 */
	@Override
	public boolean updateMenu(Menu menu) {
		if(menu != null && menu.getId() != null && menu.getId().longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Long> userIdList = cache.getKeys();
			if(userIdList != null && !userIdList.isEmpty()){
				for(Long userId : userIdList){
					try {
						cache.acquireWriteLockOnKey(userId);
						Element element = cache.get(userId);
						if(element != null){
							User user = (User) element.getObjectValue();
							if(user != null){
								Map<Long, Menu> menuMap = user.getMenuMap();
								if(menuMap != null && !menuMap.isEmpty()){
									Menu m = menuMap.get(menu.getId());
									if(m != null){
										menuMap.put(menu.getId(), menu);
										user.setMenuMap(menuMap);
									}
								}
							}
						}
					} catch (Exception e) {
						logger.warn("为缓存的[useId={}]用户更新[menuId={}]菜单时出错",userId,menu.getId().longValue(),e);
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 系统删除菜单时，删除缓存中对应的菜单信息
	 * @param menuId
	 * @return
	 */
	@Override
	public boolean removeMenu(Long menuId) {
		if(menuId != null && menuId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Long> userIdList = cache.getKeys();
			if(userIdList != null && !userIdList.isEmpty()){
				for(Long userId : userIdList){
					try {
						cache.acquireWriteLockOnKey(userId);
						Element element = cache.get(userId);
						if(element != null){
							User user = (User) element.getObjectValue();
							if(user != null){
								Map<Long, Menu> menuMap = user.getMenuMap();
								if(menuMap != null && !menuMap.isEmpty()){
									Menu menu = menuMap.get(menuId);
									if(menu != null){
										menuMap.remove(menuId);
										user.setMenuMap(menuMap);
									}
								}
							}
						}
					} catch (Exception e) {
						logger.warn("为缓存的[useId={}]用户移除[menuId={}]菜单时出错",userId,menuId,e);
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 删除角色时，为所有缓存的用户移除对应的角色<br/>
	 * 设置角色是否启用时不需要
	 * @param roleId
	 * @return
	 */
	@Override
	public boolean removeRole(Long roleId) {
		if(roleId != null && roleId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Long> userIdList = cache.getKeys();
			if(userIdList != null && !userIdList.isEmpty()){
				for(Long userId : userIdList){
					try {
						cache.acquireWriteLockOnKey(userId);
						Element element = cache.get(userId);
						if(element != null){
							User user = (User) element.getObjectValue();
							if(user != null){
								List<Long> roleIdList = user.getRoleIdList();
								if(roleIdList != null && !roleIdList.isEmpty()){
									roleIdList.remove(roleId);
									user.setRoleIdList(roleIdList);
								}
							}
						}
					} catch (Exception e) {
						logger.warn("为缓存的[useId={}]用户移除[roleId={}]权限时出错",userId,roleId,e);
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * 批量添加用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean addChannelIds(Long userId,List<Long> channelIds){
		if(userId != null && channelIds != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(userId);
				element = cache.get(userId);
			} catch (Exception e) {
				logger.error("从缓存中读取[userId={}]用户信息出错",userId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(userId);
			}
			User user = null;
			if(element != null){
			 	user = (User) element.getObjectValue();
				if(user != null){
					try {
						cache.acquireWriteLockOnKey(userId);
						List<Long> channelIdList = user.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.addAll(channelIds);
						}else{
							channelIdList = channelIds;
						}
						user.setChannelIds(channelIdList);
						return true;
					} catch (Exception e) {
						logger.error("将[channelIds={}]栏目集合添加到[userId={}]角色的缓存中出错",channelIds,userId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 批量移除用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean removeChannelIds(Long userId,List<Long> channelIds){
		if(userId != null && channelIds != null && channelIds.size() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(userId);
				element = cache.get(userId);
			} catch (Exception e) {
				logger.error("从缓存中读取[userId={}]用户信息出错",userId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(userId);
			}
			User user = null;
			if(element != null){
			 	user = (User) element.getObjectValue();
				if(user != null){
					try {
						cache.acquireWriteLockOnKey(userId);
					 	List<Long> channelIdList = user.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.removeAll(channelIds);
							user.setChannelIds(channelIds);
							cache.remove(userId);
							element = new Element(userId, user);
							cache.put(element);
						}
						return true;
					} catch (Exception e) {
						logger.error("将[channelIds={}]栏目集合从[userId={}]用户的缓存中移除出错",channelIds,userId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 添加用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean addChannelId(Long userId,Long channelId){
		if(userId != null && userId.longValue() > 0 && channelId != null && channelId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(userId);
				element = cache.get(userId);
			} catch (Exception e) {
				logger.error("从缓存中读取[userId={}]用户信息出错",userId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(userId);
			}
			User user = null;
			if(element != null){
			 	user = (User) element.getObjectValue();
				if(user != null){
					try {
						cache.acquireWriteLockOnKey(userId);
						List<Long> channelIdList = user.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.add(channelId);
						}else{
							channelIdList = new ArrayList<Long>();
							channelIdList.add(channelId);
						}
						user.setChannelIds(channelIdList);
						return true;
					} catch (Exception e) {
						logger.error("将[channelId={}]栏目添加到[roleId={}]用户的缓存中出错",channelId,userId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 移除用户可访问的栏目ID
	 * @param userId 用户ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean removeChannelId(Long userId,Long channelId){
		if(userId != null && userId.longValue() > 0 && channelId != null && channelId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(userId);
				element = cache.get(userId);
			} catch (Exception e) {
				logger.error("从缓存中读取[userId={}]用户信息出错",userId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(userId);
			}
			User user = null;
			if(element != null){
			 	user = (User) element.getObjectValue();
				if(user != null){
					try {
						cache.acquireWriteLockOnKey(userId);
					 	List<Long> channelIdList = user.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.remove(channelId);
							user.setChannelIds(channelIdList);
							cache.remove(userId);
							element = new Element(userId, user);
							cache.put(element);
						}
						return true;
					} catch (Exception e) {
						logger.error("将[channelId={}]栏目从[userId={}]用户的缓存中移除出错",channelId,userId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(userId);
					}
				}
			}
		}
		return false;
	}
	
}
