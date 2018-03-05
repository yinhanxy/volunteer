package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "USER_MENU")
public class UserMenu implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 9087579937294200931L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_USER_MENU.nextval from dual")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "CR_USER")
    private String crUser;

    @Column(name = "CR_TIME")
    private Date crTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getCrUser() {
		return crUser;
	}

	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	public Date getCrTime() {
		return crTime;
	}

	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

}