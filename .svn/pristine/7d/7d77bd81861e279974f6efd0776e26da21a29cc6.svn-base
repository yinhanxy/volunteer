package com.topstar.volunteer.dao;

import java.util.List;

import com.topstar.volunteer.entity.Appendix;

public interface AppendixDao extends BaseDao<Appendix> {

	public List<Appendix> selectByDocId(Long docId);
	
	public Integer getAppendixMaxNumber(Long docId);
	
	public Integer deleteAppendixs(Long[] ids);
	
	/**
	 * 通过附件ID获取附件真实文件名
	 * @param id
	 * @return
	 */
	public String getFileName(Long id);
}
