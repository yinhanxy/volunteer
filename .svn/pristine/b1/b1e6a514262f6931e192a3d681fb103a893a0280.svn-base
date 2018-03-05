package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.topstar.volunteer.validator.group.Groups;

public class Menu implements Serializable,Cloneable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8408262212749421075L;

	@Null(message="{menu.menuId.null.error}",groups=Groups.Add.class)
	@NotNull(message="{menu.menuId.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{menu.menuId.minValue.error}",groups=Groups.Update.class)
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_MENU.nextval from dual")
    private Long id;

	@NotNull(message="{menu.menuName.notNull.error}",groups={Groups.Add.class})
	@Pattern(regexp="^[a-zA-Z_]{2,30}$" ,message="{menu.menuName.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "MENU_NAME")
    private String menuName;

    @NotNull(message="{menu.menuDesc.notNull.error}",groups={Groups.Add.class})
    @Pattern(regexp="^[a-zA-Z\u4e00-\u9fa5]{2,50}$",message="{menu.menuDesc.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "MENU_DESC")
    private String menuDesc;
    
    @NotNull(message="{menu.menuType.notNull.error}",groups={Groups.Add.class})
    @Range(min=0,max=1,message="{menu.menuType.range.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name="MENU_TYPE")
    private Integer menuType;

    @Column(name = "URL")
    private String url;

    @Column(name = "ICON")
    private String icon;
    
    @NotNull(message="{menu.parentId.notNull.error}",groups={Groups.Add.class})
    @Min(value=0,message="{menu.parentId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "PARENT_ID")
    private Long parentId;
    
    @Column(name="PARENT_IDS")
    private String parentIds;

    @Column(name = "ORDER_NO")
    private Integer orderNo;

    @NotNull(message="{menu.isShow.notNull.error}",groups={Groups.Add.class})
    @Range(min=0,max=1,message="{menu.isShow。range.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "IS_SHOW")
    private Integer isShow;
    
    
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

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
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

	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", menuDesc=" + menuDesc + ", menuType=" + menuType
				+ ", url=" + url + ", icon=" + icon + ", parentId=" + parentId + ", parentIds=" + parentIds
				+ ", orderNo=" + orderNo + ", isShow=" + isShow + ", crUser=" + crUser + ", crTime=" + crTime + "]";
	}

	@Override
	public Menu clone(){
		try {
			return (Menu) super.clone();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	

}