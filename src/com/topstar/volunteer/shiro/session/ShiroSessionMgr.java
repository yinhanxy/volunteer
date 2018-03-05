package com.topstar.volunteer.shiro.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.common.Constant;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.LoginUser;
import com.topstar.volunteer.shiro.cache.ShiroSessionCache;
import com.topstar.volunteer.web.context.ActionContext;
import com.topstar.volunteer.web.context.SpringContextHolder;

public class ShiroSessionMgr {
	
	private static Logger logger = LoggerFactory.getLogger(ShiroSessionMgr.class);
	
	private static ShiroSessionCache sessionCache = (ShiroSessionCache) SpringContextHolder.getBean("shiroSessionCache");

	private static EnterpriseCacheSessionDAO sessionDAO = (EnterpriseCacheSessionDAO)  SpringContextHolder.getBean("sessionDAO");
	
	/**
	 * 创建会话<br/>
	 * 用户登录时，将当前用户的其他登录地址强行注销，并完成登录
	 * @param baseUser
	 */
	public static void createSession(BaseUser baseUser) {
		if(baseUser == null || baseUser.getId() == null){
			return;
		}
		Map<Serializable,ShiroSession> map = sessionCache.getAll();
		if(map !=null && !map.isEmpty()){
			for(Entry<Serializable, ShiroSession> entry:map.entrySet()){
				ShiroSession shiroSession = entry.getValue();
				if(shiroSession == null){
					continue;
				}
				if (baseUser.getId().longValue() == shiroSession.getUser().getId().longValue()) {
					try {
						remove((String)entry.getKey(),true);
					} catch (Exception e) {
						logger.error("强制下线用户[userName="+shiroSession.getUser().getUserName()+"]出错",e);
					}
					break;
				}
			}
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Date lastActiveTime = new Date();
		String ip = getIpAddr(ActionContext.getActionContext().getHttpServletRequest());
		ShiroSession mSession = new ShiroSession();
		mSession.setLastActiveTime(lastActiveTime);
		mSession.setLoginIp(ip);
		mSession.setUser(baseUser);
		session.setAttribute(Constant.SESSION_KEY, mSession);
		sessionCache.add(session.getId().toString(), mSession);
	}
	
	/**
	 * 注销用户登录信息 <br/>
	 * @param sessionId 用户会话ID
	 * @param isCompulsion 是否强制注销<br/>
	 *        正常注销登录是通过subject.logout()方法进行，
	 *        未采用subject.logout()注销登录时必须设置为强制注销
	 * @throws Exception
	 */
	public static synchronized void remove(String sessionId,boolean isCompulsion) throws Exception{
		ShiroSession mSession = sessionCache.get(sessionId);
		if(mSession != null){
			sessionCache.remove(sessionId);
			try {
				if(isCompulsion){
					Session session =  sessionDAO.readSession(sessionId);
					if(session != null){
						sessionDAO.delete(session);
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * 注销用户登录信息 <br/>
	 * @param userId 用户ID
	 * @param isCompulsion 是否强制注销<br/>
	 *        正常注销登录是通过subject.logout()方法进行，
	 *        未采用subject.logout()注销登录时必须设置为强制注销
	 * @throws Exception
	 */
	public static void kickUserByUserId(Long userId,boolean isCompulsion) {
		if (userId == null || userId.longValue() < 1){
			return;
		}
		if(sessionCache.isEmpty()){
			return;
		}
		Map<Serializable,ShiroSession> sSessions = sessionCache.getAll();
		if(sSessions != null && !sSessions.isEmpty()){
			for(Entry<Serializable, ShiroSession> entry:sSessions.entrySet()){
				ShiroSession msession = entry.getValue();
				if(msession != null){
					BaseUser user = msession.getUser();
					if(user != null && user.getId().longValue() == userId.longValue()){
						try {
							remove((String)entry.getKey(), isCompulsion);
						} catch (Exception e) {
							logger.error("强制下线用户[userName="+user.getUserName()+"]出错",e);
						}
					}
				}
			}
		}
	}

	/**
	 * 通过用户编号获取会话信息
	 * @param userId 用户编号
	 * @return
	 */
	public static ShiroSession findByUserId(Long userId) {
		ShiroSession msession = null;
		if(sessionCache.isEmpty()){
			return msession;
		}
		Map<Serializable,ShiroSession> sSessions = sessionCache.getAll();
		if(sSessions != null && !sSessions.isEmpty()){
			for(Entry<Serializable, ShiroSession> entry:sSessions.entrySet()){
				ShiroSession mSession = entry.getValue();
				if (mSession != null){
					if ( userId.longValue() == mSession.getUser().getId().longValue()) {
						msession = mSession;
						break;
					}
				}
			}
		}
		return msession;
	}

	/**
	 * 通过会话ID获取会话信息
	 * @param sessionId 会话ID
	 * @return
	 */
	public static ShiroSession getMSessionBySessionId(String sessionId) {
		return (ShiroSession)sessionCache.get(sessionId);
	}

	/**
	 * 得到当前系统所有登录用户信息
	 * @return
	 */
	public static List<BaseUser> getOnlines() {
		List<BaseUser> onlines = new ArrayList<BaseUser>();
		Map<Serializable,ShiroSession> sSessions = sessionCache.getAll();
		if(sSessions != null && !sSessions.isEmpty()){
			List<ShiroSession> list = new ArrayList<ShiroSession>(sSessions.values());
			for (ShiroSession session : list) {
				onlines.add(session.getUser());
			}
		}
		return onlines;
	}
	  
	/**
	 * 得到当前系统所有登录用户信息,并包含登录IP和最后登录时间
	 * @return
	 */
	public static List<LoginUser> getLoinUsers(){
		List<LoginUser> loginUsers = new ArrayList<LoginUser>();
	    Map<Serializable,ShiroSession> shiroSessionMap = sessionCache.getAll();
	    if(shiroSessionMap != null && !shiroSessionMap.isEmpty()){
	    	List<ShiroSession> list = new ArrayList<ShiroSession>(shiroSessionMap.values());
	    	for (ShiroSession session : list) {
	    		BaseUser baseUser =  session.getUser();
	    		if(baseUser != null){
	    			LoginUser user = new LoginUser(baseUser);
	    			user.setLoginIp(session.getLoginIp());
	    			user.setLastActiveTime(session.getLastActiveTime());
	    			loginUsers.add(user);
	    		}
	    	}
	    }
	    return loginUsers;
	}

	/**
	 * 得到当前登录用户
	 * @return 
	 */
	public static BaseUser getLoginUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		if(session != null){
			ShiroSession mSession = getMSessionBySessionId(session.getId().toString());
		    if (mSession == null){
		    	return null;
		    }
		    return mSession.getUser();
		}
		return null;
	}
	
	
	
	private static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
}
