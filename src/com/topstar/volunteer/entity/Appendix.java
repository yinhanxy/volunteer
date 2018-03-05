package com.topstar.volunteer.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Appendix  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5654121159053200998L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_APPENDIX.nextval from dual")
    private Long id;

    @Column(name = "DOC_ID")
    private Long docId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_DESC")
    private String fileDesc;

    @Column(name = "FILE_EXT")
    private String fileExt;
    
    @Column(name = "FILE_ORDER")
    private Integer fileOrder;

    @Column(name = "ATTRIBUTE")
    private String attribute;

    @Column(name = "FILE_TYPE")
    private Integer fileType;

    @Column(name = "CR_USER")
    private String crUser;

    @Column(name = "CR_TIME")
    private Timestamp crTime;
    
    /**
     * 附件类型
     */
    public enum Type{
    	file(1),image(2),video(3),link(4);
    	
    	private Integer value = 1;
    	
    	Type(Integer value){
    		this.value = value;
    	}
    	
    	public String toString(){
    		return value.toString();
    	}
    	
    	public Integer getValue(){
    		return this.value;
    	}
    }

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
     * @return DOC_ID
     */
    public Long getDocId() {
        return docId;
    }

    /**
     * @param docId
     */
    public void setDocId(Long docId) {
        this.docId = docId;
    }

    /**
     * @return FILE_NAME
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return FILE_DESC
     */
    public String getFileDesc() {
        return fileDesc;
    }

    /**
     * @param fileDesc
     */
    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    /**
     * @return FILE_EXT
     */
    public String getFileExt() {
        return fileExt;
    }

    /**
     * @param fileExt
     */
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    /**
     * @return ATTRIBUTE
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * @param attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * @return FILE_TYPE
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * @param fileType
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * @return CR_USER
     */
    public String getCrUser() {
        return crUser;
    }

    /**
     * @param crUser
     */
    public void setCrUser(String crUser) {
        this.crUser = crUser;
    }

    /**
     * @return CR_TIME
     */
    public Timestamp getCrTime() {
        return crTime;
    }

    /**
     * @param crTime
     */
    public void setCrTime(Timestamp crTime) {
        this.crTime = crTime;
    }

	public Integer getFileOrder() {
		return fileOrder;
	}

	public void setFileOrder(Integer fileOrder) {
		this.fileOrder = fileOrder;
	}
    
    public static void main(String[] args) {
		System.out.println(Appendix.Type.image);
	}
}