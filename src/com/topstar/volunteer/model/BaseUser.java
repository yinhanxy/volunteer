package com.topstar.volunteer.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import com.topstar.volunteer.common.Constant;
import com.topstar.volunteer.entity.User;


/**
 * 用户类
 */
public  class BaseUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6746285267735501293L;

	private Long id;
	
	private String userName;
	
	private String nickName;
	
	private Set<String> roles;
	
	private Set<String> permissions;
	
	public BaseUser(User user){
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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public boolean isAdmin(){
		if(this.roles != null){
			Iterator<String> iter = roles.iterator();
			while(iter.hasNext()){
				String roleName = iter.next();
				if(Constant.ADMIN.equals(roleName)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.userName;
	}
	
}
