package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.List;

import com.topstar.volunteer.entity.Area;


public class AreaView implements Serializable,Comparable<AreaView>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5746033633866134991L;

	private Long id;
	
	private Long pId;
	
	private String name;
	
	private String parentName;
	
	private String code;
	
	private Integer type;
	
	private Long order;
	
	private String remark;
	
	private boolean isParent;
	
	private boolean isOpen;
	
	private List<AreaView> areaList;

	public AreaView(Area area) {
		if(area!=null){
			this.id=area.getId();
			this.name=area.getName();
			this.pId=area.getParentId();
			this.type=area.getType();
			this.code=area.getCode();
			this.order=area.getOrder();
			this.remark=area.getRemark();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getAreaType(){
		if(type>-1 && type<3){
			switch (type) {
			case 0:
				return "省份";
			case 1:
				return "地市州";
			default:
				return "区县";
			}
		}
		return "";
	}
	
	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<AreaView> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaView> areaList) {
		this.areaList = areaList;
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
	public int compareTo(AreaView o) {
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
		return "AreaView [id=" + id + ", pId=" + pId + ", name=" + name + ", parentName=" + parentName + ", code="
				+ code + ", type=" + type + ", order=" + order + ", remark=" + remark + ", isParent=" + isParent
				+ ", isOpen=" + isOpen + ", areaList=" + areaList + "]";
	}

}
