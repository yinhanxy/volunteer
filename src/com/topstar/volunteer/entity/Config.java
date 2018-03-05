package com.topstar.volunteer.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.topstar.volunteer.validator.constraints.NotEmptyAndOracleLength;
import com.topstar.volunteer.validator.constraints.OracleLength;
import com.topstar.volunteer.validator.group.Groups;

public class Config implements Serializable,Comparable<Config>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5101568103577390982L;
	
	@Null(message="{config.configId.null.error}",groups=Groups.Add.class)
	@NotNull(message="{config.configId.notNull.error}",groups=Groups.Update.class)
	@Min(value=1,message="{config.configId.minValue.error}",groups=Groups.Update.class)
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_CONFIG.nextval from dual")
    private Long id;

	@OracleLength(min=0,max=30,message="{config.type.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "TYPE")
    private String type;
    
    @NotBlank(message="{config.configName.notNull.error}",groups={Groups.Add.class})
    @Pattern(regexp="^[a-zA-Z_0-9]{4,30}$",message="{config.configName.length.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "NAME")
    private String name;

    @NotEmptyAndOracleLength(min=1,max=200,message="{config.content.notNullAndLength.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "CONTENT")
    private String content;

    @NotEmptyAndOracleLength(min=1,message="{config.remark.notNullAndLength.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "REMARK")
    private String remark;
    
    @NotNull(message="{config.orderNo.notNull.error}",groups={Groups.Add.class,Groups.Update.class})
    @Min(value=0,message="{config.orderNo.min.error}",groups={Groups.Add.class,Groups.Update.class})
    @Max(value=99999,message="{config.orderNo.max.error}",groups={Groups.Add.class,Groups.Update.class})
    @Column(name = "ORDER_NO")
    private Integer orderNo;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return VALUE
     */
    public String getContent() {
        return content;
    }

    /**
     * @param value
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public int compareTo(Config o) {
		if(o!=null){
			int result=this.orderNo.compareTo(o.getOrderNo());
			if(result>0){
				return 1;
			}else if(result<0){
				return -1;
			}else{
				return 0;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", type=" + type + ", name=" + name + ", content=" + content + ", remark=" + remark
				+ ", orderNo=" + orderNo + "]";
	}

}