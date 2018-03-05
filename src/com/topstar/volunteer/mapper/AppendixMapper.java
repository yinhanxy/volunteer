package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.util.BaseMapper;

public interface AppendixMapper extends BaseMapper<Appendix> {
	
	public List<Appendix> selectByDocId(@Param("docId")Long docId);
	
	public Integer getAppendixMaxNumber(@Param("docId")Long docId);
	
	
	/**
	 * 通过附件ID获取附件真实文件名
	 * @param id
	 * @return
	 */
	public String getFileName(@Param("id")Long id);
	
}