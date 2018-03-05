package com.topstar.volunteer.service.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.cache.RoleCache;
import com.topstar.volunteer.dao.UserDao;
import com.topstar.volunteer.dao.UserMenuDao;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.UserMenu;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.UserMenuService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserMenuServiceImpl extends BaseServiceImpl<UserMenu> implements UserMenuService {

	@Autowired
	private LoginUserCache userCache;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleCache roleCache;
	
	@Autowired
	private UserMenuDao userMenuDao;
	
	@Override
	public List<Long> getMenuIdsByUserId(Long userId) {
		List<Long> menuIds=null;
		if(userId!=null && userId>0){
			menuIds=userMenuDao.findMenuIdsByUserId(userId);
		}
		return menuIds;
	}

	
	@Override
	public List<Long> getUserRoleMenuIdsByUserId(Long userId) {
		List<Long> menuIds=new ArrayList<Long>();
		if(userId!=null && userId>0){
			List<Long> roleIds=userDao.findRoleIdsByUserId(userId);
			if(roleIds!=null && !roleIds.isEmpty()){
				for (Long roleId : roleIds) {
					Role role=roleCache.findById(roleId);
					if(role!=null){
						 Map<Long, Menu> mapMenus=role.getMenuMap();
						 if(mapMenus!=null){
							 menuIds.addAll(mapMenus.keySet());
						 }
						 
					}
				}
			}
		}
		return menuIds;
	}
	
	@Override
	public Boolean addUserMenus(Long userId, List<Long> menuIds) {
		if(userId ==null || userId<0){
			return false;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser==null){
			return false;
		}
		List<Long> existedMenuIds=getMenuIdsByUserId(userId);
		if(existedMenuIds!=null){
			Example example=new Example(UserMenu.class);
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("userId", userId);
			int delUserMenuRow=deleteByExample(example);
			if(delUserMenuRow!=existedMenuIds.size()){
				return false;
			}
			for (Long existedMenuId : existedMenuIds) {
				userCache.removeMenu(userId, existedMenuId);
			}
		}
		if(menuIds!=null && menuIds.size()>0){
			int flag=menuIds.size();
			for (Long menuId : menuIds) {
				UserMenu userMenu=new UserMenu();
				userMenu.setUserId(userId);
				userMenu.setCrUser(crtUser.getUserName());
				userMenu.setCrTime(new Timestamp(new Date().getTime()));
				userMenu.setMenuId(menuId);
				int addUserMenu=insert(userMenu);
				if(addUserMenu>0){
					flag--;
					userCache.addMenu(userId, menuId);
				}
			}
			if(flag==0){
				return true;
			}
		}
		return true;
	}
	
}
