package com.topstar.volunteer.service;

import java.util.List;

import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.model.MenuView;

public interface MenuService  extends BaseService<Menu>{
	
	/**
	 * 获取所有的菜单信息（包括子菜单）
	 * @return
	 */
	public List<MenuView> findMenus();
	
	/**
	 * 根据父级菜单Id获取子菜单信息
	 * @param parentId 父级菜单Id
	 * @param containChildren 是否包含所有子菜单
	 * @return
	 */
	public List<MenuView> findMenusByParentId(long parentId,boolean containChildren);
	
	public boolean addMenu(Menu menu);
	
	public boolean updateMenu(Menu menu) throws Exception;
	
	public boolean deleteMenu(long menuId);

}
