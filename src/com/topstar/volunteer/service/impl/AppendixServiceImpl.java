package com.topstar.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.dao.AppendixDao;
import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.service.AppendixService;

@Service
public class AppendixServiceImpl extends BaseServiceImpl<Appendix> implements AppendixService {

	@Autowired
	private AppendixDao appendixDao;
	
	
	public int insertAppendixs(Long docId,List<Appendix> appendixList) throws RuntimeException{
		int row = 0;
		if(appendixList != null && !appendixList.isEmpty()){
			Integer orderNumber = 0;
			if(docId != null && docId.longValue() > 0){
				orderNumber = appendixDao.getAppendixMaxNumber(docId);
			}
			if(orderNumber == null){
				orderNumber = 0;
			}
			for(Appendix x : appendixList){
				orderNumber ++;
				try {
					Timestamp crTime = new Timestamp(new Date().getTime());
					x.setFileOrder(orderNumber);
					x.setDocId(docId);
					x.setCrTime(crTime);
					int i = appendixDao.insertNotNull(x);
					row += i;
				} catch (Exception e) {
					throw new RuntimeException("为文档[DCID="+docId+"]添加附件信息出错",e);
				}
			}
		}
		return row;
	}

	
	public List<Appendix> selectByDocId(Long docId){
		return appendixDao.selectByDocId(docId);
	}


	@Override
	public Integer deleteAppendixs(Long[] ids) {
		if(ids != null && ids.length > 0){
			return appendixDao.deleteAppendixs(ids);
		}
		return 0;
	}


	@Override
	public String getFileName(Long id) {
		return appendixDao.getFileName(id);
	}
}
