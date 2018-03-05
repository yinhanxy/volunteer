package com.topstar.volunteer.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.LoggerDao;
import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.mapper.LoggerMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * 日志记录数据库层实现类
 * @author admin
 * 
 */
@Component(value="loggerDao")
public class LoggerDaoImpl extends BaseDaoImpl<Logger> implements LoggerDao{
	
	@Autowired
	private LoggerMapper loggerMapper;
	
	public void log(Logger logger){
		try{
			insertNotNull(logger);
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public PageInfo<Logger> selectLogger(Logger logger,Date startTime,Date endTime,int pageIndex,int pageSize) {
		if(logger==null){
			logger=new Logger();
		}
		Example example = new Example(Logger.class);
        Example.Criteria criteria = example.createCriteria();
        if(null!=logger.getLoggerType()&&logger.getLoggerType()>0){
        	criteria.andCondition("loggerType=", logger.getLoggerType());
        }
        
        if(null!=logger.getObjectType()&&logger.getObjectType()>0){
        	criteria.andCondition("objectType=",logger.getObjectType());
        }
        
        if(null!=logger.getOperateType()&&logger.getOperateType()>0){
        	criteria.andCondition("operateType=",logger.getOperateType());
        }
        
        if(StringUtils.isNotBlank(logger.getMessage())){
        	criteria.andLike("message", "%"+logger.getMessage()+"%");
        }
        
        if(StringUtils.isNotBlank(logger.getOperateUser())){
        	criteria.andLike("operateUser", "%"+logger.getOperateUser()+"%");
        }
        
        if (null!=logger.getSuccess()&&(logger.getSuccess()==0||logger.getSuccess()==1)) {
			criteria.andCondition("success=", logger.getSuccess());
		}
        criteria.andBetween("operateTime",startTime, endTime);
		return selectByExample(example, "operateTime desc", pageIndex, pageSize);
	}

	@Override
	public List<Logger> getLoggersThree() {
		return loggerMapper.getLoggersThree();
	}

	@Override
	public int delLoggersThree() {
		return loggerMapper.delLoggersThree();
	}

}
