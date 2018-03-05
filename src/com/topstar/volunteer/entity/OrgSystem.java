package com.topstar.volunteer.entity;

import java.io.Serializable;

/**
 * 机构系统
 * @author TRS
 *
 */
public class OrgSystem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4399119825555877711L;
	
	/**
	 * 机构系统id(系统配置id)
	 */
	private Long id;
	
	/**
	 * 机构系统名称 
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
		return "OrgSystem [id=" + id + ", name=" + name + "]";
	}
}
