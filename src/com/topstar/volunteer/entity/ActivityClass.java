package com.topstar.volunteer.entity;

import java.io.Serializable;

public class ActivityClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4399119825555877711L;
	
	/**
	 * 活动分类id(系统配置id)
	 */
	private Long id;
	
	/**
	 *活动分类名称 
	 */
	private String name;

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

	@Override
	public String toString() {
		return "ActivityClass [id=" + id + ", name=" + name + "]";
	}
	
}
