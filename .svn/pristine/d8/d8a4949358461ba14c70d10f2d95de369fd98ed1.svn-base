package com.topstar.volunteer.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.cache.RoleCache;
import com.topstar.volunteer.common.Constant;
import com.topstar.volunteer.dao.MenuDao;
import com.topstar.volunteer.dao.RoleDao;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.entity.Role;

/**
 * 角色缓存实现类，缓存系统内所有的角色信息，并缓存权限对应的菜单信息
 */
public class RoleCacheImpl implements RoleCache {

	private static Logger logger = LoggerFactory.getLogger(RoleCacheImpl.class);
	
	private CacheManager cacheManager;
	
	private RoleDao roleDao;
	
	private MenuDao menuDao;
	
	private String cacheName="SYS_ROLE";
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public synchronized boolean init() {
		logger.info("开始缓存所有的角色信息");
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		//查询所有的角色信息
		List<Role> roles = roleDao.selectAll();
		if(roles != null && !roles.isEmpty()){
			for(Role role:roles){
				Element e=new Element(role.getId(), role);
				cache.put(e);
			}
		}
		logger.info("缓存角色信息成功");
		return true;
	}

	@Override
	public boolean add(Role role) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		if(role != null && role.getId() != null){
			Long roleId = role.getId();
			List<Menu> menuList = menuDao.findMenuByRoleId(roleId);
			Map<Long, Menu> menuMap = menuListToMap(menuList);
			if(menuMap != null){
				role.setMenuMap(menuMap);
			}
			try {
				cache.acquireWriteLockOnKey(roleId);
				Element ele=new Element(roleId, role);
				cache.put(ele);
				return true;
			} catch (Exception e) {
				logger.error("将[role={}]角色添加到缓存时出错",role,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(roleId);
			}
		}
		return false;
	}
	
	@Override
	public boolean update(Role role) {
		if(role != null && role.getId() != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Long roleId = role.getId();
			try {
				cache.acquireWriteLockOnKey(roleId);
				Element element = cache.get(roleId);
				Role cacheRole = null;
				if(element != null){
					cacheRole = (Role) element.getObjectValue();
				}
				if(cacheRole == null){
					return false;
				}
				Map<Long, Menu> cacheMenuMap = cacheRole.getMenuMap();
				if(cacheMenuMap != null && !cacheMenuMap.isEmpty()){
					role.setMenuMap(cacheMenuMap);
				}
				Element ele=new Element(roleId, role);
				cache.put(ele);
				return true;
			} catch (Exception e) {
				logger.error("将[roleId={}]角色更新到缓存时出错",roleId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(roleId);
			}
		}
		return false;
	}

	@Override
	public boolean delete(Long roleId) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		if( roleId != null){
			try {
				cache.acquireWriteLockOnKey(roleId);
				cache.remove(roleId);
				return true;
			} catch (Exception e) {
				logger.error("移除缓存中的[roleId={}]角色出错",roleId,e);
				return false;
			}finally{
				cache.releaseWriteLockOnKey(roleId);
			}
		}
		return false;
	}


	@Override
	public boolean addRoleMenu(Long roleId,Long menuId) {
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		if(roleId == null){
			Role role =  roleDao.findRoleByRoleName(Constant.ADMIN);
			if(role != null){
				roleId = role.getId();
			}
		}
		if(roleId != null && menuId != null){
			Menu menu = menuDao.selectByKey(menuId);
			if(menu == null || menu.getIsShow().intValue() == 0){
				return false;
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(roleId);
				element = cache.get(roleId);
			} catch (Exception e) {
				logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(roleId);
			}
			
			Role role = null;
			if(element == null){
				return false;
			}else{
				try {
					cache.acquireWriteLockOnKey(roleId);
					role = (Role) element.getObjectValue();
					if(role != null){
						Map<Long, Menu> menuMap = role.getMenuMap();
						if(menuMap == null){
							menuMap = new HashMap<Long,Menu>();
						}
						menuMap.put(menuId, menu);
						role.setMenuMap(menuMap);
						return true;
					}
				} catch (Exception e) {
					logger.error("将[menuId={}]菜单添加到[roleId={}]角色的缓存中出错",menuId,roleId,e);
					return false;
				}finally{
					cache.releaseWriteLockOnKey(roleId);
				}
			 	
			}
		}
		return true;
	}

	@Override
	public boolean removeRoleMenu(Long roleId,Long menuId) {
		if(roleId != null && menuId != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(roleId);
				element = cache.get(roleId);
			} catch (Exception e) {
				logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(roleId);
			}
			Role role = null;
			if(element != null){
			 	role = (Role) element.getObjectValue();
				if(role != null){
					try {
						cache.acquireWriteLockOnKey(roleId);
						Map<Long, Menu> menuMap = role.getMenuMap();
						if(menuMap != null && !menuMap.isEmpty()){
							menuMap.remove(menuId);
							role.setMenuMap(menuMap);
							cache.remove(roleId);
							element = new Element(roleId, role);
							cache.put(element);
						}
						return true;
					} catch (Exception e) {
						logger.error("将[menuId={}]菜单从[roleId={}]角色的缓存中移除出错",menuId,roleId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
		}
		return false;
	}


	/**
	 * 修改菜单信息
	 * @param menuId 菜单ID
	 * @return 
	 */
	public boolean updateMenu(Menu menu){
		if(menu != null && menu.getId() != null && menu.getId().longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Long> roleIdList = cache.getKeys();
			if(roleIdList != null && !roleIdList.isEmpty()){
				for(Long roleId : roleIdList){
					try {
						cache.acquireWriteLockOnKey(roleId);
						Element element = cache.get(roleId);
						if(element != null){
							Role role = (Role) element.getObjectValue();
							if(role != null){
								Map<Long, Menu> menuMap = role.getMenuMap();
								if(menuMap != null && !menuMap.isEmpty()){
									Menu m = menuMap.get(menu.getId());
									if(m != null){
										menuMap.put(menu.getId(), menu);
										role.setMenuMap(menuMap);
									}
								}
							}
						}
					} catch (Exception e) {
						logger.warn("为缓存的[roleId={}]角色更新[menuId={}]菜单时出错",roleId,menu.getId().longValue(),e);
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * 移除所有缓存角色的菜单权限
	 * @param menuId 菜单ID
	 * @return 
	 */
	public boolean removeMenu(Long menuId){
		if(menuId != null && menuId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Long> roleIdList = cache.getKeys();
			if(roleIdList != null && !roleIdList.isEmpty()){
				for(Long roleId : roleIdList){
					try {
						cache.acquireWriteLockOnKey(roleId);
						Element element = cache.get(roleId);
						if(element != null){
							Role role = (Role) element.getObjectValue();
							if(role != null){
								Map<Long, Menu> menuMap = role.getMenuMap();
								if(menuMap != null && !menuMap.isEmpty()){
									menuMap.remove(menuId);
									role.setMenuMap(menuMap);
								}
							}
						}
					} catch (Exception e) {
						logger.warn("为缓存的[roleId={}]角色移除[menuId={}]菜单时出错",roleId,menuId,e);
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 设置菜单是否可见时，同步设置所有角色缓存中对应的菜单是否可见
	 * @param menuId 菜单ID
	 * @param isShow 是否可见
	 * @return
	 */
	public boolean setShowMenu(Long menuId,Integer isShow){
		if(menuId != null && menuId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			List<Long> roleIdList = cache.getKeys();
			if(roleIdList != null && !roleIdList.isEmpty()){
				for(Long roleId : roleIdList){
					try {
						cache.acquireWriteLockOnKey(roleId);
						Element element = cache.get(roleId);
						if(element != null){
							Role role = (Role) element.getObjectValue();
							if(role != null){
								Map<Long, Menu> menuMap = role.getMenuMap();
								if(menuMap != null && !menuMap.isEmpty()){
									Menu menu = menuMap.get(menuId);
									if(menu != null){
										menu.setIsShow(isShow);
										menuMap.put(menuId, menu);
										role.setMenuMap(menuMap);
									}
								}
							}
						}
					} catch (Exception e) {
						logger.warn("为缓存的[roleId={}]角色设置[menuId={}]菜单是否可见[isShow={}]时出错",roleId,menuId,isShow,e);
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	
	@Override
	public Role findById(Long roleId) {
		if(roleId == null || roleId.longValue() < 1){
			return null;
		}
		Cache cache=cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache=cacheManager.getCache(cacheName);
		}
		try {
			cache.acquireReadLockOnKey(roleId);
			Element element = cache.get(roleId);
			if(element != null){
				Role role = (Role) element.getObjectValue();
				return role;
			}
		} catch (Exception e) {
			logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
		}finally{
			cache.releaseReadLockOnKey(roleId);
		}
		return null;
	}
	
	/**
	 * 批量添加角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean addChannelIds(Long roleId,List<Long> channelIds){
		if(roleId != null && channelIds != null){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(roleId);
				element = cache.get(roleId);
			} catch (Exception e) {
				logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(roleId);
			}
			Role role = null;
			if(element != null){
			 	role = (Role) element.getObjectValue();
				if(role != null){
					try {
						cache.acquireWriteLockOnKey(roleId);
						List<Long> channelIdList = role.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.addAll(channelIds);
						}else{
							channelIdList = channelIds;
						}
						role.setChannelIds(channelIdList);
						return true;
					} catch (Exception e) {
						logger.error("将[channelIds={}]栏目集合添加到[roleId={}]角色的缓存中出错",channelIds,roleId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 批量移除角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelIds 栏目ID集合
	 * @return
	 */
	public boolean removeChannelIds(Long roleId,List<Long> channelIds){
		if(roleId != null && channelIds != null && channelIds.size() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(roleId);
				element = cache.get(roleId);
			} catch (Exception e) {
				logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(roleId);
			}
			Role role = null;
			if(element != null){
			 	role = (Role) element.getObjectValue();
				if(role != null){
					try {
						cache.acquireWriteLockOnKey(roleId);
					 	List<Long> channelIdList = role.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.removeAll(channelIds);
							role.setChannelIds(channelIds);
							cache.remove(roleId);
							element = new Element(roleId, role);
							cache.put(element);
						}
						return true;
					} catch (Exception e) {
						logger.error("将[channelIds={}]栏目集合从[roleId={}]角色的缓存中移除出错",channelIds,roleId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 添加角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean addChannelId(Long roleId,Long channelId){
		if(roleId != null && roleId.longValue() > 0 && channelId != null && channelId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(roleId);
				element = cache.get(roleId);
			} catch (Exception e) {
				logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(roleId);
			}
			Role role = null;
			if(element != null){
			 	role = (Role) element.getObjectValue();
				if(role != null){
					try {
						cache.acquireWriteLockOnKey(roleId);
						List<Long> channelIdList = role.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.add(channelId);
						}else{
							channelIdList = new ArrayList<Long>();
							channelIdList.add(channelId);
						}
						role.setChannelIds(channelIdList);
						return true;
					} catch (Exception e) {
						logger.error("将[channelId={}]栏目添加到[roleId={}]角色的缓存中出错",channelId,roleId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 移除角色可访问的栏目ID
	 * @param roleId 角色ID
	 * @param channelId 栏目ID
	 * @return
	 */
	public boolean removeChannelId(Long roleId,Long channelId){
		if(roleId != null && roleId.longValue() > 0 && channelId != null && channelId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				cacheManager.addCache(cacheName);
				cache=cacheManager.getCache(cacheName);
			}
			Element element = null;
			try {
				cache.acquireReadLockOnKey(roleId);
				element = cache.get(roleId);
			} catch (Exception e) {
				logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
				return false;
			}finally{
				cache.releaseReadLockOnKey(roleId);
			}
			Role role = null;
			if(element != null){
			 	role = (Role) element.getObjectValue();
				if(role != null){
					try {
						cache.acquireWriteLockOnKey(roleId);
					 	List<Long> channelIdList = role.getChannelIds();
						if(channelIdList != null && !channelIdList.isEmpty()){
							channelIdList.remove(channelId);
							role.setChannelIds(channelIdList);
							cache.remove(roleId);
							element = new Element(roleId, role);
							cache.put(element);
						}
						return true;
					} catch (Exception e) {
						logger.error("将[channelId={}]栏目从[roleId={}]角色的缓存中移除出错",channelId,roleId,e);
						return false;
					}finally{
						cache.releaseWriteLockOnKey(roleId);
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 获取可访问的栏目ID集合
	 * @param roleId
	 * @return
	 */
	public List<Long> getChannelIds(Long roleId){
		if(roleId != null && roleId.longValue() > 0){
			Cache cache=cacheManager.getCache(cacheName);
			if(cache==null){
				return null;
			}
			try {
				cache.acquireReadLockOnKey(roleId);
				Element element = cache.get(roleId);
				if(element != null){
					Role role = (Role) element.getObjectValue();
					if(role != null){
						List<Long> channelIds = role.getChannelIds();
						return channelIds;
					}
				}
			} catch (Exception e) {
				logger.error("从缓存中读取[roleId={}]角色信息出错",roleId,e);
			}finally{
				cache.releaseReadLockOnKey(roleId);
			}
			
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
	

}
