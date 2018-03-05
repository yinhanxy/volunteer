package com.topstar.volunteer.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.MenuView;
import com.topstar.volunteer.service.MenuService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class MenuController {
	
	
	@Autowired
	private MenuService menuService;
	
	private Logger logger=LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value="/menu/loadMenu.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String loadMenu(HttpServletRequest request,HttpServletResponse response){
		try{
			List<MenuView> menuList=menuService.findMenus();
			LoggerServer.info(ObjectType.MENU, OperateType.SHOWMENULIST, "查看菜单列表成功",null,null, true);
			return ResultData.ok(menuList).toJSONString();
		}catch (Exception e) {
			logger.error("获取菜单列表出错",e);
			LoggerServer.error(ObjectType.MENU, OperateType.SHOWMENULIST, "查看菜单列表出错",null,null);
			return ResultData.fail("获取菜单列表出错").toJSONString();
		}
	}
	
	@RequestMapping(value="/menu/list.html")
	public  String list(HttpServletRequest request,HttpServletResponse response){
		return "/menu/list";
	}
	
	@RequestMapping(value="/menu/viewMenu.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String viewMenu(HttpServletRequest request,HttpServletResponse response){
		Long id = ActionContext.getActionContext().getParameterAsLong("id");
		try{
			Menu menu=menuService.selectByKey(id);
			LoggerServer.info(ObjectType.MENU, OperateType.SHOWMENULIST, "获取菜单信息成功【参数id值为:"+id+"】",null,null,true);
			return ResultData.ok(menu).toJSONString();
		}catch (Exception e) {
			logger.error("获取菜单信息出错",e);
			LoggerServer.error(ObjectType.MENU, OperateType.SHOWMENULIST, "获取菜单信息出错【参数id值为:"+id+"】",null,null);
			return ResultData.fail("获取菜单信息出错").toJSONString();
		}
	}
	
	@RequestMapping(value="/menu/viewMenu.html")
	public  String view(HttpServletRequest request,HttpServletResponse response){
		Long id = ActionContext.getActionContext().getParameterAsLong("mid");
		try{
			Menu menu=menuService.selectByKey(id);
			request.setAttribute("menu", menu);
			LoggerServer.info(ObjectType.MENU, OperateType.SHOWMENULIST, "获取菜单信息成功【参数id值为:"+id+"】",null,null,true);
		}catch (Exception e) {
			logger.error("获取菜单信息出错",e);
			LoggerServer.error(ObjectType.MENU, OperateType.SHOWMENULIST, "获取菜单信息出错【参数id值为:"+id+"】",null,null);
		}
		return "/menu/viewMenu";
	}
	
	@RequestMapping(value="/menu/editMenu.html")
	public  String edit(HttpServletRequest request,HttpServletResponse response){
		Long id = ActionContext.getActionContext().getParameterAsLong("mid");
		Menu menu=menuService.selectByKey(id);
		request.setAttribute("menu", menu);
		return "/menu/editMenu";
	}
	
	
	@RequestMapping(value="/menu/editMenu.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String editMenu(HttpServletRequest request,HttpServletResponse response){

			Long id = ActionContext.getActionContext().getParameterAsLong("id");

			String menuName= ActionContext.getActionContext().getParameterAsString("menuName");

			String menuDesc= ActionContext.getActionContext().getParameterAsString("menuDesc");

			int type = ActionContext.getActionContext().getParameterAsInt("menuType");

			Long parentId = ActionContext.getActionContext().getParameterAsLong("parentId");
			Menu menu=null;
			try{
			   menu=menuService.selectByKey(id);
			}catch (Exception e) {
				logger.error("获取菜单信息失败",e);
				return ResultData.fail("获取菜单信息失败").toJSONString();
			}
			Menu preMenu=menu;
			try{
				if(menu==null){
					return ResultData.fail("编辑的菜单不存在").toJSONString();
				}
				menu.setId(id);
				menu.setIcon(ActionContext.getActionContext().getParameterAsString("icon"));
				menu.setUrl(ActionContext.getActionContext().getParameterAsString("url"));
				menu.setMenuName(menuName);
				menu.setMenuDesc(menuDesc);
				menu.setMenuType(type);
				menu.setIsShow(ActionContext.getActionContext().getParameterAsInt("isShow"));
				menu.setParentId(parentId);
				menu.setOrderNo(ActionContext.getActionContext().getParameterAsInt("orderNo"));
				
				List<ValidateMessage> errors =ValidatorUtil.validate(menu, Groups.Update.class);
				if(null!=errors&&errors.size()>0){
					return ResultData.fail(errors).toJSONString();
				}
				
				Menu parentMenu=null;
				if(parentId>0){
					parentMenu=menuService.selectByKey(parentId);
					if(parentMenu==null){
						return ResultData.fail("父级菜单不存在").toJSONString();
					}
				}
				
				if(parentMenu!=null)
					menu.setParentIds(parentMenu.getParentIds()+"/"+parentId);
				else{
					menu.setParentIds("0");
				}

				boolean success=menuService.updateMenu(menu);
				if(success){
					LoggerServer.info(ObjectType.MENU, OperateType.EDITMENU, "修改菜单信息成功【修改前："+preMenu.toString()+",修改后"+menu.toString()+"】", menu.getId(), menu.getMenuDesc(), true);
				}else{
					LoggerServer.error(ObjectType.MENU, OperateType.EDITMENU, "修改菜单信息失败", menu.getId(), menu.getMenuDesc());
				}
				return ResultData.ok("编辑菜单成功").toJSONString();
			}catch (Exception e) {
				logger.error("编辑菜单出错",e);
				LoggerServer.error(ObjectType.MENU, OperateType.EDITMENU, "编辑菜单信息失败", preMenu.getId(), preMenu.getMenuDesc());
				return ResultData.fail("编辑菜单出错").toJSONString();
			}
	}
	
	@RequestMapping(value="/menu/delMenu.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String delMenu(HttpServletRequest request,HttpServletResponse response){
		
			Long id = ActionContext.getActionContext().getParameterAsLong("id");
			if(id<=0){
				return ResultData.fail("非法的参数【id】值").toJSONString();
			}
			Menu menu=menuService.selectByKey(id);
			if(menu==null){
				return ResultData.fail("删除的菜单不存在").toJSONString();
			}
			try{
				List<MenuView> list=menuService.findMenusByParentId(id, false);
				if(list!=null&&list.size()>0){
					return ResultData.fail("该菜单下存在子菜单,请先删除子菜单").toJSONString();
				}
				boolean success=menuService.deleteMenu(id);
				if(success){
					LoggerServer.info(ObjectType.MENU, OperateType.DROPMENU, "删除菜单成功", id, menu.getMenuDesc(), true);
				}else{
					LoggerServer.error(ObjectType.MENU, OperateType.DROPMENU, "删除菜单失败", id, menu.getMenuDesc());
				}
				return ResultData.ok("删除菜单成功").toJSONString();
			}catch (Exception e) {
				logger.error("删除菜单出错",e);
				LoggerServer.error(ObjectType.MENU, OperateType.DROPMENU, "删除菜单失败", id, menu.getMenuDesc());
				return ResultData.fail("删除菜单出错").toJSONString();
			}
	}
	
	
	@RequestMapping(value="/menu/addMenu.html")
	public  String add(HttpServletRequest request,HttpServletResponse response){
		Long id = ActionContext.getActionContext().getParameterAsLong("pid");
		id=id<0?0:id;
		request.setAttribute("pid", id);
		return "/menu/addMenu";
	}
	
	
	@RequestMapping(value="/menu/addMenu.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String addMenu(HttpServletRequest request,HttpServletResponse response){
		
			String menuName= ActionContext.getActionContext().getParameterAsString("menuName");
			
			String menuDesc= ActionContext.getActionContext().getParameterAsString("menuDesc");
			
			int type = ActionContext.getActionContext().getParameterAsInt("menuType");
			
			Long parentId = ActionContext.getActionContext().getParameterAsLong("parentId");
			
			Menu menu=new Menu();
			menu.setIcon(ActionContext.getActionContext().getParameterAsString("icon"));
			menu.setUrl(ActionContext.getActionContext().getParameterAsString("url"));
			menu.setMenuName(menuName);
			menu.setMenuDesc(menuDesc);
			menu.setMenuType(type);
			menu.setIsShow(ActionContext.getActionContext().getParameterAsInt("isShow"));
			menu.setParentId(parentId);
			menu.setOrderNo(ActionContext.getActionContext().getParameterAsInt("orderNo"));
			menu.setCrTime(new Date());
			
			List<ValidateMessage> errors =ValidatorUtil.validate(menu, Groups.Add.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
	
			Menu parentMenu=null;
			if(parentId>0){
				parentMenu=menuService.selectByKey(parentId);
				if(parentMenu==null){
					return ResultData.fail("父级菜单不存在").toJSONString();
				}
			}
			
			BaseUser user = ShiroSessionMgr.getLoginUser();
			menu.setCrUser(user.getUserName());
			if(parentMenu!=null)
				menu.setParentIds(parentMenu.getParentIds()+"/"+parentId);
			else{
				menu.setParentIds("0");
			}
			try{
				boolean success= menuService.addMenu(menu);
				if(!success){
					LoggerServer.error(ObjectType.MENU, OperateType.CREATEMENU, "新建菜单信息失败", menu.getId(), menu.getMenuDesc());
				}else{
					LoggerServer.info(ObjectType.MENU, OperateType.CREATEMENU, "新建菜单信息成功", menu.getId(), menu.getMenuDesc(),true);
				}
				return ResultData.ok("添加菜单成功").toJSONString();
			}catch (Exception e) {
				logger.error("添加菜单出错",e);
				LoggerServer.error(ObjectType.MENU, OperateType.CREATEMENU, "新建菜单信息失败", menu.getId(), menu.getMenuDesc());
				return ResultData.fail("添加菜单出错").toJSONString();
			}
	}
	

}
