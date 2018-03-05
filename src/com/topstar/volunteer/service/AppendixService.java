package com.topstar.volunteer.service;

import java.util.List;

import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.exception.TPSException;

public interface AppendixService extends BaseService<Appendix> {

	public int insertAppendixs(Long docId,List<Appendix> appendixList) throws TPSException;
	
	public List<Appendix> selectByDocId(Long docId);
	
	public Integer deleteAppendixs(Long[] ids);
	
	/**
	 * 通过附件ID获取附件真实文件名
	 * @param id
	 * @return
	 */
	public String getFileName(Long id);
}
