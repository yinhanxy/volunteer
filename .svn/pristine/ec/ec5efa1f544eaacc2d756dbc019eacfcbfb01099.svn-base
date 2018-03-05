package com.topstar.volunteer.shiro.listener;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.shiro.session.ShiroSession;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

public class LogoutListener extends SessionListenerAdapter{

	private static Logger logger = LoggerFactory.getLogger(LogoutListener.class);
	
	private LoginUserCache userCache;
	
	public void setUserCache(LoginUserCache userCache) {
		this.userCache = userCache;
	}

	//会话过期时触发  
	@Override  
    public void onExpiration(Session session) {
		String id =(String) session.getId();
		logger.debug("[sessionId={}]会话已过期",id);
		if(StringUtils.isNotBlank(id)){
			ShiroSession shiroSession = ShiroSessionMgr.getMSessionBySessionId(id);
			if(shiroSession != null){
				BaseUser user = shiroSession.getUser();
				if(user != null){
					Long userId = user.getId();
					try {
						logger.debug("开始从缓存中移除当前用户[userId={}]",userId);
						userCache.delete(userId);
						ShiroSessionMgr.remove(id,true);
						logger.debug("从缓存中移除当前的用户[userId={}]完成",userId);
					} catch (Exception e) {
						logger.error("从缓存中移除[userId={}]用户出错",userId,e);
					}
					
				}
			}
		}
	}
	
}

