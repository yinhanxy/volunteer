package com.topstar.volunteer.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.cache.RoleCache;
import com.topstar.volunteer.dao.MenuDao;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.model.MenuView;
import com.topstar.volunteer.service.MenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private LoginUserCache loginUserCache;
	
	@Autowired
	private RoleCache roleCache;
	
	@Override
	public List<MenuView> findMenus() {
		return findMenusByParentId(0L, true);
	}

	@Override
	public List<MenuView> findMenusByParentId(long parentId, boolean containChildren) {
		List<Menu> list= menuDao.findMenuByParentId(parentId);
		List<MenuView> menuViewList=new ArrayList<MenuView>();
		for(int i=0;list!=null&&i<list.size();i++){
			Menu menu=list.get(i);
			MenuView menuView=new MenuView(menu);
			if(containChildren){
				menuView.setMenuViews(findMenusByParentId(menu.getId(), containChildren));
			}
			menuViewList.add(menuView);
		}
		return menuViewList;
	}
	

	@Override
	public boolean addMenu(Menu menu) {
		menu.setId(null);
		int rowno=insert(menu);
		if(rowno>0){
			long menuId=menu.getId();
			roleCache.addRoleMenu(null,menuId);
		}
		return rowno>0;
	}

	@Override
	public boolean updateMenu(Menu menu) throws Exception {
		if(menu.getParentId()==menu.getId()){
			throw new Exception("不允许设置当前菜单为当前菜单的父级菜单");
		}
		List<MenuView> list= findMenusByParentId(menu.getId(), true);
		for(int i=0;null!=list&&i<list.size();i++){
			MenuView menuView=list.get(i);
			if(menuView.getId()==menu.getParentId()){
				throw new Exception("不允许设置菜单的子菜单为当前菜单的父级菜单");
			}
		}
		int rowno=updateNotNull(menu);
		updateChildMenuParentPath(menu);
		if(rowno>0){
			roleCache.updateMenu(menu);
			//roleCache.setShowMenu(menu.getId(), menu.getIsShow());
			loginUserCache.updateMenu(menu);
			//loginUserCache.setShowMenu(menu.getId(), menu.getIsShow());
		}
		return rowno>0;
	}
	
	private void updateChildMenuParentPath(Menu parentMenu){
		if(parentMenu==null||parentMenu.getId()==0L){
			return;
		}
		List<Menu> menuList=menuDao.findMenuByParentId(parentMenu.getId());
		for (int i = 0;null!=menuList&&i < menuList.size(); i++) {
			Menu menu=menuList.get(i);
			menu.setParentIds(parentMenu.getParentIds()+"/"+parentMenu.getId());
			try{
				int rowNo=updateNotNull(menu);
				if(rowNo>0){
					roleCache.updateMenu(menu);
					loginUserCache.updateMenu(menu);
				}else{
					throw new RuntimeException();
				}
			}catch (Exception e) {
				throw new RuntimeException("更新子菜单信息失败");
			}
		}
	}

	@Override
	public boolean deleteMenu(long id) {
		int rowno=deleteByKey(id);
		if(rowno>0){
			roleCache.removeMenu(id);
			loginUserCache.removeMenu(id);
		}
		
		return rowno>0;
	}
	
}
