package com.topstar.volunteer.entity;

import java.util.Date;
import javax.persistence.*;

import com.topstar.volunteer.common.AlternativeUtil;

@Table(name="volunteerCheck")
public class VolunteerCheck {
   
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_VOLUNTEERCHECK.nextval from dual")
    private Long id;

    @Column(name = "VOLUNTEER_ID")
    private Long volunteerId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "OPER_TIME")
    private Date operTime;

    @Column(name = "CHECK_CONTENT")
    private String checkContent;

    /**
     * 1(未审核) 2(通过,即正常) 3(再审) 4(已否)
     */
    @Column(name = "STATUS")
    private String status;
    
    public enum StatusType{
    	/**
    	 * 未审核
    	 */
    	wait_review(1),
    	
    	/**
    	 * 通过(正常)
    	 */
    	pass(2),
    	
    	/**
    	 *再审 
    	 */
    	retrial(3),
    	
    	/**
    	 * 已否 
    	 */
    	deny(4);
    	
    	private Integer value=1;

		StatusType(Integer value) {
			this.value = value;
		}

		public int getValue() {
			return value.intValue();
		}

		public void setValue(Integer value) {
			this.value = value;
		}
    	
		public String toString(){
    		return value.toString();
    	}
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(Long volunteerId) {
		this.volunteerId = volunteerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckStatus() {
		return AlternativeUtil.getVolunteerCheckStatus(status);
	}
	
	@Override
	public String toString() {
		return "VolunteerCheck [id=" + id + ", volunteerId=" + volunteerId + ", userName=" + userName + ", operTime="
				+ operTime + ", checkContent=" + checkContent + ", status=" + status + "]";
	}
    
}