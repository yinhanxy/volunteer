package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.List;

import com.topstar.volunteer.entity.Menu;

public class MenuView  implements Serializable,Comparable<MenuView>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long parentId;
	
	private String menuName;
	
	private String menuDesc;

	private Integer orderNo;
	
	private String url;
	
	private String icon;
	
	private Integer menuType;
	
	private List<MenuView> menuViews;
	
	public MenuView(Menu menu){
		if(menu != null){
			this.setId(menu.getId());
			this.setParentId(menu.getParentId());
			this.setMenuName(menu.getMenuName());
			this.setMenuDesc(menu.getMenuDesc());
			this.setOrderNo(menu.getOrderNo());
			this.setUrl(menu.getUrl());
			this.setIcon(menu.getIcon());
			this.setMenuType(menu.getMenuType());
		}
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getParentId() {
		return parentId;
	}



	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}



	public String getMenuName() {
		return menuName;
	}



	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public String getMenuDesc() {
		return menuDesc;
	}



	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}



	public Integer getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	public List<MenuView> getMenuViews() {
		return menuViews;
	}

	public void setMenuViews(List<MenuView> menuViews) {
		this.menuViews = menuViews;
	}
	
	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	

	@Override
	public int compareTo(MenuView o) {
		int num = this.orderNo.compareTo(o.orderNo);
		if(num > 0){
			return 1;
		}else if(num == 0){
			return 0;
		}else{
			return -1;
		}
	}
	@Override
	public String toString() {
		return "MenuView [id=" + id + ", parentId=" + parentId + ", menuName=" + menuName + ", menuDesc=" + menuDesc
				+ ", orderNo=" + orderNo + ", url=" + url + ", icon=" + icon + ", menuViews=" + menuViews + "]";
	}
	
}
