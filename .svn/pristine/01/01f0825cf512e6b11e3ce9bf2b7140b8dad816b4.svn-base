package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.List;

import com.topstar.volunteer.entity.Channel;


public class ChannelView implements Serializable,Comparable<ChannelView>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5746033633866134991L;

	private Long id;
	
	private Long pId;
	
	private String desc;
	
	private String parentName;
	
	private Long order;
	
	private boolean isParent;
	
	private boolean isOpen;

	private boolean isChkDisabled;
	
	private boolean isSite;
	
	private Long siteId;
	
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public boolean isSite() {
		return isSite;
	}

	public void setSite(boolean isSite) {
		this.isSite = isSite;
	}

	private List<ChannelView> channelList;
	

	public ChannelView(Long id, Long pId, String desc, boolean isParent, boolean isOpen,boolean isSite) {
		super();
		this.id = id;
		this.pId = pId;
		this.desc = desc;
		this.isParent = isParent;
		this.isOpen = isOpen;
		this.isSite=isSite;
	}

	public ChannelView(Channel channel) {
		if(channel!=null){
			this.id=channel.getId();
			this.desc=channel.getChnlDesc();
			this.pId=channel.getParentId();
			this.order=channel.getOrder();
			this.siteId=channel.getSiteId();
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public boolean isChkDisabled() {
		return isChkDisabled;
	}

	public void setChkDisabled(boolean isChkDisabled) {
		this.isChkDisabled = isChkDisabled;
	}

	public List<ChannelView> getChannelList() {
		return channelList;
	}


	public void setChannelList(List<ChannelView> channelList) {
		this.channelList = channelList;
	}


	@Override
	public int compareTo(ChannelView o) {
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
		return "ChannelView [id=" + id + ", pId=" + pId + ", desc=" + desc + ", parentName=" + parentName + ", order="
				+ order + ", isParent=" + isParent + ", isOpen=" + isOpen + ", isChkDisabled=" + isChkDisabled
				+ ", channelList=" + channelList + "]";
	}

}
