package com.topstar.volunteer.model;

import java.io.Serializable;

import com.topstar.volunteer.entity.Site;


public class SiteView implements Serializable,Comparable<SiteView>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5746033633866134991L;

	private Long id;
	
	private String name;
	
	private Long order;
	
	
	private boolean isParent;
	
	private boolean isOpen;
	

	public SiteView(Site site) {
		if(site!=null){
			this.id=site.getId();
			this.name=site.getSiteName();
			this.order=site.getOrder();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	@Override
	public int compareTo(SiteView o) {
		int num = this.order.compareTo(o.getOrder());
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
		return "SiteView [id=" + id + ", name=" + name + ", order=" + order + ", isParent=" + isParent + ", isOpen="
				+ isOpen + "]";
	}
	
}
