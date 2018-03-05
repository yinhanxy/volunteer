package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topstar.volunteer.dao.AppendixDao;
import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.mapper.AppendixMapper;

@Repository
public class AppendixDaoImpl extends BaseDaoImpl<Appendix> implements
		AppendixDao {

	@Autowired
	private AppendixMapper appendixMapper;
	
	@Override
	public List<Appendix> selectByDocId(Long docId){
		return appendixMapper.selectByDocId(docId);
	}

	@Override
	public Integer getAppendixMaxNumber(Long docId) {
		return appendixMapper.getAppendixMaxNumber(docId);
	}

	@Override
	public Integer deleteAppendixs(Long[] ids) {
		return deleteByKeys(ids);
	}

	@Override
	public String getFileName(Long id) {
		return appendixMapper.getFileName(id);
	}
	
}
