package com.topstar.volunteer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.topstar.volunteer.validator.group.Groups;

@Entity
@Table(name="area")
public class Area implements Serializable,Comparable<Area>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5561022532813689338L;

	/**
	 * 唯一标识
	 */
	@Null(message="{area.id.null.error}",groups=Groups.Add.class)
	@NotNull(message="{area.id.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{area.id.minValue.error}",groups=Groups.Update.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_AREA.nextval from dual")
	private Long id;
	
	/**
	 * 上级区域的唯一标识
	 */
	@NotNull(message="{area.parentId.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=0,message="{area.parentId.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Column(name="PARENT_ID")
	private Long parentId;
	
	/**
	 * 区域名称
	 */
	@NotBlank(message="{area.name.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Pattern(regexp="^[\u4e00-\u9fa5]{2,50}$",message="{area.name.length.error}",groups={Groups.Add.class,Groups.Update.class})
	private String name;
	
	/**
	 * 区域编码
	 */
	@Pattern(regexp="^[0-9]{3,20}|[0-9]{0}$",message="{area.code.pattern.error}",groups={Groups.Add.class,Groups.Update.class})
	private String code;
	
	/**
	 * 区域类型
	 */
	@NotNull(message="{area.type.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
	@Min(value=0,message="{area.type.minValue.error}",groups={Groups.Add.class,Groups.Update.class})
	@Max(value=2,message="{area.type.maxValue.error}",groups={Groups.Add.class,Groups.Update.class})
	private Integer type;

	@Column(name="order_no")
	private Long order;
	
	/**
	 * 备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
	@Override
	public int compareTo(Area o) {
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
		return "Area [id=" + id + ", parentId=" + parentId + ", name=" + name + ", code=" + code + ", type=" + type
				+ ", order=" + order + ", remark=" + remark + "]";
	}

}
