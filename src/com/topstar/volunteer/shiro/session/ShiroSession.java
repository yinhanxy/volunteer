package com.topstar.volunteer.shiro.session;

import java.io.Serializable;
import java.util.Date;

import com.topstar.volunteer.model.BaseUser;


public class ShiroSession implements Serializable {

	private static final long serialVersionUID = -553603178825470330L;

	private Date lastActiveTime;

	private String loginIp;

	private BaseUser user;

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}

	public Date getLastActiveTime() {
		return lastActiveTime;
	}

	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

}
