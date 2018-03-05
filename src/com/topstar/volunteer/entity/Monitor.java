package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.topstar.volunteer.util.DateUtil;
@Entity
@Table(name="SYS_MONITOR")
public class Monitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5655919326819678591L;
	@Id
    @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_SYS_MONITOR.nextval from dual")
	private Long id;
	@Column(name = "CPURATE")
    private Integer cpurate;
	@Column(name = "DISKRATE")
    private Integer diskrate;
	@Column(name = "MEMORYRATE")
    private Integer memoryrate;
	@Column(name = "CR_TIME")
    private Timestamp crTime;
	
	/**
	 * @return id
	 */
    public Long getId() {
        return id;
    }
    /**
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCpurate() {
        return cpurate;
    }

    public void setCpurate(Integer cpurate) {
        this.cpurate = cpurate;
    }

    public Integer getDiskrate() {
        return diskrate;
    }

    public void setDiskrate(Integer diskrate) {
        this.diskrate = diskrate;
    }

    public Integer getMemoryrate() {
        return memoryrate;
    }

    public void setMemoryrate(Integer memoryrate) {
        this.memoryrate = memoryrate;
    }

    public Timestamp getCrTime() {
        return crTime;
    }

    public void setCrTime(Timestamp crTime) {
        this.crTime = crTime;
    }

	@Override
	public String toString() {
		String crTime = "";
		if(this.crTime != null ){
			crTime = DateUtil.format(this.crTime, DateUtil.YYYY_MM_DD_HHMMSS);
		}
		return "Monitor [id=" + id + ", cpurate=" + cpurate + ", diskrate=" + diskrate + ", memoryrate=" + memoryrate
				+ ", crTime=" + crTime + "]";
	}
    
    
}