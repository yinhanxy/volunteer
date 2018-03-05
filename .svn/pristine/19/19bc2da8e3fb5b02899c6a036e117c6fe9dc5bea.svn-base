package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.Date;

public class LoginUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1400234673572787974L;

	private Long id;
	
	private String userName;
	
	private String nickName;

	private Date lastActiveTime;

	private String loginIp;
	
	public LoginUser(){}
	
	public LoginUser(BaseUser user){
		this.id = user.getId();
		this.userName = user.getUserName();
		this.nickName = user.getNickName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
