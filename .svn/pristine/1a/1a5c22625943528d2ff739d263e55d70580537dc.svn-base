
package com.topstar.volunteer.log;

import java.util.Date;

import com.topstar.volunteer.dao.LoggerDao;
import com.topstar.volunteer.entity.Logger;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.shiro.session.ShiroSession;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.web.context.SpringContextHolder;
/**
 * 日志记录写入操作类
 * @author admin
 *
 */
public  class LoggerServer {
	
	static org.slf4j.Logger log=org.slf4j.LoggerFactory.getLogger(LoggerServer.class);
	
	private static LoggerDao loggerDao = (LoggerDao) SpringContextHolder.getBean("loggerDao");
	/**
	 * 记录警告信息
	 * @param objectType 对象类型
	 * @param operateType 操作类型
	 * @param message 日志信息
	 * @param success 操作是否成功
	 */
	public  static void warn(ObjectType objectType,OperateType operateType,String message,Long objectId,String objectName,boolean success){
		log(LogType.WARN, objectType, operateType, message,objectId,objectName,success);
	}
	
	/**
	 * 记录调试信息
	 * @param objectType 对象类型
	 * @param operateType 操作类型
	 * @param message 日志信息
	 * @param success 操作是否成功
	 */
	public  static void debug(ObjectType objectType,OperateType operateType,String message,Long objectId,String objectName,boolean success){
		log(LogType.DEBUG, objectType, operateType, message,objectId,objectName,success);
	}
		
	/**
	 * 记录输出信息
	 * @param objectType 对象类型
	 * @param operateType 操作类型
	 * @param message 日志信息
	 * @param success 操作是否成功
	 */
	public  static void info(ObjectType objectType,OperateType operateType,String message,Long objectId,String objectName,boolean success){
		log(LogType.INFO, objectType, operateType, message,objectId,objectName,success);
	}
	
	/**
	 * 记录错误信息
	 * @param objectType 对象类型
	 * @param operateType 操作类型
	 * @param message 日志信息
	 * @param success 操作是否成功
	 */
	public  static void error(ObjectType objectType,OperateType operateType,String message,Long objectId,String objectName){
		log(LogType.ERROR, objectType, operateType, message,objectId,objectName,false);
	}
	
	/**
	 * 记录日志信息
	 * @LogType 日志类型(警告、调试、输出、错误)
	 * @param objectType 对象类型
	 * @param operateType 操作类型
	 * @param message 日志信息
	 * @param success 操作是否成功
	 */
	public  static void log(LogType logType,ObjectType objectType,OperateType operateType,String message,Long objectId,String objectName,boolean success){
		try{
			Logger logger=new Logger();
			logger.setLoggerType(logType.getValue());
			logger.setObjectType(objectType.getValue());
			logger.setOperateType(operateType.getValue());
			logger.setOperateTime(new Date());
			logger.setObjectId(objectId);
			logger.setObjectName(objectName);
			BaseUser user=ShiroSessionMgr.getLoginUser();
			if(user!=null){
				logger.setOperateUser(user.getUserName());
				
				ShiroSession session=ShiroSessionMgr.findByUserId(user.getId());
				logger.setIp(session.getLoginIp());
			}
			logger.setSuccess(success?1:0);
			logger.setMessage(message);
			loggerDao.log(logger);
		}catch (RuntimeException e) {
			log.error("写入操作日志出错",e);
		}catch (Exception e) {
			log.error("写入操作日志出错",e);
		}
	}
	
	

}
